package com.tesis.models;

public class Location {

    private String id;
    private String deviceId;
    private Integer sat;
    private Integer hdop;
    private Double latitude;
    private Double longitude;
    private String timestamp;

    public Location() {
    }

    public Location(String id, String deviceId, Integer sat, Integer hdop, Double latitude, Double longitude, String timestamp) {
        this.id = id;
        this.deviceId = deviceId;
        this.sat = sat;
        this.hdop = hdop;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSat() {
        return sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public Integer getHdop() {
        return hdop;
    }

    public void setHdop(Integer hdop) {
        this.hdop = hdop;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
