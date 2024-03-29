/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpeedAlertsHistory implements Serializable {

    private static final long serialVersionUID = 1121942030;

    private LocalDateTime time;
    private Long          alertId;
    private Float         speed;

    public SpeedAlertsHistory() {}

    public SpeedAlertsHistory(SpeedAlertsHistory value) {
        this.time = value.time;
        this.alertId = value.alertId;
        this.speed = value.speed;
    }

    public SpeedAlertsHistory(
        LocalDateTime time,
        Long          alertId,
        Float         speed
    ) {
        this.time = time;
        this.alertId = alertId;
        this.speed = speed;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getAlertId() {
        return this.alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SpeedAlertsHistory (");

        sb.append(time);
        sb.append(", ").append(alertId);
        sb.append(", ").append(speed);

        sb.append(")");
        return sb.toString();
    }
}
