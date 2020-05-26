/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.SpeedAlertsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpeedAlerts extends TableImpl<SpeedAlertsRecord> {

    private static final long serialVersionUID = 982813550;

    /**
     * The reference instance of <code>public.speed_alerts</code>
     */
    public static final SpeedAlerts SPEED_ALERTS = new SpeedAlerts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SpeedAlertsRecord> getRecordType() {
        return SpeedAlertsRecord.class;
    }

    /**
     * The column <code>public.speed_alerts.id</code>.
     */
    public final TableField<SpeedAlertsRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('speed_alerts_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.speed_alerts.active</code>.
     */
    public final TableField<SpeedAlertsRecord, Boolean> ACTIVE = createField(DSL.name("active"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.speed_alerts.speed</code>.
     */
    public final TableField<SpeedAlertsRecord, Float> SPEED = createField(DSL.name("speed"), org.jooq.impl.SQLDataType.REAL, this, "");

    /**
     * The column <code>public.speed_alerts.device_id</code>.
     */
    public final TableField<SpeedAlertsRecord, Long> DEVICE_ID = createField(DSL.name("device_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.speed_alerts.created_at</code>.
     */
    public final TableField<SpeedAlertsRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>public.speed_alerts.updated_at</code>.
     */
    public final TableField<SpeedAlertsRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>public.speed_alerts.activated_at</code>.
     */
    public final TableField<SpeedAlertsRecord, LocalDateTime> ACTIVATED_AT = createField(DSL.name("activated_at"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>public.speed_alerts.last_fired</code>.
     */
    public final TableField<SpeedAlertsRecord, LocalDateTime> LAST_FIRED = createField(DSL.name("last_fired"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * Create a <code>public.speed_alerts</code> table reference
     */
    public SpeedAlerts() {
        this(DSL.name("speed_alerts"), null);
    }

    /**
     * Create an aliased <code>public.speed_alerts</code> table reference
     */
    public SpeedAlerts(String alias) {
        this(DSL.name(alias), SPEED_ALERTS);
    }

    /**
     * Create an aliased <code>public.speed_alerts</code> table reference
     */
    public SpeedAlerts(Name alias) {
        this(alias, SPEED_ALERTS);
    }

    private SpeedAlerts(Name alias, Table<SpeedAlertsRecord> aliased) {
        this(alias, aliased, null);
    }

    private SpeedAlerts(Name alias, Table<SpeedAlertsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> SpeedAlerts(Table<O> child, ForeignKey<O, SpeedAlertsRecord> key) {
        super(child, key, SPEED_ALERTS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<SpeedAlertsRecord, Long> getIdentity() {
        return Keys.IDENTITY_SPEED_ALERTS;
    }

    @Override
    public UniqueKey<SpeedAlertsRecord> getPrimaryKey() {
        return Keys.SPEED_ALERTS_PKEY;
    }

    @Override
    public List<UniqueKey<SpeedAlertsRecord>> getKeys() {
        return Arrays.<UniqueKey<SpeedAlertsRecord>>asList(Keys.SPEED_ALERTS_PKEY, Keys.SPEED_ALERTS_DEVICE_ID_KEY);
    }

    @Override
    public List<ForeignKey<SpeedAlertsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SpeedAlertsRecord, ?>>asList(Keys.SPEED_ALERTS__SPEED_ALERTS_DEVICE_ID_FKEY);
    }

    public Devices devices() {
        return new Devices(this, Keys.SPEED_ALERTS__SPEED_ALERTS_DEVICE_ID_FKEY);
    }

    @Override
    public SpeedAlerts as(String alias) {
        return new SpeedAlerts(DSL.name(alias), this);
    }

    @Override
    public SpeedAlerts as(Name alias) {
        return new SpeedAlerts(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SpeedAlerts rename(String name) {
        return new SpeedAlerts(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SpeedAlerts rename(Name name) {
        return new SpeedAlerts(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, Boolean, Float, Long, LocalDateTime, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
