package com.tesis.enums;

import com.google.gson.JsonArray;

import java.util.EnumSet;

public enum UrlPermissions {
    GETVehicles ("GET /vehicles", EnumSet.of(Privileges.GET_VEHICLE)),
    GETVehiclesID ("GET /vehicles/[0-9]*", EnumSet.of(Privileges.GET_VEHICLE)),
    GETVehicleSearch("GET /vehicles/search", EnumSet.of(Privileges.GET_VEHICLE)),
    POSTVehicles("POST /vehicles", EnumSet.of(Privileges.CREATE_VEHICLE)),
    PUTVehiclesID("PUT /vehicles/[0-9]*", EnumSet.of(Privileges.UPDATE_VEHICLE)),
    DELETEVehiclesID("DELETE /vehicles/[0-9]*", EnumSet.of(Privileges.DELETE_VEHICLE)),
    GETVehiclesIDTrackings("GET /vehicles/[0-9]*/trackings", EnumSet.of(Privileges.GET_TRACKING)),
    PUTVehiclesIDActivate("GET /vehicles/[0-9]*/activate", EnumSet.of(Privileges.UPDATE_VEHICLE, Privileges.UPDATE_DEVICE)),
    GETVehiclesIDLocation("GET /vehicles/[0-9]*/location",EnumSet.of(Privileges.GET_TRACKING)),
    GETVehiclesIDAlertsSpeed("GET /vehicles/[0-9]*/alerts/speed", EnumSet.of(Privileges.GET_ALERT)),
    GETVehiclesIDAlertsMovement("GET /vehicles/[0-9]*/alerts/movement", EnumSet.of(Privileges.GET_ALERT)),
    GETVehiclesIDAlertsSpeedHistory("GET /vehicles/[0-9]*/alerts/speed/history", EnumSet.noneOf(Privileges.class)),
    GETVehiclesIDAlertsMovementHistory("GET /vehicles/[0-9]*/alerts/movement/history", EnumSet.noneOf(Privileges.class)),

    POSTAlertsSend("POST /alerts/send", EnumSet.of(Privileges.SEND_ALERT)),
    GETAlertsSpeeds("GET /alerts/speeds", EnumSet.of(Privileges.GET_ALERT)),
    POSTAlertsSpeeds("POST /alerts/speeds/", EnumSet.of(Privileges.CREATE_ALERT)),
    PUTAlertsSpeedsID("PUT /alerts/speeds/[0-9]*", EnumSet.of(Privileges.UPDATE_ALERT)),
    DELETEAlertsSpeedsID("DELETE /alerts/speeds/[0-9]*", EnumSet.of(Privileges.DELETE_ALERT)),

    GETAlertsMovements("GET /alerts/movements", EnumSet.of(Privileges.GET_ALERT)),
    POSTAlertsMovements("POST /alerts/movements", EnumSet.of(Privileges.CREATE_ALERT)),
    PUTAlertsMovementsID("PUT /alerts/movements/[0-9]*", EnumSet.of(Privileges.UPDATE_ALERT)),
    DELETEAlertsMovementsID("DELETE /alerts/movements/[0-9]*", EnumSet.of(Privileges.DELETE_ALERT)),

    POSTTrackings("POST /trackings", EnumSet.noneOf(Privileges.class)),
    GETTrackingsSearch("GET /trackings/search", EnumSet.of(Privileges.GET_TRACKING)),
    GETTrackingsID("GET /trackings/[0-9]*", EnumSet.of(Privileges.GET_TRACKING)),

    POSTAlertsSpeedsHistory("POST /alerts/speeds/history", EnumSet.noneOf(Privileges.class)),
    POSTAlertsMovementHistory("POST /alerts/movement/history", EnumSet.noneOf(Privileges.class)),

    GETDevices("GET /devices", EnumSet.of(Privileges.GET_DEVICE)),
    GETDevicesID("GET /devices/[0-9]*", EnumSet.of(Privileges.GET_DEVICE)),
    POSTDevices("POST /devices/", EnumSet.of(Privileges.CREATE_DEVICE)),
    POSTDevicesBulk("POST /devices/bulk", EnumSet.of(Privileges.CREATE_DEVICE)),
    PUTDevicesID("PUT /devices/[0-9]*", EnumSet.of(Privileges.UPDATE_DEVICE)),
    DELETEDevicesID("DELETE /devices/[0-9]*", EnumSet.of(Privileges.DELETE_DEVICE)),

    GETBrands("GET /brands", EnumSet.of(Privileges.GET_BRAND)),
    GETBrandsID("GET /brands/[0-9]*", EnumSet.of(Privileges.GET_BRAND)),
    POSTBrands("POST /brands/", EnumSet.of(Privileges.CREATE_BRAND)),
    PUTBrandsID("PUT /brands/[0-9]*", EnumSet.of(Privileges.UPDATE_BRAND)),
    DELETEBrandsID("DELETE /brands/[0-9]*", EnumSet.of(Privileges.DELETE_BRAND)),

    GETBrandsIDBrandlines("GET /brands/[0-9]*/brandlines", EnumSet.of(Privileges.GET_BRAND)),
    GETBrandsBrandlines("GET /brands/brandlines", EnumSet.of(Privileges.GET_BRAND)),
    GETBrandsBrandlinesID("GET /brands/[0-9]*/brandlines/[0-9]*", EnumSet.of(Privileges.GET_BRAND)),
    PUTBrandsBrandlinesID("PUT /brands/[0-9]*/brandlines/[0-9]*", EnumSet.of(Privileges.UPDATE_BRAND)),
    DELETEBrandsBrandlinesID("DELETE /brands/[0-9]*/brandlines/[0-9]*", EnumSet.of(Privileges.DELETE_BRAND));

    private String patter;
    private EnumSet<Privileges> privileges;

    UrlPermissions(String pattern, EnumSet<Privileges> privileges){
        this.patter = pattern;
        this.privileges = privileges;
    }

    public String getPatter() {
        return patter;
    }

    public EnumSet<Privileges> getPrivileges() {
        return privileges;
    }

    public String getPrivilegesAsJson() {
        JsonArray array = new JsonArray();
        for(Privileges privilege : privileges)
            array.add(privilege.name());
        return array.toString();
    }
}




