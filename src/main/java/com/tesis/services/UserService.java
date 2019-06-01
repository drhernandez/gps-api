package com.tesis.services;

import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.ResponseDTO;

import javax.xml.ws.Response;
import java.util.List;

public interface UserService {

    ResponseDTO<List<Users>> getUsers();
    ResponseDTO<Users> getUsersByUserID(Long userID);
    ResponseDTO<Users> createUser(Users user);
    ResponseDTO<Users> updateUser(Long userID, Users user);
    ResponseDTO<Users> deleteUser(Long userID);
}
