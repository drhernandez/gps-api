package com.tesis.services;

import com.google.inject.Inject;
import com.tesis.clients.SendGridClient;
import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.AdminRecoveryTokensDaoExt;
import com.tesis.daos.AdminUserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.daos.AdminUsersDao;
import com.tesis.jooq.tables.pojos.AdminRecoveryTokens;
import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.AdminRecoveryServiceImp;
import com.tesis.services.imp.AdminUserServiceImp;
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

@RunWith(MockitoJUnitRunner.class)
public class AdminRecoveryServiceUnitTest extends UnitTestConfigs {

    @Mock
    SendGridClient sendGridClient;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AdminRecoveryTokensDaoExt adminRecoveryTokensDao;

    @Mock
    AdminUserDaoExt adminUserDao;

    @InjectMocks
    AdminRecoveryServiceImp adminRecoveryService;

    @Test
    public void validateAdminRecoveryTokenTest_ok(){
        AdminRecoveryTokens recoveryToken = new AdminRecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        Mockito.when(adminRecoveryTokensDao.fetchOneByToken("token")).thenReturn(recoveryToken);
        ResponseDTO responseDTO = adminRecoveryService.validateAdminRecoveryToken("token");

        assertNull(responseDTO.getError());
    }

    @Test
    public void validateAdminRecoveryTokenTest_invalid(){
        AdminRecoveryTokens recoveryToken = new AdminRecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).minusDays(1)));

        Mockito.when(adminRecoveryTokensDao.fetchOneByToken(any())).thenReturn(recoveryToken);
        ResponseDTO responseDTO = adminRecoveryService.validateAdminRecoveryToken("token");

        assertEquals(responseDTO.getError().getError(), ErrorCodes.invalid_token.name());
        assertEquals(responseDTO.getError().getMessage(), "Token de recuperacion de admin invalido.");
    }

    @Test
    public void validateAdminRecoveryTokenTest_error(){
        Mockito.when(adminRecoveryTokensDao.fetchOneByToken(any())).thenReturn(null);

        ResponseDTO responseDTO = adminRecoveryService.validateAdminRecoveryToken("token");

        assertEquals(responseDTO.getError().getError(), ErrorCodes.not_found.name());
        assertEquals(responseDTO.getError().getMessage(), "No se encontro el token de admin.");
    }

    @Test
    public void createAdminRecoveryTokenTest_ok(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");

        AdminUsers user = new AdminUsers();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(adminUserDao.fetchOneByEmail(any(String.class))).thenReturn(user);
        Mockito.when(adminUserDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(adminRecoveryTokensDao.fetchOneByUserId(1L)).thenReturn(null);

        ResponseDTO responseDTO = adminRecoveryService.createAdminRecoveryToken(credentialsDTO,
                Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));
        assertNull(responseDTO.getError());
    }

    @Test
    public void createAdminRecoveryTokenTest_error(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");
        AdminRecoveryTokens recoveryToken = new AdminRecoveryTokens();
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).minusDays(1)));

        AdminUsers user = new AdminUsers();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(adminUserDao.fetchOneByEmail(any(String.class))).thenReturn(user);
//        Mockito.when(userDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(adminRecoveryTokensDao.fetchOneByUserId(1L)).thenReturn(recoveryToken);
        Mockito.doThrow(DataAccessException.class).when(adminRecoveryTokensDao).insert(any(AdminRecoveryTokens.class));

        ResponseDTO responseDTO = adminRecoveryService.createAdminRecoveryToken(credentialsDTO,
                Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        Mockito.verify(adminRecoveryTokensDao, Mockito.times(1)).deleteById(any(Long.class));
        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al crear el recovery token de admin.");
    }

    @Test
    public void updateAdminPasswordByTokenTest_ok(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");
        AdminRecoveryTokens recoveryToken = new AdminRecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        AdminUsers user = new AdminUsers();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(adminUserDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(adminRecoveryTokensDao.fetchOneByToken(any(String.class))).thenReturn(recoveryToken);
        Mockito.when(passwordEncoder.encode("passTest")).thenReturn("passEncoded");

        ResponseDTO responseDTO = adminRecoveryService.updateAdminPasswordByToken("token", credentialsDTO);

        Mockito.verify(adminRecoveryTokensDao,
                Mockito.times(1)).recoveryAdminUserPassword(any(AdminUsers.class));
        assertNull(responseDTO.getError());
    }

    @Test
    public void updateAdminPasswordByTokenTest_no_token(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");

        Mockito.when(adminRecoveryTokensDao.fetchOneByToken(any(String.class))).thenReturn(null);
        ResponseDTO responseDTO = adminRecoveryService.updateAdminPasswordByToken("token", credentialsDTO);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.not_found.name());
        assertEquals(responseDTO.getError().getMessage(), "No se encontro el usuario admin.");
    }

    @Test
    public void updateAdminPasswordByTokenTest_invalid(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");

        AdminRecoveryTokens recoveryToken = new AdminRecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).minusDays(1)));

        Mockito.when(adminRecoveryTokensDao.fetchOneByToken(any(String.class))).thenReturn(recoveryToken);
        ResponseDTO responseDTO = adminRecoveryService.updateAdminPasswordByToken("token", credentialsDTO);

        Mockito.verify(adminRecoveryTokensDao, Mockito.times(1)).deleteById(any(Long.class));
        assertEquals(responseDTO.getError().getError(), ErrorCodes.invalid_token.name());
        assertEquals(responseDTO.getError().getMessage(), "Token de recuperacion invalido.");
    }

    @Test
    public void updateAdminPasswordByTokenTest_error(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("pedropruebapedro@gmail.com", "passTest");
        AdminRecoveryTokens recoveryToken = new AdminRecoveryTokens();
        recoveryToken.setUserId(1L);
        recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(1)));

        AdminUsers user = new AdminUsers();
        user.setId(1L);
        user.setEmail("pedropruebapedro@gmail.com");

        Mockito.when(adminUserDao.fetchOneById(any(Long.class))).thenReturn(user);
        Mockito.when(adminRecoveryTokensDao.fetchOneByToken(any(String.class))).thenReturn(recoveryToken);
        Mockito.when(passwordEncoder.encode("passTest")).thenReturn("passEncoded");
        Mockito.doThrow(DataAccessException.class).when(adminRecoveryTokensDao).recoveryAdminUserPassword(any(AdminUsers.class));

        ResponseDTO responseDTO = adminRecoveryService.updateAdminPasswordByToken("token", credentialsDTO);
        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al updetear el usuario admin.");
    }
}
