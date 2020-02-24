package com.tesis.services;

import com.tesis.exceptions.ApiException;
import com.tesis.models.UserDTO;

public interface AuthService {
    void validateToken(String token, String method, String uri) throws ApiException;
    UserDTO getUser(Long userID);
}
