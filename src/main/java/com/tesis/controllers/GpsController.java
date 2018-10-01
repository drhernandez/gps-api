package com.tesis.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import com.tesis.models.ResponseDTO;
import com.tesis.services.StorageService;
import spark.Request;
import spark.Response;

public class GpsController {

    @Inject
    StorageService storageService;

    public Object getLocations(Request request, Response response) {
        JsonObject requestInfo = new JsonObject();
        JsonElement deviceId = new JsonParser().parse(request.params("device_id"));
        requestInfo.add("device_id", deviceId);
        ResponseDTO responseDTO = storageService.getLocations(requestInfo);
        return responseDTO.getModelAsJson();
    }

    public Object addLocation(Request request, Response response) {
        JsonObject requestInfo = new JsonObject();
        requestInfo.add("body", new JsonParser().parse(request.body()).getAsJsonArray());
        ResponseDTO responseDTO = storageService.addLocation(requestInfo);
        return responseDTO.getModelAsJson();
    }
}
