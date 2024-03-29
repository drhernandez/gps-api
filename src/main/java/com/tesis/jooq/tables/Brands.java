/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.BrandsRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Brands extends TableImpl<BrandsRecord> {

    private static final long serialVersionUID = -1374655829;

    /**
     * The reference instance of <code>public.brands</code>
     */
    public static final Brands BRANDS = new Brands();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BrandsRecord> getRecordType() {
        return BrandsRecord.class;
    }

    /**
     * The column <code>public.brands.id</code>.
     */
    public final TableField<BrandsRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('brands_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.brands.name</code>.
     */
    public final TableField<BrandsRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>public.brands</code> table reference
     */
    public Brands() {
        this(DSL.name("brands"), null);
    }

    /**
     * Create an aliased <code>public.brands</code> table reference
     */
    public Brands(String alias) {
        this(DSL.name(alias), BRANDS);
    }

    /**
     * Create an aliased <code>public.brands</code> table reference
     */
    public Brands(Name alias) {
        this(alias, BRANDS);
    }

    private Brands(Name alias, Table<BrandsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Brands(Name alias, Table<BrandsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Brands(Table<O> child, ForeignKey<O, BrandsRecord> key) {
        super(child, key, BRANDS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<BrandsRecord, Long> getIdentity() {
        return Keys.IDENTITY_BRANDS;
    }

    @Override
    public UniqueKey<BrandsRecord> getPrimaryKey() {
        return Keys.BRANDS_PKEY;
    }

    @Override
    public List<UniqueKey<BrandsRecord>> getKeys() {
        return Arrays.<UniqueKey<BrandsRecord>>asList(Keys.BRANDS_PKEY);
    }

    @Override
    public Brands as(String alias) {
        return new Brands(DSL.name(alias), this);
    }

    @Override
    public Brands as(Name alias) {
        return new Brands(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Brands rename(String name) {
        return new Brands(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Brands rename(Name name) {
        return new Brands(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
