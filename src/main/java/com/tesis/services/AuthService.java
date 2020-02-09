package com.tesis.services;

import com.tesis.exceptions.ApiException;

public interface AuthService {
    void validateToken(String token, String method, String uri) throws ApiException;
}
