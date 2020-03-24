package com.tesis.clients;

import com.tesis.clients.imp.AuthGPSClientImp;
import com.tesis.models.UserDTO;
import kong.unirest.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthGPSClientTest {

    private UnirestInstance instance;
    private AuthGPSClient client;

    @Before
    public void setUp() {
        instance = mock(UnirestInstance.class);
        client = new AuthGPSClientImp(instance);
    }

    @Test
    public void validateToken_ok() {

        HttpRequestWithBody httpRequestWithBody = mock(HttpRequestWithBody.class);
        RequestBodyEntity requestBodyEntity = mock(RequestBodyEntity.class);
        HttpResponse httpResponse = mock(HttpResponse.class);

        when(instance.post(anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.header(anyString(), anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.body(anyString())).thenReturn(requestBodyEntity);
        when(requestBodyEntity.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(200);

        try {
            client.validateToken("token", "privileges");
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void validateToken_unauthorized() {

        HttpRequestWithBody httpRequestWithBody = mock(HttpRequestWithBody.class);
        RequestBodyEntity requestBodyEntity = mock(RequestBodyEntity.class);
        HttpResponse httpResponse = mock(HttpResponse.class);

        when(instance.post(anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.header(anyString(), anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.body(anyString())).thenReturn(requestBodyEntity);
        when(requestBodyEntity.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(401);

        try {
            client.validateToken("token", "privileges");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Unauthorized");
        }
    }

    @Test
    public void validateToken_forbidden() {

        HttpRequestWithBody httpRequestWithBody = mock(HttpRequestWithBody.class);
        RequestBodyEntity requestBodyEntity = mock(RequestBodyEntity.class);
        HttpResponse httpResponse = mock(HttpResponse.class);

        when(instance.post(anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.header(anyString(), anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.body(anyString())).thenReturn(requestBodyEntity);
        when(requestBodyEntity.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(403);

        try {
            client.validateToken("token", "privileges");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Unauthorized");
        }
    }

    @Test
    public void validateToken_error() {

        HttpRequestWithBody httpRequestWithBody = mock(HttpRequestWithBody.class);
        RequestBodyEntity requestBodyEntity = mock(RequestBodyEntity.class);
        HttpResponse httpResponse = mock(HttpResponse.class);

        when(instance.post(anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.header(anyString(), anyString())).thenReturn(httpRequestWithBody);
        when(httpRequestWithBody.body(anyString())).thenReturn(requestBodyEntity);
        when(requestBodyEntity.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(503);

        try {
            client.validateToken("token", "privileges");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Unauthorized");
        }
    }


    @Test
    public void getUserData_ok(){

        String userMock = "{\n" +
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
                "    \"phone\": \"+543515495416\"\n" +
                "}";

        GetRequest getRequestMock = mock(GetRequest.class);
        HttpResponse httpResponseMock = mock(HttpResponse.class);

        when(instance.get(anyString())).thenReturn(getRequestMock);
        when(getRequestMock.header(anyString(), anyString())).thenReturn(getRequestMock);
        when(getRequestMock.asString()).thenReturn(httpResponseMock);
        when(httpResponseMock.getStatus()).thenReturn(200);
        when(httpResponseMock.getBody()).thenReturn(userMock);


        try {
            UserDTO user = client.getUserData(13L);
            assertEquals(new Long(13), user.getId());
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void getUserData_error(){

        GetRequest getRequestMock = mock(GetRequest.class);
        HttpResponse httpResponseMock = mock(HttpResponse.class);

        when(instance.get(anyString())).thenReturn(getRequestMock);
        when(getRequestMock.header(anyString(), anyString())).thenReturn(getRequestMock);
        when(getRequestMock.asString()).thenReturn(httpResponseMock);
        when(httpResponseMock.getStatus()).thenReturn(503);

        try {
            UserDTO user = client.getUserData(13L);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "[reason: unauthorized ] [method: AuthGPSClientImp.getUserData]");
        }
    }
}
