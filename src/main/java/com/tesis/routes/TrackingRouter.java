package com.tesis.routes;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.tesis.controllers.AlertController;
import com.tesis.controllers.TrackingController;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.services.AlertService;
import com.tesis.services.AuthService;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

import java.util.List;

import static com.tesis.config.Constants.DEFAULT_MOVEMENT_ALERT_KM;
import static com.tesis.config.Constants.EARTH_RADIUS_KM;
import static spark.Spark.halt;

public class TrackingRouter implements RouteGroup {

    Logger logger = LoggerFactory.getLogger(TrackingRouter.class);

    private TrackingController trackingController;

    @Inject
    public TrackingRouter(TrackingController trackingController) {
        this.trackingController = trackingController;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading Tracking routes...");
        Spark.path("/trackings", () -> {
//            Spark.before("/*", middlewares.accessTokenFilter);

            Spark.post("", trackingController::saveTracking);
            Spark.get("/search", trackingController::trackingSearch);
            Spark.get("/:device_id", trackingController::getTrackingsByDeviceID);
        });
    }
}
