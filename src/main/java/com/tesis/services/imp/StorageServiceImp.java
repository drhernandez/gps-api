package com.tesis.services.imp;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.tesis.clients.StorageClient;
import com.tesis.models.Location;
import com.tesis.models.ResponseDTO;
import com.tesis.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tesis.config.Constants.BODY;

public class StorageServiceImp implements StorageService {

    private final Logger log = LoggerFactory.getLogger(StorageServiceImp.class);

    @Inject
    StorageClient storageClient;

    @Override
    public ResponseDTO<List<Location>> getLocations(JsonObject requestInfo) {
        String deviceId = requestInfo.get("device_id").getAsString();
        return storageClient.getLocations(deviceId);
    }

    @Override
    public ResponseDTO<Boolean> addLocation(JsonObject requestInfo) {
        String body = requestInfo.get(BODY).getAsString();
        List<Location> locations = parseLocations(body);
        return storageClient.addLocation(locations);
    }

    private List<Location> parseLocations(String body) {

        List<Location> locations = new ArrayList<>();
        List<String> locationsList = Arrays.asList(body.split(";"));
        locationsList.forEach(elem -> {
            log.info("ELEMENT: " + elem);
            List<String> atributes = Arrays.asList(elem.split(","));
            if (atributes.size() != 6) {
                log.error("Invalid row. atributes.size != 6. Element: " + elem);
            } else {
                try {

                    String device = atributes.get(0);
                    Double latitude = Double.valueOf(atributes.get(1));
                    Double longitude = Double.valueOf(atributes.get(2));
                    Integer hdop = Integer.valueOf(atributes.get(3));
                    Integer sat = Integer.valueOf(atributes.get(4));
                    String timestamp = atributes.get(5);

                    locations.add(new Location(null, device, sat, hdop, latitude, longitude, timestamp));

                } catch (Exception e) {
                    //Los atributos están mal.. Loggear error
                    log.error("Invalid row. Element: " + elem + " Exception throwed: " + e.getMessage());
                }
            }

        });

        return locations;
    }
}
