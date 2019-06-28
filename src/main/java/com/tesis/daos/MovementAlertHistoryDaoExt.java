package com.tesis.daos;

import com.tesis.jooq.tables.MovementAlertsHistory;
import com.tesis.jooq.tables.daos.MovementAlertsHistoryDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;


public class MovementAlertHistoryDaoExt extends MovementAlertsHistoryDao {

    @Inject
    public MovementAlertHistoryDaoExt(Configuration configuration){
        super(configuration);
    }

    public void deleteMovementsAlertHistory(Long alertID){

        DSL.using(configuration()).transaction(t -> {

            t.dsl().delete(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY)
                    .where(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.ALERT_ID.eq(alertID))
                    .execute();
        });
    }
}
