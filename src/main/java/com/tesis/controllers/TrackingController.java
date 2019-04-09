package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.exceptions.ParseArgsException;
import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.models.ResponseDTO;
import com.tesis.services.TrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackingController {

    private static Logger logger = LoggerFactory.getLogger(TrackingController.class);

    @Inject
    TrackingService trackingService;

    public Object saveTracking(Request request, Response response) throws ApiException {

        String body = request.body();
        if (StringUtils.isBlank(body)) {
            throw new ApiException("invalid_data", "[reason: invalid_body] [method: TrackingController.saveTracking]");
        }

        List<Trackings> trackings = new ArrayList<>();
        logger.info(String.format("BODY: %s", body));
        Arrays.asList(body.split(";")).forEach(t -> {
            String[] args = t.split(",");
            Trackings traking = null;
            try {
                traking = new Trackings(args);
                trackings.add(traking);
            } catch (ParseArgsException e) {
                logger.error(e.getMessage(), e);
            }
        });

        ResponseDTO responseDTO = trackingService.saveTracking(trackings);
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getTrackingsByDeviceID(Request request, Response response) throws ApiException {

        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: TrackingController.getTrackingsByDeviceID]");
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: TrackingController.getTrackingsByDeviceID]");
        }

        ResponseDTO<List<Trackings>> responseDTO = trackingService.getTrackingsByDeviceID(deviceID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
