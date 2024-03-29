package com.tesis.clients.imp;

import com.google.inject.Inject;
import com.tesis.clients.AuthGPSClient;
import com.tesis.exceptions.ApiException;
import com.tesis.models.UserDTO;
import com.tesis.utils.JsonUtils;
import kong.unirest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import static com.tesis.enums.ErrorCodes.*;

public class AuthGPSClientImp implements AuthGPSClient {

    private final UnirestInstance unirest;

    @Inject
    public AuthGPSClientImp (UnirestInstance unirest){
        this.unirest = unirest;
    }

    private static String baseUrl = System.getenv("AUTH_API_BASE_URL");
    Logger logger = LoggerFactory.getLogger(AuthGPSClientImp.class);

    @Override
    public void validateToken(String token, String privileges) throws ApiException {
        try {
            String url = baseUrl + "/authentication/validate";

            RequestBodyEntity responseBodyEntity = unirest.post(url)
                    .header("x-access-token", token)
                    .header("Content-Type", "application/json")
                    .body(privileges);

            HttpResponse<JsonNode> response = responseBodyEntity.asJson();
            if (response.getStatus() != 200) {
                logger.error("[message: Invalid response validating token] [status: {}] [response: {}]", response.getStatus(), response.getBody());
                throw new ApiException(unauthorized.name(), "Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
            }

        } catch (UnirestException e) {
            logger.error("[message: Error al solicitar la validación del token] [error: {}]", e.getMessage());
            throw new ApiException(service_unavailable.name(),
                    "[reason: " + e.getMessage() + " ] [method: AuthGPSClientImp.validateToken]",
                    HttpServletResponse.SC_SERVICE_UNAVAILABLE, e);
        }
    }

    @Override
    public UserDTO getUserData(Long userID) throws ApiException {

        try {
            String url = baseUrl + "/users/" + userID;

            GetRequest request = unirest.get(url)
                .header("Content-Type", "application/json");
            HttpResponse<String> response = request.asString();

            if (response.getStatus() == 200)
                return JsonUtils.INSTANCE.GSON().fromJson(response.getBody(), UserDTO.class);
            else
                throw new ApiException(unauthorized.name(),
                        "[reason: unauthorized ] [method: AuthGPSClientImp.getUserData]",
                        HttpServletResponse.SC_UNAUTHORIZED);

        } catch (UnirestException e) {
            logger.error("Error al solicitar la informacion del usuario.");
            throw new ApiException(service_unavailable.name(),
                    "[reason: " + e.getMessage() + " ] [method: AuthGPSClientImp.getUserData]",
                    HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }

    }
}
