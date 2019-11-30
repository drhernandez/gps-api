package com.tesis.services;

import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;

import java.sql.Timestamp;


public interface RecoveryService {
    ResponseDTO validateRecoveryToken(String token);
    ResponseDTO createRecoveryToken(CredentialsDTO credentialsDTO, Timestamp expirationDate);
    ResponseDTO updatePasswordByToken(String token, CredentialsDTO credentialsDTO);
}
