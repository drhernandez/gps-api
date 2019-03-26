package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.daos.TrakingsDao;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Tracking;
import com.tesis.services.TrackingService;

import java.util.List;

public class TrackingServiceImp implements TrackingService {

    @Inject
    TrakingsDao trakingsDao;

    @Override
    public ResponseDTO<List<Tracking>> saveTracking(List<Tracking> trakings) {

        ResponseDTO<List<Tracking>> responseDTO = new ResponseDTO<>();

        try {
            trakings.forEach(tracking -> trakingsDao.insert(tracking));
            responseDTO.model = trakings;
        } catch (Exception e) {
            responseDTO.error = new ApiException("db_error", "Error inserting tracking data", e);
        }

        return responseDTO;
    }
}
