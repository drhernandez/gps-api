package com.tesis.services;

import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface TrackingService {

    ResponseDTO<List<Trackings>> saveTracking(List<Trackings> trakings);
    ResponseDTO<List<Trackings>> getTrackingsByDeviceID(Long deviceID);
    ResponseDTO<List<Trackings>> getTrackingsByVehicleID(Long vehicleID);
    ResponseDTO<Trackings> getLocationByVehicleID(Long vehicleID);
}
