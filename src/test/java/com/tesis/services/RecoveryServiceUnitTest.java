package com.tesis.services;

import com.tesis.clients.SendGridClient;
import com.tesis.daos.RecoveryTokensDaoExt;
import com.tesis.daos.UserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.pojos.RecoveryTokens;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.RecoveryServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class RecoveryServiceUnitTest {
    @Mock
    RecoveryTokensDaoExt recoveryDao;

    @Mock
    UserDaoExt userDao;

    @Mock
    SendGridClient sendGridClient;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    RecoveryServiceImp recoveryService;

    @Test
    public void validateRecoveryTokenTest_valid(){
        RecoveryTokens recoveryToken = new RecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        Mockito.when(recoveryDao.fetchOneByToken(any())).thenReturn(recoveryToken);
        ResponseDTO responseDTO = recoveryService.validateRecoveryToken("token");

        assertNull(responseDTO.getError());
    }

    @Test
    public void validateRecoveryTokenTest_invalid(){
        RecoveryTokens recoveryToken = new RecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).minusDays(1)));

        Mockito.when(recoveryDao.fetchOneByToken(any())).thenReturn(recoveryToken);
        ResponseDTO responseDTO = recoveryService.validateRecoveryToken("token");

        assertEquals(responseDTO.getError().getError(), ErrorCodes.invalid_token.name());
        assertEquals(responseDTO.getError().getMessage(), "Token de recuperacion invalido.");
    }

    @Test
    public void validateRecoveryTokenTest_error(){
        Mockito.when(recoveryDao.fetchOneByToken(any())).thenReturn(null);

        ResponseDTO responseDTO = recoveryService.validateRecoveryToken("token");

        assertEquals(responseDTO.getError().getError(), ErrorCodes.not_found.name());
        assertEquals(responseDTO.getError().getMessage(), "No se encontro el token.");
    }

    @Test
    public void createRecoveryTokenTest_ok(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");

        Users user = new Users();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(userDao.fetchOneByEmail(any(String.class))).thenReturn(user);
        Mockito.when(userDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(recoveryDao.fetchOneByUserId(1L)).thenReturn(null);

        ResponseDTO responseDTO = recoveryService.createRecoveryToken(credentialsDTO, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));
        assertNull(responseDTO.getError());
    }

    @Test
    public void createRecoveryTokenTest_error(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");
        RecoveryTokens recoveryToken = new RecoveryTokens();
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).minusDays(1)));

        Users user = new Users();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(userDao.fetchOneByEmail(any(String.class))).thenReturn(user);
        Mockito.when(userDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(recoveryDao.fetchOneByUserId(1L)).thenReturn(recoveryToken);
        Mockito.doThrow(DataAccessException.class).when(recoveryDao).insert(any(RecoveryTokens.class));

        ResponseDTO responseDTO = recoveryService.createRecoveryToken(credentialsDTO, Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        Mockito.verify(recoveryDao, times(1)).deleteById(any(Long.class));
        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al crear el recovery token.");
    }

    @Test
    public void updatePasswordByTokenTest_ok(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");
        RecoveryTokens recoveryToken = new RecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        Users user = new Users();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(userDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(recoveryDao.fetchOneByToken(any(String.class))).thenReturn(recoveryToken);
        Mockito.when(passwordEncoder.encode("passTest")).thenReturn("passEncoded");

        ResponseDTO responseDTO = recoveryService.updatePasswordByToken("token", credentialsDTO);

        Mockito.verify(recoveryDao, times(1)).recoveryUserPassword(any(Users.class));
        assertNull(responseDTO.getError());
    }

    @Test
    public void updatePasswordByTokenTest_no_token(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");

        Mockito.when(recoveryDao.fetchOneByToken(any(String.class))).thenReturn(null);
        ResponseDTO responseDTO = recoveryService.updatePasswordByToken("token", credentialsDTO);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.not_found.name());
        assertEquals(responseDTO.getError().getMessage(), "No se encontro el usuario.");
    }

    @Test
    public void updatePasswordByTokenTest_invalid(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");

        RecoveryTokens recoveryToken = new RecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).minusDays(1)));

        Mockito.when(recoveryDao.fetchOneByToken(any(String.class))).thenReturn(recoveryToken);
        ResponseDTO responseDTO = recoveryService.updatePasswordByToken("token", credentialsDTO);

        Mockito.verify(recoveryDao, times(1)).deleteById(any(Long.class));
        assertEquals(responseDTO.getError().getError(), ErrorCodes.invalid_token.name());
        assertEquals(responseDTO.getError().getMessage(), "Token de recuperacion invalido.");
    }


    @Test
    public void updatePasswordByTokenTest_error(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");
        RecoveryTokens recoveryToken = new RecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        Users user = new Users();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(userDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(recoveryDao.fetchOneByToken(any(String.class))).thenReturn(recoveryToken);
        Mockito.when(passwordEncoder.encode("passTest")).thenReturn("passEncoded");
        Mockito.doThrow(DataAccessException.class).when(recoveryDao).recoveryUserPassword(any(Users.class));

        ResponseDTO responseDTO = recoveryService.updatePasswordByToken("token", credentialsDTO);
        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al crear el recovery token.");
    }
}
