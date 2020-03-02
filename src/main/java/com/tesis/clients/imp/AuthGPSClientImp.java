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

            HttpResponse response = responseBodyEntity.asEmpty();

            switch (response.getStatus()) {
                case 200:
                    break;
                case 401:
                    throw new ApiException(unauthorized.name(),
                            "[reason: access token expired ] [method: AuthGPSClientImp.validateToken]",
                            HttpServletResponse.SC_UNAUTHORIZED);
                case 403:
                    throw new ApiException(forbidden.name(),
                            "[reason: forbidden ] [method: AuthGPSClientImp.validateToken]",
                            HttpServletResponse.SC_FORBIDDEN);
                default:
                    throw new ApiException(internal_error.name(),
                            "[reason: internal server error ] [method: AuthGPSClientImp.validateToken]",
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (UnirestException e) {
            logger.error("Error al solicitar la validación del token.");
            throw new ApiException(service_unavailable.name(),
                    "[reason: " + e.getMessage() + " ] [method: AuthGPSClientImp.validateToken]",
                    HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public UserDTO  getUserData(Long userID) throws ApiException {

        String mock = "{\n" +
                "    \"id\": 13,\n" +
                "    \"status\": \"INACTIVE\",\n" +
                "    \"role\":\n" +
                "      {\n" +
                "          \"name\": \"ADMIN\",\n" +
                "          \"privileges\": [\n" +
                "              {\n" +
                "                  \"name\": \"GET_CLIENT\"\n" +
                "              },\n" +
                "              {\n" +
                "                  \"name\": \"CREATE_CLIENT\"\n" +
                "              }\n" +
                "          ]\n" +
                "      },\n" +
                "    \"email\": \"ddrhernandez92@gmail.com\",\n" +
                "    \"name\": \"Diego\",\n" +
                "    \"last_name\": \"Hernández\",\n" +
                "    \"dni\": \"36354805\",\n" +
                "    \"address\": \"Tomás de Irobi 165\",\n" +
                "    \"phone\": \"3525-480782\"\n" +
                "}";

        UserDTO user = JsonUtils.INSTANCE.GSON().fromJson(mock, UserDTO.class);
        System.out.println(user);
        return user;

// ------------- TODO remove mock --------------
/*
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
         */
    }
}
