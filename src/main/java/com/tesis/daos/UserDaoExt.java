package com.tesis.daos;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import com.tesis.jooq.tables.*;
import com.tesis.jooq.tables.daos.UsersDao;
import com.tesis.models.Pagination;
import com.tesis.utils.filters.UserFilters;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.User;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class UserDaoExt extends  UsersDao{

    Logger logger = LoggerFactory.getLogger(UserDaoExt.class);

    @Inject
    public UserDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.Users> findAllActives(){
        return DSL.using(configuration()).selectFrom(Users.USERS).where(Users.USERS.DELETED_AT.isNull()).fetch().map(mapper());
    }

    public void deleteUserCascade(Long userID) {

        DSL.using(configuration()).transaction(tx -> {

            try {
                List<Long> vehiclesIds = tx.dsl().selectFrom(Vehicles.VEHICLES)
                        .where(Vehicles.VEHICLES.USER_ID.eq(userID)).fetch(Vehicles.VEHICLES.ID, Long.class);

                List<Long> devicesIds = tx.dsl().selectFrom(Vehicles.VEHICLES)
                        .where(Vehicles.VEHICLES.USER_ID.eq(userID)).fetch(Vehicles.VEHICLES.DEVICE_ID, Long.class);

                List<Long> speedAlertIds = tx.dsl().selectFrom(SpeedAlerts.SPEED_ALERTS)
                        .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.in(devicesIds)).fetch(SpeedAlerts.SPEED_ALERTS.ID, Long.class);
                List<Long> movementAlertIds = tx.dsl().selectFrom(MovementAlerts.MOVEMENT_ALERTS)
                        .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.in(devicesIds)).fetch(MovementAlerts.MOVEMENT_ALERTS.ID, Long.class);

                tx.dsl().delete(Trackings.TRACKINGS)
                        .where(Trackings.TRACKINGS.DEVICE_ID.in(devicesIds))
                        .execute();

                tx.dsl().delete(SpeedAlertsHistory.SPEED_ALERTS_HISTORY)
                        .where(SpeedAlertsHistory.SPEED_ALERTS_HISTORY.ALERT_ID.in(speedAlertIds)).execute();
                tx.dsl().delete(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY)
                        .where(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.ALERT_ID.in(movementAlertIds)).execute();

                tx.dsl().update(SpeedAlerts.SPEED_ALERTS)
                        .set(SpeedAlerts.SPEED_ALERTS.ACTIVE, false)
                        .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.in(devicesIds)).execute();
                tx.dsl().update(MovementAlerts.MOVEMENT_ALERTS)
                        .set(MovementAlerts.MOVEMENT_ALERTS.ACTIVE, false)
                        .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.in(devicesIds)).execute();

                tx.dsl().update(Devices.DEVICES)
                        .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .where(Devices.DEVICES.ID.in(vehiclesIds)).execute();

                tx.dsl().update(Vehicles.VEHICLES)
                        .set(Vehicles.VEHICLES.DEVICE_ID, (Long) null)
                        .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .where(Vehicles.VEHICLES.ID.in(vehiclesIds)).execute();

                tx.dsl().update(Users.USERS)
                        .set(Users.USERS.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .where(Users.USERS.ID.eq(userID)).execute();

            } catch (Exception e) {
                logger.error(String.format("[message: Error trying to delete user %s on cascade] [e-message: %s]", userID, e.getMessage()));
                throw e;
            }
        });
    }

    public List<com.tesis.jooq.tables.pojos.Users> findByFilters(UserFilters filters, Pagination pagination){

        Condition searchCondition = DSL.trueCondition();

        if (filters.getEmail() != null)
            searchCondition = searchCondition.and(Users.USERS.EMAIL.like("%" + filters.getEmail() + "%"));
        if (filters.getName() != null)
            searchCondition = searchCondition.and(Users.USERS.NAME.like("%" + filters.getName() + "%"));
        if (filters.getLast_name() != null)
            searchCondition = searchCondition.and(Users.USERS.LAST_NAME.like("%" + filters.getLast_name() + "%"));
        if (filters.getDni() != null)
            searchCondition = searchCondition.and(Users.USERS.DNI.like("%" + filters.getDni() + "%"));


        int count = DSL.using(configuration())
                .fetchCount(DSL.selectFrom(Users.USERS).where(searchCondition).and(Users.USERS.DELETED_AT.isNull()));

        pagination.setTotal(count);

        return DSL.using(configuration())
                .selectFrom(Users.USERS)
                .where(searchCondition)
                .and(Users.USERS.DELETED_AT.isNull())
                .limit(pagination.getLimit())
                .offset((pagination.getPage()-1)*pagination.getLimit())
                .fetch()
                .map(mapper());
    }
}
