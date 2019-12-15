package com.tesis.clients;

import com.tesis.exceptions.ApiException;
import com.tesis.models.SMSRequest;

public interface SendSMSCClient {

    void sendSMS(SMSRequest smsRequest) throws ApiException;
    void sendAlertSMS(SMSRequest smsRequest, String alertType) throws ApiException;
}
