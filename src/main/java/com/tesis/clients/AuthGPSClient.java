package com.tesis.clients;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tesis.exceptions.ApiException;

import java.io.IOException;
import java.net.MalformedURLException;

public interface AuthGPSClient {

    /**
     * Envía una solicitud de validación de token a la api de autenticación y autorización
     * @param token
     * @throws ApiException
     */
    void validateToken(String token, String privileges) throws ApiException, IOException, UnirestException;

}
