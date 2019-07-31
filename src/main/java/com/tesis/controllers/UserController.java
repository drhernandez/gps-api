package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.ResponseDTO;
import com.tesis.services.UserService;
import com.tesis.services.VehicleService;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class UserController {

    private static Logger logger = LoggerFactory.getLogger(TrackingController.class);

    @Inject
    UserService userService;
    @Inject
    VehicleService vehicleService;


    public Object userLogin(Request request, Response response) throws ApiException {
        String userName = request.queryParams("user_name");
        String pass = request.queryParams("pass");

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(pass)) {
            throw new ApiException("invalid_data", "[reason: invalid_credentials] [method: UserController.userLogin]");
        }

        ResponseDTO responseDTO = new ResponseDTO();

        if(userService.checkCredentials(userName, pass))
            response.status(200);
        else
            response.status(404);

        return responseDTO.getModelAsJson();
    }

    public Object getUsers(Request request, Response response) throws ApiException {

        ResponseDTO<List<Users>> responseDTO = userService.getUsers();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getUsersByUserID(Request request, Response response) throws ApiException {

        String param = request.params("user_id");
        Long userID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.getUserByUserID]");
        }

        try {
            userID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.getUserByUserID]");
        }

        ResponseDTO<Users> responseDTO = userService.getUsersByUserID(userID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object createUser(Request request, Response response) throws ApiException {

        Users user = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Users.class);
        //Agregar validaciones

        ResponseDTO<Users> responseDTO = userService.createUser(user);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateUser(Request request, Response response) throws ApiException {

        String param = request.params("user_id");
        Long userID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.updateUser]");
        }

        try {
            userID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.updateUser]");
        }

        Users user = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Users.class);
        //Add validations

        ResponseDTO<Users> responseDTO = userService.updateUser(userID, user);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteUser(Request request, Response response) throws  ApiException {
        String param = request.params("user_id");
        Long userID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.deleteUser]");
        }
        try {
            userID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.deleteUser]");
        }
        ResponseDTO responseDTO = userService.deleteUser(userID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getVehiclesByUserID(Request request, Response response) throws ApiException {
        String param = request.params("user_id");
        Long userID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.getVehiclesByUserID]");
        }
        try {
            userID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.getVehiclesByUserID]");
        }
        ResponseDTO responseDTO = vehicleService.getVehiclesByUserID(userID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
