package com.tesis.services;

import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AdminAccessTokens;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;


public interface AuthAdminService {

    Boolean checkAdminUserCredentials(CredentialsDTO credentialsDTO);
    ResponseDTO<AdminAccessTokens> getOrCreateAdminAccessToken(CredentialsDTO credentialsDTO);
    void checkAdminAccessToken(String jwt) throws ApiException;

}
