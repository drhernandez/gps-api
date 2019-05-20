package com.tesis.daos;

import javax.inject.Inject;

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

public class VehicleDaoExt extends VehiclesDao {

    @Inject
    public VehicleDaoExt(Configuration configuration){
        super(configuration);
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

            List<Long> devicesIds = t.dsl().selectFrom(Devices.DEVICES)
                    .where(Devices.DEVICES.VEHICLE_ID.eq(vehicleID))
                    .fetch(Trackings.TRACKINGS.ID, Long.class);
            t.dsl().delete(Trackings.TRACKINGS)
                    .where(Trackings.TRACKINGS.DEVICE_ID.in(devicesIds))
                    .execute();
            t.dsl().update(Devices.DEVICES)
                    .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                    .where(Devices.DEVICES.VEHICLE_ID.eq(Long.valueOf(vehicleID)))
                    .execute();
            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID))
                    .execute();
        });
    }
}
