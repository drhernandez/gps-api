/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.records;


import com.tesis.jooq.tables.Users;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
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
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record11<Integer, Timestamp, Timestamp, String, String, String, String, String, String, String, String> {

    private static final long serialVersionUID = 1004809831;

    /**
     * Setter for <code>public.users.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.users.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.users.deleted_at</code>.
     */
    public void setDeletedAt(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.users.deleted_at</code>.
     */
    public Timestamp getDeletedAt() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.users.last_updated</code>.
     */
    public void setLastUpdated(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.users.last_updated</code>.
     */
    public Timestamp getLastUpdated() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.users.user_name</code>.
     */
    public void setUserName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.users.user_name</code>.
     */
    public String getUserName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.users.password</code>.
     */
    public void setPassword(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.users.password</code>.
     */
    public String getPassword() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.users.name</code>.
     */
    public void setName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.users.name</code>.
     */
    public String getName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.users.last_name</code>.
     */
    public void setLastName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.users.last_name</code>.
     */
    public String getLastName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.users.dni</code>.
     */
    public void setDni(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.users.dni</code>.
     */
    public String getDni() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.users.address</code>.
     */
    public void setAddress(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.users.address</code>.
     */
    public String getAddress() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.users.phone</code>.
     */
    public void setPhone(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.users.phone</code>.
     */
    public String getPhone() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.users.email</code>.
     */
    public void setEmail(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.users.email</code>.
     */
    public String getEmail() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<Integer, Timestamp, Timestamp, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<Integer, Timestamp, Timestamp, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Users.USERS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Users.USERS.DELETED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Users.USERS.LAST_UPDATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Users.USERS.USER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Users.USERS.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Users.USERS.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Users.USERS.LAST_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Users.USERS.DNI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return Users.USERS.ADDRESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Users.USERS.PHONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Users.USERS.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component2() {
        return getDeletedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getUserName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getLastName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getDni();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getDeletedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getUserName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getLastName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getDni();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value2(Timestamp value) {
        setDeletedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value3(Timestamp value) {
        setLastUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value4(String value) {
        setUserName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value5(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value6(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value7(String value) {
        setLastName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value8(String value) {
        setDni(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value9(String value) {
        setAddress(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value10(String value) {
        setPhone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value11(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord values(Integer value1, Timestamp value2, Timestamp value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(Integer id, Timestamp deletedAt, Timestamp lastUpdated, String userName, String password, String name, String lastName, String dni, String address, String phone, String email) {
        super(Users.USERS);

        set(0, id);
        set(1, deletedAt);
        set(2, lastUpdated);
        set(3, userName);
        set(4, password);
        set(5, name);
        set(6, lastName);
        set(7, dni);
        set(8, address);
        set(9, phone);
        set(10, email);
    }
}
