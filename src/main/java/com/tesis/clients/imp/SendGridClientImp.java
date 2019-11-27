package com.tesis.clients.imp;

import com.google.inject.Inject;
import com.sendgrid.*;
import com.tesis.clients.SendGridClient;
import com.tesis.exceptions.ApiException;
import com.tesis.services.imp.AuthServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SendGridClientImp implements SendGridClient {

    private SendGrid sendGrid;

    @Inject
    public SendGridClientImp(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    @Override
    public void sendMail(Mail mail) throws IOException, ApiException{
        Request request = new Request();
        request.method = Method.POST;
        request.endpoint = "mail/send";
        try {
            request.body = mail.build();
            Response response = sendGrid.api(request);
            if (response.statusCode != 202 && response.statusCode != 201 && response.statusCode != 200 ){
                logger.error("Error al enviar email.");
                logger.error("Response code: " + response.statusCode + ", Body: " + response.body);
                logger.error("Headers: " + response.headers);
                throw new ApiException("email_error", "[reason: Response code: " + response.statusCode + ", Body: " + response.body + "] [method: SendGridClient.sendMail]");
            }
        } catch (Exception e) {
            logger.error("Error al enviar email");
            throw e;
        }
    }
}
