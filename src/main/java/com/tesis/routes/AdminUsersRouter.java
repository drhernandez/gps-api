package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.AdminUserController;
import com.tesis.services.AuthAdminService;
import com.tesis.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

import static spark.Spark.halt;

public class AdminUsersRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    AdminUserController adminUserController;

    @Inject
    AuthAdminService authAdminService;

    @Override
    public void addRoutes() {

        logger.info("Loading admin users routes...");
        Spark.path("/admins/users", () -> {
            Spark.post("", adminUserController::createAdminUser);
//            Spark.get("", adminUserController::getAdminUsers);
//            Spark.get("/search", adminUserController::adminUserSearch);
            Spark.get("/:admin_user_id", adminUserController::getAdminUsersByAdminUserID);
            Spark.put("/:admin_user_id", adminUserController::updateAdminUser);
            Spark.delete("/:admin_user_id", adminUserController::deleteAdminUser);

        });

        // Validación de accessToken
        Spark.before("/admins/*",((request, response) -> {
            String accessToken = request.headers("Authorization");
            if (accessToken != null) {
                accessToken = accessToken.split(" ")[1];
                try {
                    authAdminService.checkAdminAccessToken(accessToken);
                } catch (Exception e) {
                    logger.info("Admin authorization fail, Reason: " + e.getMessage());
                    halt(401, "Unauthorized");
                }
            } else
                halt(400, "Auth info is required");
        }));


    }
}
