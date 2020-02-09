package com.tesis.enums;

import java.util.EnumSet;

public enum UrlPermissions {
    GETVehicles ("GET /vehicles", EnumSet.of(Privileges.GET_VEHICLE)),
    GETVehiclesId ("GET /vehicles/[0-9]*", EnumSet.of(Privileges.GET_VEHICLE)),
    POSTVehicles("POST /vehicles", EnumSet.of(Privileges.CREATE_VEHICLE)),
    PUTVehicles("PUT /vehicles/[0-9]*", EnumSet.of(Privileges.UPDATE_VEHICLE)),
    DELETEVehicles("DELETE /vehicles/[0-9]*", EnumSet.of(Privileges.DELETE_VEHICLE));

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
}




