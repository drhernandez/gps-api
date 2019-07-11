package com.tesis.services;

import com.tesis.jooq.tables.pojos.MovementAlerts;
import com.tesis.jooq.tables.pojos.MovementAlertsHistory;
import com.tesis.jooq.tables.pojos.SpeedAlerts;
import com.tesis.jooq.tables.pojos.SpeedAlertsHistory;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface AlertService {
    ResponseDTO<SpeedAlerts> createSpeedAlert(SpeedAlerts speedAlert);
    ResponseDTO<List<SpeedAlerts>> getSpeedAlerts();
    ResponseDTO<SpeedAlerts> getSpeedAlertByVehicleID(Long vehicleID);
    ResponseDTO<SpeedAlerts> updateSpeedAlert(Long speedAlertID, SpeedAlerts newSpeedAlert);
    ResponseDTO<SpeedAlerts> deleteSpeedAlert(Long deviceId);

    ResponseDTO<MovementAlerts> createMovementAlert(MovementAlerts movementAlert);
    ResponseDTO<List<MovementAlerts>> getMovementAlert();
    ResponseDTO<MovementAlerts> getMovementAlertByVehicleID(Long vehicleID);
    ResponseDTO<MovementAlerts> updateMovementAlert(Long movementAlertID, MovementAlerts newMovementAlert);
    ResponseDTO<MovementAlerts> deleteMovementAlert(Long deviceId);

    ResponseDTO<SpeedAlertsHistory> createSpeedAlertHistory(SpeedAlertsHistory speedAlertsHistory);
    ResponseDTO<List<SpeedAlertsHistory>> getSpeedHistoryByVehicleID(Long vehicleID);
    ResponseDTO<SpeedAlertsHistory> deleteSpeedAlertHistory(Long deviceId);

    ResponseDTO<MovementAlertsHistory> createMovementAlertHistory(MovementAlertsHistory speedAlertsHistory);
    ResponseDTO<List<MovementAlertsHistory>> getMovementHistoryByVehicleID(Long vehicleID);
    ResponseDTO<MovementAlertsHistory> deleteMovementAlertHistory(Long deviceId);

}
