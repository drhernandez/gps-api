package com.tesis.daos;

import com.tesis.jooq.tables.SpeedAlertsHistory;
import com.tesis.jooq.tables.daos.SpeedAlertsHistoryDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;


public class SpeedAlertHistoryDaoExt extends SpeedAlertsHistoryDao{

    @Inject
    public SpeedAlertHistoryDaoExt(Configuration configuration){
        super(configuration);
    }

    public void deleteSpeedAlertHistory(Long alertID){

        DSL.using(configuration()).transaction(t -> {

            t.dsl().delete(SpeedAlertsHistory.SPEED_ALERTS_HISTORY)
                    .where(SpeedAlertsHistory.SPEED_ALERTS_HISTORY.ALERT_ID.eq(alertID))
                    .execute();
        });
    }
}
