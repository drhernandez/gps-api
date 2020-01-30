package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class UsersRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    private UserController userController;
    private Middlewares middlewares;

    @Inject
    public UsersRouter(UserController userController, Middlewares mideMiddlewares) {
        this.userController = userController;
        this.middlewares = mideMiddlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading users routes...");
        Spark.path("/users", () -> {
//            Spark.before("/*", middlewares.requiredTokenCheck);
            Spark.post("", userController::createUser);
            Spark.get("", userController::getUsers);
            Spark.get("/:user_id", userController::getUsersByUserID);
            Spark.put("/:user_id", userController::updateUser);
            Spark.delete("/:user_id", userController::deleteUser);

            Spark.put("/:user_id/activate", userController::activateUser);
            Spark.put("/:user_id/deactivate", userController::deactivateUser);

        });
    }
}
