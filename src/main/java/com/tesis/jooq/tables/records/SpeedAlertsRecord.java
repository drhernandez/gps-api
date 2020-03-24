/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.SpeedAlerts;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpeedAlertsRecord extends UpdatableRecordImpl<SpeedAlertsRecord> implements Record7<Long, Boolean, Float, Long, LocalDateTime, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 221680303;

    /**
     * Setter for <code>public.speed_alerts.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.speed_alerts.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.speed_alerts.active</code>.
     */
    public void setActive(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.speed_alerts.active</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>public.speed_alerts.speed</code>.
     */
    public void setSpeed(Float value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.speed_alerts.speed</code>.
     */
    public Float getSpeed() {
        return (Float) get(2);
    }

    /**
     * Setter for <code>public.speed_alerts.device_id</code>.
     */
    public void setDeviceId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.speed_alerts.device_id</code>.
     */
    public Long getDeviceId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.speed_alerts.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.speed_alerts.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>public.speed_alerts.updated_at</code>.
     */
    public void setUpdatedAt(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.speed_alerts.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>public.speed_alerts.activated_at</code>.
     */
    public void setActivatedAt(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.speed_alerts.activated_at</code>.
     */
    public LocalDateTime getActivatedAt() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Boolean, Float, Long, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, Boolean, Float, Long, LocalDateTime, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return SpeedAlerts.SPEED_ALERTS.ID;
    }

    @Override
    public Field<Boolean> field2() {
        return SpeedAlerts.SPEED_ALERTS.ACTIVE;
    }

    @Override
    public Field<Float> field3() {
        return SpeedAlerts.SPEED_ALERTS.SPEED;
    }

    @Override
    public Field<Long> field4() {
        return SpeedAlerts.SPEED_ALERTS.DEVICE_ID;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return SpeedAlerts.SPEED_ALERTS.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return SpeedAlerts.SPEED_ALERTS.UPDATED_AT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return SpeedAlerts.SPEED_ALERTS.ACTIVATED_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Boolean component2() {
        return getActive();
    }

    @Override
    public Float component3() {
        return getSpeed();
    }

    @Override
    public Long component4() {
        return getDeviceId();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component6() {
        return getUpdatedAt();
    }

    @Override
    public LocalDateTime component7() {
        return getActivatedAt();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Boolean value2() {
        return getActive();
    }

    @Override
    public Float value3() {
        return getSpeed();
    }

    @Override
    public Long value4() {
        return getDeviceId();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value6() {
        return getUpdatedAt();
    }

    @Override
    public LocalDateTime value7() {
        return getActivatedAt();
    }

    @Override
    public SpeedAlertsRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord value2(Boolean value) {
        setActive(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord value3(Float value) {
        setSpeed(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord value4(Long value) {
        setDeviceId(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord value5(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord value6(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord value7(LocalDateTime value) {
        setActivatedAt(value);
        return this;
    }

    @Override
    public SpeedAlertsRecord values(Long value1, Boolean value2, Float value3, Long value4, LocalDateTime value5, LocalDateTime value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SpeedAlertsRecord
     */
    public SpeedAlertsRecord() {
        super(SpeedAlerts.SPEED_ALERTS);
    }

    /**
     * Create a detached, initialised SpeedAlertsRecord
     */
    public SpeedAlertsRecord(Long id, Boolean active, Float speed, Long deviceId, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime activatedAt) {
        super(SpeedAlerts.SPEED_ALERTS);

        set(0, id);
        set(1, active);
        set(2, speed);
        set(3, deviceId);
        set(4, createdAt);
        set(5, updatedAt);
        set(6, activatedAt);
    }
}
