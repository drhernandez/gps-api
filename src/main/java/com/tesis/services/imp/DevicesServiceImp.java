package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.DeviceDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DevicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class DevicesServiceImp implements DevicesService {

    Logger logger = LoggerFactory.getLogger(DevicesServiceImp.class);

    @Inject
    DeviceDaoExt devicesDao;

    @Override
    public ResponseDTO<Devices> createDevice(Devices device) {
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();

        try {
            devicesDao.insert(device);
            responseDTO.model = device;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el device %s", device.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el device.");
        }

        return responseDTO;
    }

    public ResponseDTO<List<Devices>> getDevices() {
        return new ResponseDTO(devicesDao.findAllActives(), null);
    }

    public ResponseDTO<Devices> getDeciveByDeviceID(Long deviceID) {
        return new ResponseDTO<Devices>(devicesDao.fetchOneById(deviceID), null);
    }

    @Override
    public ResponseDTO<Devices> updateDevice(Long deviceID, Devices newDevice) {
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();
        Devices device = devicesDao.fetchOneById(deviceID);
        device.setDeletedAt(null);
        device.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        device.setVehicleId(newDevice.getVehicleId());
        device.setModel(newDevice.getModel());
        device.setSoftwareVersion(newDevice.getSoftwareVersion());

        try {
            devicesDao.update(device);
            responseDTO.model = device;
        } catch (Exception e){
            logger.error(String.format("No se pudo modificar el device %s", device.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el device.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Devices> deleteDevice(Long deviceId) {
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();
        try {
            devicesDao.deleteDevice(deviceId);
        }catch (Exception e) {
            logger.error(String.format("No se pudo eliminar el device %s", deviceId));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el device.");
        }
        return responseDTO;
    }
}
