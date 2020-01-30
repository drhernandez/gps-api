package com.tesis.routes.Admin;

import com.google.inject.Inject;
import com.tesis.controllers.AdminUserController;
import com.tesis.controllers.UserController;
import com.tesis.controllers.VehicleController;
import com.tesis.routes.Middlewares;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;


public class AdminUsersRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(AdminUsersRouter.class);

    private VehicleController vehicleController;
    private UserController userController;
    private Middlewares middlewares;

    @Inject
    public AdminUsersRouter(VehicleController vehicleController, UserController userController, Middlewares middlewares) {
        this.vehicleController = vehicleController;
        this.userController = userController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading admin users routes...");

        Spark.path("/admins/users", () -> {
//            Spark.before("/*", middlewares.adminAccessTokenFilter);

            Spark.post("", userController::createUser);
            Spark.get("/search", userController::userSearch);
            Spark.get("/:user_id", userController::getUsersByUserID);
            Spark.get("/:user_id/vehicles", userController::getVehiclesByUserID);
            Spark.put("/:user_id", userController::updateUser);
            Spark.delete("/:user_id", userController::deleteUser);
        });

        Spark.path("/admins/vehicles", () -> {
//            Spark.before("/*", middlewares.adminAccessTokenFilter);

            Spark.post("", vehicleController::createVehicle);
            Spark.get("", vehicleController::getVehicles);
            Spark.put("/:vehicle_id", vehicleController::updateVehicle);

        });
    }
}
