package com.tesis.routes;

import com.google.common.net.MediaType;
import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.exceptions.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import javax.servlet.http.HttpServletResponse;

import static com.tesis.enums.ErrorCodes.internal_error;
import static com.tesis.enums.ErrorCodes.route_not_found;

public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    @Inject
    GpsRouter gpsRouter;

    public void init() {

        // Validar el device_id
//        Spark.before();

        Spark.notFound((request, response) -> {
            response.header("Content-Type", MediaType.JSON_UTF_8.toString());

            response.status(HttpServletResponse.SC_NOT_FOUND);

            ApiException apiException = new ApiException(route_not_found.name(), String.format("Route %s not found", request.uri()), HttpServletResponse.SC_NOT_FOUND);
            logger.error(apiException.getMessage(), apiException);
            return apiException.toJson();
        });

        Spark.exception(Exception.class, (e, request, response) -> {
            Throwable t = ExceptionUtils.getFromChain(e, ApiException.class);

            ApiException apiException = t instanceof ApiException ? (ApiException) t : new ApiException(internal_error.name(), "Internal server error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, t);
            logger.error(apiException.getMessage(), e);
            response.status(apiException.getStatus());
            response.body(apiException.toJson());
        });

        Spark.get("/ping", (request, response) -> {

            response.status(HttpServletResponse.SC_OK);
            response.header("Content-Type", MediaType.PLAIN_TEXT_UTF_8.toString());

            return "pong";
        });

        Spark.after(((request, response) -> {
            if (!response.raw().containsHeader("Content-Type")) {
                response.header("Content-Type", MediaType.JSON_UTF_8.toString());
            }
        }));

        gpsRouter.addRoutes();
    }
}
