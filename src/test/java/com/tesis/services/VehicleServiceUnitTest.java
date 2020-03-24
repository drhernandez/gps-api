package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.VehicleServiceImp;
import com.tesis.utils.filters.TrackingFilters;
import com.tesis.utils.filters.VehicleFilters;
import org.apache.http.HttpStatus;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceUnitTest {

    @Mock
    VehicleDaoExt vehicleDao;

    @Mock
    DeviceService deviceService;

    @InjectMocks
    VehicleServiceImp vehicleService;

    @Test
    public void createVehicleTest_ok(){
        Vehicles vehicle = new Vehicles();
        vehicle.setPlate("ABC-123");

        Mockito.when(vehicleDao.createVehicle(vehicle)).thenReturn(vehicle);
        ResponseDTO<Vehicles> responseDTO = vehicleService.createVehicle(vehicle);

        assertEquals(responseDTO.getModel().getPlate(), vehicle.getPlate());
        assertEquals(responseDTO.getModel().getStatus(), Status.PENDING.toString());
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

        assertNull(responseDTO.getModel());
    }

    @Test
    public void updateVehicleTest_ok(){
        Vehicles vehicle = mock(Vehicles.class);
        vehicle.setPlate("ABC-123");
        vehicle.setStatus(Status.ACTIVE.toString());

        Mockito.when(vehicleDao.fetchOneById(any())).thenReturn(vehicle);
        ResponseDTO<Vehicles> responseDTO = vehicleService.updateVehicle(new Random().nextLong(), vehicle);

        assertEquals(responseDTO.getModel().getPlate(), vehicle.getPlate());
        assertEquals(responseDTO.getModel().getStatus(), vehicle.getStatus());
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

    @Test
    public void getUserIDByVehicleIDTest_ok(){
        Vehicles vehicle = mock(Vehicles.class);
        vehicle.setPlate("ABC-123");
        vehicle.setUserId(1L);
        Long userId = vehicleService.getUserIDByVehicleID(1L);

        assertEquals(userId, vehicle.getUserId());
    }

    @Test
    public void getUserIDByVehicleIDTest_error(){

        Mockito.when(vehicleDao.fetchByIDForUserID(any())).thenReturn(null);
        Long userId = vehicleService.getUserIDByVehicleID(1L);

        assertNull(userId);
    }

    @Test
    public void vehicleSearchTest_ok(){

        List<Vehicles> vehicleList = new ArrayList<>();
        vehicleList.add(Mockito.mock(Vehicles.class));
        Pagination pag = Mockito.mock(Pagination.class);

        Mockito.when(vehicleDao.findByFilters(any(VehicleFilters.class), any(Pagination.class))).thenReturn(vehicleList);
        ResponseDTO responseDTO = vehicleService.vehicleSearch(Mockito.mock(VehicleFilters.class), pag);

        assertNotNull(responseDTO.getModel());
    }

    @Test
    public void activateVehicle_ok(){
        Vehicles vehicleMock = Mockito.mock(Vehicles.class);
        Devices deviceMock = Mockito.mock(Devices.class);
        ResponseDTO<Devices> deviceDTO = new ResponseDTO<>(deviceMock, null);


        Mockito.when(vehicleDao.fetchOneById(anyLong())).thenReturn(vehicleMock);
        Mockito.when(vehicleMock.getStatus()).thenReturn("PENDING");
        Mockito.when(deviceService.getDeviceByPhysicalID(anyLong())).thenReturn(deviceDTO);
        Mockito.when(deviceMock.getStatus()).thenReturn("INACTIVE");

        try {
            vehicleService.activateVehicle(1L, 1L);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void activateVehicle_no_vehicle() {
        Mockito.when(vehicleDao.fetchOneById(anyLong())).thenReturn(null);
        ResponseDTO<Vehicles> responseDTO = vehicleService.activateVehicle(1L, 1L);
        assertEquals(responseDTO.error.getMessage(), "Vehiculo no encontrado");
    }

    @Test
    public void activateVehicle_vehicle_active() {
        Vehicles vehicleMock = Mockito.mock(Vehicles.class);

        Mockito.when(vehicleDao.fetchOneById(anyLong())).thenReturn(vehicleMock);
        Mockito.when(vehicleMock.getStatus()).thenReturn("ACTIVE");

        ResponseDTO<Vehicles> responseDTO = vehicleService.activateVehicle(1L, 1L);
        assertEquals(responseDTO.error.getMessage(), "Vehiculo actualente activado");
    }

    @Test
    public void activateVehicle_device_error() {
        Vehicles vehicleMock = Mockito.mock(Vehicles.class);
        ResponseDTO<Devices> deviceDTO = new ResponseDTO<>(null, new ApiException(ErrorCodes.internal_error.toString(),
                "internal server error" ,
                HttpStatus.SC_INTERNAL_SERVER_ERROR));

        Mockito.when(vehicleDao.fetchOneById(anyLong())).thenReturn(vehicleMock);
        Mockito.when(vehicleMock.getStatus()).thenReturn("PENDING");
        Mockito.when(deviceService.getDeviceByPhysicalID(anyLong())).thenReturn(deviceDTO);

        ResponseDTO<Vehicles> responseDTO = vehicleService.activateVehicle(1L, 1L);
        assertEquals(responseDTO.error.getMessage(), "No se pudo activar el vehiculo. Reason: internal server error");
    }

    @Test
    public void activateVehicle_no_device() {
        Vehicles vehicleMock = Mockito.mock(Vehicles.class);
        ResponseDTO<Devices> deviceDTO = new ResponseDTO<>(null, null);

        Mockito.when(vehicleDao.fetchOneById(anyLong())).thenReturn(vehicleMock);
        Mockito.when(vehicleMock.getStatus()).thenReturn("PENDING");
        Mockito.when(deviceService.getDeviceByPhysicalID(anyLong())).thenReturn(deviceDTO);

        ResponseDTO<Vehicles> responseDTO = vehicleService.activateVehicle(1L, 1L);
        assertEquals(responseDTO.error.getMessage(), "No se pudo activar el vehiculo. Reason: Physical id inválido");
    }

    @Test
    public void activateVehicle_device_active() {
        Vehicles vehicleMock = Mockito.mock(Vehicles.class);
        Devices deviceMock = Mockito.mock(Devices.class);
        ResponseDTO<Devices> deviceDTO = new ResponseDTO<>(deviceMock, null);

        Mockito.when(vehicleDao.fetchOneById(anyLong())).thenReturn(vehicleMock);
        Mockito.when(vehicleMock.getStatus()).thenReturn("PENDING");
        Mockito.when(deviceService.getDeviceByPhysicalID(anyLong())).thenReturn(deviceDTO);
        Mockito.when(deviceMock.getStatus()).thenReturn("ACTIVE");

        ResponseDTO<Vehicles> responseDTO = vehicleService.activateVehicle(1L, 1L);
        assertEquals(responseDTO.error.getMessage(), "No se pudo activar el vehiculo. Reason: El physical id ingresado ya está en uso");
    }



}
