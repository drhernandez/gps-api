package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.DeviceDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class DeviceServiceImp implements DeviceService {

    Logger logger = LoggerFactory.getLogger(DeviceServiceImp.class);

    @Inject
    DeviceDaoExt devicesDao;

    @Override
    public ResponseDTO<Devices> createDevice(Devices device) {
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();

        try {
            devicesDao.insertDevice(device);
            responseDTO.model = device;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el device %s. Razon: %s", device.toString(), e.getMessage()));
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
        try {

            Devices device = devicesDao.fetchOneById(deviceID);
            device.setPhysicalId(newDevice.getPhysicalId());
            device.setDeletedAt(null);
            device.setLastUpdated(LocalDateTime.now(Clock.systemUTC()));
            device.setModel(newDevice.getModel());
            device.setSoftwareVersion(newDevice.getSoftwareVersion());

            devicesDao.update(device);
            responseDTO.model = device;
        } catch (Exception e){
            logger.error("No se pudo modificar el device");
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

    public ResponseDTO<Devices> getDeviceByPhysicalID(Long physicalID){
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();
        try {
            responseDTO.setModel(devicesDao.getDeviceByPhysicalID(physicalID));
        } catch (Exception e) {
            logger.error(String.format("No se pudo obtener el device con id fisico %s", physicalID));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), e.getMessage());
        }
        return responseDTO;
    }
}
