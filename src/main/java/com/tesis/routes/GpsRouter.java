package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.GpsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class GpsRouter {

    private static Logger logger = LoggerFactory.getLogger(GpsRouter.class);

    @Inject
    GpsController gpsController;

    protected void addRoutes() {

        logger.info("Loading locations routes...");
        Spark.path("/locations", () -> {
            Spark.post("", gpsController::addLocation);
            Spark.get("/:device_id", gpsController::getLocations);
        });
    }
}
