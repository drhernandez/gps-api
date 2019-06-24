package com.tesis.daos;

import javax.inject.Inject;

import com.tesis.jooq.tables.SpeedAlerts;
import com.tesis.jooq.tables.Trackings;
import org.jooq.Configuration;
import com.tesis.jooq.tables.daos.VehiclesDao;
import com.tesis.jooq.tables.Vehicles;
import com.tesis.jooq.tables.Devices;

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

            t.dsl().insertInto(SpeedAlerts.SPEED_ALERTS,
                        SpeedAlerts.SPEED_ALERTS.ACTIVE,
                        SpeedAlerts.SPEED_ALERTS.DEVICE_ID,
                        SpeedAlerts.SPEED_ALERTS.SPEED)
                    .values(
                            false,
                            vehicle.getDeviceId(),
                            DEFAULT_SPEED_ALERT)
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
