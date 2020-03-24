package com.tesis.services;

import com.tesis.daos.DeviceDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.DataException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.DeviceServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceUnitTest {


    @Mock
    DeviceDaoExt deviceDao;

    @InjectMocks
    DeviceServiceImp deviceService;

    @Test
    public void createDeviceTest_ok(){
        Devices device = new Devices();
        device.setModel("ARDUINO-UNO");

        ResponseDTO<Devices> responseDTO = deviceService.createDevice(device);

        assertEquals(responseDTO.getModel().getModel(), device.getModel());

    }

    @Test
    public void createDeviceTest_error() throws DataException {
        Devices device = new Devices();
        device.setModel("ARDUINO-UNO");

        Mockito.doThrow(DataAccessException.class).when(deviceDao).insertDevice(device);
        ResponseDTO<Devices> responseDTO = deviceService.createDevice(device);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el device.");
    }

    @Test
    public void getDeciveByDeviceID_ok(){
        Devices device = new Devices();
        device.setModel("ARDUINO-UNO");

        Mockito.when(deviceDao.fetchOneById(any())).thenReturn(device);
        ResponseDTO<Devices> responseDTO = deviceService.getDeciveByDeviceID(new Random().nextLong());

        assertEquals(responseDTO.getModel().getModel(), device.getModel());
    }

    @Test
    public void getDeciveByDeviceID_error(){
        Mockito.when(deviceDao.fetchOneById(any())).thenReturn(null);
        ResponseDTO<Devices> responseDTO = deviceService.getDeciveByDeviceID(new Random().nextLong());

        assertNull(responseDTO.getModel());
    }

    @Test
    public void updateDeviceTest_ok(){
        Devices device = mock(Devices.class);
        device.setModel("ABC-123");


        Mockito.when(deviceDao.fetchOneById(any())).thenReturn(device);
        ResponseDTO<Devices> responseDTO = deviceService.updateDevice(new Random().nextLong(), device);

        assertEquals(responseDTO.getModel().getModel(), device.getModel());
    }

    @Test
    public void updateDeviceTest_error(){
        Devices device = mock(Devices.class);
        device.setModel("ABC-123");

        Mockito.when(deviceDao.fetchOneById(any())).thenThrow(DataAccessException.class);
        ResponseDTO<Devices> responseDTO = deviceService.updateDevice(new Random().nextLong(), device);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar el device.");

    }

    @Test
    public void getDeviceByPhysicalIDTest_ok(){
        Devices devicesMock = mock(Devices.class);
        devicesMock.setId(1L);

        Mockito.when(deviceDao.getDeviceByPhysicalID(anyLong())).thenReturn(devicesMock);

        assertEquals(devicesMock.getId(), deviceService.getDeviceByPhysicalID(1L).getModel().getId());
    }

    @Test
    public void getDeviceByPhysicalIDTest_error(){

        Mockito.doThrow(DataAccessException.class).when(deviceDao).getDeviceByPhysicalID(anyLong());
        try {
            deviceService.getDeviceByPhysicalID(1L);
        } catch (Exception e) {
            assertEquals("internal error", e.getMessage());
        }

    }
}
