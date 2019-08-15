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

        if(authService.checkUserCredentials(credentialsDTO)){
            responseDTO = authService.checkAccessToken(credentialsDTO);
        }
        else
            responseDTO.error = new ApiException("401", ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);

        return responseDTO.getModelAsJson();
    }
}
