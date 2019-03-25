package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.jooq.tables.daos.DevicesDao;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.DevicesService;

import java.util.List;

@Singleton
public class DevicesServiceImp implements DevicesService {

    @Inject
    DevicesDao devicesDao;

    public ResponseDTO<List<Devices>> getDevices() {
        return new ResponseDTO(devicesDao.findAll(), null);
    }
}
