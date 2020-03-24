/*
 * This file is generated by jOOQ.
 */
package com.tesis.jooq.tables.pojos;


import com.tesis.exceptions.ParseArgsException;
import com.tesis.utils.JsonUtils;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Trackings implements Serializable {

    private static final long serialVersionUID = -1376061898;

    private Long          id;
    private Long          deviceId;
    private Float         lat;
    private Float         lng;
    private Float         speed;
    private Integer       sat;
    private Integer       hdop;
    private LocalDateTime time;

    public Trackings() {}

    public Trackings(Trackings value) {
        this.id = value.id;
        this.deviceId = value.deviceId;
        this.lat = value.lat;
        this.lng = value.lng;
        this.speed = value.speed;
        this.sat = value.sat;
        this.hdop = value.hdop;
        this.time = value.time;
    }

    public Trackings(
        Long          id,
        Long          deviceId,
        Float         lat,
        Float         lng,
        Float         speed,
        Integer       sat,
        Integer       hdop,
        LocalDateTime time
    ) {
        this.id = id;
        this.deviceId = deviceId;
        this.lat = lat;
        this.lng = lng;
        this.speed = speed;
        this.sat = sat;
        this.hdop = hdop;
        this.time = time;
    }

    public Trackings(String[] args) throws ParseArgsException {
        try {
            this.id = null;
            this.deviceId = Long.valueOf(args[0]);
            this.lat = Float.valueOf(args[1]);
            this.lng = Float.valueOf(args[2]);
            this.speed = Float.valueOf(args[3]);
            this.sat = Integer.valueOf(args[4]);
            this.hdop = Integer.valueOf(args[5]);
            this.time = LocalDateTime.now();
        } catch (Exception e) {
            throw new ParseArgsException("Cannot create new tracking from args: " + JsonUtils.INSTANCE.GSON().toJson(args));
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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

    public Float getSpeed() {
        return this.speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Integer getSat() {
        return this.sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public Integer getHdop() {
        return this.hdop;
    }

    public void setHdop(Integer hdop) {
        this.hdop = hdop;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Trackings (");

        sb.append(id);
        sb.append(", ").append(deviceId);
        sb.append(", ").append(lat);
        sb.append(", ").append(lng);
        sb.append(", ").append(speed);
        sb.append(", ").append(sat);
        sb.append(", ").append(hdop);
        sb.append(", ").append(time);

        sb.append(")");
        return sb.toString();
    }
}
