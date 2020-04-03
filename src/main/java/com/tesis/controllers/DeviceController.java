package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DeviceService;
import com.tesis.utils.JsonUtils;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceController {

    private DeviceService devicesService;

    @Inject
    public DeviceController(DeviceService devicesService) {
        this.devicesService = devicesService;
    }

    public Object bulkCreate(Request request, Response response){
        String body = request.body();
        if (StringUtils.isBlank(body)) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_body] [method: DeviceController.bulkCreate]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        body = body.replace("[", "")
                .replace("]", "")
                .replace(" ", "");
        List<Long> idList = getDeviceIdsFromBody(body);

        ResponseDTO<List<Devices>> responseDTO = devicesService.bulkCreate(idList);
        if (responseDTO.error != null)
            throw responseDTO.error;

        return responseDTO.getModelAsJson();
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: deviceController.getDeciveByDeviceID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_Vehicle_id] [method: deviceController.getDeciveByDeviceID]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: deviceController.getDeviceByDeviceID]",
                    HttpStatus.SC_BAD_REQUEST);
        }

        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: deviceController.getDeviceByDeviceID]",
                    HttpStatus.SC_BAD_REQUEST);
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
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: deviceController.deleteDevice]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        try {
            deviceID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data",
                    "[reason: invalid_device_id] [method: deviceController.deleteDevice]",
                    HttpStatus.SC_BAD_REQUEST);
        }
        ResponseDTO<Devices> responseDTO = devicesService.deleteDevice(deviceID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    private List<Long> getDeviceIdsFromBody(String body){
        return Arrays.stream(body.split(",")).map(Long::valueOf).collect(Collectors.toList());
    }
}
