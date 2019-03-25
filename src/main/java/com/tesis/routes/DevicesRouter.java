package com.tesis.routes;

import com.google.inject.Inject;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DevicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.RouteGroup;
import spark.Spark;

import java.util.List;

public class DevicesRouter implements RouteGroup {

    private static Logger logger = LoggerFactory.getLogger(DevicesRouter.class);

    @Inject
    DevicesService devicesService;

    @Override
    public void addRoutes() {

        logger.info("Loading devices routes...");
        Spark.path("/devices", () -> {
            Spark.get("", (Request request, Response response) -> {
                ResponseDTO<List<Devices>> responseDTO = devicesService.getDevices();
                if (responseDTO.error != null) {
                    throw responseDTO.error;
                }

                return responseDTO.getModelAsJson();
            });
        });
    }
}
