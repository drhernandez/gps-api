package com.tesis.controllers;

import com.google.inject.Inject;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.ResponseDTO;
import com.tesis.models.SMSRequest;
import com.tesis.models.SMSResponse;
import com.tesis.routes.Router;
import com.tesis.services.AlertService;
import com.tesis.services.UserService;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.tesis.config.Constants.DEFAULT_TEXT_MOVEMENT_ALERT;
import static com.tesis.config.Constants.DEFAULT_TEXT_SPEED_ALERT;
import static com.tesis.enums.ErrorCodes.invalid_data;

public class AlertController {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);


    @Inject
    AlertService alertService;

    @Inject
    UserService userService;

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


    //  ----------------  SMSC Api methods ----------------

    public void sendAlarm(Long deviceId, String alertType) throws ApiException {
        Users user = userService.getUsersByDeviceId(deviceId).getModel();

        try {
            String result = Unirest.get("https://www.smsc.com.ar/api/0.3/?alias=" + System.getenv("SMSC_ALIAS") +
                    "&apikey=" + System.getenv("SMSC_API_KEY") +
                    "&cmd=enviar&num=" + user.getPhone() +
                    "&msj=" + (alertType.equals("SPEED") ? DEFAULT_TEXT_SPEED_ALERT : DEFAULT_TEXT_MOVEMENT_ALERT))

                    .asJson()
                    .getBody()
                    .toString();

            SMSResponse resp = JsonUtils.INSTANCE.GSON().fromJson(result, SMSResponse.class);

            switch (resp.getCode()){
                case "200":
                    break;
                case "403":
                    logger.error(resp.getMessage());
                    throw new ApiException(invalid_data.name(),
                            String.format("invalid phone number: %s", user.getPhone()),
                            HttpServletResponse.SC_NOT_FOUND);
                case "405":
                    logger.error(resp.getMessage());
                    throw new ApiException(invalid_data.name(),
                            "no messages available",
                            HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApiException(ErrorCodes.internal_error.toString(), "Error al enviar el mensaje de alerta.");
        }

    }

    public Object sendSMS(Request request, Response response) throws ApiException, UnirestException {
        SMSRequest smsRequest = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SMSRequest.class);

        try {

            String result = Unirest.get("https://www.smsc.com.ar/api/0.3/?alias=" + System.getenv("SMSC_ALIAS") +
                    "&apikey=" + System.getenv("SMSC_API_KEY") +
                    "&cmd=enviar&num=" + smsRequest.getReceptor() +
                    "&msj=Test%20de%20alerta%20de%20texto%20GPS-TESIS")

                    .asJson()
                    .getBody()
                    .toString();

            SMSResponse resp = JsonUtils.INSTANCE.GSON().fromJson(result, SMSResponse.class);

            switch (resp.getCode()){
                case "200":
                    return new ResponseDTO();
                case "403":
                    logger.error(resp.getMessage());
                    throw new ApiException(invalid_data.name(),
                            String.format("invalid phone number: %s", smsRequest.getReceptor()),
                            HttpServletResponse.SC_NOT_FOUND);
                case "405":
                    logger.error(resp.getMessage());
                    throw new ApiException(invalid_data.name(),
                            "no messages available",
                            HttpServletResponse.SC_NOT_FOUND);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;

        }
        return new ResponseDTO();
    }
}
