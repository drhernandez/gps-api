package com.tesis.services;

import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface UserService {

    Boolean checkCredentials(CredentialsDTO credentialsDTO);
    Boolean checkToken(CredentialsDTO credentialsDTO);
    ResponseDTO<List<Users>> getUsers();
    ResponseDTO<Users> getUsersByUserID(Long userID);
    ResponseDTO<Users> createUser(Users user);
    ResponseDTO<Users> updateUser(Long userID, Users user);
    ResponseDTO<Users> deleteUser(Long userID);
}
