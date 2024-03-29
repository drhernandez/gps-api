/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Indexes;
import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.MovementAlertsRecord;

import java.sql.Timestamp;
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
public class MovementAlerts extends TableImpl<MovementAlertsRecord> {

    private static final long serialVersionUID = -641856022;

    /**
     * The reference instance of <code>public.movement_alerts</code>
     */
    public static final MovementAlerts MOVEMENT_ALERTS = new MovementAlerts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovementAlertsRecord> getRecordType() {
        return MovementAlertsRecord.class;
    }

    /**
     * The column <code>public.movement_alerts.id</code>.
     */
    public final TableField<MovementAlertsRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('movement_alerts_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.movement_alerts.active</code>.
     */
    public final TableField<MovementAlertsRecord, Boolean> ACTIVE = createField("active", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.movement_alerts.lat</code>.
     */
    public final TableField<MovementAlertsRecord, Float> LAT = createField("lat", org.jooq.impl.SQLDataType.REAL, this, "");

    /**
     * The column <code>public.movement_alerts.lng</code>.
     */
    public final TableField<MovementAlertsRecord, Float> LNG = createField("lng", org.jooq.impl.SQLDataType.REAL, this, "");

    /**
     * The column <code>public.movement_alerts.device_id</code>.
     */
    public final TableField<MovementAlertsRecord, Long> DEVICE_ID = createField("device_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.movement_alerts.created_at</code>.
     */
    public final TableField<MovementAlertsRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>public.movement_alerts.updated_at</code>.
     */
    public final TableField<MovementAlertsRecord, Timestamp> UPDATED_AT = createField("updated_at", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>public.movement_alerts.activated_at</code>.
     */
    public final TableField<MovementAlertsRecord, Timestamp> ACTIVATED_AT = createField("activated_at", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * Create a <code>public.movement_alerts</code> table reference
     */
    public MovementAlerts() {
        this(DSL.name("movement_alerts"), null);
    }

    /**
     * Create an aliased <code>public.movement_alerts</code> table reference
     */
    public MovementAlerts(String alias) {
        this(DSL.name(alias), MOVEMENT_ALERTS);
    }

    /**
     * Create an aliased <code>public.movement_alerts</code> table reference
     */
    public MovementAlerts(Name alias) {
        this(alias, MOVEMENT_ALERTS);
    }

    private MovementAlerts(Name alias, Table<MovementAlertsRecord> aliased) {
        this(alias, aliased, null);
    }

    private MovementAlerts(Name alias, Table<MovementAlertsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> MovementAlerts(Table<O> child, ForeignKey<O, MovementAlertsRecord> key) {
        super(child, key, MOVEMENT_ALERTS);
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
        return Arrays.<Index>asList(Indexes.MOVEMENT_ALERTS_DEVICE_ID_KEY, Indexes.MOVEMENT_ALERTS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<MovementAlertsRecord, Long> getIdentity() {
        return Keys.IDENTITY_MOVEMENT_ALERTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MovementAlertsRecord> getPrimaryKey() {
        return Keys.MOVEMENT_ALERTS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MovementAlertsRecord>> getKeys() {
        return Arrays.<UniqueKey<MovementAlertsRecord>>asList(Keys.MOVEMENT_ALERTS_PKEY, Keys.MOVEMENT_ALERTS_DEVICE_ID_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<MovementAlertsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MovementAlertsRecord, ?>>asList(Keys.MOVEMENT_ALERTS__MOVEMENT_ALERTS_DEVICE_ID_FKEY);
    }

    public Devices devices() {
        return new Devices(this, Keys.MOVEMENT_ALERTS__MOVEMENT_ALERTS_DEVICE_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlerts as(String alias) {
        return new MovementAlerts(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlerts as(Name alias) {
        return new MovementAlerts(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MovementAlerts rename(String name) {
        return new MovementAlerts(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MovementAlerts rename(Name name) {
        return new MovementAlerts(name, null);
    }
}
