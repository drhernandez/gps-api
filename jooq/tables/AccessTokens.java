/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Indexes;
import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.AccessTokensRecord;

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
public class AccessTokens extends TableImpl<AccessTokensRecord> {

    private static final long serialVersionUID = -718848369;

    /**
     * The reference instance of <code>public.access_tokens</code>
     */
    public static final AccessTokens ACCESS_TOKENS = new AccessTokens();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccessTokensRecord> getRecordType() {
        return AccessTokensRecord.class;
    }

    /**
     * The column <code>public.access_tokens.user_id</code>.
     */
    public final TableField<AccessTokensRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.access_tokens.token</code>.
     */
    public final TableField<AccessTokensRecord, String> TOKEN = createField("token", org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>public.access_tokens</code> table reference
     */
    public AccessTokens() {
        this(DSL.name("access_tokens"), null);
    }

    /**
     * Create an aliased <code>public.access_tokens</code> table reference
     */
    public AccessTokens(String alias) {
        this(DSL.name(alias), ACCESS_TOKENS);
    }

    /**
     * Create an aliased <code>public.access_tokens</code> table reference
     */
    public AccessTokens(Name alias) {
        this(alias, ACCESS_TOKENS);
    }

    private AccessTokens(Name alias, Table<AccessTokensRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccessTokens(Name alias, Table<AccessTokensRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AccessTokens(Table<O> child, ForeignKey<O, AccessTokensRecord> key) {
        super(child, key, ACCESS_TOKENS);
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
        return Arrays.<Index>asList(Indexes.ACCESS_TOKENS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccessTokensRecord> getPrimaryKey() {
        return Keys.ACCESS_TOKENS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccessTokensRecord>> getKeys() {
        return Arrays.<UniqueKey<AccessTokensRecord>>asList(Keys.ACCESS_TOKENS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AccessTokensRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AccessTokensRecord, ?>>asList(Keys.ACCESS_TOKENS__ACCESS_TOKENS_USER_ID_FKEY);
    }

    public Users users() {
        return new Users(this, Keys.ACCESS_TOKENS__ACCESS_TOKENS_USER_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokens as(String alias) {
        return new AccessTokens(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokens as(Name alias) {
        return new AccessTokens(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessTokens rename(String name) {
        return new AccessTokens(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessTokens rename(Name name) {
        return new AccessTokens(name, null);
    }
}
