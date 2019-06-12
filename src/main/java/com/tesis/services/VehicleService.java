package com.tesis.services;

import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface VehicleService {

    ResponseDTO<List<Vehicles>> getVehicles();
    ResponseDTO<Vehicles> getVehiclesByVehicleID(Long VehicleID);
    ResponseDTO<Vehicles> createVehicle(Vehicles Vehicle);
    ResponseDTO<Vehicles> updateVehicle(Long VehicleID, Vehicles Vehicle);
    ResponseDTO<Vehicles> deleteVehicle(Long VehicleID);
    ResponseDTO<List<Vehicles>> getVehiclesByUserID(Long userID);
}
