package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.tesis.clients.SendGridClient;
import com.tesis.daos.AdminRecoveryTokensDaoExt;
import com.tesis.daos.AdminUserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AdminRecoveryTokens;
import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AdminRecoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Singleton
public class AdminRecoveryServiceImp implements AdminRecoveryService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    @Inject
    SendGridClient sendGridClient;

    @Inject
    AdminRecoveryTokensDaoExt adminRecoveryDao;

    @Inject
    AdminUserDaoExt adminUserDao;

    @Inject
    PasswordEncoder passwordEncoder;


    @Override
    public ResponseDTO validateAdminRecoveryToken(String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        AdminRecoveryTokens adminRecoveryToken = adminRecoveryDao.fetchOneByToken(token);

        if (adminRecoveryToken != null) {
            if (adminRecoveryToken.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))) {
                adminRecoveryDao.deleteById(adminRecoveryToken.getUserId());
                responseDTO.error = new ApiException(ErrorCodes.invalid_token.toString(), "Token de recuperacion de admin invalido.", 498);
            }
        }
        else
            responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "No se encontro el token de admin.", 404);

        return responseDTO;
    }

    @Override
    public ResponseDTO createAdminRecoveryToken(CredentialsDTO credentialsDTO, Timestamp expirationDate) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        AdminRecoveryTokens adminRecoveryToken;

        try {
            AdminUsers adminUser = adminUserDao.fetchOneByEmail(credentialsDTO.getEmail());
            if (adminUser == null){
                logger.error("No se encontro el usuario admin.");
                responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "No se encontro el usuario admin.", 404);
                return responseDTO;
            }

            adminRecoveryToken = adminRecoveryDao.fetchOneByUserId(adminUser.getId());
            if (adminRecoveryToken == null)
                adminRecoveryToken = insertAdminRecoveryToken(adminUser.getId(), expirationDate);

            else
                if (adminRecoveryToken.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))) {
                    adminRecoveryDao.deleteById(adminUser.getId());
                    adminRecoveryToken = insertAdminRecoveryToken(adminUser.getId(), expirationDate);
                }

            sendAdminRecoveryMail(adminRecoveryToken);

        } catch (Exception e) {
            logger.error("No se pudo crear el recovery token de admin");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al crear el recovery token de admin.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO updateAdminPasswordByToken(String token, CredentialsDTO credentialsDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            AdminRecoveryTokens adminRecoveryToken = adminRecoveryDao.fetchOneByToken(token);

            if (adminRecoveryToken == null){
                logger.error("No se encontro el usuario admin.");
                responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "No se encontro el usuario admin.", 404);
                return responseDTO;
            }

            if (adminRecoveryToken.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))) {
                adminRecoveryDao.deleteById(adminRecoveryToken.getUserId());
                responseDTO.error = new ApiException(ErrorCodes.invalid_token.toString(), "Token de recuperacion invalido.", 498);
                return responseDTO;
            }

            AdminUsers user = adminUserDao.fetchOneById(adminRecoveryToken.getUserId());
            user.setPassword(passwordEncoder.encode(credentialsDTO.getPassword()));
            adminRecoveryDao.recoveryAdminUserPassword(user);
        }
        catch (Exception e) {
            logger.error("No se pudo updetear el usuario admin.");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al updetear el usuario admin.");
        }

        return responseDTO;
    }

    private AdminRecoveryTokens insertAdminRecoveryToken(Long userId, Timestamp expirationDate){
        AdminRecoveryTokens adminRecoveryToken = new AdminRecoveryTokens();

        try {
            adminRecoveryToken.setUserId(userId);
            adminRecoveryToken.setToken(UUID.randomUUID().toString());
            adminRecoveryToken.setExpirationDate(expirationDate);
            adminRecoveryDao.insert(adminRecoveryToken);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }

        return adminRecoveryToken;
    }

    private void sendAdminRecoveryMail(AdminRecoveryTokens adminRecoveryToken){
        AdminUsers adminUser = adminUserDao.fetchOneById(adminRecoveryToken.getUserId());

        Email from = new Email(System.getenv("SENDGRID_SENDER"));
        String subject = "GPS-TESIS Cambio de contraseña de administrador";
        Email to = new Email(adminUser.getEmail());
        Content content = new Content("text/plain",
                "Link de cambio de contraseña de administrador: " +
                        System.getenv("FRONT_DOMAIN") +"/reset-password/" + adminRecoveryToken.getToken());
        Mail mail = new Mail(from, subject, to, content);

        try {
            sendGridClient.sendMail(mail);
        } catch (Exception e) {
            logger.error(String.format("Error al enviar email de cambio de contraseña para el usuario admin %s. Reason: %s",
                    adminUser.toString(),
                    e.getMessage()));
        }
    }
}
