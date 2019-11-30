package com.tesis.controllers;

import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.RecoveryServiceImp;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;

import static com.tesis.config.Constants.EXPIRATION_RECOVERY_TIME;

public class RecoveryController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Inject
    RecoveryServiceImp recoveryService;

    public Object createRecoveryPasswordToken(Request request, Response response) throws ApiException {
        CredentialsDTO credentialsDTO = JsonUtils.INSTANCE.GSON().fromJson(request.body(), CredentialsDTO.class);

        ResponseDTO responseDTO = recoveryService.createRecoveryToken(credentialsDTO,
                Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(EXPIRATION_RECOVERY_TIME)));

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        response.status(200);
        return responseDTO;
    }

    public Object validateRecoveryPasswordToken(Request request, Response response) throws ApiException {
        String token = request.params("recovery_token");

        ResponseDTO responseDTO = recoveryService.validateRecoveryToken(token);
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }
        response.status(200);
        return responseDTO;
    }

    public Object updatePasswordFromRecovery(Request request, Response response) throws ApiException {
        String token = request.params("recovery_token");
        CredentialsDTO credentialsDTO = JsonUtils.INSTANCE.GSON().fromJson(request.body(), CredentialsDTO.class);
        ResponseDTO responseDTO = new ResponseDTO();

        if (credentialsDTO.getPassword() == null){
            logger.error("Password es requerida.");
            responseDTO.error = new ApiException(ErrorCodes.not_found.toString(), "Password es requerida.", 404);
            return responseDTO;
        }

        responseDTO = recoveryService.updatePasswordByToken(token, credentialsDTO);
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }
        response.status(200);
        return responseDTO;
    }
}
