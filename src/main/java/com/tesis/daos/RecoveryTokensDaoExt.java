package com.tesis.daos;

import com.tesis.jooq.tables.RecoveryTokens;
import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.daos.RecoveryTokensDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class RecoveryTokensDaoExt extends RecoveryTokensDao {

    Logger logger = LoggerFactory.getLogger(AccessTokenDaoExt.class);

    @Inject
    public RecoveryTokensDaoExt(Configuration configuration) {
        super(configuration);
    }

    public void recoveryUserPassword(com.tesis.jooq.tables.pojos.Users user){
        DSL.using(configuration()).transaction( t -> {
            t.dsl().delete(RecoveryTokens.RECOVERY_TOKENS)
                    .where(RecoveryTokens.RECOVERY_TOKENS.USER_ID.eq(user.getId()))
                    .execute();

            t.dsl().update(Users.USERS)
                    .set(Users.USERS.PASSWORD, user.getPassword())
                    .where(Users.USERS.ID.eq(user.getId()))
                    .execute();
        });
    }
}
