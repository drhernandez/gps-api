package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.Till;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.MovementAlerts;
import com.tesis.jooq.tables.pojos.MovementAlertsHistory;
import com.tesis.jooq.tables.pojos.SpeedAlerts;
import com.tesis.jooq.tables.pojos.SpeedAlertsHistory;
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

    //  ----------------  Speed Alert methods ----------------

    public Object createSpeedAlert(Request request, Response response) throws ApiException {
        SpeedAlerts speedAlert = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlerts.class);
        ResponseDTO<SpeedAlerts> responseDTO = alertService.createSpeedAlert(speedAlert);

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

    public Object updateSpeedAlert(Request request, Response response) throws ApiException{
        String param = request.params("speed_alert_id");
        Long speedAlertID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_speed_alert_id] [method: AlertController.updateSpeedAlert]");
        }

        try {
            speedAlertID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_speed_alert_id] [method: AlertController.updateSpeedAlert]");
        }

        SpeedAlerts speedAlerts = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlerts.class);
        //Add validations

        ResponseDTO<SpeedAlerts> responseDTO = alertService.updateSpeedAlert(speedAlertID, speedAlerts);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteSpeedAlert(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteSpeedAlert]");
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteSpeedAlert]");
        }
        ResponseDTO responseDTO = alertService.deleteSpeedAlert(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }
        return responseDTO.getModelAsJson();
    }

//  ----------------  Movement Alert methods ----------------

    public Object createMovementAlert(Request request, Response response) throws ApiException {
        MovementAlerts movementAlert = JsonUtils.INSTANCE.GSON().fromJson(request.body(), MovementAlerts.class);
        ResponseDTO<MovementAlerts> responseDTO = alertService.createMovementAlert(movementAlert);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getMovementAlerts(Request request, Response response) throws ApiException {

        ResponseDTO<List<MovementAlerts>> responseDTO = alertService.getMovementAlert();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateMovementAlert(Request request, Response response) throws ApiException{
        String param = request.params("movement_alert_id");
        Long movementAlertID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_movement_alert_id] [method: AlertController.updateMovementAlert]");
        }

        try {
            movementAlertID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_movement_alert_id] [method: AlertController.updateMovementAlert]");
        }

        MovementAlerts movementAlert = JsonUtils.INSTANCE.GSON().fromJson(request.body(), MovementAlerts.class);
        //Add validations

        ResponseDTO<MovementAlerts> responseDTO = alertService.updateMovementAlert(movementAlertID, movementAlert);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteMovementAlert(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteMovementAlert]");
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteMovementAlert]");
        }
        ResponseDTO responseDTO = alertService.deleteMovementAlert(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


    //  ----------------  Speed Alert History methods ----------------
    public Object createSpeedHistory(Request request, Response response) throws ApiException {
        SpeedAlertsHistory speedAlertsHistory = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlertsHistory.class);
        ResponseDTO<SpeedAlertsHistory> responseDTO = alertService.createSpeedAlertHistory(speedAlertsHistory);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteSpeedHistory(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteSpeedHistory]");
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteSpeedHistory]");
        }
        ResponseDTO responseDTO = alertService.deleteSpeedAlertHistory(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }
        return responseDTO.getModelAsJson();
    }


    //  ----------------  Movement Alert History methods ----------------

    public Object createMovementHistory(Request request, Response response) throws ApiException {
        MovementAlertsHistory movementAlertsHistory = JsonUtils.INSTANCE.GSON().fromJson(request.body(), MovementAlertsHistory.class);
        ResponseDTO<MovementAlertsHistory> responseDTO = alertService.createMovementAlertHistory(movementAlertsHistory);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteMovementHistory(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteMovementHistory]");
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: AlertController.deleteMovementHistory]");
        }
        ResponseDTO responseDTO = alertService.deleteMovementAlertHistory(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }
        return responseDTO.getModelAsJson();
    }

    public void sendAlarm(String alertType){
        switch (alertType){
            case "SPEED":
                System.out.println("Locura.. te pasaste de speed loco!!");
                break;
            case "MOVEMENT":
                System.out.println("Locura.. tambien te estan llevando el auto gil!!");
                break;
            default:
                System.out.println("Wachin.. guarda con el auto que algo está pasando");
        }
    }

    public Object sendSMS(Request request, Response response){
        String phoneNumber = request.params("phone_number");
        try {
            Till.send(
                    "https://platform.tillmobile.com/api/send",
                    "ff93cf9df324474d88c11e70472f4c",
                    "5819e8e98176a0549235ed602c003954f3f01c3c",
                    "{\"phone\":[\"" + phoneNumber + "\"], \"text\":\"Hello Till from Java!\"}"
            );
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseDTO();
    }
}
