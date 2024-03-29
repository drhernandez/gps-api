package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.BrandController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class BrandRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    private BrandController brandController;
    private Middlewares middlewares;

    @Inject
    public BrandRouter(BrandController brandController, Middlewares middlewares) {
        this.brandController = brandController;
        this.middlewares = middlewares;
    }

    @Override
    public void addRoutes() {

        logger.info("Loading brand routes...");
        Spark.path("/brands", () -> {
            Spark.before("", middlewares.accessTokenFilter);
            Spark.before("*", middlewares.accessTokenFilter);

            Spark.get("", brandController::getBrands);
            Spark.get("/brandlines", brandController::getBrandLines);
            Spark.get("/:brand_id", brandController::getBrandByBrandID);

            Spark.get("/:brand_id/brandlines", brandController::getBrandLineByBrandID);
            Spark.get("/:brand_id/brandlines/:brand_line_id", brandController::getBrandLineByBrandLineID);
        });
    }
}
