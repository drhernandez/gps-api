package com.tesis.clients;

import com.sendgrid.Mail;
import com.tesis.exceptions.ApiException;

import java.io.IOException;

public interface SendGridClient {

    /**
     * Envía un mail a magoya
     * @param mail
     * @return
     */
    void sendMail(Mail mail) throws IOException, ApiException;
}
