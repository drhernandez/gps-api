package com.tesis.services;

import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;


public interface RecoveryService {
    ResponseDTO validateRecoveryToken(String token);
    ResponseDTO createRecoveryToken(CredentialsDTO credentialsDTO);
    ResponseDTO updatePasswordByToken(String token, CredentialsDTO credentialsDTO);
}
