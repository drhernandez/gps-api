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
import java.util.Collections;
import java.util.List;

@Singleton
public class AlertServiceImp implements AlertService {

    Logger logger = LoggerFactory.getLogger(AlertServiceImp.class);

    @Inject
    VehicleDaoExt vehicleDao;
    @Inject
    SpeedAlertDaoExt speedAlertsDao;
    @Inject
    MovementAlertDaoExt movementAlertDao;
    @Inject
    SpeedAlertHistoryDaoExt speedAlertsHistoryDao;
    @Inject
    MovementAlertHistoryDaoExt movementAlertsHistoryDao;


    //  ----------------  Speed Alert methods ----------------

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
        Vehicles vehicle = vehicleDao.fetchOneById(vehicleID);
        ResponseDTO<SpeedAlerts> responseDTO =  new ResponseDTO<>();
        if(vehicle != null) {
            responseDTO.setModel(speedAlertsDao.fetchOneByDeviceId(vehicle.getDeviceId()));
        }
        return responseDTO;
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

    public SpeedAlerts getSpeedIfActive(Long devideId){
        SpeedAlerts speedAlert = speedAlertsDao.fetchOneByDeviceId(devideId);
        return speedAlert.getActive() ? speedAlert : null;
    }

    //  ----------------  Movement Alert methods ----------------

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
        Vehicles vehicle = vehicleDao.fetchOneById(vehicleID);
        ResponseDTO<MovementAlerts> responseDTO =  new ResponseDTO<>();
        if(vehicle != null)
            responseDTO.setModel(movementAlertDao.fetchOneByDeviceId(vehicle.getDeviceId()));
        return responseDTO;
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
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el movementAlert.");
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
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el movementAlert.");
        }
        return responseDTO;
    }


    public MovementAlerts getMovementIfActive(Long devideId){
        MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(devideId);
        return movementAlert.getActive() ? movementAlert : null;
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
        ResponseDTO<List<SpeedAlertsHistory>> responseDTO = new ResponseDTO<>();
        responseDTO.setModel(Collections.emptyList());
        Vehicles vehicle = vehicleDao.fetchOneById(vehicleID);
        if (vehicle != null){
            SpeedAlerts speedAlert = speedAlertsDao.fetchOneByDeviceId(vehicle.getDeviceId());
            if(speedAlert != null)
                responseDTO.setModel(speedAlertsHistoryDao.fetchByAlertId(speedAlert.getId()));
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<SpeedAlertsHistory> deleteSpeedAlertHistory(Long deviceId) {
        ResponseDTO<SpeedAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            SpeedAlerts speedAlert = speedAlertsDao.fetchOneByDeviceId(deviceId);
            speedAlertsHistoryDao.deleteSpeedAlertHistory(speedAlert.getId());
        } catch (Exception e){
            logger.error(String.format("No se pudo eliminar los speedAlertsHistory del device  %s", deviceId));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar los speedAlertHistory.");
        }
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
        ResponseDTO<List<MovementAlertsHistory>> responseDTO = new ResponseDTO<>();
        responseDTO.setModel(Collections.emptyList());
        Vehicles vehicle = vehicleDao.fetchOneById(vehicleID);
        if (vehicle != null){
            MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(vehicle.getDeviceId());
            if (movementAlert != null)
                responseDTO.setModel(movementAlertsHistoryDao.fetchByAlertId(movementAlert.getId()));
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<MovementAlertsHistory> deleteMovementAlertHistory(Long deviceId) {
        ResponseDTO<MovementAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(deviceId);
            movementAlertsHistoryDao.deleteMovementsAlertHistory(movementAlert.getId());
        } catch (Exception e){
            logger.error(String.format("No se pudo eliminar los speedAlertsHistory del device  %s", deviceId));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar los movementAlertHistory.");
        }

        return responseDTO;
    }

    public Long getUserIDByDeviceID(Long deviceID){
        return vehicleDao.fetchOneByDeviceId(deviceID).getUserId();
    }

    public Long getUserIDBySpeedAlertID(Long speedAlertID){
        return getUserIDByDeviceID(speedAlertsDao.fetchOneById(speedAlertID).getDeviceId());
    }

    public Long getUserIDByMovementAlertID(Long movementAlertID){
        return getUserIDByDeviceID(movementAlertDao.fetchOneById(movementAlertID).getDeviceId());
    }
}
