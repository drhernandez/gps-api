package com.tesis.enums;

/**
 * Created by diehernandez on 4/7/18.
 */
public enum ErrorCodes {
    internal_error,
    invalid_data,
    invalid_response,
    invalid_access_token,
    route_not_found,
    //inbound
    shipment_already_exists,
    shipment_already_in_hub,
    shipment_status,
    close_inbound,
    //Outbounds
    error_zone_id;
}