/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.google.inject.Inject;
import com.tesis.jooq.tables.Trackings;
import com.tesis.jooq.tables.records.TrackingsRecord;

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
public class TrackingsDao extends DAOImpl<TrackingsRecord, com.tesis.jooq.tables.pojos.Trackings, Integer> {

    /**
     * Create a new TrackingsDao without any configuration
     */
    public TrackingsDao() {
        super(Trackings.TRACKINGS, com.tesis.jooq.tables.pojos.Trackings.class);
    }

    /**
     * Create a new TrackingsDao with an attached configuration
     */
    @Inject
    public TrackingsDao(Configuration configuration) {
        super(Trackings.TRACKINGS, com.tesis.jooq.tables.pojos.Trackings.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.tesis.jooq.tables.pojos.Trackings object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchById(Integer... values) {
        return fetch(Trackings.TRACKINGS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Trackings fetchOneById(Integer value) {
        return fetchOne(Trackings.TRACKINGS.ID, value);
    }

    /**
     * Fetch records that have <code>device_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByDeviceId(Long... values) {
        return fetch(Trackings.TRACKINGS.DEVICE_ID, values);
    }

    /**
     * Fetch records that have <code>lat IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByLat(Float... values) {
        return fetch(Trackings.TRACKINGS.LAT, values);
    }

    /**
     * Fetch records that have <code>long IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByLong(Float... values) {
        return fetch(Trackings.TRACKINGS.LONG, values);
    }

    /**
     * Fetch records that have <code>sat IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchBySat(Integer... values) {
        return fetch(Trackings.TRACKINGS.SAT, values);
    }

    /**
     * Fetch records that have <code>hdop IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByHdop(Integer... values) {
        return fetch(Trackings.TRACKINGS.HDOP, values);
    }

    /**
     * Fetch records that have <code>time IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByTime(Timestamp... values) {
        return fetch(Trackings.TRACKINGS.TIME, values);
    }
}
