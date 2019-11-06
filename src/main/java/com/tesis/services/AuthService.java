package com.tesis.services;

import com.tesis.jooq.tables.pojos.AccessTokens;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import io.jsonwebtoken.Claims;


public interface AuthService {

    Boolean checkUserCredentials(CredentialsDTO credentialsDTO);
    ResponseDTO<AccessTokens> checkAccessToken(CredentialsDTO credentialsDTO);//TODO renombrar
    void validateAccessToken(String jwt);

    //ResponseDTO<AccessTokens> createAccessToken(CredentialsDTO credentialsDTO);
}
