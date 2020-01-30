package com.tesis.routes.Admin;

import com.google.inject.Inject;
import com.tesis.controllers.AdminUserController;
import com.tesis.controllers.VehicleController;
import com.tesis.routes.Middlewares;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;


public class AdminRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(AdminRouter.class);

    private AdminUserController adminUserController;
    private Middlewares middlewares;

    @Inject
    public AdminRouter(AdminUserController adminUserController, Middlewares middlewares) {
        this.adminUserController = adminUserController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading admin routes...");
        Spark.path("/admins", () -> {
//                Spark.before("", middlewares.adminAccessTokenFilter);
                Spark.before("/:admin_user_id", middlewares.adminAccessTokenFilter);

                Spark.post("", adminUserController::createAdminUser);
                Spark.get("/:admin_user_id", adminUserController::getAdminUsersByAdminUserID);
                Spark.put("/:admin_user_id", adminUserController::updateAdminUser);
                Spark.delete("/:admin_user_id", adminUserController::deleteAdminUser);
        });
    }
}
