package com.tesis.services;

import com.tesis.models.ResponseDTO;
import com.tesis.models.Tracking;

import java.util.List;

public interface TrackingService {

    ResponseDTO<List<Tracking>> saveTracking(List<Tracking> trakings);
}
