package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.jooq.tables.daos.UsersDao;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.UserService;

import java.util.List;

@Singleton
public class UserServiceImp implements UserService {

    @Inject
    UsersDao usersDao;

    public ResponseDTO<List<Devices>> getUsers() {
        return new ResponseDTO(usersDao.findAll(), null);
    }
}
