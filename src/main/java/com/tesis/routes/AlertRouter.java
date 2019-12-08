package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.AlertController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class AlertRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(AlertRouter.class);

    private AlertController alertController;
    private Middlewares middlewares;

    @Inject
    public AlertRouter(AlertController alertController, Middlewares middlewares) {
        this.alertController = alertController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading alert routes...");
        Spark.path("/alerts", () -> {
            Spark.before("/*", middlewares.accessTokenFilter);

            Spark.post("/speeds", alertController::createSpeedAlert);
            Spark.get("/speeds", alertController::getSpeedAlerts);
            Spark.put("/speeds/:speed_alert_id", alertController::updateSpeedAlert);
            Spark.delete("/speeds/:speed_alert_id", alertController::deleteSpeedAlert);

            Spark.post("/movements", alertController::createMovementAlert);
            Spark.get("/movements", alertController::getMovementAlerts);
            Spark.put("/movements/:movement_alert_id", alertController::updateMovementAlert);
            Spark.delete("/movements/:device_id", alertController::deleteMovementAlert);

            Spark.post("/speeds/history", alertController::createSpeedHistory);
            Spark.delete("/speeds/:device_id/history", alertController::deleteSpeedHistory);

            Spark.post("/movement/history", alertController::createMovementHistory);
            Spark.delete("/movement/:device_id/history", alertController::deleteMovementHistory);

            Spark.post("/send", alertController::sendSMS);
        });
    }
}
