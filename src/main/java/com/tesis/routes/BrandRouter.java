package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.controllers.BrandController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;

public class BrandRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    BrandController brandController;

    @Override
    public void addRoutes() {

        logger.info("Loading brand routes...");
        Spark.path("/brands", () -> {
            Spark.post("", brandController::createBrand);
            Spark.get("", brandController::getBrands);
            Spark.get("/brandlines", brandController::getBrandLines);
            Spark.get("/:brand_id", brandController::getBrandByBrandID);
            Spark.put("/:brand_id", brandController::updateBrand);
            Spark.delete("/:brand_id", brandController::deleteBrand);

            Spark.post("/:brand_id/brandlines", brandController::createBrandLine);
            Spark.get("/:brand_id/brandlines", brandController::getBrandLineByBrandID);
            Spark.get("/:brand_id/brandlines/:brand_line_id", brandController::getBrandLineByBrandLineID);
            Spark.put("/:brand_id/brandlines/:brand_line_id", brandController::updateBrandLine);
            Spark.delete("/:brand_id/brandlines/:brand_line_id", brandController::deleteBrandLine);
        });
    }
}
