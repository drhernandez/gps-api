package com.tesis.daos;

import com.tesis.jooq.tables.Vehicles;
import com.tesis.jooq.tables.daos.DevicesDao;
import com.tesis.jooq.tables.Devices;

import javax.inject.Inject;
import java.sql.Timestamp;
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

    public void deleteVehicle(Long deviceID){
        Integer vehicleID = Math.toIntExact(fetchOneById(deviceID).getVehicleId());

        DSL.using(configuration()).transaction(t -> {
            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now()))
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID))
                    .execute();
            t.dsl().update(Devices.DEVICES)
                   .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now()))
                   .where(Devices.DEVICES.ID.eq(deviceID))
                   .execute();
        });
    }
}
