package com.tesis.services;

import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.utils.filters.VehicleFilters;

import java.util.List;

public interface VehicleService {

    ResponseDTO<List<Vehicles>> getVehicles();
    ResponseDTO<Vehicles> getVehiclesByVehicleID(Long VehicleID);
    ResponseDTO<Vehicles> createVehicle(Vehicles Vehicle);
    ResponseDTO<Vehicles> updateVehicle(Long VehicleID, Vehicles Vehicle);
    ResponseDTO<Vehicles> deleteVehicle(Long VehicleID);
    ResponseDTO<List<Vehicles>> getVehiclesByUserID(Long userID);
    Long getUserIDByVehicleID(Long vehicleID);
    ResponseDTO<Search> vehicleSearch(VehicleFilters filters, Pagination pagination);
}
