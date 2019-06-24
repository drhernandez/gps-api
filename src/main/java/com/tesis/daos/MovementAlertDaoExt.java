package com.tesis.daos;

import com.tesis.jooq.tables.MovementAlerts;
import com.tesis.jooq.tables.daos.MovementAlertsDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.util.List;


public class MovementAlertDaoExt extends MovementAlertsDao {

    @Inject
    public MovementAlertDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.MovementAlerts> findAllActives() {
        return DSL
                .using(configuration())
                .selectFrom(MovementAlerts.MOVEMENT_ALERTS)
                .where(MovementAlerts.MOVEMENT_ALERTS.ACTIVE)
                .fetch().map(mapper());
    }

    public void deleteMovementAlert(Long deviceID){

        DSL.using(configuration()).transaction(t -> {

            t.dsl().update(MovementAlerts.MOVEMENT_ALERTS)
                   .set(MovementAlerts.MOVEMENT_ALERTS.ACTIVE, false)
                   .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                   .execute();
        });
    }
}
