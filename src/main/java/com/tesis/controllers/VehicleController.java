package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.ResponseDTO;
import com.tesis.services.*;
import com.tesis.utils.JsonUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class VehicleController {

    private static Logger logger = LoggerFactory.getLogger(VehicleController.class);

    private VehicleService vehicleService;
    private TrackingService trackingService;
    private AlertService alertService;
    private AuthAdminService authAdminService;
    private AuthService authService;

    @Inject
    public VehicleController(VehicleService vehicleService,
                             TrackingService trackingService,
                             AlertService alertService,
                             AuthAdminService authAdminService,
                             AuthService authService) {
        this.vehicleService = vehicleService;
        this.trackingService = trackingService;
        this.alertService = alertService;
        this.authAdminService = authAdminService;
        this.authService = authService;
    }

    public Object getVehicles(Request request, Response response) throws ApiException {

        ResponseDTO<List<Vehicles>> responseDTO = vehicleService.getVehicles();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getVehiclesByVehicleID(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("Vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }

        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
            if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
                logger.error("User access unauthorized [method: VehicleController.getVehiclesByVehicleID]");
                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
                throw responseDTO.error;
            }
        }

        responseDTO = vehicleService.getVehiclesByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object createVehicle(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];

        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
            logger.error("User access unauthorized [method: VehicleController.createVehicle]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        Vehicles Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Vehicles.class);
        //Agregar validaciones

        responseDTO = vehicleService.createVehicle(Vehicle);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public  Object updateVehicle(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("Vehicle_id");
        Long VehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.updateVehicle]");
        }

        try {
            VehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.updateVehicle]");
        }

        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
            logger.error("User access unauthorized [method: VehicleController.updateVehicle]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        Vehicles Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Vehicles.class);
        //Add validations

        responseDTO = vehicleService.updateVehicle(VehicleID, Vehicle);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteVehicle(Request request, Response response) throws  ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("Vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.deleteVehicle]");
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.deleteVehicle]");
        }

        ResponseDTO responseDTO = new ResponseDTO();
        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
            if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
                logger.error("User access unauthorized [method: VehicleController.deleteVehicle]");
                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
                throw responseDTO.error;
            }
        }

        responseDTO = vehicleService.deleteVehicle(vehicleID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getTrackingsByVehicleID(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_vehicle_id] [method: VehicleController.getTrackingsByVehicleID]");
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_vehicle_id] [method: VehicleController.getTrackingsByVehicleID]");
        }

        ResponseDTO responseDTO = new ResponseDTO();
        if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
            logger.error("User access unauthorized [method: VehicleController.getTrackingsByVehicleID]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = trackingService.getTrackingsByVehicleID(vehicleID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getLocationByVehicleID(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_vehicle_id] [method: VehicleController.getLocationByVehicleID]");
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_vehicle_id] [method: VehicleController.getLocationByVehicleID]");
        }

        ResponseDTO responseDTO = new ResponseDTO();
        if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
            logger.error("User access unauthorized [method: VehicleController.getLocationByVehicleID]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = trackingService.getLocationByVehicleID(vehicleID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedAlertByVehicleID(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getSpeedAlertByVehicleID]");
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getSpeedAlertByVehicleID]");
        }

        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
        if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
            logger.error("User access unauthorized [method: VehicleController.getSpeedAlertByVehicleID]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = alertService.getSpeedAlertByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getMovementAlertByVehicleID(Request request, Response response) throws ApiException{
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getMovementAlertByVehicleID]");
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getMovementAlertByVehicleID]");
        }

        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
        if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
            logger.error("User access unauthorized [method: VehicleController.getMovementAlertByVehicleID]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = alertService.getMovementAlertByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedHistoryByVehicleID(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getSpeedHistoryByVehicleID]");
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getSpeedHistoryByVehicleID]");
        }

        ResponseDTO<List<SpeedAlertsHistory>> responseDTO = new ResponseDTO<>();
        if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
            logger.error("User access unauthorized [method: VehicleController.getSpeedHistoryByVehicleID]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = alertService.getSpeedHistoryByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getMovementHistoryByVehicleID(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getMovementHistoryByVehicleID]");
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: vehicle_id] [method: AlertController.getMovementHistoryByVehicleID]");
        }

        ResponseDTO<List<MovementAlertsHistory>> responseDTO = new ResponseDTO<>();
        if (!authService.checkUserPermissions(accessToken, vehicleService.getUserIDByVehicleID(vehicleID))){
            logger.error("User access unauthorized [method: VehicleController.getMovementHistoryByVehicleID]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = alertService.getMovementHistoryByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

}
