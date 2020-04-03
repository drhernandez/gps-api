package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.AlertService;
import com.tesis.services.TrackingService;
import com.tesis.services.VehicleService;
import com.tesis.utils.JsonUtils;
import com.tesis.utils.filters.VehicleFilters;
import com.tesis.utils.filters.vehicleActivateBody;
import org.apache.http.HttpStatus;
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

    @Inject
    public VehicleController(VehicleService vehicleService,
                             TrackingService trackingService,
                             AlertService alertService) {
        this.vehicleService = vehicleService;
        this.trackingService = trackingService;
        this.alertService = alertService;
    }

    public Object getVehicles(Request request, Response response) throws ApiException {

        ResponseDTO<List<Vehicles>> responseDTO = vehicleService.getVehicles();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getVehiclesByVehicleID(Request request, Response response) throws ApiException {
        String param = request.params("Vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<Vehicles> responseDTO = vehicleService.getVehiclesByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object createVehicle(Request request, Response response) throws ApiException {

        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();

        Vehicles Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Vehicles.class);
        //Agregar validaciones

        responseDTO = vehicleService.createVehicle(Vehicle);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public  Object updateVehicle(Request request, Response response) throws ApiException {
        String param = request.params("Vehicle_id");

        Long VehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: VehicleController.updateVehicle]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            VehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: VehicleController.updateVehicle]",
                    HttpStatus.SC_BAD_REQUEST);
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
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: VehicleController.deleteVehicle]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: VehicleController.deleteVehicle]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<Vehicles> responseDTO = vehicleService.deleteVehicle(vehicleID);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_vehicle_id] [method: VehicleController.getTrackingsByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_vehicle_id] [method: VehicleController.getTrackingsByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException(ErrorCodes.invalid_data.name(),
                    "[reason: invalid_vehicle_id] [method: VehicleController.getLocationByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException(ErrorCodes.invalid_data.name(),
                    "[reason: invalid_vehicle_id] [method: VehicleController.getLocationByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<Trackings> responseDTO = trackingService.getLocationByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getSpeedAlertByVehicleID(Request request, Response response) throws ApiException{
        String param = request.params("vehicle_id");
        Long vehicleID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getSpeedAlertByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getSpeedAlertByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getMovementAlertByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getMovementAlertByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getSpeedHistoryByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getSpeedHistoryByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getMovementHistoryByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: vehicle_id] [method: VehicleController.getMovementHistoryByVehicleID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<List<MovementAlertsHistory>> responseDTO = alertService.getMovementHistoryByVehicleID(vehicleID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object vehicleSearch(Request request, Response response) throws ApiException {
        VehicleFilters vehicleFilters = new VehicleFilters();
        Pagination pagination = new Pagination();

        try{
            vehicleFilters.setStatus(request.queryParams("status"));
            vehicleFilters.setUserId(request.queryParams("user_id") != null ? Long.valueOf(request.queryParams("user_id")) : null);
            vehicleFilters.setDeviceId(request.queryParams("device_id") != null ? Long.valueOf(request.queryParams("device_id")) : null);
            vehicleFilters.setPlate(request.queryParams("plate"));
            vehicleFilters.setBrand(request.queryParams("brand"));
            vehicleFilters.setBrandLine(request.queryParams("brand_line"));

            pagination.setPage(request.queryParams("page") != null ? Integer.parseInt(request.queryParams("page")) : 1);
            pagination.setLimit(request.queryParams("limit") != null ? Integer.parseInt(request.queryParams("limit")) : 10);

        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_params] [method: VehicleController.vehicleSearch]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<Search> responseDTO = vehicleService.vehicleSearch(vehicleFilters, pagination);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object vehicleActivate(Request request, Response response) throws ApiException {
        String param = request.params("vehicle_id");
        Long vehicleID;
        Long physicalID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException(ErrorCodes.invalid_data.toString(), "[reason: vehicle_id]", HttpStatus.SC_BAD_REQUEST);
        }
        try {
            vehicleID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException(ErrorCodes.invalid_data.toString(), "[reason: vehicle_id]", HttpStatus.SC_BAD_REQUEST);
        }

        physicalID = JsonUtils.INSTANCE.GSON().fromJson(request.body(), vehicleActivateBody.class).getPhysicalId();
        if (physicalID == null) {
            throw new ApiException(ErrorCodes.invalid_data.toString(), "[reason: vehicle_id]", HttpStatus.SC_BAD_REQUEST);
        }

        ResponseDTO<Vehicles> responseDTO = vehicleService.activateVehicle(vehicleID, physicalID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
