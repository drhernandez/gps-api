package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.DeviceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class DevicesRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    private DeviceController deviceController;
    private Middlewares middlewares;

    @Inject
    public DevicesRouter(DeviceController deviceController, Middlewares middlewares) {
        this.deviceController = deviceController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading devices routes...");
        Spark.path("/devices", () -> {
//            Spark.before("*", middlewares.adminAccessTokenFilter);
            Spark.post("", deviceController::createDevice);
            Spark.get("", deviceController::getDevices);
            Spark.get("/:device_id", deviceController::getDeciveByDeviceID);
            Spark.put("/:device_id", deviceController::updateDevice);
            Spark.delete("/:device_id", deviceController::deleteDevice);
        });
    }
}
