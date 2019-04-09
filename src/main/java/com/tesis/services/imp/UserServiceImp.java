package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.daos.UsersDao;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.ResponseDTO;
import com.tesis.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class UserServiceImp implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Inject
    UsersDao usersDao;

    public ResponseDTO<List<Users>> getUsers() {
        return new ResponseDTO(usersDao.findAll(), null);
    }

    public ResponseDTO<Users> getUsersByUserID(Integer userID) {
        ResponseDTO<Users> responseDTO = new ResponseDTO(usersDao.fetchOneById(userID), null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> createUser(Users user) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        try {
            usersDao.insert(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el usuario %s", user.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el usuario.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> updateUser(Integer userID, Users newData) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();
        Users user = usersDao.fetchOneById(userID);
        user.setUserName(newData.getUserName());
        user.setPassword(newData.getPassword());
        user.setName(newData.getName());
        user.setLastName(newData.getLastName());
        user.setDni(newData.getDni());
        user.setAddress(newData.getAddress());
        user.setPhone(newData.getPhone());
        user.setEmail(newData.getEmail());
        try {
            usersDao.update(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error(String.format("No se pudo modificar el usuario %s", user.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el usuario.");
        }
        return responseDTO;
    }
}
