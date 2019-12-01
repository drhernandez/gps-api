package com.tesis.daos;

import com.tesis.enums.Status;
import com.tesis.jooq.tables.*;
import com.tesis.jooq.tables.daos.AdminUsersDao;
import com.tesis.models.Pagination;
import com.tesis.utils.filters.UserFilters;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class AdminUserDaoExt extends AdminUsersDao{

    Logger logger = LoggerFactory.getLogger(AdminUserDaoExt.class);

    @Inject
    public AdminUserDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.AdminUsers> findAllActives(){
        return DSL.using(configuration())
                .selectFrom(AdminUsers.ADMIN_USERS)
                .where(AdminUsers.ADMIN_USERS.DELETED_AT.isNull())
//                .and(AdminUsers.ADMIN_USERS.STATUS.eq(Status.ACTIVE.toString()))
                .fetch()
                .map(mapper());
    }

    public void deleteAdminUser(Long adminUserID) {
        DSL.using(configuration()).transaction(tx -> {
            try {
                tx.dsl().update(AdminUsers.ADMIN_USERS)
                        .set(AdminUsers.ADMIN_USERS.DELETED_AT, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))
                        .set(AdminUsers.ADMIN_USERS.STATUS, Status.DELETED.toString())
                        .where(AdminUsers.ADMIN_USERS.ID.eq(adminUserID)).execute();

            } catch (Exception e) {
                logger.error(String.format("[message: Error trying to delete admin user %s] [e-message: %s]", adminUserID, e.getMessage()));
                throw e;
            }
        });
    }

    public List<com.tesis.jooq.tables.pojos.AdminUsers> findByFilters(UserFilters filters, Pagination pagination){

        Condition searchCondition = DSL.trueCondition();

        if (filters.getStatus() != null)
            searchCondition = searchCondition.and(AdminUsers.ADMIN_USERS.STATUS.like(filters.getStatus()));
        if (filters.getEmail() != null)
            searchCondition = searchCondition.and(AdminUsers.ADMIN_USERS.EMAIL.like("%" + filters.getEmail() + "%"));
        if (filters.getName() != null)
            searchCondition = searchCondition.and(AdminUsers.ADMIN_USERS.NAME.like("%" + filters.getName() + "%"));
        if (filters.getLast_name() != null)
            searchCondition = searchCondition.and(AdminUsers.ADMIN_USERS.LAST_NAME.like("%" + filters.getLast_name() + "%"));
        if (filters.getDni() != null)
            searchCondition = searchCondition.and(AdminUsers.ADMIN_USERS.DNI.like("%" + filters.getDni() + "%"));


        int count = DSL.using(configuration())
                .fetchCount(DSL.selectFrom(AdminUsers.ADMIN_USERS)
                        .where(searchCondition)
                        .and(AdminUsers.ADMIN_USERS.DELETED_AT.isNull()));

        pagination.setTotal(count);

        return DSL.using(configuration())
                .selectFrom(AdminUsers.ADMIN_USERS)
                .where(searchCondition)
                .and(AdminUsers.ADMIN_USERS.DELETED_AT.isNull())
                .limit(pagination.getLimit())
                .offset((pagination.getPage()-1)*pagination.getLimit())
                .fetch()
                .map(mapper());
    }
}
