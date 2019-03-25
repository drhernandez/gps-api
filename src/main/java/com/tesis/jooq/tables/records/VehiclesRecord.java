/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.Vehicles;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
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
public class VehiclesRecord extends UpdatableRecordImpl<VehiclesRecord> implements Record1<Long> {

    private static final long serialVersionUID = 1249720124;

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
    // Record1 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<Long> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<Long> valuesRow() {
        return (Row1) super.valuesRow();
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
    public Long component1() {
        return getId();
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
    public VehiclesRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehiclesRecord values(Long value1) {
        value1(value1);
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
    public VehiclesRecord(Long id) {
        super(Vehicles.VEHICLES);

        set(0, id);
    }
}
