package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.AlertController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class AlertRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(AlertRouter.class);

    @Inject
    AlertController alertController;

    @Override
    public void addRoutes() {

        logger.info("Loading devices routes...");
        Spark.path("/alerts", () -> {
            Spark.post("/speeds", alertController::createSpeedAlert);
            Spark.get("/speeds", alertController::getSpeedAlerts);
            Spark.get("/speeds/:device_id", alertController::getSpeedAlertByDeviceID);
            Spark.put("/speeds/:speed_alert_id", alertController::updateSpeedAlert);
            Spark.delete("/speeds/:speed_alert_id", alertController::deleteSpeedAlert);
        });
    }
}
