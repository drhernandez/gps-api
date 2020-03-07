package com.tesis.clients;

import com.tesis.exceptions.ApiException;
import com.tesis.models.SMSRequest;

public interface SMSClient {

    void sendSMS(SMSRequest smsRequest) throws ApiException;
}
