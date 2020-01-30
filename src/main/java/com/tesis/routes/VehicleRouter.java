package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.VehicleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class VehicleRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    private VehicleController vehicleController;
    private Middlewares middlewares;

    @Inject
    public VehicleRouter(VehicleController vehicleController, Middlewares middlewares) {
        this.vehicleController = vehicleController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading Vehicles routes...");
        Spark.path("/vehicles", () -> {
//            Spark.before("/*", middlewares.requiredTokenCheck);

            Spark.post("", vehicleController::createVehicle);
            Spark.get("", vehicleController::getVehicles);
            Spark.get("/:vehicle_id", vehicleController::getVehiclesByVehicleID);
            Spark.put("/:vehicle_id", vehicleController::updateVehicle);
            Spark.delete("/:vehicle_id", vehicleController::deleteVehicle);

            Spark.get("/:vehicle_id/trackings", vehicleController::getTrackingsByVehicleID);
            Spark.get("/:vehicle_id/location", vehicleController::getLocationByVehicleID);

            Spark.get("/:vehicle_id/alerts/speed", vehicleController::getSpeedAlertByVehicleID);
            Spark.get("/:vehicle_id/alerts/speed/history", vehicleController::getSpeedHistoryByVehicleID);
            Spark.get("/:vehicle_id/alerts/movement", vehicleController::getMovementAlertByVehicleID);
            Spark.get("/:vehicle_id/alerts/movement/history", vehicleController::getMovementHistoryByVehicleID);

        });
    }
}
