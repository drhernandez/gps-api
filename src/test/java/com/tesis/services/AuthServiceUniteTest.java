package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.AccessTokenDaoExt;
import com.tesis.daos.UserDaoExt;
import com.tesis.jooq.tables.pojos.AccessTokens;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.AuthServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


@RunWith(MockitoJUnitRunner.class)
public class AuthServiceUniteTest extends UnitTestConfigs {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AccessTokenDaoExt accessTokensDao;

    @Mock
    UserDaoExt usersDao;

    @InjectMocks
    AuthServiceImp authService;

    @Test
    public void checkUserCredentialsTest_ok(){
        Users user = mock(Users.class);

        Mockito.when(usersDao.fetchOneByEmail(any())).thenReturn(user);
        Mockito.when(passwordEncoder.matches(any(),any())).thenReturn(true);

        assertTrue(authService.checkUserCredentials(mock(CredentialsDTO.class)));
    }

    @Test
    public void checkUserCredentialsTest_error(){
        Mockito.when(usersDao.fetchOneByEmail(any())).thenReturn(null);

        assertFalse(authService.checkUserCredentials(mock(CredentialsDTO.class)));
    }

    @Test
    public void checkAccessTokenTest_ok(){
        AccessTokens token = new AccessTokens(1L, "eyJ0eXBlIjoiYWNjZXNzLXRva2VuIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJVc2VyIiwiZXhwIjo3ODgzNzM3Nzc3LCJpYXQiOjE1NzIzOTA1NzcsInVzZXJJZCI6MSwidXNlck5hbWUiOiJOaWNvbGFzIiwidXNlckxhc3ROYW1lIjoiQ2FyZ25lbHV0dGkiLCJ1c2VyRW1haWwiOiJuZWNhcmduZWx1dHRpQGdtYWlsLmNvbSJ9.2WAogWSSsxggZQfye2HN-EQWuYX_7IIscyOMpFhALFS8A-7cfhl4tfkM-m1sAogfZgRhY2XvWZ0pAY5xhjnehA");
        Users user = new Users();
        user.setId(1L);

        Mockito.when(usersDao.fetchOneByEmail(any())).thenReturn(mock(Users.class));
        Mockito.when(accessTokensDao.fetchOneByUserId(any())).thenReturn(token);

        assertEquals(token.getToken(), authService.checkAccessToken(mock(CredentialsDTO.class)).model.getToken());
    }

    @Test
    public void checkAccessTokenTest_invalidToken(){
        AccessTokens token = new AccessTokens(1L, "eyJ0eXBlIjoiYWNjZXNzLXRva2VuIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJVc2VyIiwiZXhwIjo3ODgzNzM3Nzc3LCJpYXQiOjE1NzIzOTA1NzcsInVzZXJJZCI6MSwidXNlck5hbWUiOiJOaWNvbGFzIiwidXNlckxhc3ROYW1lIjoiQ2FyZ25lbHV0dGkiLCJ1c2VyRW1haWwiOiJuZWNhcmduZWx1dHRpQGdtYWlsLmNvbSJ9.2WAogWSSsxggZQfye2HN-EQWuYX_7IIscyOMpFhALFS8A-7cfhl4tfkM-m1sAogfZgRhY2XvWZ0pAY5xhjnehb");
        Users user = new Users();
        user.setId(1L);

        Mockito.when(usersDao.fetchOneByEmail(any())).thenReturn(mock(Users.class));
        Mockito.when(accessTokensDao.fetchOneByUserId(any())).thenReturn(token);

        ResponseDTO<AccessTokens> responseDTO = authService.checkAccessToken(mock(CredentialsDTO.class));

        assertNotEquals(token.getToken(), responseDTO.getModel().getToken());
        Mockito.verify(accessTokensDao, times(1)).insert(any(AccessTokens.class));
    }
}
