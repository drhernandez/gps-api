package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.services.imp.AuthServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import static spark.Spark.halt;

public class Middlewares {

    private static final Logger logger = LoggerFactory.getLogger(Middlewares.class);

    private AuthServiceImp authService;

    @Inject
    public Middlewares(AuthServiceImp authService) {
        this.authService = authService;
    }

    public Filter accessTokenFilter = (Request request, Response response) -> {
        String accessToken = request.headers("x-access-token");
        if (accessToken != null) {
            try {
                logger.info("Access to " + request.requestMethod() + " " + request.uri());
                authService.validateToken(accessToken, request.requestMethod(), request.uri());
            } catch (ApiException e){
                logger.info("Authorization fail. Reason: " + e.getMessage());
                if (e.getStatus() == HttpServletResponse.SC_FORBIDDEN)
                    halt(403, "Forbidden");
                if (e.getStatus() == HttpServletResponse.SC_UNAUTHORIZED)
                    halt(401, "Unauthorized");
                if (e.getStatus() == HttpServletResponse.SC_SERVICE_UNAVAILABLE)
                    halt(503, "Service Unavailable");
                else
                    halt(500, "Internal Service Error");
            }
        }
        else
            halt(400, "Auth info is required");
    };

}
