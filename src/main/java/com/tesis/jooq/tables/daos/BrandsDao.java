/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.tesis.jooq.tables.Brands;
import com.tesis.jooq.tables.records.BrandsRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BrandsDao extends DAOImpl<BrandsRecord, com.tesis.jooq.tables.pojos.Brands, Long> {

    /**
     * Create a new BrandsDao without any configuration
     */
    public BrandsDao() {
        super(Brands.BRANDS, com.tesis.jooq.tables.pojos.Brands.class);
    }

    /**
     * Create a new BrandsDao with an attached configuration
     */
    public BrandsDao(Configuration configuration) {
        super(Brands.BRANDS, com.tesis.jooq.tables.pojos.Brands.class, configuration);
    }

    @Override
    public Long getId(com.tesis.jooq.tables.pojos.Brands object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Brands> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Brands.BRANDS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Brands> fetchById(Long... values) {
        return fetch(Brands.BRANDS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Brands fetchOneById(Long value) {
        return fetchOne(Brands.BRANDS.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Brands> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Brands.BRANDS.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Brands> fetchByName(String... values) {
        return fetch(Brands.BRANDS.NAME, values);
    }
}
