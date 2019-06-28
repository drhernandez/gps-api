package com.tesis.daos;

import com.tesis.jooq.tables.*;
import com.tesis.jooq.tables.daos.DevicesDao;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DSL;


public class DeviceDaoExt extends  DevicesDao{

    @Inject
    public DeviceDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.Devices> findAllActives() {
        return DSL
                .using(configuration())
                .selectFrom(Devices.DEVICES)
                .where(Devices.DEVICES.DELETED_AT.isNull())
                .fetch()
                .map(mapper());
    }

    public void deleteDevice(Long deviceID){

        DSL.using(configuration()).transaction(t -> {

            Long speedAlertID = t.dsl().selectFrom(SpeedAlerts.SPEED_ALERTS)
                    .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.eq(deviceID))
                    .fetchAny(SpeedAlerts.SPEED_ALERTS.ID);

            t.dsl().delete(SpeedAlertsHistory.SPEED_ALERTS_HISTORY)
                    .where(SpeedAlertsHistory.SPEED_ALERTS_HISTORY.ALERT_ID.eq(speedAlertID))
                    .execute();

            Long movementAlertID = t.dsl().selectFrom(MovementAlerts.MOVEMENT_ALERTS)
                    .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                    .fetchAny(MovementAlerts.MOVEMENT_ALERTS.ID);

            t.dsl().delete(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY)
                    .where(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.ALERT_ID.eq(movementAlertID))
                    .execute();

            t.dsl().delete(Trackings.TRACKINGS)
                    .where(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID))
                    .execute();
            t.dsl().update(SpeedAlerts.SPEED_ALERTS)
                    .set(SpeedAlerts.SPEED_ALERTS.ACTIVE, false)
                    .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.eq(deviceID))
                    .execute();
            t.dsl().update(MovementAlerts.MOVEMENT_ALERTS)
                    .set(MovementAlerts.MOVEMENT_ALERTS.ACTIVE, false)
                    .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                    .execute();
            t.dsl().update(Devices.DEVICES)
                   .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                   .where(Devices.DEVICES.ID.eq(deviceID))
                   .execute();
            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DEVICE_ID, (Long) null)
                    .where(Vehicles.VEHICLES.DEVICE_ID.eq(deviceID))
                    .execute();
        });
    }
}
