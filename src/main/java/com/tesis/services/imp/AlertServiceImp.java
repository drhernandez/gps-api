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
    @Inject
    TrackingDaoExt trackingDao;


    //  ----------------  Speed Alert methods ----------------

    @Override
    public ResponseDTO<SpeedAlerts> createSpeedAlert(SpeedAlerts speedAlert) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();

        try {
            speedAlert.setCreatedAt(LocalDateTime.now(Clock.systemUTC()));
            speedAlert.setUpdatedAt(null);
            speedAlert.setActivatedAt(null);
            speedAlert.setLastFired(null);
            speedAlertsDao.insert(speedAlert);
            responseDTO.model = speedAlert;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar el speedAlert {}] [error: {}]", speedAlert.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el speedAlert.", e);
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
        if(!speedAlert.getActive() && newSpeedAlert.getActive()) {
            speedAlert.setActive(newSpeedAlert.getActive());
            speedAlert.setActivatedAt(LocalDateTime.now(Clock.systemUTC()));
        }
        speedAlert.setSpeed(newSpeedAlert.getSpeed());
        speedAlert.setDeviceId(newSpeedAlert.getDeviceId());
        speedAlert.setUpdatedAt(LocalDateTime.now(Clock.systemUTC()));
        speedAlert.setLastFired(newSpeedAlert.getLastFired());


        try {
            speedAlertsDao.update(speedAlert);
            responseDTO.model = speedAlert;
        } catch (Exception e){
            logger.error("[message: No se pudo modificar el speedAlert {}] [error: {}]", speedAlert.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el speedAlert.", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<SpeedAlerts> deleteSpeedAlert(Long deviceID) {
        ResponseDTO<SpeedAlerts> responseDTO = new ResponseDTO<>();
        try {
            speedAlertsDao.deleteSpeedAlert(deviceID);
        }catch (Exception e) {
            logger.error("[message: No se pudo eliminar el speedAlert {}] [error: {}]", deviceID, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el speedAlert.", e);
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
        movementAlert.setCreatedAt(LocalDateTime.now(Clock.systemUTC()));
        movementAlert.setUpdatedAt(null);
        movementAlert.setActivatedAt(null);
        movementAlert.setLastFired(null);
        try {
            movementAlertDao.insert(movementAlert);
            responseDTO.model = movementAlert;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar el movementAlert {}] [error: {}]", movementAlert.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el movementAlert.", e);
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
        MovementAlerts movementAlert = movementAlertDao.fetchOneById(movementAlertID);

        if(!movementAlert.getActive() && newMovementAlert.getActive()) {
            Trackings location = trackingDao.findLocationByDeviceID(movementAlert.getDeviceId());
            movementAlert.setActive(newMovementAlert.getActive());
            movementAlert.setLat(location.getLat());
            movementAlert.setLng(location.getLng());
            movementAlert.setActivatedAt(LocalDateTime.now(Clock.systemUTC()));
        }
        movementAlert.setDeviceId(newMovementAlert.getDeviceId());
        movementAlert.setUpdatedAt(LocalDateTime.now(Clock.systemUTC()));
        movementAlert.setLastFired(newMovementAlert.getLastFired());

        try {
            movementAlertDao.update(movementAlert);
            responseDTO.model = movementAlert;
        } catch (Exception e){
            logger.error("[message: No se pudo modificar el movementAlert {}] [error: {}]", movementAlert.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el movementAlert.", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<MovementAlerts> deleteMovementAlert(Long deviceId) {
        ResponseDTO<MovementAlerts> responseDTO = new ResponseDTO<>();
        try {
            movementAlertDao.deleteMovementAlert(deviceId);
        }catch (Exception e) {
            logger.error("[message: No se pudo eliminar el speedAlert {}] [error: {}]", deviceId, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el movementAlert.", e);
        }
        return responseDTO;
    }


    public MovementAlerts getMovementIfActive(Long deviceID){
        MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(deviceID);
        return movementAlert.getActive() ? movementAlert : null;
    }

    @Override
    public ResponseDTO<SpeedAlertsHistory> createSpeedAlertHistory(SpeedAlertsHistory speedAlertsHistory) {
        ResponseDTO<SpeedAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            speedAlertsHistoryDao.insert(speedAlertsHistory);
            responseDTO.model = speedAlertsHistory;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar el speedAlertsHistory {}] [error: {}]", speedAlertsHistory.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el speedAlertHistory.", e);
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
    public ResponseDTO<SpeedAlertsHistory> deleteSpeedAlertHistory(Long deviceID) {
        ResponseDTO<SpeedAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            SpeedAlerts speedAlert = speedAlertsDao.fetchOneByDeviceId(deviceID);
            speedAlertsHistoryDao.deleteSpeedAlertHistory(speedAlert.getId());
        } catch (Exception e){
            logger.error("[message: No se pudo eliminar los speedAlertsHistory del device {}] [error: {}]", deviceID, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar los speedAlertHistory.", e);
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
            logger.error("[message: No se pudo guardar el movementAlertsHistory {}] [error: {}]", movementAlertsHistory.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el movementAlertHistory.", e);
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
    public ResponseDTO<MovementAlertsHistory> deleteMovementAlertHistory(Long deviceID) {
        ResponseDTO<MovementAlertsHistory> responseDTO = new ResponseDTO<>();

        try {
            MovementAlerts movementAlert = movementAlertDao.fetchOneByDeviceId(deviceID);
            movementAlertsHistoryDao.deleteMovementsAlertHistory(movementAlert.getId());
        } catch (Exception e){
            logger.error("[message: No se pudo eliminar los speedAlertsHistory del device {}] [error: {}]", deviceID, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar los movementAlertHistory.", e);
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
