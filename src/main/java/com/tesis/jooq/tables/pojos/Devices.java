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
public class Devices implements Serializable {

    private static final long serialVersionUID = 257961945;

    private Long      id;
    private Long      physicalId;
    private Timestamp deletedAt;
    private Timestamp lastUpdated;
    private String    model;
    private String    softwareVersion;

    public Devices() {}

    public Devices(Devices value) {
        this.id = value.id;
        this.physicalId = value.physicalId;
        this.deletedAt = value.deletedAt;
        this.lastUpdated = value.lastUpdated;
        this.model = value.model;
        this.softwareVersion = value.softwareVersion;
    }

    public Devices(
        Long      id,
        Long      physicalId,
        Timestamp deletedAt,
        Timestamp lastUpdated,
        String    model,
        String    softwareVersion
    ) {
        this.id = id;
        this.physicalId = physicalId;
        this.deletedAt = deletedAt;
        this.lastUpdated = lastUpdated;
        this.model = model;
        this.softwareVersion = softwareVersion;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhysicalId() {
        return this.physicalId;
    }

    public void setPhysicalId(Long physicalId) {
        this.physicalId = physicalId;
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

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Devices (");

        sb.append(id);
        sb.append(", ").append(physicalId);
        sb.append(", ").append(deletedAt);
        sb.append(", ").append(lastUpdated);
        sb.append(", ").append(model);
        sb.append(", ").append(softwareVersion);

        sb.append(")");
        return sb.toString();
    }
}
