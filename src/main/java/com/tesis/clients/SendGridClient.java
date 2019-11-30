package com.tesis.clients;

import com.sendgrid.Mail;
import com.tesis.exceptions.ApiException;

import java.io.IOException;

public interface SendGridClient {

    /**
     * Envia emails con los parametros recibidos
     * @param mail
     * @throws IOException
     * @throws ApiException
     */
    void sendMail(Mail mail) throws IOException, ApiException;
}
