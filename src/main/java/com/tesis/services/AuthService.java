package com.tesis.services;

import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AccessTokens;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;


public interface AuthService {

    Boolean checkUserCredentials(CredentialsDTO credentialsDTO);
    ResponseDTO<AccessTokens> getOrCreateAccessToken(CredentialsDTO credentialsDTO);
    void validateAccessToken(String jwt) throws ApiException;
    boolean checkUserPermissions(String jwt, Long idRequired);
    void checkTokenRequired(String jwt);
}
