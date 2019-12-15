/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Indexes;
import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.BrandLinesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class BrandLines extends TableImpl<BrandLinesRecord> {

    private static final long serialVersionUID = -1373093704;

    /**
     * The reference instance of <code>public.brand_lines</code>
     */
    public static final BrandLines BRAND_LINES = new BrandLines();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BrandLinesRecord> getRecordType() {
        return BrandLinesRecord.class;
    }

    /**
     * The column <code>public.brand_lines.id</code>.
     */
    public final TableField<BrandLinesRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('brand_lines_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.brand_lines.name</code>.
     */
    public final TableField<BrandLinesRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.brand_lines.brand_id</code>.
     */
    public final TableField<BrandLinesRecord, Long> BRAND_ID = createField("brand_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>public.brand_lines</code> table reference
     */
    public BrandLines() {
        this(DSL.name("brand_lines"), null);
    }

    /**
     * Create an aliased <code>public.brand_lines</code> table reference
     */
    public BrandLines(String alias) {
        this(DSL.name(alias), BRAND_LINES);
    }

    /**
     * Create an aliased <code>public.brand_lines</code> table reference
     */
    public BrandLines(Name alias) {
        this(alias, BRAND_LINES);
    }

    private BrandLines(Name alias, Table<BrandLinesRecord> aliased) {
        this(alias, aliased, null);
    }

    private BrandLines(Name alias, Table<BrandLinesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> BrandLines(Table<O> child, ForeignKey<O, BrandLinesRecord> key) {
        super(child, key, BRAND_LINES);
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
        return Arrays.<Index>asList(Indexes.BRAND_LINES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<BrandLinesRecord, Long> getIdentity() {
        return Keys.IDENTITY_BRAND_LINES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BrandLinesRecord> getPrimaryKey() {
        return Keys.BRAND_LINES_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BrandLinesRecord>> getKeys() {
        return Arrays.<UniqueKey<BrandLinesRecord>>asList(Keys.BRAND_LINES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<BrandLinesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<BrandLinesRecord, ?>>asList(Keys.BRAND_LINES__BRAND_LINES_BRAND_ID_FKEY);
    }

    public Brands brands() {
        return new Brands(this, Keys.BRAND_LINES__BRAND_LINES_BRAND_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BrandLines as(String alias) {
        return new BrandLines(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BrandLines as(Name alias) {
        return new BrandLines(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BrandLines rename(String name) {
        return new BrandLines(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BrandLines rename(Name name) {
        return new BrandLines(name, null);
    }
}
