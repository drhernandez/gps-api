/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Indexes;
import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.TrakingsRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Trakings extends TableImpl<TrakingsRecord> {

    private static final long serialVersionUID = -196283869;

    /**
     * The reference instance of <code>public.trakings</code>
     */
    public static final Trakings TRAKINGS = new Trakings();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TrakingsRecord> getRecordType() {
        return TrakingsRecord.class;
    }

    /**
     * The column <code>public.trakings.id</code>.
     */
    public final TableField<TrakingsRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.trakings.device_id</code>.
     */
    public final TableField<TrakingsRecord, Long> DEVICE_ID = createField("device_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.trakings.lat</code>.
     */
    public final TableField<TrakingsRecord, Float> LAT = createField("lat", org.jooq.impl.SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>public.trakings.long</code>.
     */
    public final TableField<TrakingsRecord, Float> LONG = createField("long", org.jooq.impl.SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>public.trakings.sat</code>.
     */
    public final TableField<TrakingsRecord, Integer> SAT = createField("sat", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.trakings.hdop</code>.
     */
    public final TableField<TrakingsRecord, Integer> HDOP = createField("hdop", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.trakings.time</code>.
     */
    public final TableField<TrakingsRecord, Timestamp> TIME = createField("time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * Create a <code>public.trakings</code> table reference
     */
    public Trakings() {
        this(DSL.name("trakings"), null);
    }

    /**
     * Create an aliased <code>public.trakings</code> table reference
     */
    public Trakings(String alias) {
        this(DSL.name(alias), TRAKINGS);
    }

    /**
     * Create an aliased <code>public.trakings</code> table reference
     */
    public Trakings(Name alias) {
        this(alias, TRAKINGS);
    }

    private Trakings(Name alias, Table<TrakingsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Trakings(Name alias, Table<TrakingsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Trakings(Table<O> child, ForeignKey<O, TrakingsRecord> key) {
        super(child, key, TRAKINGS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TRAKINGS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TrakingsRecord> getPrimaryKey() {
        return Keys.TRAKINGS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TrakingsRecord>> getKeys() {
        return Arrays.<UniqueKey<TrakingsRecord>>asList(Keys.TRAKINGS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TrakingsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TrakingsRecord, ?>>asList(Keys.TRAKINGS__TRAKINGS_DEVICE_ID_FKEY);
    }

    public Devices devices() {
        return new Devices(this, Keys.TRAKINGS__TRAKINGS_DEVICE_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trakings as(String alias) {
        return new Trakings(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trakings as(Name alias) {
        return new Trakings(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Trakings rename(String name) {
        return new Trakings(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Trakings rename(Name name) {
        return new Trakings(name, null);
    }
}
