/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq;


import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.MovementAlerts;
import com.tesis.jooq.tables.MovementAlertsHistory;
import com.tesis.jooq.tables.SpeedAlerts;
import com.tesis.jooq.tables.SpeedAlertsHistory;
import com.tesis.jooq.tables.Trackings;
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
     * The table <code>public.movement_alerts</code>.
     */
    public static final MovementAlerts MOVEMENT_ALERTS = com.tesis.jooq.tables.MovementAlerts.MOVEMENT_ALERTS;

    /**
     * The table <code>public.movement_alerts_history</code>.
     */
    public static final MovementAlertsHistory MOVEMENT_ALERTS_HISTORY = com.tesis.jooq.tables.MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY;

    /**
     * The table <code>public.speed_alerts</code>.
     */
    public static final SpeedAlerts SPEED_ALERTS = com.tesis.jooq.tables.SpeedAlerts.SPEED_ALERTS;

    /**
     * The table <code>public.speed_alerts_history</code>.
     */
    public static final SpeedAlertsHistory SPEED_ALERTS_HISTORY = com.tesis.jooq.tables.SpeedAlertsHistory.SPEED_ALERTS_HISTORY;

    /**
     * The table <code>public.trackings</code>.
     */
    public static final Trackings TRACKINGS = com.tesis.jooq.tables.Trackings.TRACKINGS;

    /**
     * The table <code>public.users</code>.
     */
    public static final Users USERS = com.tesis.jooq.tables.Users.USERS;

    /**
     * The table <code>public.vehicles</code>.
     */
    public static final Vehicles VEHICLES = com.tesis.jooq.tables.Vehicles.VEHICLES;
}
