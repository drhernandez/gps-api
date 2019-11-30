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
public class SpeedAlerts implements Serializable {

    private static final long serialVersionUID = 965692939;

    private Long      id;
    private Boolean   active;
    private Float     speed;
    private Long      deviceId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp activatedAt;

    public SpeedAlerts() {}

    public SpeedAlerts(SpeedAlerts value) {
        this.id = value.id;
        this.active = value.active;
        this.speed = value.speed;
        this.deviceId = value.deviceId;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
        this.activatedAt = value.activatedAt;
    }

    public SpeedAlerts(
        Long      id,
        Boolean   active,
        Float     speed,
        Long      deviceId,
        Timestamp createdAt,
        Timestamp updatedAt,
        Timestamp activatedAt
    ) {
        this.id = id;
        this.active = active;
        this.speed = speed;
        this.deviceId = deviceId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.activatedAt = activatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getActivatedAt() {
        return this.activatedAt;
    }

    public void setActivatedAt(Timestamp activatedAt) {
        this.activatedAt = activatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SpeedAlerts (");

        sb.append(id);
        sb.append(", ").append(active);
        sb.append(", ").append(speed);
        sb.append(", ").append(deviceId);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(activatedAt);

        sb.append(")");
        return sb.toString();
    }
}
