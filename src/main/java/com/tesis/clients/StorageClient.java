package com.tesis.clients;

import com.tesis.models.Location;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface StorageClient {

    /**
     * Get available locations by device_id
     * @param deviceId
     * @return
     */
    ResponseDTO<List<Location>> getLocations(String deviceId);

    /**
     * Add new location by device_id
     * @param locations
     * @return
     */
    ResponseDTO<Boolean> addLocation(List<Location> locations);
}
