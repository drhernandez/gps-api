package com.tesis.daos;

import java.util.List;

import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.daos.UsersDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;

public class UserDaoExt extends  UsersDao{
    @Inject
    public UserDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.Users> findAllActives(){
        return DSL.using(configuration()).selectFrom(Users.USERS).where(Users.USERS.DELETED_AT.isNull()).fetch().map(mapper());
    }
}
