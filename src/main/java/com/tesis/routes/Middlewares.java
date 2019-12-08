package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.services.AuthAdminService;
import com.tesis.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class Middlewares {

    private static final Logger logger = LoggerFactory.getLogger(Middlewares.class);

    private AuthService authService;
    private AuthAdminService authAdminService;

    @Inject
    public Middlewares(AuthService authService, AuthAdminService authAdminService) {
        this.authService = authService;
        this.authAdminService = authAdminService;
    }

    public Filter accessTokenFilter = (Request request, Response response) -> {

        String accessToken = request.headers("Authorization");
        if (accessToken != null) {
            accessToken = accessToken.split(" ")[1];
            try {
                authService.validateAccessToken(accessToken);
            } catch (Exception e){
                logger.info("Authorization fail, Reason: " + e.getMessage());
                halt(401, "Unauthorized");
            }
        }
        else
            halt(400, "Auth info is required");
    };

    public Filter adminAccessTokenFilter = (Request request, Response response) -> {

        String accessToken = request.headers("Authorization");
        if (accessToken != null) {
            accessToken = accessToken.split(" ")[1];
            try {
                authAdminService.checkAdminAccessToken(accessToken);
            } catch (Exception e){
                logger.info("Authorization fail, Reason: " + e.getMessage());
                halt(401, "Unauthorized");
            }
        }
        else
            halt(400, "Auth info is required");
    };

    public Filter requiredTokenCheck = (Request request, Response response) -> {
        String accessToken = request.headers("Authorization");
        if (accessToken != null) {
            accessToken = accessToken.split(" ")[1];
            try {
                authService.checkTokenRequired(accessToken);
            } catch (Exception e){
                logger.info("Authorization fail, Reason: " + e.getMessage());
                halt(401, "Unauthorized");
            }
        }
        else
            halt(400, "Auth info is required");
    };

}
