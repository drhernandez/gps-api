package com.tesis.daos;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.Vehicles;
import com.tesis.jooq.tables.daos.UsersDao;
import org.jooq.Configuration;
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
                List<Long> vehiclesIds = tx.dsl().selectFrom(Devices.DEVICES)
                        .where(Devices.DEVICES.USER_ID.eq(userID)).fetch(Devices.DEVICES.VEHICLE_ID, Long.class);

                int vehiclesUpdateExecution = tx.dsl().update(Vehicles.VEHICLES)
                        .set(Vehicles.VEHICLES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .where(Vehicles.VEHICLES.ID.in(vehiclesIds)).execute();

                int devicesUpdateExecution = tx.dsl().update(Devices.DEVICES)
                        .set(Devices.DEVICES.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .where(Devices.DEVICES.USER_ID.eq(userID)).execute();

                int userUpdateExecution = tx.dsl().update(Users.USERS)
                        .set(Users.USERS.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .where(Users.USERS.ID.eq(userID)).execute();

                logger.info(String.format("Deleted user %s. Execution result: %d", userID, vehiclesUpdateExecution + devicesUpdateExecution + userUpdateExecution));

            } catch (Exception e) {
                logger.error(String.format("[message: Error trying to delete user %s on cascade] [e-message: %s]", userID, e.getMessage()));
                throw e;
            }
        });
    }
}
