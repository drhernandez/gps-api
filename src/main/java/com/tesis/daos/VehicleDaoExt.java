package com.tesis.daos;

import javax.inject.Inject;

import com.tesis.jooq.tables.*;
import org.jooq.Configuration;
import com.tesis.jooq.tables.daos.VehiclesDao;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.jooq.impl.DSL;

import static com.tesis.config.Constants.DEFAULT_SPEED_ALERT;

public class VehicleDaoExt extends VehiclesDao {

    @Inject
    public VehicleDaoExt(Configuration configuration){
        super(configuration);
    }

    public com.tesis.jooq.tables.pojos.Vehicles createVehicle(com.tesis.jooq.tables.pojos.Vehicles vehicle){
        DSL.using(configuration()).transaction(t -> {
            t.dsl().insertInto(Vehicles.VEHICLES,
                        Vehicles.VEHICLES.DELETED_AT,
                        Vehicles.VEHICLES.LAST_UPDATED,
                        Vehicles.VEHICLES.USER_ID,
                        Vehicles.VEHICLES.DEVICE_ID,
                        Vehicles.VEHICLES.TYPE,
                        Vehicles.VEHICLES.PLATE,
                        Vehicles.VEHICLES.MODEL)
                    .values(null,
                            null,
                            vehicle.getUserId(),
                            vehicle.getDeviceId(),
                            vehicle.getType(),
                            vehicle.getPlate(),
                            vehicle.getModel())
                    .execute();
        });

        return vehicle;
    }

    public List<com.tesis.jooq.tables.pojos.Vehicles> findAllActives(){
        return DSL
                .using(configuration())
                .selectFrom(Vehicles.VEHICLES)
                .where(Vehicles.VEHICLES.DELETED_AT.isNull())
                .fetch()
                .map(mapper());
    }

    public void deleteVehicle(Long vehicleID){
        DSL.using(configuration()).transaction(t -> {

            Long deviceID = t.dsl().selectFrom(Vehicles.VEHICLES)
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID)).fetchAny(Vehicles.VEHICLES.DEVICE_ID);

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

            t.dsl().delete(Trackings.TRACKINGS).where(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID)).execute();
            t.dsl().update(Devices.DEVICES)
                    .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                    .where(Devices.DEVICES.ID.eq(deviceID))
                    .execute();

            t.dsl().update(SpeedAlerts.SPEED_ALERTS)
                    .set(SpeedAlerts.SPEED_ALERTS.ACTIVE, false)
                    .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.eq(deviceID))
                    .execute();

            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                    .set(Vehicles.VEHICLES.DEVICE_ID, (Long) null)
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID))
                    .execute();
        });
    }
}
