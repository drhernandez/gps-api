package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DevicesService;
import spark.Request;
import spark.Response;

import java.util.List;

public class DeviceController {

    @Inject
    DevicesService devicesService;

    public Object getDevices(Request request, Response response) throws ApiException {

        ResponseDTO<List<Devices>> responseDTO = devicesService.getDevices();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
