/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.tesis.jooq.tables.Trackings;
import com.tesis.jooq.tables.records.TrackingsRecord;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TrackingsDao extends DAOImpl<TrackingsRecord, com.tesis.jooq.tables.pojos.Trackings, Long> {

    /**
     * Create a new TrackingsDao without any configuration
     */
    public TrackingsDao() {
        super(Trackings.TRACKINGS, com.tesis.jooq.tables.pojos.Trackings.class);
    }

    /**
     * Create a new TrackingsDao with an attached configuration
     */
    public TrackingsDao(Configuration configuration) {
        super(Trackings.TRACKINGS, com.tesis.jooq.tables.pojos.Trackings.class, configuration);
    }

    @Override
    public Long getId(com.tesis.jooq.tables.pojos.Trackings object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchById(Long... values) {
        return fetch(Trackings.TRACKINGS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Trackings fetchOneById(Long value) {
        return fetchOne(Trackings.TRACKINGS.ID, value);
    }

    /**
     * Fetch records that have <code>device_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfDeviceId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.DEVICE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>device_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByDeviceId(Long... values) {
        return fetch(Trackings.TRACKINGS.DEVICE_ID, values);
    }

    /**
     * Fetch records that have <code>lat BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfLat(Float lowerInclusive, Float upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.LAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>lat IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByLat(Float... values) {
        return fetch(Trackings.TRACKINGS.LAT, values);
    }

    /**
     * Fetch records that have <code>lng BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfLng(Float lowerInclusive, Float upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.LNG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>lng IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByLng(Float... values) {
        return fetch(Trackings.TRACKINGS.LNG, values);
    }

    /**
     * Fetch records that have <code>speed BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfSpeed(Float lowerInclusive, Float upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.SPEED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>speed IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchBySpeed(Float... values) {
        return fetch(Trackings.TRACKINGS.SPEED, values);
    }

    /**
     * Fetch records that have <code>sat BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfSat(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.SAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sat IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchBySat(Integer... values) {
        return fetch(Trackings.TRACKINGS.SAT, values);
    }

    /**
     * Fetch records that have <code>hdop BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfHdop(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.HDOP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>hdop IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByHdop(Integer... values) {
        return fetch(Trackings.TRACKINGS.HDOP, values);
    }

    /**
     * Fetch records that have <code>time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchRangeOfTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Trackings.TRACKINGS.TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>time IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Trackings> fetchByTime(LocalDateTime... values) {
        return fetch(Trackings.TRACKINGS.TIME, values);
    }
}
