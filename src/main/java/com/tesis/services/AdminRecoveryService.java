package com.tesis.services;

import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;

import java.sql.Timestamp;


public interface AdminRecoveryService {
    ResponseDTO validateAdminRecoveryToken(String token);
    ResponseDTO createAdminRecoveryToken(CredentialsDTO credentialsDTO, Timestamp expirationDate);
    ResponseDTO updateAdminPasswordByToken(String token, CredentialsDTO credentialsDTO);
}
