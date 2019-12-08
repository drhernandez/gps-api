package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.controllers.AlertController;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.enums.Status;
import com.tesis.exceptions.ApiException;
import com.tesis.daos.TrackingDaoExt;
import com.tesis.jooq.tables.pojos.*;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.routes.TrackingRouter;
import com.tesis.services.AlertService;
import com.tesis.services.TrackingService;
import com.tesis.utils.filters.TrackingFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.tesis.config.Constants.DEFAULT_MOVEMENT_ALERT_KM;
import static com.tesis.config.Constants.EARTH_RADIUS_KM;
import static spark.Spark.halt;

public class TrackingServiceImp implements TrackingService {

    Logger logger = LoggerFactory.getLogger(TrackingRouter.class);


    @Inject
    TrackingDaoExt trakingsDao;

    @Inject
    VehicleDaoExt vehiclesDao;

    @Inject
    AlertService alertService;

    @Inject
    AlertController alertController; //TODO change to SMSC Client

    @Override
    public ResponseDTO<List<Trackings>> saveTracking(List<Trackings> trackings) {

        ResponseDTO<List<Trackings>> responseDTO = new ResponseDTO<>();

        try {
            SpeedAlerts speedAlert = alertService.getSpeedIfActive(trakingsDao.getDeviceIDFromPhysicalID(trackings.get(0).getDeviceId()));
            MovementAlerts movementAlert = alertService.getMovementIfActive(trakingsDao.getDeviceIDFromPhysicalID(trackings.get(0).getDeviceId()));

            for(int i=0; i<trackings.size(); i++){
              Trackings tracking = trackings.get(i);
                trakingsDao.saveTracking(tracking);

                // Control de alerta de velocidad
                if(speedAlert != null) {
                    if(tracking.getSpeed() > speedAlert.getSpeed() &&
                            tracking.getTime().after(speedAlert.getActivatedAt())) {
                        try {
                            alertService.createSpeedAlertHistory(new SpeedAlertsHistory(
                                    tracking.getTime(),
                                    speedAlert.getId(),
                                    tracking.getSpeed()));
                            alertController.sendAlarm(trakingsDao.getDeviceIDFromPhysicalID(tracking.getDeviceId()),"SPEED");
                            break;
                        } catch (Exception e) {
                            logger.error("Error en la comprobacion de alertas de velocidad de trakings");
                            halt(500, e.getMessage());
                        }
                    }
                }

                // Control de alerta de movimiento
                if(movementAlert != null) {
                    if(tracking.getTime().after(movementAlert.getActivatedAt()) &&
                            checkDistance(tracking, movementAlert)){
                        try {
                            alertService.createMovementAlertHistory(new MovementAlertsHistory(
                                    tracking.getTime(),
                                    movementAlert.getId(),
                                    tracking.getLat(),
                                    tracking.getLng()
                            ));
                            alertController.sendAlarm(trakingsDao.getDeviceIDFromPhysicalID(tracking.getDeviceId()),"MOVEMENT");
                            break;
                        } catch (Exception e) {
                            logger.error("Error en la comprobacion de alertas de movimineto de trakings");
                            halt(500, e.getMessage());
                        }
                    }
                }
            }
            responseDTO.model = trackings;
        } catch (Exception e) {
            logger.error("Error al guardar el tracking. Reason: " + e.getMessage());
            responseDTO.error = new ApiException("db_error", "Error al guardar el tracking", e);
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

    public Boolean checkVehicleStatus(Trackings tracking){
        Vehicles vehicle = vehiclesDao.fetchOneByDeviceId(trakingsDao.getDeviceIDFromPhysicalID(tracking.getDeviceId()));
        if (vehicle == null)
            return false;
        return (vehicle.getStatus().equals(Status.ACTIVE.toString()));
    }

    private boolean checkDistance(Trackings tracking, MovementAlerts alert){

        double lat1 = Math.toRadians(tracking.getLat());
        double lng1 = Math.toRadians(tracking.getLng());
        double lat2 = Math.toRadians(alert.getLat());
        double lng2 = Math.toRadians(alert.getLng());

        // Haversine equation
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lng2 - lng1) / 2),2);

        return (2 * Math.asin(Math.sqrt(a)) * EARTH_RADIUS_KM) > DEFAULT_MOVEMENT_ALERT_KM;

    }
}
