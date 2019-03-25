/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.Devices;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class DevicesRecord extends UpdatableRecordImpl<DevicesRecord> implements Record5<Long, Long, Long, String, String> {

    private static final long serialVersionUID = -49163922;

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
     * Setter for <code>public.devices.user_id</code>.
     */
    public void setUserId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.devices.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.devices.vehicle_id</code>.
     */
    public void setVehicleId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.devices.vehicle_id</code>.
     */
    public Long getVehicleId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.devices.model</code>.
     */
    public void setModel(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.devices.model</code>.
     */
    public String getModel() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.devices.software_version</code>.
     */
    public void setSoftwareVersion(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.devices.software_version</code>.
     */
    public String getSoftwareVersion() {
        return (String) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, Long, Long, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, Long, Long, String, String> valuesRow() {
        return (Row5) super.valuesRow();
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
    public Field<Long> field2() {
        return Devices.DEVICES.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return Devices.DEVICES.VEHICLE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Devices.DEVICES.MODEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
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
    public Long component2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getVehicleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
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
    public Long value2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getVehicleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
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
    public DevicesRecord value2(Long value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value3(Long value) {
        setVehicleId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value4(String value) {
        setModel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord value5(String value) {
        setSoftwareVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DevicesRecord values(Long value1, Long value2, Long value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
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
    public DevicesRecord(Long id, Long userId, Long vehicleId, String model, String softwareVersion) {
        super(Devices.DEVICES);

        set(0, id);
        set(1, userId);
        set(2, vehicleId);
        set(3, model);
        set(4, softwareVersion);
    }
}
