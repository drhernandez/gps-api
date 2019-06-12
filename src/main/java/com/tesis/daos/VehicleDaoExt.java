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

            Long deviceID = t.dsl().selectFrom(Vehicles.VEHICLES)
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID)).fetchAny(Vehicles.VEHICLES.DEVICE_ID);

            t.dsl().delete(Trackings.TRACKINGS).where(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID)).execute();
            t.dsl().update(Devices.DEVICES)
                    .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                    .where(Devices.DEVICES.ID.eq(deviceID))
                    .execute();

            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                    .set(Vehicles.VEHICLES.DEVICE_ID, (Long) null)
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID))
                    .execute();
        });
    }
}
