package com.tesis.clients.imp;

import com.google.inject.Inject;
import com.tesis.clients.SMSClient;
import com.tesis.exceptions.ApiException;
import com.tesis.models.SMSRequest;
import com.tesis.routes.Router;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import static com.tesis.enums.ErrorCodes.*;

public class SMSClientImp implements SMSClient {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);
    private final UnirestInstance instance;
    private static final String url = "https://rest.messagebird.com/messages";

    @Inject
    public SMSClientImp(UnirestInstance instance) {
        this.instance = instance;
    }

    @Override
    public void sendSMS(SMSRequest smsRequest) throws ApiException {

        try {

            String body = "{" +
                    "\"recipients\": \"" + smsRequest.getReceptor() + "\", " +
                    "\"originator\": \"" + System.getenv("SMS_ORIGINATOR") + "\", " +
                    "\"body\": \"" + smsRequest.getMessage() + " \"" +
                    "}";

            HttpResponse<String> result = instance.post(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "AccessKey " + System.getenv("MESSAGE_BIRD_ACCESS_KEY"))
                    .body(body).asString();

            switch (result.getStatus()) {
                case 201:
                    logger.info("SMS enviado a " + smsRequest.getReceptor());
                    break;
                case 401:
                    logger.error(String.format("Unauthorized. Code: %s. Reason: %s", result.getStatus(), result.getBody()));
                    throw new ApiException(unauthorized.name(),
                            String.format("ERROR. Reason: %s", result.getBody()),
                            HttpServletResponse.SC_UNAUTHORIZED);
                default:
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s", result.getStatus(), result.getBody()));
                    throw new ApiException(internal_error.name(),
                            "no messages available",
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e){
            logger.error(e.getMessage());
            throw new ApiException(internal_error.name(), e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
