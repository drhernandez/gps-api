package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class UsersRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    UserController userController;

    @Override
    public void addRoutes() {

        logger.info("Loading users routes...");
        Spark.path("/users", () -> {
            Spark.post("", userController::createUser);
            Spark.get("", userController::getUsers);
            Spark.get("/:user_id", userController::getUsersByUserID);
            Spark.put("/:user_id", userController::updateUser);
            Spark.delete("/:user_id", userController::deleteUser);
        });
    }
}
