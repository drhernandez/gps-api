package com.tesis.services;

import com.tesis.exceptions.ApiException;

public interface AuthService {
    String validateToken(String token) throws ApiException;
}
