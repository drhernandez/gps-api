package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.*;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.AlertServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AlertServiceUnitTest extends UnitTestConfigs {


    @Mock
    VehicleDaoExt vehicleDao;

    @Mock
    SpeedAlertDaoExt speedAlertsDao;

    @Mock
    MovementAlertDaoExt movementAlertDao;

    @Mock
    SpeedAlertHistoryDaoExt speedAlertsHistoryDao;

    @Mock
    MovementAlertHistoryDaoExt movementAlertsHistoryDao;

    @InjectMocks
    AlertServiceImp alertService;

    //  ----------------  Speed Alert methods ----------------

    @Test
    public void createSpeedAlertTest_ok(){
        SpeedAlerts speedAlert = new SpeedAlerts();
        speedAlert.setDeviceId(1L);

        ResponseDTO<SpeedAlerts> responseDTO = alertService.createSpeedAlert(speedAlert);

        assertNull(responseDTO.getModel().getActivatedAt());
        assertNull(responseDTO.getModel().getUpdatedAt());
    }

    @Test
    public void createSpeedAlertTest_error(){
        SpeedAlerts speedAlert = new SpeedAlerts();
        speedAlert.setDeviceId(1L);

        Mockito.doThrow(DataAccessException.class).when(speedAlertsDao).insert(any(SpeedAlerts.class));
        ResponseDTO<SpeedAlerts> responseDTO = alertService.createSpeedAlert(speedAlert);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el speedAlert.");
    }

    @Test
    public void getSpeedAlertsTest_ok(){
        List<SpeedAlerts> speedAlerts = new ArrayList<>();
        speedAlerts.add(Mockito.mock(SpeedAlerts.class));

        Mockito.when(speedAlertsDao.findAllActives()).thenReturn(speedAlerts);
        ResponseDTO<List<SpeedAlerts>> responseDTO = alertService.getSpeedAlerts();

        assertEquals(responseDTO.getModel().size(), speedAlerts.size());
    }

    @Test
    public void getSpeedAlertsTest_error(){
        Mockito.when(speedAlertsDao.findAllActives()).thenReturn(new ArrayList<>());
        ResponseDTO<List<SpeedAlerts>> responseDTO = alertService.getSpeedAlerts();

        assertEquals(responseDTO.getModel().size(), 0);
    }

    @Test
    public void getSpeedAlertByVehicleIDTest_ok(){
        SpeedAlerts speedAlert = new SpeedAlerts();
        speedAlert.setId(1L);
        Vehicles vehicle = new Vehicles();
        vehicle.setId(1L);
        vehicle.setDeviceId(1L);

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(vehicle);
        Mockito.when(speedAlertsDao.fetchOneByDeviceId(any(Long.class))).thenReturn(speedAlert);

        ResponseDTO<SpeedAlerts> responseDTO = alertService.getSpeedAlertByVehicleID(1L);

        assertEquals(responseDTO.getModel().getId(), speedAlert.getId());
    }

    @Test
    public void getSpeedAlertByVehicleIDTest_error(){
        SpeedAlerts speedAlert = new SpeedAlerts();
        speedAlert.setId(1L);

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(null);
//        Mockito.when(speedAlertsDao.fetchOneByDeviceId(any(Long.class))).thenReturn(speedAlert);

        ResponseDTO<SpeedAlerts> responseDTO = alertService.getSpeedAlertByVehicleID(1L);

        assertNull(responseDTO.getModel());
    }

    @Test
    public void updateSpeedAlertTest_ok(){
        SpeedAlerts speedAlert = new SpeedAlerts();
        speedAlert.setActive(true);

        Mockito.when(speedAlertsDao.fetchOneById(any(Long.class))).thenReturn(speedAlert);
        ResponseDTO<SpeedAlerts> responseDTO = alertService.updateSpeedAlert(1L, speedAlert);

        assertEquals(responseDTO.getModel().getActive(), speedAlert.getActive());
    }

    @Test
    public void updateSpeedAlertTest_error(){

        SpeedAlerts speedAlert = new SpeedAlerts();
        speedAlert.setActive(true);

        Mockito.when(speedAlertsDao.fetchOneById(any(Long.class))).thenReturn(speedAlert);
        Mockito.doThrow(DataAccessException.class).when(speedAlertsDao).update(any(SpeedAlerts.class));
        ResponseDTO<SpeedAlerts> responseDTO = alertService.updateSpeedAlert(1L, speedAlert);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar el speedAlert.");
    }

    @Test
    public void deleteSpeedAlertTest_ok(){
        ResponseDTO<SpeedAlerts> responseDTO = alertService.deleteSpeedAlert(1L);
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteSpeedAlertTest_error(){

        Mockito.doThrow(DataAccessException.class).when(speedAlertsDao).deleteSpeedAlert(any(Long.class));
        ResponseDTO<SpeedAlerts> responseDTO = alertService.deleteSpeedAlert(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar el speedAlert.");
    }

    //  ----------------  Movement Alert methods ----------------

    @Test
    public void createMovementAlertTest_ok(){
        MovementAlerts movementAlert = new MovementAlerts();
        movementAlert.setDeviceId(1L);

        ResponseDTO<MovementAlerts> responseDTO = alertService.createMovementAlert(movementAlert);

        assertNull(responseDTO.getModel().getActivatedAt());
        assertNull(responseDTO.getModel().getUpdatedAt());
    }

    @Test
    public void createMovementAlertTest_error(){
        MovementAlerts movementAlert = new MovementAlerts();
        movementAlert.setDeviceId(1L);

        Mockito.doThrow(DataAccessException.class).when(movementAlertDao).insert(any(MovementAlerts.class));
        ResponseDTO<MovementAlerts> responseDTO = alertService.createMovementAlert(movementAlert);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el movementAlert.");
    }

    @Test
    public void getMovementAlertTest_ok(){
        List<MovementAlerts> movementAlert = new ArrayList<>();
        movementAlert.add(Mockito.mock(MovementAlerts.class));

        Mockito.when(movementAlertDao.findAllActives()).thenReturn(movementAlert);
        ResponseDTO<List<MovementAlerts>> responseDTO = alertService.getMovementAlert();

        assertEquals(responseDTO.getModel().size(), movementAlert.size());
    }

    @Test
    public void getMovementAlertTest_error(){
        Mockito.when(movementAlertDao.findAllActives()).thenReturn(new ArrayList<>());
        ResponseDTO<List<MovementAlerts>> responseDTO = alertService.getMovementAlert();

        assertEquals(responseDTO.getModel().size(), 0);
    }

    @Test
    public void getMovementAlertByVehicleIDTest_ok(){
        MovementAlerts moventAlert = new MovementAlerts();
        moventAlert.setId(1L);
        Vehicles vehicle = new Vehicles();
        vehicle.setId(1L);
        vehicle.setDeviceId(1L);

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(vehicle);
        Mockito.when(movementAlertDao.fetchOneByDeviceId(any(Long.class))).thenReturn(moventAlert);

        ResponseDTO<MovementAlerts> responseDTO = alertService.getMovementAlertByVehicleID(1L);

        assertEquals(responseDTO.getModel().getId(), moventAlert.getId());
    }

    @Test
    public void getMovementAlertByVehicleIDTest_error(){
        MovementAlerts movementAlerts = new MovementAlerts();
        movementAlerts.setId(1L);

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(null);
//        Mockito.when(movementAlertDao.fetchOneByDeviceId(any(Long.class))).thenReturn(movementAlerts);

        ResponseDTO<MovementAlerts> responseDTO = alertService.getMovementAlertByVehicleID(1L);

        assertNull(responseDTO.getModel());
    }

    @Test
    public void updateMovementAlertTest_ok(){
        MovementAlerts movementAlert = new MovementAlerts();
        movementAlert.setActive(true);

        Mockito.when(movementAlertDao.fetchOneById(any(Long.class))).thenReturn(movementAlert);
        ResponseDTO<MovementAlerts> responseDTO = alertService.updateMovementAlert(1L, movementAlert);

        assertEquals(responseDTO.getModel().getActive(), movementAlert.getActive());
    }

    @Test
    public void updateMovementAlertTest_error(){
        MovementAlerts movementAlert = new MovementAlerts();
        movementAlert.setActive(true);

        Mockito.when(movementAlertDao.fetchOneById(any(Long.class))).thenReturn(movementAlert);
        Mockito.doThrow(DataAccessException.class).when(movementAlertDao).update(any(MovementAlerts.class));
        ResponseDTO<MovementAlerts> responseDTO = alertService.updateMovementAlert(1L, movementAlert);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar el movementAlert.");
    }

    @Test
    public void deleteMovementAlertTest_ok(){
        ResponseDTO<MovementAlerts> responseDTO = alertService.deleteMovementAlert(1L);
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteMovementAlertTest_error(){
        Mockito.doThrow(DataAccessException.class).when(movementAlertDao).deleteMovementAlert(any(Long.class));
        ResponseDTO<MovementAlerts> responseDTO = alertService.deleteMovementAlert(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar el movementAlert.");
    }

    //  ----------------  SpeedAlertHistory Alert methods ----------------

    @Test
    public void createSpeedAlertHistoryTest_ok(){
        SpeedAlertsHistory speedAlertsHistory = new SpeedAlertsHistory();
        speedAlertsHistory.setSpeed(10F);

        ResponseDTO<SpeedAlertsHistory> responseDTO = alertService.createSpeedAlertHistory(speedAlertsHistory);
        assertEquals(responseDTO.getModel().getSpeed(), speedAlertsHistory.getSpeed());
    }

    @Test
    public void createSpeedAlertHistoryTest_error(){
        SpeedAlertsHistory speedAlertHistory = new SpeedAlertsHistory();
        speedAlertHistory.setSpeed(10F);

        Mockito.doThrow(DataAccessException.class).when(speedAlertsHistoryDao).insert(any(SpeedAlertsHistory.class));
        ResponseDTO<SpeedAlertsHistory> responseDTO = alertService.createSpeedAlertHistory(speedAlertHistory);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el speedAlertHistory.");
    }

    @Test
    public void getSpeedHistoryByVehicleIDTest_ok(){
        List<SpeedAlertsHistory> speedAlertHistoryList = new ArrayList<>();
        speedAlertHistoryList.add(Mockito.mock(SpeedAlertsHistory.class));

        SpeedAlerts speedAlerts = new SpeedAlerts();
        speedAlerts.setId(1L);
        speedAlerts.setDeviceId(1L);

        Vehicles vehicle = new Vehicles();
        vehicle.setId(1L);
        vehicle.setDeviceId(1L);

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(vehicle);
        Mockito.when(speedAlertsDao.fetchOneByDeviceId(any(Long.class))).thenReturn(speedAlerts);
        Mockito.when(speedAlertsHistoryDao.fetchByAlertId(any(Long.class))).thenReturn(speedAlertHistoryList);

        ResponseDTO<List<SpeedAlertsHistory>> responseDTO = alertService.getSpeedHistoryByVehicleID(1L);

        assertEquals(responseDTO.getModel().size(), speedAlertHistoryList.size());
    }

    @Test
    public void getSpeedHistoryByVehicleIDTest_error(){

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(null);
        ResponseDTO<List<SpeedAlertsHistory>> responseDTO = alertService.getSpeedHistoryByVehicleID(1L);

        assertEquals(responseDTO.getModel().size(), 0);
    }

    @Test
    public void deleteSpeedAlertHistoryTest_ok(){

        SpeedAlerts speedAlerts = new SpeedAlerts();
        speedAlerts.setId(1L);
        speedAlerts.setDeviceId(1L);

        Mockito.when(speedAlertsDao.fetchOneByDeviceId(any(Long.class))).thenReturn(speedAlerts);
        ResponseDTO<SpeedAlertsHistory> responseDTO = alertService.deleteSpeedAlertHistory(1L);
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteSpeedAlertHistoryTest_error(){
//        Mockito.doThrow(DataAccessException.class).when(speedAlertsHistoryDao).deleteSpeedAlertHistory(any(Long.class));
        ResponseDTO<SpeedAlertsHistory> responseDTO = alertService.deleteSpeedAlertHistory(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar los speedAlertHistory.");
    }

    @Test
    public void createMovementAlertHistoryTest_ok(){
        MovementAlertsHistory movementAlertsHistory = new MovementAlertsHistory();
        movementAlertsHistory.setLat(10F);
        movementAlertsHistory.setLng(10F);

        ResponseDTO<MovementAlertsHistory> responseDTO = alertService.createMovementAlertHistory(movementAlertsHistory);
        assertEquals(responseDTO.getModel().getLat(), movementAlertsHistory.getLat());
        assertEquals(responseDTO.getModel().getLng(), movementAlertsHistory.getLng());
    }

    @Test
    public void createMovementAlertHistoryTest_error(){
        MovementAlertsHistory movementAlertHistory = new MovementAlertsHistory();
        movementAlertHistory.setLat(10F);
        movementAlertHistory.setLng(10F);

        Mockito.doThrow(DataAccessException.class).when(movementAlertsHistoryDao).insert(any(MovementAlertsHistory.class));
        ResponseDTO<MovementAlertsHistory> responseDTO = alertService.createMovementAlertHistory(movementAlertHistory);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el movementAlertHistory.");
    }

    @Test
    public void getMovementHistoryByVehicleIDTest_ok(){
        List<MovementAlertsHistory> movementAlertHistoryList = new ArrayList<>();
        movementAlertHistoryList.add(Mockito.mock(MovementAlertsHistory.class));

        MovementAlerts movementAlerts = new MovementAlerts();
        movementAlerts.setId(1L);
        movementAlerts.setDeviceId(1L);

        Vehicles vehicle = new Vehicles();
        vehicle.setId(1L);
        vehicle.setDeviceId(1L);

        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(vehicle);
        Mockito.when(movementAlertDao.fetchOneByDeviceId(any(Long.class))).thenReturn(movementAlerts);
        Mockito.when(movementAlertsHistoryDao.fetchByAlertId(any(Long.class))).thenReturn(movementAlertHistoryList);

        ResponseDTO<List<MovementAlertsHistory>> responseDTO = alertService.getMovementHistoryByVehicleID(1L);

        assertEquals(responseDTO.getModel().size(), movementAlertHistoryList.size());
    }
    @Test
    public void getMovementHistoryByVehicleIDTest_error(){
        Mockito.when(vehicleDao.fetchOneById(any(Long.class))).thenReturn(null);
        ResponseDTO<List<MovementAlertsHistory>> responseDTO = alertService.getMovementHistoryByVehicleID(1L);

        assertEquals(responseDTO.getModel().size(), 0);
    }

    @Test
    public void deleteMovementAlertHistoryTest_ok(){
        MovementAlerts movementAlert = new MovementAlerts();
        movementAlert.setId(1L);
        movementAlert.setDeviceId(1L);

        Mockito.when(movementAlertDao.fetchOneByDeviceId(any(Long.class))).thenReturn(movementAlert);
        ResponseDTO<MovementAlertsHistory> responseDTO = alertService.deleteMovementAlertHistory(1L);
        assertNull(responseDTO.getModel());

    }

    @Test
    public void deleteMovementAlertHistoryTest_error(){
//        Mockito.doThrow(DataAccessException.class).when(movementAlertsHistoryDao).deleteMovementsAlertHistory(any(Long.class));
        ResponseDTO<MovementAlertsHistory> responseDTO = alertService.deleteMovementAlertHistory(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar los movementAlertHistory.");
    }
}
