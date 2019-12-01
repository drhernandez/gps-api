package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AuthService;
import com.tesis.utils.JsonUtils;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Inject
    AuthService authService;

    public Object login(Request request, Response response) throws ApiException {
        CredentialsDTO credentialsDTO = JsonUtils.INSTANCE.GSON().fromJson(request.body(), CredentialsDTO.class);
        //Agregar validaciones

        ResponseDTO responseDTO = new ResponseDTO();

        if (authService.checkUserCredentials(credentialsDTO)) {
            response.status(HttpStatus.OK_200);
            responseDTO = authService.getOrCreateAccessToken(credentialsDTO);
        }
        else {
            response.status(HttpStatus.UNAUTHORIZED_401);
            responseDTO.error = new ApiException("401", ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);
        }

        if (responseDTO.error != null)
            throw responseDTO.error;

        return responseDTO.getModelAsJson();
    }

    public Object checkAccess(Request request, Response response) throws ApiException {
        String accessTocken = request.headers("Authorization");
        ResponseDTO responseDTO = new ResponseDTO();
        if (accessTocken != null) {
            accessTocken = accessTocken.split(" ")[1];
            try {
                authService.validateAccessToken(accessTocken);
                response.status(HttpStatus.OK_200);
                return responseDTO.getModelAsJson();
            } catch (Exception e){
                logger.error("Authorization fail, Reason: " + e.getMessage());
                response.status(HttpStatus.UNAUTHORIZED_401);
                responseDTO.error = new ApiException("401", ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);
            }
        }
        else {
            response.status(HttpStatus.BAD_REQUEST_400);
            responseDTO.error = new ApiException("400", "Auth info is required" , HttpStatus.BAD_REQUEST_400);
        }

        if (responseDTO.error != null)
            throw responseDTO.error;

        return responseDTO.getModelAsJson();
    }
}
