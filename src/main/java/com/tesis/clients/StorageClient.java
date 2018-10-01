package com.tesis.clients;

import com.google.gson.JsonObject;
import com.tesis.models.Location;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface StorageClient {

    /**
     * Get available locations by device_id
     * @param requestInfo
     * @return
     */
    ResponseDTO<List<Location>> getLocations(JsonObject requestInfo);

    /**
     * Add new location by device_id
     * @param requestInfo
     * @return
     */
    ResponseDTO<Boolean> addLocation(JsonObject requestInfo);
}
