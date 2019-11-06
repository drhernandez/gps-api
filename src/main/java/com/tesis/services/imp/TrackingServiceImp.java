package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.exceptions.ApiException;
import com.tesis.daos.TrackingDaoExt;
import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.TrackingService;
import com.tesis.utils.filters.TrackingFilters;

import java.util.List;

public class TrackingServiceImp implements TrackingService {

    @Inject
    TrackingDaoExt trakingsDao;
    @Inject
    VehicleDaoExt vehiclesDao;

    @Override
    public ResponseDTO<List<Trackings>> saveTracking(List<Trackings> trakings) {

        ResponseDTO<List<Trackings>> responseDTO = new ResponseDTO<>();

        try {
            trakings.forEach(tracking -> trakingsDao.insert(tracking));
            responseDTO.model = trakings;
        } catch (Exception e) {
            responseDTO.error = new ApiException("db_error", "Error inserting tracking data", e);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<Trackings>> getTrackingsByDeviceID(Long deviceID) {

        ResponseDTO<List<Trackings>> responseDTO = new ResponseDTO();
        responseDTO.model = trakingsDao.findAllActivesByDeviceID(deviceID);
        return responseDTO;
    }

    public ResponseDTO<List<Trackings>> getTrackingsByVehicleID(Long vehicleID) {
        Vehicles vehicle = vehiclesDao.fetchOneById(vehicleID);

        ResponseDTO<List<Trackings>> responseDTO = new ResponseDTO();
        responseDTO.model = trakingsDao.fetchByDeviceId(vehicle.getDeviceId());
        return responseDTO;
    }

    @Override
    public ResponseDTO<Trackings> getLocationByVehicleID(Long vehicleID) {
        Vehicles vehicle = vehiclesDao.fetchOneById(vehicleID);

        ResponseDTO<Trackings> responseDTO = new ResponseDTO();
        if (vehicle != null){
            responseDTO.model = trakingsDao.findLocationByDeviceID(vehicle.getDeviceId());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Search> trackingSearch(TrackingFilters filters, Pagination pagination) {
        ResponseDTO<Search> responseDTO = new ResponseDTO<>();
        responseDTO.model = new Search<>(trakingsDao.findByFilters(filters, pagination), pagination);
        return responseDTO;
    }
}
