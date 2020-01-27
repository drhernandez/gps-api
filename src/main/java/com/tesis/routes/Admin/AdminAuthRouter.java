package com.tesis.routes.Admin;

import com.google.inject.Inject;
import com.tesis.controllers.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;


public class AdminAuthRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(AdminAuthRouter.class);

    private AuthController authController;


    @Inject
    public AdminAuthRouter(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading admin auth routes...");
        Spark.path("/admins/auth", () -> {
            Spark.post("/login", authController::adminLogin);
            Spark.get("/validate", authController::checkAdminAccess);
        });
    }
}
