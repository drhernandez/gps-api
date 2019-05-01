package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.ResponseDTO;
import com.tesis.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class VehicleServiceImp implements VehicleService {

    Logger logger = LoggerFactory.getLogger(VehicleServiceImp.class);

    @Inject
    VehicleDaoExt vehiclesDao;

    public ResponseDTO<List<Vehicles>> getVehicles() {
        return new ResponseDTO(vehiclesDao.findAllActives(), null);
    }

    public ResponseDTO<Vehicles> getVehiclesByVehicleID(Integer VehicleID) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO(vehiclesDao.fetchOneById(VehicleID), null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> createVehicle(Vehicles Vehicle) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();

        try {
            vehiclesDao.insert(Vehicle);
            responseDTO.model = Vehicle;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el usuario %s", Vehicle.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el usuario.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> updateVehicle(Integer VehicleID, Vehicles newData) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        Vehicles Vehicle = vehiclesDao.fetchOneById(VehicleID);
        Vehicle.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        Vehicle.setDeletedAt(null);
        Vehicle.setType(newData.getType());
        Vehicle.setPlate(newData.getPlate());
        Vehicle.setModel(newData.getModel());
        try {
            vehiclesDao.update(Vehicle);
            responseDTO.model = Vehicle;
        } catch (Exception e) {
            logger.error(String.format("No se pudo modificar el usuario %s", Vehicle.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el usuario.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> deleteVehicle(Integer VehicleID) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        try {
            Vehicles Vehicle = vehiclesDao.fetchOneById(VehicleID);
            Vehicle.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
            vehiclesDao.update(Vehicle);
        }catch (Exception e) {
            logger.error(String.format("No se pudo eliminar el usuario %s", VehicleID));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el usuario.");
        }
        return responseDTO;
    }
}
