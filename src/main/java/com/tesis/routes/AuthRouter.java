package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class AuthRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    AuthController authController;

    @Override
    public void addRoutes() {

        logger.info("Loading auth routes...");
        Spark.path("/auth", () -> {
            Spark.post("/login", authController::login);
            Spark.post("/adminlogin", authController::adminLogin);
            Spark.get("/validate", authController::checkAccess);
        });
    }
}
