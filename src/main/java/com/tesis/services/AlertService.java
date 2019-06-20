package com.tesis.services;

import com.tesis.jooq.tables.pojos.SpeedAlerts;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface AlertService {
    ResponseDTO<SpeedAlerts> createSpeedAlert(SpeedAlerts speedAlert);
    ResponseDTO<List<SpeedAlerts>> getSpeedAlerts();
    ResponseDTO<SpeedAlerts> getDeciveBySpeedAlertID(Long speedAlertID);
    ResponseDTO<SpeedAlerts> updateSpeedAlert(Long speedAlertID, SpeedAlerts newSpeedAlert);
    ResponseDTO<SpeedAlerts> deleteSpeedAlert(Long deviceId);
}
