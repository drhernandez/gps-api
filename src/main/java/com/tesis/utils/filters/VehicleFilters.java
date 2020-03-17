package com.tesis.utils.filters;

public class VehicleFilters {
    private String    status;
    private Long      userId;
    private Long      deviceId;
    private String    plate;
    private String    brand;
    private String    brandLine;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandLine() {
        return brandLine;
    }

    public void setBrandLine(String brandLine) {
        this.brandLine = brandLine;
    }
}
