/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.daos;


import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.records.UsersRecord;

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
public class UsersDao extends DAOImpl<UsersRecord, com.tesis.jooq.tables.pojos.Users, Long> {

    /**
     * Create a new UsersDao without any configuration
     */
    public UsersDao() {
        super(Users.USERS, com.tesis.jooq.tables.pojos.Users.class);
    }

    /**
     * Create a new UsersDao with an attached configuration
     */
    public UsersDao(Configuration configuration) {
        super(Users.USERS, com.tesis.jooq.tables.pojos.Users.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.tesis.jooq.tables.pojos.Users object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchById(Long... values) {
        return fetch(Users.USERS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tesis.jooq.tables.pojos.Users fetchOneById(Long value) {
        return fetchOne(Users.USERS.ID, value);
    }

    /**
     * Fetch records that have <code>deleted_at IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByDeletedAt(Timestamp... values) {
        return fetch(Users.USERS.DELETED_AT, values);
    }

    /**
     * Fetch records that have <code>last_updated IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByLastUpdated(Timestamp... values) {
        return fetch(Users.USERS.LAST_UPDATED, values);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByEmail(String... values) {
        return fetch(Users.USERS.EMAIL, values);
    }

    /**
     * Fetch a unique record that has <code>email = value</code>
     */
    public com.tesis.jooq.tables.pojos.Users fetchOneByEmail(String value) {
        return fetchOne(Users.USERS.EMAIL, value);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByPassword(String... values) {
        return fetch(Users.USERS.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByName(String... values) {
        return fetch(Users.USERS.NAME, values);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByLastName(String... values) {
        return fetch(Users.USERS.LAST_NAME, values);
    }

    /**
     * Fetch records that have <code>dni IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByDni(String... values) {
        return fetch(Users.USERS.DNI, values);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByAddress(String... values) {
        return fetch(Users.USERS.ADDRESS, values);
    }

    /**
     * Fetch records that have <code>phone IN (values)</code>
     */
    public List<com.tesis.jooq.tables.pojos.Users> fetchByPhone(String... values) {
        return fetch(Users.USERS.PHONE, values);
    }
}
