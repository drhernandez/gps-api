package com.tesis.daos;

import com.tesis.jooq.tables.AdminRecoveryTokens;
import com.tesis.jooq.tables.AdminUsers;
import com.tesis.jooq.tables.daos.AdminRecoveryTokensDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class AdminRecoveryTokensDaoExt extends AdminRecoveryTokensDao {

    Logger logger = LoggerFactory.getLogger(AccessTokenDaoExt.class);

    @Inject
    public AdminRecoveryTokensDaoExt(Configuration configuration) {
        super(configuration);
    }

    public void recoveryAdminUserPassword(com.tesis.jooq.tables.pojos.AdminUsers adminUser){
        DSL.using(configuration()).transaction( t -> {
            t.dsl().delete(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS)
                    .where(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS.USER_ID.eq(adminUser.getId()))
                    .execute();

            t.dsl().update(AdminUsers.ADMIN_USERS)
                    .set(AdminUsers.ADMIN_USERS.PASSWORD, adminUser.getPassword())
                    .where(AdminUsers.ADMIN_USERS.ID.eq(adminUser.getId()))
                    .execute();
        });
    }
}
