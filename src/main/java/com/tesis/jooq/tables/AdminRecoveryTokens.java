/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Indexes;
import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.AdminRecoveryTokensRecord;

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
public class AdminRecoveryTokens extends TableImpl<AdminRecoveryTokensRecord> {

    private static final long serialVersionUID = -2032114319;

    /**
     * The reference instance of <code>public.admin_recovery_tokens</code>
     */
    public static final AdminRecoveryTokens ADMIN_RECOVERY_TOKENS = new AdminRecoveryTokens();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AdminRecoveryTokensRecord> getRecordType() {
        return AdminRecoveryTokensRecord.class;
    }

    /**
     * The column <code>public.admin_recovery_tokens.user_id</code>.
     */
    public final TableField<AdminRecoveryTokensRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.admin_recovery_tokens.token</code>.
     */
    public final TableField<AdminRecoveryTokensRecord, String> TOKEN = createField("token", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.admin_recovery_tokens.expiration_date</code>.
     */
    public final TableField<AdminRecoveryTokensRecord, Timestamp> EXPIRATION_DATE = createField("expiration_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * Create a <code>public.admin_recovery_tokens</code> table reference
     */
    public AdminRecoveryTokens() {
        this(DSL.name("admin_recovery_tokens"), null);
    }

    /**
     * Create an aliased <code>public.admin_recovery_tokens</code> table reference
     */
    public AdminRecoveryTokens(String alias) {
        this(DSL.name(alias), ADMIN_RECOVERY_TOKENS);
    }

    /**
     * Create an aliased <code>public.admin_recovery_tokens</code> table reference
     */
    public AdminRecoveryTokens(Name alias) {
        this(alias, ADMIN_RECOVERY_TOKENS);
    }

    private AdminRecoveryTokens(Name alias, Table<AdminRecoveryTokensRecord> aliased) {
        this(alias, aliased, null);
    }

    private AdminRecoveryTokens(Name alias, Table<AdminRecoveryTokensRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AdminRecoveryTokens(Table<O> child, ForeignKey<O, AdminRecoveryTokensRecord> key) {
        super(child, key, ADMIN_RECOVERY_TOKENS);
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
        return Arrays.<Index>asList(Indexes.ADMIN_RECOVERY_TOKENS_PKEY, Indexes.ADMIN_RECOVERY_TOKENS_TOKEN_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AdminRecoveryTokensRecord> getPrimaryKey() {
        return Keys.ADMIN_RECOVERY_TOKENS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AdminRecoveryTokensRecord>> getKeys() {
        return Arrays.<UniqueKey<AdminRecoveryTokensRecord>>asList(Keys.ADMIN_RECOVERY_TOKENS_PKEY, Keys.ADMIN_RECOVERY_TOKENS_TOKEN_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AdminRecoveryTokensRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AdminRecoveryTokensRecord, ?>>asList(Keys.ADMIN_RECOVERY_TOKENS__ADMIN_RECOVERY_TOKENS_USER_ID_FKEY);
    }

    public AdminUsers adminUsers() {
        return new AdminUsers(this, Keys.ADMIN_RECOVERY_TOKENS__ADMIN_RECOVERY_TOKENS_USER_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdminRecoveryTokens as(String alias) {
        return new AdminRecoveryTokens(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdminRecoveryTokens as(Name alias) {
        return new AdminRecoveryTokens(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AdminRecoveryTokens rename(String name) {
        return new AdminRecoveryTokens(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AdminRecoveryTokens rename(Name name) {
        return new AdminRecoveryTokens(name, null);
    }
}