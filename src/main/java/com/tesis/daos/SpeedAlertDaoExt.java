package com.tesis.daos;

import com.tesis.jooq.tables.SpeedAlerts;
import com.tesis.jooq.tables.daos.SpeedAlertsDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.util.List;


public class SpeedAlertDaoExt extends  SpeedAlertsDao{

    @Inject
    public SpeedAlertDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.SpeedAlerts> findAllActives() {
        return DSL
                .using(configuration())
                .selectFrom(SpeedAlerts.SPEED_ALERTS)
                .where(SpeedAlerts.SPEED_ALERTS.ACTIVE)
                .fetch()
                .map(mapper());
    }

    public void deleteSpeedAlert(Long deviceID){

        DSL.using(configuration()).transaction(t -> {

            t.dsl().update(SpeedAlerts.SPEED_ALERTS)
                   .set(SpeedAlerts.SPEED_ALERTS.ACTIVE, false)
                   .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.eq(deviceID))
                   .execute();
        });
    }
}
