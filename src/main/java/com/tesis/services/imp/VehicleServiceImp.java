package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
import com.tesis.exceptions.ApiException;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.DeviceService;
import com.tesis.services.VehicleService;
import com.tesis.utils.JsonUtils;
import com.tesis.utils.filters.VehicleFilters;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Response;
<<<<<<< HEAD
=======
import java.sql.Timestamp;
>>>>>>> bce7209bd47b20d5d2ed31ac69909773d08b03e4
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class VehicleServiceImp implements VehicleService {

    Logger logger = LoggerFactory.getLogger(VehicleServiceImp.class);

    @Inject
    VehicleDaoExt vehiclesDao;
    @Inject
    DeviceService deviceService;

    public ResponseDTO<List<Vehicles>> getVehicles() {
        return new ResponseDTO<>(vehiclesDao.findAllActives(), null);
    }

    public ResponseDTO<Vehicles> getVehiclesByVehicleID(Long VehicleID) {
        return new ResponseDTO<>(vehiclesDao.fetchOneById(VehicleID), null);
    }

    @Override
    public ResponseDTO<Vehicles> createVehicle(Vehicles vehicle) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();

        try {
            vehicle.setStatus(Status.PENDING.toString());
            vehiclesDao.createVehicle(vehicle);
            responseDTO.model = vehicle;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar el vehículo {}] [error: {}]", vehicle.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el vehiculo.", e);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> updateVehicle(Long VehicleID, Vehicles newData) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();
        try {

            Vehicles vehicle = vehiclesDao.fetchOneById(VehicleID);
            vehicle.setStatus(newData.getStatus());
            vehicle.setLastUpdated(LocalDateTime.now());
            vehicle.setDeletedAt(null);
            vehicle.setUserId(newData.getUserId());
            vehicle.setPlate(newData.getPlate());
            vehicle.setDeviceId(newData.getDeviceId());
            vehicle.setBrand(newData.getBrand());
            vehicle.setBrandLine(newData.getBrandLine());

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

    public Long getUserIDByVehicleID(Long vehicleID){
        return vehiclesDao.fetchByIDForUserID(vehicleID);
    }

    @Override
    public ResponseDTO<Search> vehicleSearch(VehicleFilters filters, Pagination pagination) {
        ResponseDTO<Search> responseDTO = new ResponseDTO<>();
        responseDTO.model = new Search<Vehicles>(vehiclesDao.findByFilters(filters, pagination), pagination);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Vehicles> activateVehicle(Long vehicleId, Long physicalId) {
        ResponseDTO<Vehicles> responseDTO = new ResponseDTO<>();

        try {
            Vehicles vehicle = getVehiclesByVehicleID(vehicleId).getModel();

            if (vehicle == null)
                throw new ApiException(ErrorCodes.not_found.toString(),
                        "Vehiculo no encontrado",
                        HttpStatus.SC_NOT_FOUND);

            if (vehicle.getStatus().equals(Status.ACTIVE.name()))
                throw new ApiException(ErrorCodes.bad_request.toString(),
                        "Vehiculo actualente activado",
                        HttpStatus.SC_BAD_REQUEST);

            ResponseDTO<Devices> deviceDTO = deviceService.getDeviceByPhysicalID(physicalId);
            if (deviceDTO.error != null)
                throw new ApiException(ErrorCodes.internal_error.toString(),
                        "No se pudo activar el vehiculo. Reason: " + deviceDTO.error.getMessage(),
                        HttpStatus.SC_INTERNAL_SERVER_ERROR);

            Devices device = deviceDTO.getModel();
            if (device == null)
                throw new ApiException(ErrorCodes.bad_request.name(),
                        "No se pudo activar el vehiculo. Reason: Physical id inválido",
                        HttpStatus.SC_BAD_REQUEST);

            if (device.getStatus().equals(Status.ACTIVE.name()))
                throw new ApiException(ErrorCodes.bad_request.name(),
                        "No se pudo activar el vehiculo. Reason: El physical id ingresado ya está en uso",
                        HttpStatus.SC_BAD_REQUEST);


            if (device.getStatus().equals(Status.INACTIVE.name())) {
                device.setStatus(Status.ACTIVE.name());
                deviceService.updateDevice(device.getId(), device);
            }

            vehicle.setStatus(Status.ACTIVE.toString());
            vehicle.setDeviceId(device.getId());
            updateVehicle(vehicleId, vehicle);
            responseDTO.model = vehicle;

        } catch (ApiException e) {
            responseDTO.error = e;
        }

        return responseDTO;
    }
}
