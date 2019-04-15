package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.daos.TrackingsDao;
import com.tesis.jooq.tables.pojos.Trackings;
import com.tesis.models.ResponseDTO;
import com.tesis.services.TrackingService;

import java.util.List;

public class TrackingServiceImp implements TrackingService {

    @Inject
    TrackingsDao trakingsDao;

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
        responseDTO.model = trakingsDao.fetchByDeviceId(deviceID);
        return responseDTO;
    }
}
