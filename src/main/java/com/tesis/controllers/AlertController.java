package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.clients.SMSClient;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.MovementAlerts;
import com.tesis.jooq.tables.pojos.MovementAlertsHistory;
import com.tesis.jooq.tables.pojos.SpeedAlerts;
import com.tesis.jooq.tables.pojos.SpeedAlertsHistory;
import com.tesis.models.ResponseDTO;
import com.tesis.models.SMSRequest;
import com.tesis.routes.Router;
import com.tesis.services.AlertService;
import com.tesis.utils.JsonUtils;
import kong.unirest.UnirestException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class AlertController {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    private AlertService alertService;
    private SMSClient smsClient;

    @Inject
    public AlertController(AlertService alertService, SMSClient smsClient) {
        this.alertService = alertService;
        this.smsClient = smsClient;
    }

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
            throw new ApiException("invalid_data",
                    "[reason: invalid_speed_alert_id] [method: AlertController.updateSpeedAlert]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            speedAlertID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_speed_alert_id] [method: AlertController.updateSpeedAlert]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteSpeedAlert]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteSpeedAlert]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<SpeedAlerts> responseDTO = alertService.deleteSpeedAlert(deviceID);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_movement_alert_id] [method: AlertController.updateMovementAlert]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            movementAlertID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_movement_alert_id] [method: AlertController.updateMovementAlert]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteMovementAlert]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteMovementAlert]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<MovementAlerts> responseDTO = alertService.deleteMovementAlert(deviceID);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteSpeedHistory]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteSpeedHistory]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<SpeedAlertsHistory> responseDTO = alertService.deleteSpeedAlertHistory(deviceID);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteMovementHistory]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: AlertController.deleteMovementHistory]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO responseDTO = alertService.deleteMovementAlertHistory(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


    //  ----------------  SMS Api methods ----------------

    public Object sendSMS(Request request, Response response) throws ApiException, UnirestException {
        SMSRequest smsRequest = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SMSRequest.class);

        try {
            smsClient.sendSMS(smsRequest);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return new ResponseDTO();
    }

}
