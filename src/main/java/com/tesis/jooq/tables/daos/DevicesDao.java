/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.google.inject.Inject;
import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.records.DevicesRecord;

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
public class DevicesDao extends DAOImpl<DevicesRecord, com.tesis.jooq.tables.pojos.Devices, Long> {

    /**
     * Create a new DevicesDao without any configuration
     */
    public DevicesDao() {
        super(Devices.DEVICES, com.tesis.jooq.tables.pojos.Devices.class);
    }

    /**
     * Create a new DevicesDao with an attached configuration
     */
    @Inject
    public DevicesDao(Configuration configuration) {
        super(Devices.DEVICES, com.tesis.jooq.tables.pojos.Devices.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.tesis.jooq.tables.pojos.Devices object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Devices> fetchById(Long... values) {
        return fetch(Devices.DEVICES.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Devices fetchOneById(Long value) {
        return fetchOne(Devices.DEVICES.ID, value);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Devices> fetchByUserId(Long... values) {
        return fetch(Devices.DEVICES.USER_ID, values);
    }

    /**
     * Fetch records that have <code>vehicle_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Devices> fetchByVehicleId(Long... values) {
        return fetch(Devices.DEVICES.VEHICLE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>vehicle_id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Devices fetchOneByVehicleId(Long value) {
        return fetchOne(Devices.DEVICES.VEHICLE_ID, value);
    }

    /**
     * Fetch records that have <code>model IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Devices> fetchByModel(String... values) {
        return fetch(Devices.DEVICES.MODEL, values);
    }

    /**
     * Fetch records that have <code>software_version IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Devices> fetchBySoftwareVersion(String... values) {
        return fetch(Devices.DEVICES.SOFTWARE_VERSION, values);
    }
}