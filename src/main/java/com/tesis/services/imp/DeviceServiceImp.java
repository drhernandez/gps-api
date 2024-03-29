package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.DeviceDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DeviceServiceImp implements DeviceService {

    Logger logger = LoggerFactory.getLogger(DeviceServiceImp.class);

    @Inject
    DeviceDaoExt devicesDao;

    @Override
    public ResponseDTO<List<Devices>> bulkCreate(List<Long> idList) {
        ResponseDTO<List<Devices>> responseDTO = new ResponseDTO<>();
        responseDTO.setModel(new ArrayList<Devices>());

        for (Long id : idList) {
            try {
                responseDTO.model.add(devicesDao.createDevice(id));
            } catch (Exception e) {
                logger.error("[message: No se pudo guardar el device con physical_id {}] [error: {}]", id, e.getMessage());
                responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar los devices.", e);
                break;
            }
        };

        return responseDTO;
    }

    @Override
    public ResponseDTO<Devices> createDevice(Devices device) {
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();

        try {
            device.setStatus(Status.INACTIVE.toString());
            devicesDao.insertDevice(device);
            responseDTO.model = device;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar el device {}] [error: {}]", device.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el device.", e);
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
            device.setStatus(newDevice.getStatus());

            devicesDao.update(device);
            responseDTO.model = device;
        } catch (Exception e){
            logger.error("[message: No se pudo modificar el device] [error: {}]", e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el device.", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Devices> deleteDevice(Long deviceId) {
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();
        try {
            devicesDao.deleteDevice(deviceId);
        }catch (Exception e) {
            logger.error("[message: No se pudo eliminar el device {}] [error: {}]}", deviceId, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el device.", e);
        }
        return responseDTO;
    }

    public ResponseDTO<Devices> getDeviceByPhysicalID(Long physicalID){
        ResponseDTO<Devices> responseDTO = new ResponseDTO<>();
        try {
            responseDTO.setModel(devicesDao.getDeviceByPhysicalID(physicalID));
        } catch (Exception e) {
            logger.error("[message: No se pudo obtener el device con id fisico {}] [error: {}]}", physicalID, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), e.getMessage());
        }
        return responseDTO;
    }
}
