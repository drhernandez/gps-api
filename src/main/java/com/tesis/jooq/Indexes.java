/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq;


import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.Trackings;
import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.Vehicles;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index DEVICES_PKEY = Indexes0.DEVICES_PKEY;
    public static final Index TRACKINGS_PKEY = Indexes0.TRACKINGS_PKEY;
    public static final Index USERS_PKEY = Indexes0.USERS_PKEY;
    public static final Index VEHICLES_PKEY = Indexes0.VEHICLES_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index DEVICES_PKEY = Internal.createIndex("devices_pkey", Devices.DEVICES, new OrderField[] { Devices.DEVICES.ID }, true);
        public static Index TRACKINGS_PKEY = Internal.createIndex("trackings_pkey", Trackings.TRACKINGS, new OrderField[] { Trackings.TRACKINGS.ID }, true);
        public static Index USERS_PKEY = Internal.createIndex("users_pkey", Users.USERS, new OrderField[] { Users.USERS.ID }, true);
        public static Index VEHICLES_PKEY = Internal.createIndex("vehicles_pkey", Vehicles.VEHICLES, new OrderField[] { Vehicles.VEHICLES.ID }, true);
    }
}
