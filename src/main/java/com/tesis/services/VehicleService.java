package com.tesis.services;

import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface VehicleService {

    ResponseDTO<List<Vehicles>> getVehicles();
    ResponseDTO<Vehicles> getVehiclesByVehicleID(Integer VehicleID);
    ResponseDTO<Vehicles> createVehicle(Vehicles Vehicle);
    ResponseDTO<Vehicles> updateVehicle(Integer VehicleID, Vehicles Vehicle);
    ResponseDTO<Vehicles> deleteVehicle(Integer VehicleID);
}
