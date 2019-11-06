package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.TrackingDaoExt;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.TrackingServiceImp;
import com.tesis.utils.filters.TrackingFilters;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class TrackingServiceUnitTest extends UnitTestConfigs {

    @Mock
    VehicleDaoExt vehiclesDao;

    @Mock
    TrackingDaoExt trackingsDao;

    @InjectMocks
    TrackingServiceImp trackingService;

    @Test
    public void saveTrackingTest_ok(){

        Mockito.doNothing().when(trackingsDao).insert(any(Trackings.class));
        List<Trackings> trackingsList = new ArrayList<>();
        trackingsList.add(Mockito.mock(Trackings.class));

        ResponseDTO responseDTO = trackingService.saveTracking(trackingsList);
        Mockito.verify(trackingsDao, times(trackingsList.size())).insert(any(Trackings.class));
        assertEquals(trackingsList.toString(), responseDTO.getModel().toString());
    }
    @Test
    public void saveTrackingTest_error(){

        Mockito.doThrow(DataAccessException.class).when(trackingsDao).insert(any(Trackings.class));
        List<Trackings> trackingsList = new ArrayList<>();
        trackingsList.add(Mockito.mock(Trackings.class));

        ResponseDTO responseDTO = trackingService.saveTracking(trackingsList);
        assertEquals(responseDTO.getError().getMessage(), "Error inserting tracking data");
    }

    @Test
    public void getTrackingsByDeviceIDTest_ok(){
        List<Trackings> trackingsList = new ArrayList<>();
        trackingsList.add(Mockito.mock(Trackings.class));
        Mockito.when(trackingsDao.findAllActivesByDeviceID(any(Long.class))).thenReturn(trackingsList);

        ResponseDTO responseDTO = trackingService.getTrackingsByDeviceID(1L);

        assertEquals(trackingsList.toString(), responseDTO.getModel().toString());
    }
    @Test
    public void getTrackingsByDeviceIDTest_error(){
        List<Trackings> trackingsList = new ArrayList<>();
        Mockito.when(trackingsDao.findAllActivesByDeviceID(any(Long.class))).thenReturn(trackingsList);

        ResponseDTO responseDTO = trackingService.getTrackingsByDeviceID(1L);

        assertEquals(trackingsList.toString(), responseDTO.getModel().toString());
    }

    @Test
    public void getLocationByVehicleIDTest_ok(){
        Vehicles vehicle = new Vehicles();
        vehicle.setPlate("ABC-123");
        vehicle.setId(1L);
        vehicle.setDeviceId(1L);

        Trackings tracking = Mockito.mock(Trackings.class);

        Mockito.when(vehiclesDao.fetchOneById(any(Long.class))).thenReturn(vehicle);
        Mockito.when(trackingsDao.findLocationByDeviceID(any(Long.class))).thenReturn(tracking);

        ResponseDTO responseDTO = trackingService.getLocationByVehicleID(1L);

        assertEquals(tracking.toString(), responseDTO.getModel().toString());
    }

    @Test
    public void getLocationByVehicleIDTest_error(){
        Vehicles vehicle = new Vehicles();
        vehicle.setId(1L);
        vehicle.setDeviceId(1L);

        Mockito.when(vehiclesDao.fetchOneById(any(Long.class))).thenReturn(null);

        ResponseDTO responseDTO = trackingService.getLocationByVehicleID(1L);

        assertNull(responseDTO.getModel());
    }


    @Test
    public void trackingSearchTest_ok(){
        List<Trackings> trackingsList = new ArrayList<>();
        trackingsList.add(Mockito.mock(Trackings.class));
        Pagination pag = Mockito.mock(Pagination.class);

        Mockito.when(trackingsDao.findByFilters(any(TrackingFilters.class), any(Pagination.class))).thenReturn(trackingsList);
        ResponseDTO responseDTO = trackingService.trackingSearch(Mockito.mock(TrackingFilters.class), pag);

        assertNotNull(responseDTO.getModel());
    }
}
