package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.RecoveryTokensDaoExt;
import com.tesis.daos.UserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.RecoveryTokens;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.RecoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import com.sendgrid.*;
import java.io.IOException;

import static com.tesis.config.Constants.EXPIRATION_RECOVERY_TIME;

@Singleton
public class RecoveryServiceImp implements RecoveryService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    @Inject
    RecoveryTokensDaoExt recoveryDao;

    @Inject
    UserDaoExt userDao;

    @Inject
    PasswordEncoder passwordEncoder;


    @Override
    public ResponseDTO validateRecoveryToken(String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        RecoveryTokens recoveryToken = recoveryDao.fetchOneByToken(token);

        if (recoveryToken != null) {
            if (recoveryToken.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))) {
                recoveryDao.deleteById(recoveryToken.getUserId());
                responseDTO.error = new ApiException(ErrorCodes.invalid_token.toString(), "Token de recuperacion invalido.", 498);
            }
        }
        else
            responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "No se encontro el token.", 404);

        return responseDTO;
    }

    @Override
    public ResponseDTO createRecoveryToken(CredentialsDTO credentialsDTO) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        RecoveryTokens recoveryToken;

        try {
            Users user = userDao.fetchOneByEmail(credentialsDTO.getEmail());
            if (user == null){
                logger.error("No se encontro el usuario.");
                responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "No se encontro el usuario.", 404);
                return responseDTO;
            }

            recoveryToken = recoveryDao.fetchOneByUserId(user.getId());
            if (recoveryToken == null)
                recoveryToken = insertRecoveryToken(user.getId());

            else
                if (recoveryToken.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))) {
                    recoveryDao.deleteById(user.getId());
                    recoveryToken = insertRecoveryToken(user.getId());
                }

            sendRecoveryMail(recoveryToken);

        } catch (Exception e) {
            logger.error("No se pudo crear el recovery token");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al crear el recovery token.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO updatePasswordByToken(String token, CredentialsDTO credentialsDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            RecoveryTokens recoveryToken = recoveryDao.fetchOneByToken(token);

            if (recoveryToken == null){
                logger.error("No se encontro el usuario.");
                responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "No se encontro el usuario.", 404);
                return responseDTO;
            }

            if (recoveryToken.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())))) {
                recoveryDao.deleteById(recoveryToken.getUserId());
                responseDTO.error = new ApiException(ErrorCodes.invalid_token.toString(), "Token de recuperacion invalido.", 498);
                return responseDTO;
            }

            Users user = userDao.fetchOneById(recoveryToken.getUserId());
            user.setPassword(passwordEncoder.encode(credentialsDTO.getPassword()));
            recoveryDao.recoveryUserPassword(user);
        }
        catch (Exception e) {
            logger.error("No se pudo updetear el usuario.");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al crear el recovery token.");
        }

        return responseDTO;
    }

    private RecoveryTokens insertRecoveryToken(Long userId){
        RecoveryTokens recoveryToken = new RecoveryTokens();

        try {
            recoveryToken.setUserId(userId);
            recoveryToken.setToken(UUID.randomUUID().toString());
            recoveryToken.setExpirationDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(EXPIRATION_RECOVERY_TIME)));
            recoveryDao.insert(recoveryToken);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }

        return recoveryToken;
    }

    private void sendRecoveryMail(RecoveryTokens recoveryToken) throws IOException{
        Users user = userDao.fetchOneById(recoveryToken.getUserId());

        Email from = new Email(System.getenv("SENDGRID_SENDER"));
        String subject = "GPS-TESIS Recuperacion de contraseña";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain",
                "Link de recuperacion de contraseña: "+ System.getenv("FRONT_DOMAIN") +"/reset-password/" + recoveryToken.getToken());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            if (response.statusCode != 202){
                logger.error("Error al enviar el mail de recuperacion.");
                logger.error("Response code: " + response.statusCode + ", Body: " + response.body);
                logger.error("Headers: " + response.headers);
            }
            logger.info(String.format("Email de recuperacion enviado para el usuario %S", user.toString()));
        } catch (IOException e) {
            logger.error("No se pudo enviar el mail de recuperacion.");
            throw e;
        }
    }
}
