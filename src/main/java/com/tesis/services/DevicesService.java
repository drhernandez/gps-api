package com.tesis.services;

import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;

import javax.xml.ws.Response;
import java.util.List;

public interface DevicesService {
    ResponseDTO<Devices> createDevice(Devices device);
    ResponseDTO<List<Devices>> getDevices();
    ResponseDTO<Devices> getDeciveByVehicleID(Long deviceID);
    ResponseDTO<Devices> updateDevice(Long deviceID, Devices newDevice);
    ResponseDTO<Devices> deleteVehicle(Long deviceId);
}
