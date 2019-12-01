/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq;


import com.tesis.jooq.tables.AccessTokens;
import com.tesis.jooq.tables.AdminAccessTokens;
import com.tesis.jooq.tables.AdminRecoveryTokens;
import com.tesis.jooq.tables.AdminUsers;
import com.tesis.jooq.tables.BrandLines;
import com.tesis.jooq.tables.Brands;
import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.MovementAlerts;
import com.tesis.jooq.tables.MovementAlertsHistory;
import com.tesis.jooq.tables.RecoveryTokens;
import com.tesis.jooq.tables.SpeedAlerts;
import com.tesis.jooq.tables.SpeedAlertsHistory;
import com.tesis.jooq.tables.Trackings;
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

    private static final long serialVersionUID = -1414168163;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.access_tokens</code>.
     */
    public final AccessTokens ACCESS_TOKENS = com.tesis.jooq.tables.AccessTokens.ACCESS_TOKENS;

    /**
     * The table <code>public.admin_access_tokens</code>.
     */
    public final AdminAccessTokens ADMIN_ACCESS_TOKENS = com.tesis.jooq.tables.AdminAccessTokens.ADMIN_ACCESS_TOKENS;

    /**
     * The table <code>public.admin_recovery_tokens</code>.
     */
    public final AdminRecoveryTokens ADMIN_RECOVERY_TOKENS = com.tesis.jooq.tables.AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS;

    /**
     * The table <code>public.admin_users</code>.
     */
    public final AdminUsers ADMIN_USERS = com.tesis.jooq.tables.AdminUsers.ADMIN_USERS;

    /**
     * The table <code>public.brand_lines</code>.
     */
    public final BrandLines BRAND_LINES = com.tesis.jooq.tables.BrandLines.BRAND_LINES;

    /**
     * The table <code>public.brands</code>.
     */
    public final Brands BRANDS = com.tesis.jooq.tables.Brands.BRANDS;

    /**
     * The table <code>public.devices</code>.
     */
    public final Devices DEVICES = com.tesis.jooq.tables.Devices.DEVICES;

    /**
     * The table <code>public.movement_alerts</code>.
     */
    public final MovementAlerts MOVEMENT_ALERTS = com.tesis.jooq.tables.MovementAlerts.MOVEMENT_ALERTS;

    /**
     * The table <code>public.movement_alerts_history</code>.
     */
    public final MovementAlertsHistory MOVEMENT_ALERTS_HISTORY = com.tesis.jooq.tables.MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY;

    /**
     * The table <code>public.recovery_tokens</code>.
     */
    public final RecoveryTokens RECOVERY_TOKENS = com.tesis.jooq.tables.RecoveryTokens.RECOVERY_TOKENS;

    /**
     * The table <code>public.speed_alerts</code>.
     */
    public final SpeedAlerts SPEED_ALERTS = com.tesis.jooq.tables.SpeedAlerts.SPEED_ALERTS;

    /**
     * The table <code>public.speed_alerts_history</code>.
     */
    public final SpeedAlertsHistory SPEED_ALERTS_HISTORY = com.tesis.jooq.tables.SpeedAlertsHistory.SPEED_ALERTS_HISTORY;

    /**
     * The table <code>public.trackings</code>.
     */
    public final Trackings TRACKINGS = com.tesis.jooq.tables.Trackings.TRACKINGS;

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
            Sequences.ADMIN_USERS_ID_SEQ,
            Sequences.BRAND_LINES_ID_SEQ,
            Sequences.BRANDS_ID_SEQ,
            Sequences.DEVICES_ID_SEQ,
            Sequences.MOVEMENT_ALERTS_ID_SEQ,
            Sequences.SPEED_ALERTS_ID_SEQ,
            Sequences.TRACKINGS_ID_SEQ,
            Sequences.USERS_ID_SEQ,
            Sequences.VEHICLES_ID_SEQ,
            Sequences.VEHICLES_USER_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            AccessTokens.ACCESS_TOKENS,
            AdminAccessTokens.ADMIN_ACCESS_TOKENS,
            AdminRecoveryTokens.ADMIN_RECOVERY_TOKENS,
            AdminUsers.ADMIN_USERS,
            BrandLines.BRAND_LINES,
            Brands.BRANDS,
            Devices.DEVICES,
            MovementAlerts.MOVEMENT_ALERTS,
            MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY,
            RecoveryTokens.RECOVERY_TOKENS,
            SpeedAlerts.SPEED_ALERTS,
            SpeedAlertsHistory.SPEED_ALERTS_HISTORY,
            Trackings.TRACKINGS,
            Users.USERS,
            Vehicles.VEHICLES);
    }
}
