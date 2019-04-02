package com.tesis.services;

import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface UserService {

    ResponseDTO<List<Users>> getUsers();
    ResponseDTO<List<Users>> getUsersByUserID(Long userID);
}
