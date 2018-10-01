package com.tesis.services.imp;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.tesis.clients.StorageClient;
import com.tesis.models.Location;
import com.tesis.models.ResponseDTO;
import com.tesis.services.StorageService;

import java.util.List;

public class StorageServiceImp implements StorageService {

    @Inject
    StorageClient storageClient;

    @Override
    public ResponseDTO<List<Location>> getLocations(JsonObject requestInfo) {
        return storageClient.getLocations(requestInfo);
    }

    @Override
    public ResponseDTO<Boolean> addLocation(JsonObject requestInfo) {
        return storageClient.addLocation(requestInfo);
    }
}
