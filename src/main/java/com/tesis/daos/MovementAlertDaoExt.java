package com.tesis.daos;

import com.tesis.jooq.tables.MovementAlerts;
import com.tesis.jooq.tables.MovementAlertsHistory;
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

            Long movementAlertID = t.dsl().selectFrom(MovementAlerts.MOVEMENT_ALERTS)
                    .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                    .fetchAny(MovementAlerts.MOVEMENT_ALERTS.ID);

            t.dsl().delete(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY)
                    .where(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.ALERT_ID.eq(movementAlertID))
                    .execute();

            t.dsl().update(MovementAlerts.MOVEMENT_ALERTS)
                   .set(MovementAlerts.MOVEMENT_ALERTS.ACTIVE, false)
                   .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                   .execute();
        });
    }
}
