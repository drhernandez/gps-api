package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.TrackingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class TrackingRouter implements RouteGroup {

    Logger logger = LoggerFactory.getLogger(TrackingRouter.class);

    private TrackingController trackingController;
    private Middlewares middlewares;

    @Inject
    public TrackingRouter(TrackingController trackingController, Middlewares middlewares) {
        this.trackingController = trackingController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading Tracking routes...");
        Spark.path("/trackings", () -> {
            Spark.before("/*", middlewares.accessTokenFilter);

            Spark.post("", trackingController::saveTracking);
            Spark.get("/search", trackingController::trackingSearch);
            Spark.get("/:device_id", trackingController::getTrackingsByDeviceID);
        });
    }
}
