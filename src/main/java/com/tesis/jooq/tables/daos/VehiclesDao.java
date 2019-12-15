/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.tesis.jooq.tables.Vehicles;
import com.tesis.jooq.tables.records.VehiclesRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VehiclesDao extends DAOImpl<VehiclesRecord, com.tesis.jooq.tables.pojos.Vehicles, Long> {

    /**
     * Create a new VehiclesDao without any configuration
     */
    public VehiclesDao() {
        super(Vehicles.VEHICLES, com.tesis.jooq.tables.pojos.Vehicles.class);
    }

    /**
     * Create a new VehiclesDao with an attached configuration
     */
    public VehiclesDao(Configuration configuration) {
        super(Vehicles.VEHICLES, com.tesis.jooq.tables.pojos.Vehicles.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.tesis.jooq.tables.pojos.Vehicles object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchById(Long... values) {
        return fetch(Vehicles.VEHICLES.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Vehicles fetchOneById(Long value) {
        return fetchOne(Vehicles.VEHICLES.ID, value);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByStatus(String... values) {
        return fetch(Vehicles.VEHICLES.STATUS, values);
    }

    /**
     * Fetch records that have <code>deleted_at IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByDeletedAt(Timestamp... values) {
        return fetch(Vehicles.VEHICLES.DELETED_AT, values);
    }

    /**
     * Fetch records that have <code>last_updated IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByLastUpdated(Timestamp... values) {
        return fetch(Vehicles.VEHICLES.LAST_UPDATED, values);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles>   fetchByUserId(Long... values) {
        return fetch(Vehicles.VEHICLES.USER_ID, values);
    }

    /**
     * Fetch records that have <code>device_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByDeviceId(Long... values) {
        return fetch(Vehicles.VEHICLES.DEVICE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>device_id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Vehicles fetchOneByDeviceId(Long value) {
        return fetchOne(Vehicles.VEHICLES.DEVICE_ID, value);
    }

    /**
     * Fetch records that have <code>plate IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByPlate(String... values) {
        return fetch(Vehicles.VEHICLES.PLATE, values);
    }

    /**
     * Fetch records that have <code>brand IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByBrand(String... values) {
        return fetch(Vehicles.VEHICLES.BRAND, values);
    }

    /**
     * Fetch records that have <code>brand_line IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Vehicles> fetchByBrandLine(String... values) {
        return fetch(Vehicles.VEHICLES.BRAND_LINE, values);
    }
}
