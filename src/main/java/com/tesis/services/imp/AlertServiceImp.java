package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.MovementAlertDaoExt;
import com.tesis.daos.SpeedAlertDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.MovementAlerts;
import com.tesis.jooq.tables.pojos.SpeedAlerts;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class AlertServiceImp implements AlertService {

    Logger logger = LoggerFactory.getLogger(AlertServiceImp.class);

    @Inject
    SpeedAlertDaoExt speedAlertsDao;
    @Inject
    MovementAlertDaoExt movementAlertDao;

    @Override
    public ResponseDTO<SpeedAlerts> createSpeedAlert(SpeedAlerts speedAlert) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();

        try {
            speedAlertsDao.insert(speedAlert);
            responseDTO.model = speedAlert;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el speedAlert %s", speedAlert.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el speedAlert.");
        }

        return responseDTO;
    }

    public ResponseDTO<List<SpeedAlerts>> getSpeedAlerts() {
        return new ResponseDTO(speedAlertsDao.findAllActives(), null);
    }

    public ResponseDTO<SpeedAlerts> getSpeedAlertByDeviceID(Long deviceID) {
        return new ResponseDTO<SpeedAlerts>(speedAlertsDao.fetchOneByDeviceId(deviceID), null);
    }

    @Override
    public ResponseDTO<SpeedAlerts> updateSpeedAlert(Long speedAlertID, SpeedAlerts newSpeedAlert) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
        SpeedAlerts speedAlert = speedAlertsDao.fetchOneById(speedAlertID);
        speedAlert.setActive(newSpeedAlert.getActive());
        speedAlert.setSpeed(newSpeedAlert.getSpeed());
        speedAlert.setDeviceId(newSpeedAlert.getDeviceId());

        try {
            speedAlertsDao.update(speedAlert);
            responseDTO.model = speedAlert;
        } catch (Exception e){
            logger.error(String.format("No se pudo modificar el speedAlert %s", speedAlert.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el speedAlert.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<SpeedAlerts> deleteSpeedAlert(Long deviceID) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
        try {
            speedAlertsDao.deleteSpeedAlert(deviceID);
        }catch (Exception e) {
            logger.error(String.format("No se pudo eliminar el speedAlert %s", deviceID));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el speedAlert.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<MovementAlerts> createMovementAlert(MovementAlerts movementAlert) {
        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();

        try {
            movementAlertDao.insert(movementAlert);
            responseDTO.model = movementAlert;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el movementAlert %s", movementAlert.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el movementAlert.");
        }

        return responseDTO;

    }

    @Override
    public ResponseDTO<List<MovementAlerts>> getMovementAlert() {
        return new ResponseDTO(movementAlertDao.findAllActives(), null);
    }

    @Override
    public ResponseDTO<MovementAlerts> getMovementAlertByDeviceID(Long deviceID) {
        return new ResponseDTO<MovementAlerts>(movementAlertDao.fetchOneByDeviceId(deviceID), null);
    }

    @Override
    public ResponseDTO<MovementAlerts> updateMovementAlert(Long movementAlertID, MovementAlerts newMovementAlert) {
        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
        MovementAlerts momovementAlert = movementAlertDao.fetchOneById(movementAlertID);
        momovementAlert.setActive(newMovementAlert.getActive());
        momovementAlert.setLat(newMovementAlert.getLat());
        momovementAlert.setLng(newMovementAlert.getLng());
        momovementAlert.setDeviceId(newMovementAlert.getDeviceId());

        try {
            movementAlertDao.update(momovementAlert);
            responseDTO.model = momovementAlert;
        } catch (Exception e){
            logger.error(String.format("No se pudo modificar el momovementAlert %s", momovementAlert.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el momovementAlert.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<MovementAlerts> deleteMovementAlert(Long deviceId) {
        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
        try {
            movementAlertDao.deleteMovementAlert(deviceId);
        }catch (Exception e) {
            logger.error(String.format("No se pudo eliminar el speedAlert %s", deviceId));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el speedAlert.");
        }
        return responseDTO;
    }
}
