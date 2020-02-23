package com.tesis.services;

import com.tesis.clients.imp.AuthGPSClientImp;
import com.tesis.exceptions.ApiException;
import com.tesis.services.imp.AuthServiceImp;
import kong.unirest.HttpMethod;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tesis.enums.ErrorCodes.unauthorized;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceUnitTest {

    private AuthGPSClientImp client;
    private AuthService authService;

    @Before
    public void setUp() {
        client = mock(AuthGPSClientImp.class);
        authService = new AuthServiceImp(client);
    }

    @Test
    public void validateTokenTest_ok(){
        try {

            Pattern patternMock = mock(Pattern.class);
            Matcher matcherMock = mock(Matcher.class);

            when(patternMock.matcher(anyString())).thenReturn(matcherMock);
            when(matcherMock.matches()).thenReturn(true);
            doNothing().when(client).validateToken(anyString(), anyString());

            authService.validateToken("token", HttpMethod.POST.name(), "/validate");

        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void validateTokenTest_error(){
        try {
            Pattern patternMock = mock(Pattern.class);
            Matcher matcherMock = mock(Matcher.class);

            when(patternMock.matcher(anyString())).thenReturn(matcherMock);
            when(matcherMock.matches()).thenReturn(true);
            doThrow(new ApiException(unauthorized.name(),
                    "[reason: access token expired ] [method: AuthGPSClientImp.validateToken]",
                    HttpServletResponse.SC_UNAUTHORIZED)).when(client).validateToken(anyString(), anyString());

            authService.validateToken("token", HttpMethod.POST.name(), "/validate");

        } catch (Exception e) {
            assertEquals(e.getMessage(), "[reason: access token expired ] [method: AuthGPSClientImp.validateToken]");
        }
    }
}
