package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AlertService;
import com.tesis.services.TrackingService;
import com.tesis.services.VehicleService;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class VehicleController {

    private static Logger logger = LoggerFactory.getLogger(VehicleController.class);


    @Inject
    VehicleService vehicleService;
    @Inject
    TrackingService trackingService;
    @Inject
    AlertService alertService;

    public Object getVehicles(Request request, Response response) throws ApiException {

        ResponseDTO<List<Vehicles>> responseDTO = vehicleService.getVehicles();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getVehiclesByVehicleID(Request request, Response response) throws ApiException {

        String param = request.params("Vehicle_id");
        Long VehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }

        try {
            VehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }

        ResponseDTO<Vehicles> responseDTO = vehicleService.getVehiclesByVehicleID(VehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object createVehicle(Request request, Response response) throws ApiException {

        Vehicles Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Vehicles.class);
        //Agregar validaciones

        ResponseDTO<Vehicles> responseDTO = vehicleService.createVehicle(Vehicle);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public  Object updateVehicle(Request request, Response response) throws ApiException {

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

        Vehicles Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Vehicles.class);
        //Add validations

        ResponseDTO<Vehicles> responseDTO = vehicleService.updateVehicle(VehicleID, Vehicle);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteVehicle(Request request, Response response) throws  ApiException {
        String param = request.params("Vehicle_id");
        Long VehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.deleteVehicle]");
        }
        try {
            VehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.deleteVehicle]");
        }
        ResponseDTO responseDTO = vehicleService.deleteVehicle(VehicleID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getTrackingsByVehicleID(Request request, Response response) throws ApiException {
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

        ResponseDTO responseDTO = trackingService.getTrackingsByVehicleID(vehicleID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getLocationByVehicleID(Request request, Response response) throws ApiException {
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

        ResponseDTO responseDTO = trackingService.getLocationByVehicleID(vehicleID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedAlertByVehicleID(Request request, Response response) throws ApiException{
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
        ResponseDTO<SpeedAlerts> responseDTO = alertService.getSpeedAlertByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getMovementAlertByVehicleID(Request request, Response response) throws ApiException{
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
        ResponseDTO<MovementAlerts> responseDTO = alertService.getMovementAlertByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedHistoryByVehicleID(Request request, Response response) throws ApiException {
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

        ResponseDTO<List<SpeedAlertsHistory>> responseDTO = alertService.getSpeedHistoryByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getMovementHistoryByVehicleID(Request request, Response response) throws ApiException {
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

        ResponseDTO<List<MovementAlertsHistory>> responseDTO = alertService.getMovementHistoryByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

}
