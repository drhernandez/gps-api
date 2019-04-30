package com.tesis.daos;

import com.tesis.jooq.tables.daos.VehiclesDao;
import com.tesis.jooq.tables.Vehicles;
import java.util.List;
import org.jooq.impl.DSL;

public class VehicleDaoExt extends VehiclesDao {
    public List<com.tesis.jooq.tables.pojos.Vehicles> findAllActives(){
        return DSL
                .using(configuration())
                .selectFrom(Vehicles.VEHICLES)
                .where(Vehicles.VEHICLES.DELETED_AT.isNull())
                .fetch()
                .map(mapper());
    }
}
