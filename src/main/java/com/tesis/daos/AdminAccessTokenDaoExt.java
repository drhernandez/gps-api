package com.tesis.daos;

import com.tesis.jooq.tables.daos.AdminAccessTokensDao;
import org.jooq.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class AdminAccessTokenDaoExt extends AdminAccessTokensDao {

    Logger logger = LoggerFactory.getLogger(AdminAccessTokenDaoExt.class);

    @Inject
    public AdminAccessTokenDaoExt(Configuration configuration){
        super(configuration);
    }

}
