package com.tesis.daos;

import com.tesis.jooq.tables.daos.BrandsDao;
import org.jooq.Configuration;

import javax.inject.Inject;


public class BrandDaoExt extends BrandsDao {

    @Inject
    public BrandDaoExt(Configuration configuration){
        super(configuration);
    }

}
