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
    public ResponseDTO<List<Location>> getLocations(String deviceId) {

        ResponseDTO<List<Location>> responseDTO = new ResponseDTO<>();
        responseDTO.model = locations.stream().filter(l -> l.getDeviceId().equals(deviceId)).collect(Collectors.toList());

        return responseDTO;
    }

    @Override
    public ResponseDTO<Boolean> addLocation(List<Location> locations) {

        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        this.locations.addAll(locations);
        responseDTO.model = true;

        return responseDTO;
    }
}
