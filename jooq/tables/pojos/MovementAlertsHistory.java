/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class MovementAlertsHistory implements Serializable {

    private static final long serialVersionUID = -2014932791;

    private Timestamp time;
    private Long      alertId;
    private Float     lat;
    private Float     lng;

    public MovementAlertsHistory() {}

    public MovementAlertsHistory(MovementAlertsHistory value) {
        this.time = value.time;
        this.alertId = value.alertId;
        this.lat = value.lat;
        this.lng = value.lng;
    }

    public MovementAlertsHistory(
        Timestamp time,
        Long      alertId,
        Float     lat,
        Float     lng
    ) {
        this.time = time;
        this.alertId = alertId;
        this.lat = lat;
        this.lng = lng;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getAlertId() {
        return this.alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Float getLat() {
        return this.lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return this.lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MovementAlertsHistory (");

        sb.append(time);
        sb.append(", ").append(alertId);
        sb.append(", ").append(lat);
        sb.append(", ").append(lng);

        sb.append(")");
        return sb.toString();
    }
}