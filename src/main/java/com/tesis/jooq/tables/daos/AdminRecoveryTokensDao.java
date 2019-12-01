/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.tesis.jooq.tables.AdminRecoveryTokens;
import com.tesis.jooq.tables.records.AdminRecoveryTokensRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class AdminRecoveryTokensDao extends DAOImpl<AdminRecoveryTokensRecord, com.tesis.jooq.tables.pojos.AdminRecoveryTokens, Long> {

    /**
     * Create a new AdminRecoveryTokensDao without any configuration
     */
    public AdminRecoveryTokensDao() {
        super(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS, com.tesis.jooq.tables.pojos.AdminRecoveryTokens.class);
    }

    /**
     * Create a new AdminRecoveryTokensDao with an attached configuration
     */
    public AdminRecoveryTokensDao(Configuration configuration) {
        super(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS, com.tesis.jooq.tables.pojos.AdminRecoveryTokens.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.tesis.jooq.tables.pojos.AdminRecoveryTokens object) {
        return object.getUserId();
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.AdminRecoveryTokens> fetchByUserId(Long... values) {
        return fetch(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS.USER_ID, values);
    }

    /**
     * Fetch a unique record that has <code>user_id = value</code>
     */
    public com.tesis.jooq.tables.pojos.AdminRecoveryTokens fetchOneByUserId(Long value) {
        return fetchOne(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS.USER_ID, value);
    }

    /**
     * Fetch records that have <code>token IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.AdminRecoveryTokens> fetchByToken(String... values) {
        return fetch(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS.TOKEN, values);
    }

    /**
     * Fetch a unique record that has <code>token = value</code>
     */
    public com.tesis.jooq.tables.pojos.AdminRecoveryTokens fetchOneByToken(String value) {
        return fetchOne(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS.TOKEN, value);
    }

    /**
     * Fetch records that have <code>expiration_date IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.AdminRecoveryTokens> fetchByExpirationDate(Timestamp... values) {
        return fetch(AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS.EXPIRATION_DATE, values);
    }
}
