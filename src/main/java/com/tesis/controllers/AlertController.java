package com.tesis.controllers;

import com.google.inject.Inject;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tesis.clients.SendSMSCClient;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.ResponseDTO;
import com.tesis.models.SMSRequest;
import com.tesis.models.SMSResponse;
import com.tesis.routes.Router;
import com.tesis.services.AlertService;
import com.tesis.services.AuthService;
import com.tesis.services.UserService;
import com.tesis.utils.JsonUtils;
import org.eclipse.jetty.http.HttpStatus;
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

    private AlertService alertService;
    private UserService userService;
    private AuthService authService;
    private SendSMSCClient sendSMSCClient;

    @Inject
    public AlertController(AlertService alertService,
                           UserService userService,
                           AuthService authService,
                           SendSMSCClient sendSMSCClient) {
        this.alertService = alertService;
        this.userService = userService;
        this.authService = authService;
        this.sendSMSCClient = sendSMSCClient;
    }

    //  ----------------  Speed Alert methods ----------------

    public Object createSpeedAlert(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        SpeedAlerts speedAlert = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlerts.class);
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken, alertService.getUserIDByDeviceID(speedAlert.getDeviceId()))){
//                logger.error("User access unauthorized [method: AlertController.createSpeedAlert]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.createSpeedAlert(speedAlert);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedAlerts(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        ResponseDTO<List<SpeedAlerts>> responseDTO = new ResponseDTO<>();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            logger.error("User access unauthorized [method: AlertController.getSpeedAlerts]");
//            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//            throw responseDTO.error;
//        }

        responseDTO = alertService.getSpeedAlerts();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateSpeedAlert(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];

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

        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
        if (!authService.checkUserPermissions(accessToken, alertService.getUserIDByDeviceID(speedAlerts.getDeviceId()))){
            logger.error("User access unauthorized [method: AlertController.updateSpeedAlert]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = alertService.updateSpeedAlert(speedAlertID, speedAlerts);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteSpeedAlert(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];

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

        ResponseDTO responseDTO = new ResponseDTO();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken, alertService.getUserIDByDeviceID(deviceID))){
//                logger.error("User access unauthorized [method: AlertController.deleteSpeedAlert]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.deleteSpeedAlert(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

//  ----------------  Movement Alert methods ----------------

    public Object createMovementAlert(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        MovementAlerts movementAlert = JsonUtils.INSTANCE.GSON().fromJson(request.body(), MovementAlerts.class);
        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken, alertService.getUserIDByDeviceID(movementAlert.getDeviceId()))){
//                logger.error("User access unauthorized [method: AlertController.createMovementAlert]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.createMovementAlert(movementAlert);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getMovementAlerts(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        ResponseDTO<List<MovementAlerts>> responseDTO = new ResponseDTO<>();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            logger.error("User access unauthorized [method: AlertController.getMovementAlerts]");
//            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//            throw responseDTO.error;
//        }

        responseDTO = alertService.getMovementAlert();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateMovementAlert(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];

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

        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
        if (!authService.checkUserPermissions(accessToken, alertService.getUserIDByDeviceID(movementAlert.getDeviceId()))){
            logger.error("User access unauthorized [method: AlertController.updateMovementAlert]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = alertService.updateMovementAlert(movementAlertID, movementAlert);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteMovementAlert(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];

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

        ResponseDTO responseDTO = new ResponseDTO();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken, alertService.getUserIDByDeviceID(deviceID))){
//                logger.error("User access unauthorized [method: AlertController.deleteMovementAlert]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.deleteMovementAlert(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


    //  ----------------  Speed Alert History methods ----------------

    public Object createSpeedHistory(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        SpeedAlertsHistory speedAlertsHistory = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SpeedAlertsHistory.class);
        ResponseDTO<SpeedAlertsHistory> responseDTO = new ResponseDTO<>();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken,
//                    alertService.getUserIDByDeviceID(alertService.getUserIDBySpeedAlertID(speedAlertsHistory.getAlertId())))){
//                logger.error("User access unauthorized [method: AlertController.createSpeedHistory]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.createSpeedAlertHistory(speedAlertsHistory);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteSpeedHistory(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];

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

        ResponseDTO responseDTO = new ResponseDTO();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken,
//                    alertService.getUserIDByDeviceID(deviceID))){
//                logger.error("User access unauthorized [method: AlertController.deleteSpeedHistory]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.deleteSpeedAlertHistory(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }
        return responseDTO.getModelAsJson();
    }


    //  ----------------  Movement Alert History methods ----------------

    public Object createMovementHistory(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        MovementAlertsHistory movementAlertsHistory = JsonUtils.INSTANCE.GSON().fromJson(request.body(), MovementAlertsHistory.class);
        ResponseDTO<MovementAlertsHistory> responseDTO = new ResponseDTO<>();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken,
//                    alertService.getUserIDByDeviceID(alertService.getUserIDByMovementAlertID(movementAlertsHistory.getAlertId())))){
//                logger.error("User access unauthorized [method: AlertController.createMovementHistory]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.createMovementAlertHistory(movementAlertsHistory);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteMovementHistory(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];

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

        ResponseDTO responseDTO = new ResponseDTO();
//        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
//            if (!authService.checkUserPermissions(accessToken,
//                    alertService.getUserIDByDeviceID(deviceID))){
//                logger.error("User access unauthorized [method: AlertController.deleteMovementHistory]");
//                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
//                throw responseDTO.error;
//            }
//        }

        responseDTO = alertService.deleteMovementAlertHistory(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


    //  ----------------  SMSC Api methods ----------------

    public Object sendSMS(Request request, Response response) throws ApiException, UnirestException {
        SMSRequest smsRequest = JsonUtils.INSTANCE.GSON().fromJson(request.body(), SMSRequest.class);

        try {
            sendSMSCClient.sendSMS(smsRequest);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return new ResponseDTO();
    }

}
