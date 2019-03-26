package com.tesis.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Trakings;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Tracking;
import com.tesis.services.TrackingService;
import com.tesis.utils.JsonUtils;
import com.tesis.utils.ValidationUtils;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackingController {

    @Inject
    TrackingService trackingService;

    public Object saveTracking(Request request, Response response) throws ApiException {

        String body = request.body();
        if (ValidationUtils.isNullOrEmpty(body)) {
            throw new ApiException("invalid_data", "[method: saveTracking] [reason: invalid_body]");
        }

        List<Tracking> trackings = new ArrayList<>();
        Arrays.asList(body.split(";")).forEach(t -> {
            String[] args = t.split(",");
            Tracking traking = new Tracking(args);
            trackings.add(traking);
        });

        ResponseDTO responseDTO = trackingService.saveTracking(trackings);
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
