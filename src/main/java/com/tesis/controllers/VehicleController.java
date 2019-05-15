package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.ResponseDTO;
import com.tesis.services.VehicleService;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class VehicleController {

    private static Logger logger = LoggerFactory.getLogger(TrackingController.class);


    @Inject
    VehicleService vehicleService;

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
        return responseDTO.getModelAsJson();
    }
}
