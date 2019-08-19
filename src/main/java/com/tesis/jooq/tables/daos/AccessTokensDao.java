/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.tesis.jooq.tables.AccessTokens;
import com.tesis.jooq.tables.records.AccessTokensRecord;

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
public class AccessTokensDao extends DAOImpl<AccessTokensRecord, com.tesis.jooq.tables.pojos.AccessTokens, Long> {

    /**
     * Create a new AccessTokensDao without any configuration
     */
    public AccessTokensDao() {
        super(AccessTokens.ACCESS_TOKENS, com.tesis.jooq.tables.pojos.AccessTokens.class);
    }

    /**
     * Create a new AccessTokensDao with an attached configuration
     */
    public AccessTokensDao(Configuration configuration) {
        super(AccessTokens.ACCESS_TOKENS, com.tesis.jooq.tables.pojos.AccessTokens.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.tesis.jooq.tables.pojos.AccessTokens object) {
        return object.getUserId();
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.AccessTokens> fetchByUserId(Long... values) {
        return fetch(AccessTokens.ACCESS_TOKENS.USER_ID, values);
    }

    /**
     * Fetch a unique record that has <code>user_id = value</code>
     */
    public com.tesis.jooq.tables.pojos.AccessTokens fetchOneByUserId(Long value) {
        return fetchOne(AccessTokens.ACCESS_TOKENS.USER_ID, value);
    }

    /**
     * Fetch records that have <code>token IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.AccessTokens> fetchByToken(String... values) {
        return fetch(AccessTokens.ACCESS_TOKENS.TOKEN, values);
    }
}