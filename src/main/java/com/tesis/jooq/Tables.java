/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq;


import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.Trakings;
import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.Vehicles;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.devices</code>.
     */
    public static final Devices DEVICES = com.tesis.jooq.tables.Devices.DEVICES;

    /**
     * The table <code>public.trakings</code>.
     */
    public static final Trakings TRAKINGS = com.tesis.jooq.tables.Trakings.TRAKINGS;

    /**
     * The table <code>public.users</code>.
     */
    public static final Users USERS = com.tesis.jooq.tables.Users.USERS;

    /**
     * The table <code>public.vehicles</code>.
     */
    public static final Vehicles VEHICLES = com.tesis.jooq.tables.Vehicles.VEHICLES;
}
