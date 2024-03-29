package com.tesis.services;

import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;

import javax.xml.ws.Response;
import java.util.List;

public interface DeviceService {
    ResponseDTO<List<Devices>> bulkCreate(List<Long> idList);
    ResponseDTO<Devices> createDevice(Devices device);
    ResponseDTO<List<Devices>> getDevices();
    ResponseDTO<Devices> getDeciveByDeviceID(Long deviceID);
    ResponseDTO<Devices> updateDevice(Long deviceID, Devices newDevice);
    ResponseDTO<Devices> deleteDevice(Long deviceId);
    ResponseDTO<Devices> getDeviceByPhysicalID(Long physicalID);
}
