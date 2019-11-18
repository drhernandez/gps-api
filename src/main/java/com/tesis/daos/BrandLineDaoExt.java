package com.tesis.daos;

import com.tesis.jooq.tables.daos.BrandLinesDao;
import org.jooq.Configuration;

import javax.inject.Inject;


public class BrandLineDaoExt extends BrandLinesDao {

    @Inject
    public BrandLineDaoExt(Configuration configuration){
        super(configuration);
    }

}
