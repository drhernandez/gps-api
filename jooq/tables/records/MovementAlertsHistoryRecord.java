/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.MovementAlertsHistory;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class MovementAlertsHistoryRecord extends UpdatableRecordImpl<MovementAlertsHistoryRecord> implements Record4<Timestamp, Long, Float, Float> {

    private static final long serialVersionUID = 100458526;

    /**
     * Setter for <code>public.movement_alerts_history.time</code>.
     */
    public void setTime(Timestamp value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.movement_alerts_history.time</code>.
     */
    public Timestamp getTime() {
        return (Timestamp) get(0);
    }

    /**
     * Setter for <code>public.movement_alerts_history.alert_id</code>.
     */
    public void setAlertId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.movement_alerts_history.alert_id</code>.
     */
    public Long getAlertId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.movement_alerts_history.lat</code>.
     */
    public void setLat(Float value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.movement_alerts_history.lat</code>.
     */
    public Float getLat() {
        return (Float) get(2);
    }

    /**
     * Setter for <code>public.movement_alerts_history.lng</code>.
     */
    public void setLng(Float value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.movement_alerts_history.lng</code>.
     */
    public Float getLng() {
        return (Float) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Timestamp> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Timestamp, Long, Float, Float> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Timestamp, Long, Float, Float> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field1() {
        return MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.ALERT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Float> field3() {
        return MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.LAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Float> field4() {
        return MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.LNG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component1() {
        return getTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getAlertId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Float component3() {
        return getLat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Float component4() {
        return getLng();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value1() {
        return getTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getAlertId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Float value3() {
        return getLat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Float value4() {
        return getLng();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistoryRecord value1(Timestamp value) {
        setTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistoryRecord value2(Long value) {
        setAlertId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistoryRecord value3(Float value) {
        setLat(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistoryRecord value4(Float value) {
        setLng(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistoryRecord values(Timestamp value1, Long value2, Float value3, Float value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MovementAlertsHistoryRecord
     */
    public MovementAlertsHistoryRecord() {
        super(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY);
    }

    /**
     * Create a detached, initialised MovementAlertsHistoryRecord
     */
    public MovementAlertsHistoryRecord(Timestamp time, Long alertId, Float lat, Float lng) {
        super(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY);

        set(0, time);
        set(1, alertId);
        set(2, lat);
        set(3, lng);
    }
}
