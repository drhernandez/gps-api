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
    private AlertController alertController;
    private AlertService alertService;
    private AuthService authService;
    private Middlewares middlewares;

    @Inject
    public TrackingRouter(TrackingController trackingController,
                          AlertController alertController,
                          AlertService alertService,
                          AuthService authService,
                          Middlewares middlewares) {
        this.trackingController = trackingController;
        this.alertController = alertController;
        this.alertService = alertService;
        this.authService = authService;
        this.middlewares = middlewares;
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

//        Spark.after("/trackings",((request, response) -> {
//            if (request.requestMethod().equals("POST") && response.status() == 200) {
//                List<Trackings> trackingsList = JsonUtils.INSTANCE.GSON().fromJson(response.body(), new TypeToken<List<Trackings>>(){}.getType());
//                SpeedAlerts speedAlert = alertService.getSpeedIfActive(trackingsList.get(0).getDeviceId());
//                if(speedAlert != null) {
//                    for(int i=0; i<trackingsList.size(); i++){
//                        if(trackingsList.get(i).getSpeed() > speedAlert.getSpeed() &&
//                                trackingsList.get(i).getTime().after(speedAlert.getActivatedAt())){
//                            alertService.createSpeedAlertHistory(new SpeedAlertsHistory(
//                                    trackingsList.get(i).getTime(),
//                                    speedAlert.getId(),
//                                    trackingsList.get(i).getSpeed()));
//                            try {
//                                alertController.sendAlarm(trackingsList.get(i).getDeviceId(),"SPEED");
//                            } catch (Exception e) {
//                                halt(500, e.getMessage());
//                            }
//                            break;
//                        }
//                    }
//                }
//
//                MovementAlerts movementAlert = alertService.getMovementIfActive(trackingsList.get(0).getDeviceId());
//                if(movementAlert != null) {
//
//                    for(int i=0; i<trackingsList.size(); i++) {
//                        if(trackingsList.get(i).getTime().after(movementAlert.getActivatedAt()) &&
//                                checkDistance(trackingsList.get(i), movementAlert)){
//                            alertService.createMovementAlertHistory(new MovementAlertsHistory(
//                                    trackingsList.get(i).getTime(),
//                                    movementAlert.getId(),
//                                    trackingsList.get(i).getLat(),
//                                    trackingsList.get(i).getLng()
//                            ));
//                            try {
//                                alertController.sendAlarm(trackingsList.get(i).getDeviceId(),"MOVEMENT");
//                            } catch (Exception e) {
//                                halt(500, e.getMessage());
//                            }
//                            break;
//                        }
//                    }
//
//                }
//            }
//        }));
    }

    public boolean checkDistance(Trackings tracking, MovementAlerts alert){

        double lat1 = Math.toRadians(tracking.getLat());
        double lng1 = Math.toRadians(tracking.getLng());
        double lat2 = Math.toRadians(alert.getLat());
        double lng2 = Math.toRadians(alert.getLng());

        // Haversine equation
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lng2 - lng1) / 2),2);

        return (2 * Math.asin(Math.sqrt(a)) * EARTH_RADIUS_KM) > DEFAULT_MOVEMENT_ALERT_KM;

    }
}


//normal alert
//10001,-31.4109,-64.1897,1.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,1.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,1.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,1.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,1.0,8,246,25-09-2019T20:51:09:000-03:00


//movement alert
//10001,-31.4109,-64.1897,10.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,10.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,10.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,10.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,10.0,8,246,25-09-2019T20:51:09:000-03:00


//speed alert
//10001,-31.4109,-64.1897,100.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,100.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,100.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,100.0,8,246,25-09-2019T20:51:09:000-03:00;10001,-31.4109,-64.1897,100.0,8,246,25-09-2019T20:51:09:000-03:00