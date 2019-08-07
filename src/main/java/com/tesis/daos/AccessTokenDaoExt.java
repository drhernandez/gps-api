package com.tesis.daos;

import com.tesis.jooq.tables.daos.AccessTokensDao;
import org.jooq.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class AccessTokenDaoExt extends AccessTokensDao{

    Logger logger = LoggerFactory.getLogger(AccessTokenDaoExt.class);

    @Inject
    public AccessTokenDaoExt(Configuration configuration){
        super(configuration);
    }

}
