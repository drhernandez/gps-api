package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.*;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class AlertServiceImp implements AlertService {

    Logger logger = LoggerFactory.getLogger(AlertServiceImp.class);

    @Inject
    VehicleDaoExt vehicleDaoExt;
    @Inject
    SpeedAlertDaoExt speedAlertsDao;
    @Inject
    MovementAlertDaoExt movementAlertDao;
    @Inject
    SpeedAlertHistoryDaoExt speedAlertsHistoryDao;
    @Inject
    MovementAlertHistoryDaoExt movementAlertsHistoryDao;

    @Override
    public ResponseDTO<SpeedAlerts> createSpeedAlert(SpeedAlerts speedAlert) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();

        try {
            speedAlert.setCreatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
            speedAlert.setUpdatedAt(null);
            speedAlert.setActivatedAt(null);
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

    public ResponseDTO<SpeedAlerts> getSpeedAlertByVehicleID(Long vehicleID) {
        Vehicles vehicle = vehicleDaoExt.fetchOneById(vehicleID);
        return new ResponseDTO<SpeedAlerts>(speedAlertsDao.fetchOneByDeviceId(vehicle.getDeviceId()), null);
    }

    @Override
    public ResponseDTO<SpeedAlerts> updateSpeedAlert(Long speedAlertID, SpeedAlerts newSpeedAlert) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
        SpeedAlerts speedAlert = speedAlertsDao.fetchOneById(speedAlertID);
        speedAlert.setActive(newSpeedAlert.getActive());
        speedAlert.setSpeed(newSpeedAlert.getSpeed());
        speedAlert.setDeviceId(newSpeedAlert.getDeviceId());
        speedAlert.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
        if(newSpeedAlert.getActive())
            speedAlert.setActivatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));

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
        movementAlert.setCreatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
        movementAlert.setUpdatedAt(null);
        movementAlert.setActivatedAt(null);
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
    public ResponseDTO<MovementAlerts> getMovementAlertByVehicleID(Long vehicleID) {
        Vehicles vehicle = vehicleDaoExt.fetchOneById(vehicleID);
        return new ResponseDTO<MovementAlerts>(movementAlertDao.fetchOneByDeviceId(vehicle.getDeviceId()), null);
    }

    @Override
    public ResponseDTO<MovementAlerts> updateMovementAlert(Long movementAlertID, MovementAlerts newMovementAlert) {
        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
        MovementAlerts momovementAlert = movementAlertDao.fetchOneById(movementAlertID);
        momovementAlert.setActive(newMovementAlert.getActive());
        momovementAlert.setLat(newMovementAlert.getLat());
        momovementAlert.setLng(newMovementAlert.getLng());
        momovementAlert.setDeviceId(newMovementAlert.getDeviceId());
        momovementAlert.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
        if(newMovementAlert.getActive())
            momovementAlert.setActivatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));

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

    @Override
    public ResponseDTO<SpeedAlertsHistory> createSpeedAlertHistory(SpeedAlertsHistory speedAlertsHistory) {
        ResponseDTO<SpeedAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            speedAlertsHistoryDao.insert(speedAlertsHistory);
            responseDTO.model = speedAlertsHistory;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el speedAlertsHistory %s", speedAlertsHistory.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el speedAlertHistory.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<SpeedAlertsHistory>> getSpeedHistoryByVehicleID(Long vehicleID) {
        Vehicles vehicle = vehicleDaoExt.fetchOneById(vehicleID);
        SpeedAlerts speedAlert = speedAlertsDao.fetchOneByDeviceId(vehicle.getDeviceId());
        return new ResponseDTO(speedAlertsHistoryDao.fetchByAlertId(speedAlert.getId()), null);
    }

    @Override
    public ResponseDTO<SpeedAlertsHistory> deleteSpeedAlertHistory(Long deviceId) {
        ResponseDTO<SpeedAlertsHistory> responseDTO = new ResponseDTO<>();

        SpeedAlerts speedAlert = speedAlertsDao.fetchOneByDeviceId(deviceId);
        speedAlertsHistoryDao.deleteSpeedAlertHistory(speedAlert.getId());

        return responseDTO;
    }

    @Override
    public ResponseDTO<MovementAlertsHistory> createMovementAlertHistory(MovementAlertsHistory movementAlertsHistory) {
        ResponseDTO<MovementAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            movementAlertsHistoryDao.insert(movementAlertsHistory);
            responseDTO.model = movementAlertsHistory;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el movementAlertsHistory %s", movementAlertsHistory.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el movementAlertHistory.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<MovementAlertsHistory>> getMovementHistoryByVehicleID(Long vehicleID) {
        Vehicles vehicle = vehicleDaoExt.fetchOneById(vehicleID);
        MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(vehicle.getDeviceId());
        return new ResponseDTO(movementAlertsHistoryDao.fetchByAlertId(movementAlert.getId()), null);
    }

    @Override
    public ResponseDTO<MovementAlertsHistory> deleteMovementAlertHistory(Long deviceId) {
        ResponseDTO<MovementAlertsHistory> responseDTO = new ResponseDTO<>();

        MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(deviceId);
        movementAlertsHistoryDao.deleteMovementsAlertHistory(movementAlert.getId());

        return responseDTO;
    }
}
