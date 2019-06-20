package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.SpeedAlerts;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AlertService;
import com.tesis.utils.JsonUtils;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class AlertController {

    @Inject
    AlertService alertService;

    public Object createSpeedAlert(Request request, Response response) throws ApiException {
        SpeedAlerts device = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlerts.class);
        ResponseDTO<SpeedAlerts> responseDTO = alertService.createSpeedAlert(device);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedAlerts(Request request, Response response) throws ApiException {

        ResponseDTO<List<SpeedAlerts>> responseDTO = alertService.getSpeedAlerts();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedAlertBySpeedAlertID(Request request, Response response) throws ApiException{
        String param = request.params("speed_alert_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }
        ResponseDTO<SpeedAlerts> responseDTO = alertService.getDeciveBySpeedAlertID(deviceID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateSpeedAlert(Request request, Response response) throws ApiException{
        String param = request.params("speed_alert_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_speed_alert_id] [method: AlertController.getSpeedAlertBySpeedAlertID]");
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_speed_alert_id] [method: AlertController.getSpeedAlertBySpeedAlertID]");
        }

        SpeedAlerts Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlerts.class);
        //Add validations

        ResponseDTO<SpeedAlerts> responseDTO = alertService.updateSpeedAlert(deviceID, Vehicle);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteSpeedAlert(Request request, Response response) throws ApiException{
        String param = request.params("speed_alert_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_speed_alert_id] [method: AlertController.deleteSpeedAlert]");
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_speed_alert_id] [method: AlertController.deleteSpeedAlert]");
        }
        ResponseDTO responseDTO = alertService.deleteSpeedAlert(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
