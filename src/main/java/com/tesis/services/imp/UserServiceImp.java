package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.daos.UsersDao;
import com.tesis.jooq.tables.pojos.Devices;
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

    public ResponseDTO<List<Users>> getUsersByUserID(Integer userID) {
        ResponseDTO<List<Users>> responseDTO = new ResponseDTO();
        responseDTO.model = usersDao.fetchById(userID);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> createUser(Users users) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        try {
            usersDao.insert(users);
            responseDTO.model = users;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el usuario %s", users.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el usuario");
        }

        return responseDTO;
    }
}
