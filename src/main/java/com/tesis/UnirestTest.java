package com.tesis;

import com.mashape.unirest.http.Unirest;
import com.tesis.clients.imp.AuthGPSClientImp;
import com.tesis.exceptions.ApiException;

import static com.tesis.config.Constants.EARTH_RADIUS_KM;

public class UnirestTest {

    public static void main(String[] args)
    {
        AuthGPSClientImp authClient = new AuthGPSClientImp(new Unirest());
        try {
            authClient.validateToken("token");
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}