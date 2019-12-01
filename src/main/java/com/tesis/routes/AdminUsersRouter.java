package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.AdminUserController;
import com.tesis.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class AdminUsersRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    AdminUserController adminUserController;

    @Override
    public void addRoutes() {

        logger.info("Loading admin users routes...");
        Spark.path("/admins", () -> {
            Spark.post("", adminUserController::createAdminUser);
            Spark.get("", adminUserController::getAdminUsers);
            Spark.get("/search", adminUserController::adminUserSearch);
            Spark.get("/:user_id", adminUserController::getAdminUsersByAdminUserID);
            Spark.put("/:user_id", adminUserController::updateAdminUser);
            Spark.delete("/:user_id", adminUserController::deleteAdminUser);

        });
    }
}
