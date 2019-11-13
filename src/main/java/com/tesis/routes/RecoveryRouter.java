package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.RecoveryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class RecoveryRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    RecoveryController recoveryController;

    @Override
    public void addRoutes() {

        logger.info("Loading recovery routes...");
        Spark.path("/recovery", () -> {
            Spark.post("", recoveryController::createRecoveryPasswordToken);
            Spark.get("/:recovery_token/validate", recoveryController::validateRecoveryPasswordToken);
            Spark.put("/:recovery_token", recoveryController::updatePasswordFromRecovery);
        });
    }
}
