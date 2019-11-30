package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
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

    public ResponseDTO<Vehicles> getVehiclesByVehicleID(Long VehicleID) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO(vehiclesDao.fetchOneById(VehicleID), null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> createVehicle(Vehicles vehicle) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();

        try {
            vehicle.setStatus(Status.PENDING.toString());
            vehiclesDao.createVehicle(vehicle);
            responseDTO.model = vehicle;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el vehiculo %s", vehicle.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el vehiculo.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> updateVehicle(Long VehicleID, Vehicles newData) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        try {

            Vehicles vehicle = vehiclesDao.fetchOneById(VehicleID);
            vehicle.setStatus(newData.getStatus());
            vehicle.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
            vehicle.setDeletedAt(null);
            vehicle.setUserId(newData.getUserId());
            vehicle.setType(newData.getType());
            vehicle.setPlate(newData.getPlate());
            vehicle.setModel(newData.getModel());
            vehicle.setDeviceId(newData.getDeviceId());

            vehiclesDao.update(vehicle);
            responseDTO.model = vehicle;
        } catch (Exception e) {
            logger.error("No se pudo modificar el vahiculo");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el vehiculo.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> deleteVehicle(Long vehicleID) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        try {
            vehiclesDao.deleteVehicle(vehicleID);
        }catch (Exception e) {
            logger.error(String.format("No se pudo eliminar el vehiculo %s", vehicleID));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el vehiculo.");
        }
        return responseDTO;
    }

    public ResponseDTO<List<Vehicles>> getVehiclesByUserID(Long userID){
        return new ResponseDTO(vehiclesDao.fetchByUserId(userID), null);
    }
}
