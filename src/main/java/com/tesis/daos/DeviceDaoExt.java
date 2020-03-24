package com.tesis.daos;

import com.tesis.exceptions.DataException;
import com.tesis.jooq.tables.*;
import com.tesis.jooq.tables.daos.DevicesDao;

import javax.inject.Inject;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import com.tesis.jooq.tables.records.DevicesRecord;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import static com.tesis.config.Constants.DEFAULT_SPEED_ALERT;


public class DeviceDaoExt extends DevicesDao{

    @Inject
    public DeviceDaoExt(Configuration configuration){
        super(configuration);
    }

    public void insertDevice(com.tesis.jooq.tables.pojos.Devices device) throws DataException{
        List<com.tesis.jooq.tables.pojos.Devices> devicesList = DSL.using(configuration())
                .selectFrom(Devices.DEVICES)
                .where(Devices.DEVICES.PHYSICAL_ID.eq(device.getPhysicalId()))
                .and(Devices.DEVICES.DELETED_AT.isNull())
                .fetch()
                .map(mapper());

        if (devicesList.size() == 0)
            DSL.using(configuration()).transaction(t -> {

                DevicesRecord deviceRecord = t.dsl().insertInto(Devices.DEVICES,
                        Devices.DEVICES.PHYSICAL_ID,
                        Devices.DEVICES.DELETED_AT,
                        Devices.DEVICES.LAST_UPDATED,
                        Devices.DEVICES.MODEL,
                        Devices.DEVICES.SOFTWARE_VERSION)
                        .values(device.getPhysicalId(),
                                null,
                                null,
                                device.getModel(),
                                device.getSoftwareVersion())
                        .returning(Devices.DEVICES.ID)
                        .fetchOne();

                t.dsl().insertInto(SpeedAlerts.SPEED_ALERTS,
                        SpeedAlerts.SPEED_ALERTS.ACTIVE,
                        SpeedAlerts.SPEED_ALERTS.DEVICE_ID,
                        SpeedAlerts.SPEED_ALERTS.SPEED,
                        SpeedAlerts.SPEED_ALERTS.CREATED_AT,
                        SpeedAlerts.SPEED_ALERTS.UPDATED_AT,
                        SpeedAlerts.SPEED_ALERTS.ACTIVATED_AT)
                        .values(
                                false,
                                deviceRecord.getValue(Devices.DEVICES.ID),
                                DEFAULT_SPEED_ALERT,
                                LocalDateTime.now(Clock.systemUTC()),
                                null,
                                null)
                        .execute();

                t.dsl().insertInto(MovementAlerts.MOVEMENT_ALERTS,
                        MovementAlerts.MOVEMENT_ALERTS.ACTIVE,
                        MovementAlerts.MOVEMENT_ALERTS.LAT,
                        MovementAlerts.MOVEMENT_ALERTS.LNG,
                        MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID,
                        MovementAlerts.MOVEMENT_ALERTS.CREATED_AT,
                        MovementAlerts.MOVEMENT_ALERTS.UPDATED_AT,
                        MovementAlerts.MOVEMENT_ALERTS.ACTIVATED_AT)
                        .values(false,
                                null,
                                null,
                                deviceRecord.getValue(Devices.DEVICES.ID),
                                LocalDateTime.now(Clock.systemUTC()),
                                null,
                                null)
                        .execute();
            });

        else
            throw new DataException(String.format("Error al insertar device %s", device.toString()),
                    String.format("Id fisico %s en uso", device.getPhysicalId().toString()));
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
                   .set(Devices.DEVICES.DELETED_AT, LocalDateTime.now(Clock.systemUTC()))
                   .where(Devices.DEVICES.ID.eq(deviceID))
                   .execute();
            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DEVICE_ID, (Long) null)
                    .where(Vehicles.VEHICLES.DEVICE_ID.eq(deviceID))
                    .execute();
        });
    }

    public com.tesis.jooq.tables.pojos.Devices getDeviceByPhysicalID(Long physicalID){
        return DSL
                .using(configuration())
                .selectFrom(Devices.DEVICES)
                .where(Devices.DEVICES.PHYSICAL_ID.eq(physicalID).and(Devices.DEVICES.DELETED_AT.isNull()))
                .fetchOneInto(com.tesis.jooq.tables.pojos.Devices.class);
    }
}
