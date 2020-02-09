package com.tesis.clients.imp;

import com.google.inject.Inject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tesis.clients.AuthGPSClient;
import com.tesis.exceptions.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import static com.tesis.enums.ErrorCodes.*;

public class AuthGPSClientImp implements AuthGPSClient {

    private Unirest unirest;

    @Inject
    public AuthGPSClientImp (Unirest unirest){
        this.unirest = unirest;
    }

    private String baseUrl = "https://gps-auth.herokuapp.com";
    Logger logger = LoggerFactory.getLogger(AuthGPSClientImp.class);

    @Override
    public void validateToken(String token, String privileges) throws ApiException {
        try {
            String url = baseUrl + "/validate";
            String body =  "{\"privileges\": " + privileges + "}";

            HttpResponse<String> response = unirest.post(url)
                    .header("x-access-token", token)
                    .body(body)
                    .asString();

            //            UserDTO user = JsonUtils.INSTANCE.GSON().fromJson(response.getBody(), UserDTO.class); dejo esto por aqui para el futuro

            switch (response.getStatus()) {
                case 401:
                    throw new ApiException(unauthorized.name(),
                            "[reason: access token expired ] [method: AuthGPSClientImp.validateToken]",
                            HttpServletResponse.SC_UNAUTHORIZED);
                case 403:
                    throw new ApiException(forbidden.name(),
                            "[reason: forbidden ] [method: AuthGPSClientImp.validateToken]",
                            HttpServletResponse.SC_FORBIDDEN);
//                default:
//                    throw new ApiException(internal_error.name(),
//                            "[reason: internal server error ] [method: AuthGPSClientImp.validateToken]",
//                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (UnirestException e) {
            logger.error("Error al solicitar la validación del token.");
            throw new ApiException(service_unavailable.name(),
                    "[reason: " + e.getMessage() + " ] [method: AuthGPSClientImp.validateToken]",
                    HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }

    }
}
