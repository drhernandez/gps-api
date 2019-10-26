package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.VehicleServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceUnitTest extends UnitTestConfigs {

    @Mock
    VehicleDaoExt vehicleDao;

    @InjectMocks
    VehicleServiceImp vehicleService;

    @Test
    public void createVehicleTest_ok(){
        Vehicles vehicle = new Vehicles();
        vehicle.setPlate("ABC-123");

        Mockito.when(vehicleDao.createVehicle(vehicle)).thenReturn(vehicle);
        ResponseDTO<Vehicles> responseDTO = vehicleService.createVehicle(vehicle);

        assertEquals(responseDTO.getModel().getPlate(), vehicle.getPlate());
    }

    @Test
    public void createVehicleTest_error(){
        Vehicles vehicle = new Vehicles();
        vehicle.setPlate("ABC-123");

        Mockito.when(vehicleDao.createVehicle(vehicle)).thenThrow(NullPointerException.class);
        ResponseDTO<Vehicles> responseDTO = vehicleService.createVehicle(vehicle);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el vehiculo.");

    }

    @Test
    public void getVehiclesByVehicleIDTest_ok(){
        Vehicles vehicle = mock(Vehicles.class);
        vehicle.setPlate("ABC-123");

        Mockito.when(vehicleDao.fetchOneById(any())).thenReturn(vehicle);
        ResponseDTO<Vehicles> responseDTO = vehicleService.getVehiclesByVehicleID(new Random().nextLong());

        assertEquals(responseDTO.getModel().getPlate(), vehicle.getPlate());
    }

    @Test
    public void getVehiclesByVehicleIDTest_error(){
        Mockito.when(vehicleDao.fetchOneById(any())).thenReturn(null);
        ResponseDTO<Vehicles> responseDTO = vehicleService.getVehiclesByVehicleID(new Random().nextLong());

        assertEquals(responseDTO.getModel(), null);
    }

    @Test
    public void updateVehicleTest_ok(){
        Vehicles vehicle = mock(Vehicles.class);

        Mockito.when(vehicleDao.fetchOneById(any())).thenReturn(vehicle);
        ResponseDTO<Vehicles> responseDTO = vehicleService.updateVehicle(new Random().nextLong(), vehicle);

        assertEquals(responseDTO.getModel().getPlate(), vehicle.getPlate());
        vehicle.setPlate("ABC-123");
    }

    @Test
    public void updateVehicleTest_error(){
        Vehicles vehicle = mock(Vehicles.class);
        vehicle.setPlate("ABC-123");

        Mockito.when(vehicleDao.fetchOneById(any())).thenThrow(DataAccessException.class);
        ResponseDTO<Vehicles> responseDTO = vehicleService.updateVehicle(new Random().nextLong(), vehicle);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar el vehiculo.");
    }

    @Test
    public void deleteVehicleTest_ok(){

        ResponseDTO<Vehicles> responseDTO = vehicleService.deleteVehicle(new Random().nextLong());
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteVehicleTest_error(){

        doThrow(DataAccessException.class).when(vehicleDao).deleteVehicle(any());
        ResponseDTO<Vehicles> responseDTO = vehicleService.deleteVehicle(new Random().nextLong());

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar el vehiculo.");
    }

}
