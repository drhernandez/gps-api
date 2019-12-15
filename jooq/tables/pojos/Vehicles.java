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
public class Vehicles implements Serializable {

    private static final long serialVersionUID = -1273811951;

    private Long      id;
    private Timestamp deletedAt;
    private Timestamp lastUpdated;
    private Long      userId;
    private Long      deviceId;
    private String    type;
    private String    plate;
    private String    model;

    public Vehicles() {}

    public Vehicles(Vehicles value) {
        this.id = value.id;
        this.deletedAt = value.deletedAt;
        this.lastUpdated = value.lastUpdated;
        this.userId = value.userId;
        this.deviceId = value.deviceId;
        this.type = value.type;
        this.plate = value.plate;
        this.model = value.model;
    }

    public Vehicles(
        Long      id,
        Timestamp deletedAt,
        Timestamp lastUpdated,
        Long      userId,
        Long      deviceId,
        String    type,
        String    plate,
        String    model
    ) {
        this.id = id;
        this.deletedAt = deletedAt;
        this.lastUpdated = lastUpdated;
        this.userId = userId;
        this.deviceId = deviceId;
        this.type = type;
        this.plate = plate;
        this.model = model;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Timestamp getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vehicles (");

        sb.append(id);
        sb.append(", ").append(deletedAt);
        sb.append(", ").append(lastUpdated);
        sb.append(", ").append(userId);
        sb.append(", ").append(deviceId);
        sb.append(", ").append(type);
        sb.append(", ").append(plate);
        sb.append(", ").append(model);

        sb.append(")");
        return sb.toString();
    }
}
