package com.tesis.clients;


import com.tesis.exceptions.ApiException;
import com.tesis.models.UserDTO;
import kong.unirest.UnirestException;

import java.io.IOException;

public interface AuthGPSClient {

    /**
     * Envía una solicitud de validación de token a la api de autenticación y autorización
     * @param token
     * @throws ApiException
     */
    void validateToken(String token, String privileges) throws ApiException, IOException, UnirestException;

    /**
     * Solicita la informacion de un usuario especifico a traves de su ID
     * @param userID
     * @return Informacion del usuario en un DTO
     */
    UserDTO getUserData(Long userID) throws ApiException;
}
