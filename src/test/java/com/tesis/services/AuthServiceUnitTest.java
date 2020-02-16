package com.tesis.services;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import com.tesis.clients.imp.AuthGPSClientImp;
import com.tesis.configs.UnitTestConfigs;
import com.tesis.services.imp.AuthServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceUnitTest  extends UnitTestConfigs {

    private Unirest unirest = Mockito.mock(Unirest.class);
    private AuthGPSClientImp authClient = new AuthGPSClientImp(unirest);
    private AuthServiceImp authService = new AuthServiceImp(authClient);

    @Test
    public void validateTokenTest_ok(){
        String token = "token";
        String url = "/validate";

        RequestBodyEntity requestBodyEntityMock = Mockito.mock(RequestBodyEntity.class);
        HttpRequestWithBody httpRequestWithBodyMock = Mockito.mock(HttpRequestWithBody.class);
        HttpResponse<String> responseMock = Mockito.mock(HttpResponse.class);


        Mockito.when(unirest.post(Mockito.anyString())).thenReturn(httpRequestWithBodyMock);

        Mockito.when(responseMock.getStatus()).thenReturn(200);

        try {
            Mockito.when(requestBodyEntityMock.asString()).thenReturn(responseMock);
            authService.validateToken(token, HttpMethod.POST.name(), url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
