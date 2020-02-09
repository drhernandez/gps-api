package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.services.imp.AuthServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class Middlewares {

    private static final Logger logger = LoggerFactory.getLogger(Middlewares.class);

    private AuthServiceImp authService;

    @Inject
    public Middlewares(AuthServiceImp authService) {
        this.authService = authService;
    }

    public Filter accessTokenFilter = (Request request, Response response) -> {
        String accessToken = request.headers("Authorization");
        if (accessToken != null) {
            accessToken = accessToken.split(" ")[1];
            try {
                logger.info("Access to " + request.requestMethod() + " " + request.uri());
                authService.validateToken(accessToken, request.uri());
            } catch (Exception e){
                logger.info("Authorization fail. Reason: " + e.getMessage());
                halt(401, "Unauthorized");
            }
        }
        else
            halt(400, "Auth info is required");
    };

}
