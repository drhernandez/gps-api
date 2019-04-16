/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.Devices;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class DevicesRecord extends UpdatableRecordImpl<DevicesRecord> implements Record7<Long, Timestamp, Timestamp, Integer, Long, String, String> {

    private static final long serialVersionUID = -1068038690;

    /**
     * Setter for <code>public.devices.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.devices.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.devices.deleted_at</code>.
     */
    public void setDeletedAt(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.devices.deleted_at</code>.
     */
    public Timestamp getDeletedAt() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.devices.last_updated</code>.
     */
    public void setLastUpdated(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.devices.last_updated</code>.
     */
    public Timestamp getLastUpdated() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.devices.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.devices.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.devices.vehicle_id</code>.
     */
    public void setVehicleId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.devices.vehicle_id</code>.
     */
    public Long getVehicleId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.devices.model</code>.
     */
    public void setModel(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.devices.model</code>.
     */
    public String getModel() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.devices.software_version</code>.
     */
    public void setSoftwareVersion(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.devices.software_version</code>.
     */
    public String getSoftwareVersion() {
        return (String) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, Timestamp, Timestamp, Integer, Long, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, Timestamp, Timestamp, Integer, Long, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Devices.DEVICES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Devices.DEVICES.DELETED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Devices.DEVICES.LAST_UPDATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Devices.DEVICES.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return Devices.DEVICES.VEHICLE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Devices.DEVICES.MODEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Devices.DEVICES.SOFTWARE_VERSION;
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
    public Integer component4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component5() {
        return getVehicleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getSoftwareVersion();
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
    public Integer value4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getVehicleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getSoftwareVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value2(Timestamp value) {
        setDeletedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value3(Timestamp value) {
        setLastUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value4(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value5(Long value) {
        setVehicleId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value6(String value) {
        setModel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value7(String value) {
        setSoftwareVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord values(Long value1, Timestamp value2, Timestamp value3, Integer value4, Long value5, String value6, String value7) {
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
     * Create a detached DevicesRecord
     */
    public DevicesRecord() {
        super(Devices.DEVICES);
    }

    /**
     * Create a detached, initialised DevicesRecord
     */
    public DevicesRecord(Long id, Timestamp deletedAt, Timestamp lastUpdated, Integer userId, Long vehicleId, String model, String softwareVersion) {
        super(Devices.DEVICES);

        set(0, id);
        set(1, deletedAt);
        set(2, lastUpdated);
        set(3, userId);
        set(4, vehicleId);
        set(5, model);
        set(6, softwareVersion);
    }
}
