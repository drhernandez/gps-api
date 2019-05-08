package com.tesis.daos;

import javax.inject.Inject;

import org.jooq.Configuration;
import com.tesis.jooq.tables.daos.VehiclesDao;
import com.tesis.jooq.tables.Vehicles;
import com.tesis.jooq.tables.Devices;

import java.sql.Timestamp;
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

    public void deleteVehicle(Integer vehicleID){
        DSL.using(configuration()).transaction(t1 -> {
            t1.dsl().update(Devices.DEVICES)
                    .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now()))
                    .where(Devices.DEVICES.VEHICLE_ID.eq(Long.valueOf(vehicleID)))
                    .execute();
            t1.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now()))
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID))
                    .execute();
        });
    }
}
