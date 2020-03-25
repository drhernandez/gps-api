package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.clients.imp.AuthGPSClientImp;
import com.tesis.enums.UrlPermissions;
import com.tesis.exceptions.ApiException;
import com.tesis.models.UserDTO;
import com.tesis.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class AuthServiceImp implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    private AuthGPSClientImp authGPSClient;

    @Inject
    public AuthServiceImp(AuthGPSClientImp authGPSClient) {
        this.authGPSClient = authGPSClient;
    }

    @Override
    public void validateToken(String token, String method, String uri) throws ApiException {
        Pattern pattern;
        String privileges = "[]";
        String operationRequest = method + " " + uri;
        for (UrlPermissions permission : UrlPermissions.values()){
            pattern = Pattern.compile(permission.getPatter());
            if (pattern.matcher(operationRequest).matches()) {
                privileges = permission.getPrivilegesAsJson();
                break;
            }
        }
        try {
            authGPSClient.validateToken(token, privileges);
        } catch (ApiException e) {
            logger.error("[message: Validación de token fallida] [error: {}]", e.getMessage());
            throw e;
        }
    }

    @Override
    public UserDTO getUser(Long userID) {
        try {
            return authGPSClient.getUserData(userID);
        }
        catch (ApiException e){
            logger.error("[message: Error al obtener la informacion del usuario {}] [error: {}]", userID, e.getMessage());
            return null;
        }
    }
}
