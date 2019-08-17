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
            responseDTO = authService.checkAccessToken(credentialsDTO);
        }
        else {
            response.status(HttpStatus.UNAUTHORIZED_401);
            responseDTO.error = new ApiException("401", ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);
        }

        return responseDTO;
    }

    public Object checkAccess(Request request, Response response){
        String accessTocken = request.headers("Authorization");
        ResponseDTO responseDTO = new ResponseDTO();
        if (accessTocken != null) {
            accessTocken = accessTocken.split(" ")[1];
            try {
                authService.validateAccessToken(accessTocken);
                response.status(HttpStatus.OK_200);
                return responseDTO.getModelAsJson();
            } catch (Exception e){
                logger.error("Authorization fail");
                response.status(HttpStatus.UNAUTHORIZED_401);
                responseDTO.error = new ApiException("401", ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);
            }
        }
        else
            response.status(HttpStatus.BAD_REQUEST_400);

        return responseDTO;
    }
}
