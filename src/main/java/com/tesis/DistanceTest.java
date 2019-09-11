package com.tesis;
import com.google.inject.Inject;
import com.tesis.controllers.AlertController;

import java.lang.*;

import static com.tesis.config.Constants.EARTH_RADIUS_KM;

public class DistanceTest {

    public static double distance(double lat1, double lng1, double lat2, double lng2)
    {
        lng1 = Math.toRadians(lng1);
        lng2 = Math.toRadians(lng2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine equation
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lng2 - lng1) / 2),2);

        return 2 * Math.asin(Math.sqrt(a)) * EARTH_RADIUS_KM;
    }

    public static void main(String[] args)
    {
        System.out.println(distance(-31.4109,-64.1897,-31.4220676,-64.1865005) + " KM");
    }
}