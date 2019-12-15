/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.Vehicles;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
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
public class VehiclesRecord extends UpdatableRecordImpl<VehiclesRecord> implements Record8<Long, Timestamp, Timestamp, Long, Long, String, String, String> {

    private static final long serialVersionUID = -2113161817;

    /**
     * Setter for <code>public.vehicles.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.vehicles.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.vehicles.deleted_at</code>.
     */
    public void setDeletedAt(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.vehicles.deleted_at</code>.
     */
    public Timestamp getDeletedAt() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.vehicles.last_updated</code>.
     */
    public void setLastUpdated(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.vehicles.last_updated</code>.
     */
    public Timestamp getLastUpdated() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.vehicles.user_id</code>.
     */
    public void setUserId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.vehicles.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.vehicles.device_id</code>.
     */
    public void setDeviceId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.vehicles.device_id</code>.
     */
    public Long getDeviceId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.vehicles.type</code>.
     */
    public void setType(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.vehicles.type</code>.
     */
    public String getType() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.vehicles.plate</code>.
     */
    public void setPlate(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.vehicles.plate</code>.
     */
    public String getPlate() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.vehicles.model</code>.
     */
    public void setModel(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.vehicles.model</code>.
     */
    public String getModel() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Long, Timestamp, Timestamp, Long, Long, String, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Long, Timestamp, Timestamp, Long, Long, String, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Vehicles.VEHICLES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Vehicles.VEHICLES.DELETED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Vehicles.VEHICLES.LAST_UPDATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return Vehicles.VEHICLES.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return Vehicles.VEHICLES.DEVICE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Vehicles.VEHICLES.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Vehicles.VEHICLES.PLATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Vehicles.VEHICLES.MODEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component2() {
        return getDeletedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component5() {
        return getDeviceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getPlate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getDeletedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getDeviceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getPlate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value2(Timestamp value) {
        setDeletedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value3(Timestamp value) {
        setLastUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value4(Long value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value5(Long value) {
        setDeviceId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value6(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value7(String value) {
        setPlate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord value8(String value) {
        setModel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord values(Long value1, Timestamp value2, Timestamp value3, Long value4, Long value5, String value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VehiclesRecord
     */
    public VehiclesRecord() {
        super(Vehicles.VEHICLES);
    }

    /**
     * Create a detached, initialised VehiclesRecord
     */
    public VehiclesRecord(Long id, Timestamp deletedAt, Timestamp lastUpdated, Long userId, Long deviceId, String type, String plate, String model) {
        super(Vehicles.VEHICLES);

        set(0, id);
        set(1, deletedAt);
        set(2, lastUpdated);
        set(3, userId);
        set(4, deviceId);
        set(5, type);
        set(6, plate);
        set(7, model);
    }
}
