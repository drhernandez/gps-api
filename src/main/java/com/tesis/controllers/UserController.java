package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;
import com.tesis.services.UserService;
import spark.Request;
import spark.Response;

import java.util.List;

public class UserController {

    @Inject
    UserService userService;

    public Object getUsers(Request request, Response response) throws ApiException {

        ResponseDTO<List<Devices>> responseDTO = userService.getUsers();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
