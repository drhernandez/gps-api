package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DeviceService;
import com.tesis.utils.JsonUtils;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class DeviceController {

    private DeviceService devicesService;

    @Inject
    public DeviceController(DeviceService devicesService) {
        this.devicesService = devicesService;
    }

    public Object createDevice(Request request, Response response) throws ApiException {
        Devices device = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Devices.class);
        ResponseDTO<Devices> responseDTO = devicesService.createDevice(device);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getDevices(Request request, Response response) throws ApiException {

        ResponseDTO<List<Devices>> responseDTO = devicesService.getDevices();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getDeciveByDeviceID(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_Vehicle_id] [method: VehicleController.getVehicleByVehicleID]");
        }
        ResponseDTO<Devices> responseDTO = devicesService.getDeciveByDeviceID(deviceID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateDevice(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: deviceController.getDeviceByDeviceID]");
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: deviceController.getDeviceByDeviceID]");
        }

        Devices Vehicle = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Devices.class);
        //Add validations

        ResponseDTO<Devices> responseDTO = devicesService.updateDevice(deviceID, Vehicle);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteDevice(Request request, Response response) throws ApiException{
        String param = request.params("device_id");
        Long deviceID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: deviceController.deleteDevice]");
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: deviceController.deleteDevice]");
        }
        ResponseDTO responseDTO = devicesService.deleteDevice(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
