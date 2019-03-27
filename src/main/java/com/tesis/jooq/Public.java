/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq;


import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.Trakings;
import com.tesis.jooq.tables.Users;
import com.tesis.jooq.tables.Vehicles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1502350284;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.devices</code>.
     */
    public final Devices DEVICES = com.tesis.jooq.tables.Devices.DEVICES;

    /**
     * The table <code>public.trakings</code>.
     */
    public final Trakings TRAKINGS = com.tesis.jooq.tables.Trakings.TRAKINGS;

    /**
     * The table <code>public.users</code>.
     */
    public final Users USERS = com.tesis.jooq.tables.Users.USERS;

    /**
     * The table <code>public.vehicles</code>.
     */
    public final Vehicles VEHICLES = com.tesis.jooq.tables.Vehicles.VEHICLES;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.TRAKINGS_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Devices.DEVICES,
            Trakings.TRAKINGS,
            Users.USERS,
            Vehicles.VEHICLES);
    }
}
