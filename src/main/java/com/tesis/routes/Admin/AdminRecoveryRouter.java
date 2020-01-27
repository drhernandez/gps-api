package com.tesis.routes.Admin;

import com.google.inject.Inject;
import com.tesis.controllers.AdminRecoveryController;
import com.tesis.routes.DevicesRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class AdminRecoveryRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    private AdminRecoveryController adminrecoveryController;

    @Inject
    public AdminRecoveryRouter(AdminRecoveryController adminrecoveryController) {
        this.adminrecoveryController = adminrecoveryController;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading admin recovery routes...");
        Spark.path("/admins/recovery", () -> {
            Spark.post("", adminrecoveryController::createRecoveryAdminPasswordToken);
            Spark.get("/:recovery_token/validate", adminrecoveryController::validateRecoveryAdminPasswordToken);
            Spark.put("/:recovery_token", adminrecoveryController::updateAdminPasswordFromRecovery);
        });
    }
}
