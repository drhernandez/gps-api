/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables;


import com.tesis.jooq.Keys;
import com.tesis.jooq.Public;
import com.tesis.jooq.tables.records.MovementAlertsHistoryRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class MovementAlertsHistory extends TableImpl<MovementAlertsHistoryRecord> {

    private static final long serialVersionUID = -653093394;

    /**
     * The reference instance of <code>public.movement_alerts_history</code>
     */
    public static final MovementAlertsHistory MOVEMENT_ALERTS_HISTORY = new MovementAlertsHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovementAlertsHistoryRecord> getRecordType() {
        return MovementAlertsHistoryRecord.class;
    }

    /**
     * The column <code>public.movement_alerts_history.time</code>.
     */
    public final TableField<MovementAlertsHistoryRecord, Timestamp> TIME = createField("time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>public.movement_alerts_history.alert_id</code>.
     */
    public final TableField<MovementAlertsHistoryRecord, Long> ALERT_ID = createField("alert_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.movement_alerts_history.lat</code>.
     */
    public final TableField<MovementAlertsHistoryRecord, Float> LAT = createField("lat", org.jooq.impl.SQLDataType.REAL, this, "");

    /**
     * The column <code>public.movement_alerts_history.lng</code>.
     */
    public final TableField<MovementAlertsHistoryRecord, Float> LNG = createField("lng", org.jooq.impl.SQLDataType.REAL, this, "");

    /**
     * Create a <code>public.movement_alerts_history</code> table reference
     */
    public MovementAlertsHistory() {
        this(DSL.name("movement_alerts_history"), null);
    }

    /**
     * Create an aliased <code>public.movement_alerts_history</code> table reference
     */
    public MovementAlertsHistory(String alias) {
        this(DSL.name(alias), MOVEMENT_ALERTS_HISTORY);
    }

    /**
     * Create an aliased <code>public.movement_alerts_history</code> table reference
     */
    public MovementAlertsHistory(Name alias) {
        this(alias, MOVEMENT_ALERTS_HISTORY);
    }

    private MovementAlertsHistory(Name alias, Table<MovementAlertsHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private MovementAlertsHistory(Name alias, Table<MovementAlertsHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> MovementAlertsHistory(Table<O> child, ForeignKey<O, MovementAlertsHistoryRecord> key) {
        super(child, key, MOVEMENT_ALERTS_HISTORY);
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
    public UniqueKey<MovementAlertsHistoryRecord> getPrimaryKey() {
        return Keys.MOVEMENT_ALERTS_HISTORY_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MovementAlertsHistoryRecord>> getKeys() {
        return Arrays.<UniqueKey<MovementAlertsHistoryRecord>>asList(Keys.MOVEMENT_ALERTS_HISTORY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<MovementAlertsHistoryRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MovementAlertsHistoryRecord, ?>>asList(Keys.MOVEMENT_ALERTS_HISTORY__MOVEMENT_ALERTS_HISTORY_ALERT_ID_FKEY);
    }

    public SpeedAlerts speedAlerts() {
        return new SpeedAlerts(this, Keys.MOVEMENT_ALERTS_HISTORY__MOVEMENT_ALERTS_HISTORY_ALERT_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistory as(String alias) {
        return new MovementAlertsHistory(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementAlertsHistory as(Name alias) {
        return new MovementAlertsHistory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MovementAlertsHistory rename(String name) {
        return new MovementAlertsHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MovementAlertsHistory rename(Name name) {
        return new MovementAlertsHistory(name, null);
    }
}
