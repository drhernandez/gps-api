package com.tesis.clients.imp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Singleton;
import com.tesis.clients.StorageClient;
import com.tesis.models.Location;
import com.tesis.models.ResponseDTO;
import com.tesis.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class StorageClientImp implements StorageClient {

    private List<Location> locations = new ArrayList<>();

    @Override
    public ResponseDTO<List<Location>> getLocations(JsonObject requestInfo) {

        ResponseDTO<List<Location>> responseDTO = new ResponseDTO<>();

        String deviceId = requestInfo.get("device_id").toString();
        responseDTO.model = locations.stream().filter(l -> l.getDeviceId().equals(deviceId)).collect(Collectors.toList());

        return responseDTO;
    }

    @Override
    public ResponseDTO<Boolean> addLocation(JsonObject requestInfo) {

        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        JsonElement body = requestInfo.get("body");
        try {
            List locationsBody = JsonUtils.INSTANCE.GSON().fromJson(body, List.class);
            locationsBody.forEach(l -> {
               Location location = JsonUtils.INSTANCE.GSON().fromJson(JsonUtils.INSTANCE.GSON().toJson(l), Location.class);
               locations.add(location);
            });
            responseDTO.model = true;
        } catch (Exception e) {
            responseDTO.model = false;
        }

        return responseDTO;
    }
}
