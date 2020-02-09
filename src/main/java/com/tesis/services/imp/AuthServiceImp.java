package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.clients.imp.AuthGPSClientImp;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.services.AuthService;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tesis.enums.ErrorCodes.unauthorized;

public class AuthServiceImp implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    @Inject
    AuthGPSClientImp authGPSClient;

    @Override
    public String validateToken(String token) throws ApiException {
        try {
            authGPSClient.validateToken("token");
        } catch (ApiException e) {
            logger.error("Validación de token fallida");
            throw new ApiException(unauthorized.name(), ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);
        }

        return null;
    }

}
