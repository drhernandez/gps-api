package com.tesis.clients.imp;

import com.google.inject.Inject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import com.tesis.clients.AuthGPSClient;
import com.tesis.exceptions.ApiException;
import com.tesis.models.UserDTO;
import com.tesis.utils.JsonUtils;
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

    private static String baseUrl = "https://gps-auth.herokuapp.com";
    Logger logger = LoggerFactory.getLogger(AuthGPSClientImp.class);

    @Override
    public void validateToken(String token, String privileges) throws ApiException {
        try {
            String url = baseUrl + "/validate";
            String body =  "{\"privileges\": " + privileges + "}";

            RequestBodyEntity responseBodyEntity = unirest.post(url)
                    .header("x-access-token", token)
                    .body(body);

            HttpResponse<String> response = responseBodyEntity.asString();


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
                "    \"phone\": \"3515495416\"\n" +
                "}";

        UserDTO user = JsonUtils.INSTANCE.GSON().fromJson(mock, UserDTO.class);
        System.out.println(user);
        return user;

        /*

        try {
            String url = baseUrl + "/users/" + userID;
            HttpResponse<String> response = unirest.get(url).asString();



            if (response.getStatus() == 200)
                return JsonUtils.INSTANCE.GSON().fromJson(response.getBody(), UserDTO.class);
            else
                throw new ApiException(unauthorized.name(),
                        "[reason: access token expired ] [method: AuthGPSClientImp.getUserData]",
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
