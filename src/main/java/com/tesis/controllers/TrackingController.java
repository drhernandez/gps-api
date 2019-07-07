package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.exceptions.ParseArgsException;
import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.TrackingService;
import com.tesis.utils.filters.TrackingFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tesis.config.Constants.MIN_LENGTH;

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
        Arrays.asList(body.split(";")).forEach(t -> {
            String[] args = t.split(",");
            if (args.length >= MIN_LENGTH) {
                try {
                    Trackings traking = new Trackings(args);
                    trackings.add(traking);
                } catch (ParseArgsException e) {
                    logger.error(e.getMessage());
                }
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

    public Object trackingSearch(Request request, Response response) throws ApiException {
        Long deviceId;
        Float speed;
        Integer sat, hdop;
        Timestamp timeStart, timeEnd;
        TrackingFilters filters = new TrackingFilters();
        Pagination pagination = new Pagination();


        try {
            deviceId = request.queryParams("device_id") != null ? Long.valueOf(request.queryParams("device_id")) : null;
            filters.setDeviceId(deviceId);
            speed = request.queryParams("speed") != null ? Float.valueOf(request.queryParams("speed")) : null;
            filters.setSpeed(speed);
            sat = request.queryParams("sat") != null ? Integer.valueOf(request.queryParams("sat")) : null;
            filters.setSat(sat);
            hdop = request.queryParams("hdop") != null ? Integer.valueOf(request.queryParams("hdop")) : null;
            filters.setHdop(hdop);
            timeStart = request.queryParams("time_start") != null ? Timestamp.valueOf(request.queryParams("time_start")) : null;
            filters.setTimeStart(timeStart);
            timeEnd = request.queryParams("time_end") != null ? Timestamp.valueOf(request.queryParams("time_end")) : null;
            filters.setTimeEnd(timeEnd);

            pagination.setPage(request.queryParams("page") != null ? Integer.valueOf(request.queryParams("page")) : null);
            pagination.setLimit(request.queryParams("limit") != null ? Integer.valueOf(request.queryParams("limit")) : null);

        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_params] [method: TrackingController.trackingSearch]");
        }

        ResponseDTO<Search> responseDTO = trackingService.trackingSearch(filters, pagination);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
