package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.AuthAdminService;
import com.tesis.services.AuthService;
import com.tesis.services.UserService;
import com.tesis.services.VehicleService;
import com.tesis.utils.JsonUtils;
import com.tesis.utils.filters.UserFilters;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private VehicleService vehicleService;
    private AuthService authService;
    private AuthAdminService authAdminService;

    @Inject
    public UserController(UserService userService,
                          VehicleService vehicleService,
                          AuthService authService,
                          AuthAdminService authAdminService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.authService = authService;
        this.authAdminService = authAdminService;
    }

    public Object activateUser(Request request, Response response) throws ApiException{

        String param = request.params("user_id");
        Long userID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.activateUser]");
        }
        try {
            userID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.activateUser]");
        }

        ResponseDTO<Users> responseDTO = userService.activateUser(userID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deactivateUser(Request request, Response response) throws ApiException{

        String param = request.params("user_id");
        Long userID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.deactivateUser]");
        }
        try {
            userID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_user_id] [method: UserController.deactivateUser]");
        }

        ResponseDTO<Users> responseDTO = userService.deactivateUser(userID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


    public Object getUsers(Request request, Response response) throws ApiException {

        ResponseDTO<List<Users>> responseDTO = new ResponseDTO<>();
        String accessToken = request.headers("Authorization").split(" ")[1];
        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
            logger.error("User access unauthorized [method: UserController.getUsers]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = userService.getUsers();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getUsersByUserID(Request request, Response response) throws ApiException {

        String accessToken = request.headers("Authorization").split(" ")[1];
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

        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        if (!authAdminService.checkAdminUserPermissions(accessToken, userID)) {
            if (!authService.checkUserPermissions(accessToken, userID)){
                logger.error("User access unauthorized [method: UserController.getUserByUserID]");
                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
                throw responseDTO.error;
            }
        }

        responseDTO = userService.getUsersByUserID(userID);

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
        String accessToken = request.headers("Authorization").split(" ")[1];
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

        ResponseDTO<Users> responseDTO = new ResponseDTO<>();
        if (!authAdminService.checkAdminUserPermissions(accessToken, userID)) {
            if (!authService.checkUserPermissions(accessToken, userID)){
                logger.error("User access unauthorized [method: UserController.updateUser]");
                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
                throw responseDTO.error;
            }
        }

        Users user = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Users.class);
        //Add validations

        responseDTO = userService.updateUser(userID, user);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteUser(Request request, Response response) throws  ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
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

        ResponseDTO responseDTO = new ResponseDTO();
        if (!authAdminService.checkAdminUserPermissions(accessToken, userID)) {
            logger.error("User access unauthorized [method: UserController.deleteUser]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        responseDTO = userService.deleteUser(userID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getVehiclesByUserID(Request request, Response response) throws ApiException {
        String accessToken = request.headers("Authorization").split(" ")[1];
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

        ResponseDTO responseDTO = new ResponseDTO();
        if (!authAdminService.checkAdminUserPermissions(accessToken, userID)) {
            if (!authService.checkUserPermissions(accessToken, userID)){
                logger.error("User access unauthorized [method: UserController.getVehiclesByUserID]");
                responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
                throw responseDTO.error;
            }
        }

        responseDTO = vehicleService.getVehiclesByUserID(userID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object userSearch(Request request, Response response) throws ApiException{

        String accessToken = request.headers("Authorization").split(" ")[1];

        ResponseDTO<Search> responseDTO = new ResponseDTO<>();
        if (!authAdminService.checkAdminUserPermissions(accessToken, null)) {
            logger.error("User access unauthorized [method: UserController.updateUser]");
            responseDTO.setError(new ApiException("401", ErrorCodes.unauthorized.name(), HttpStatus.UNAUTHORIZED_401));
            throw responseDTO.error;
        }

        UserFilters filters = new UserFilters();
        Pagination pagination = new Pagination();

        filters.setStatus(request.queryParams("status"));
        filters.setEmail(request.queryParams("email"));
        filters.setName(request.queryParams("name"));
        filters.setLast_name(request.queryParams("last_name"));
        filters.setDni(request.queryParams("dni"));

        pagination.setPage(request.queryParams("page") != null ? Integer.valueOf(request.queryParams("page")) : 1);
        pagination.setLimit(request.queryParams("limit") != null ? Integer.valueOf(request.queryParams("limit")) : 10);

        responseDTO = userService.userSearch(filters, pagination);
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
