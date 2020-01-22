package com.tesis.routes.Admin;

import com.google.inject.Inject;
import com.tesis.controllers.AdminUserController;
import com.tesis.controllers.VehicleController;
import com.tesis.routes.Middlewares;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;


public class AdminUsersRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(AdminUsersRouter.class);

    private AdminUserController adminUserController;
    private VehicleController vehicleController;
    private Middlewares middlewares;

    @Inject
    public AdminUsersRouter(AdminUserController adminUserController, VehicleController vehicleController, Middlewares middlewares) {
        this.adminUserController = adminUserController;
        this.vehicleController = vehicleController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading admin users routes...");

        Spark.path("/admins/users", () -> {
            Spark.before("/*", middlewares.adminAccessTokenFilter);

            Spark.post("", adminUserController::createAdminUser);
            Spark.get("/:admin_user_id", adminUserController::getAdminUsersByAdminUserID);
            Spark.put("/:admin_user_id", adminUserController::updateAdminUser);
            Spark.delete("/:admin_user_id", adminUserController::deleteAdminUser);
        });

        Spark.path("/admins/vehicles", () -> {
            Spark.before("/*", middlewares.adminAccessTokenFilter);
            Spark.post("", vehicleController::createVehicle);
            Spark.get("", vehicleController::getVehicles);
            Spark.put("/:vehicle_id", vehicleController::updateVehicle);

        });
    }
}
