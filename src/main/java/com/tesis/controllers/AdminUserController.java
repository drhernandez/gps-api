package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.AdminUserService;
import com.tesis.utils.JsonUtils;
import com.tesis.utils.filters.UserFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class AdminUserController {

    private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Inject
    AdminUserService adminUserService;


    public Object getAdminUsers(Request request, Response response) throws ApiException {

        ResponseDTO<List<AdminUsers>> responseDTO = adminUserService.getAdminUsers();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getAdminUsersByAdminUserID(Request request, Response response) throws ApiException {

        String param = request.params("admin_user_id");
        Long AdminUserID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_admin_user_id] [method: AdminUserController.getAdminUsersByAdminUserID]");
        }

        try {
            AdminUserID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_admin_user_id] [method: AdminUserController.getAdminUsersByAdminUserID]");
        }

        ResponseDTO<AdminUsers> responseDTO = adminUserService.getAdminUsersByAdminUserID(AdminUserID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object createAdminUser(Request request, Response response) throws ApiException {

        AdminUsers adminuser = JsonUtils.INSTANCE.GSON().fromJson(request.body(), AdminUsers.class);
        //Agregar validaciones

        ResponseDTO<AdminUsers> responseDTO = adminUserService.createAdminUser(adminuser);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateAdminUser(Request request, Response response) throws ApiException {

        String param = request.params("admin_user_id");
        Long AdminUserID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_admin_user_id] [method: AdminUserController.updateAdminUser]");
        }

        try {
            AdminUserID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_admin_user_id] [method: AdminUserController.updateAdminUser]");
        }

        AdminUsers adminUser = JsonUtils.INSTANCE.GSON().fromJson(request.body(), AdminUsers.class);
        //Add validations

        ResponseDTO<AdminUsers> responseDTO = adminUserService.updateAdminUser(AdminUserID, adminUser);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteAdminUser(Request request, Response response) throws  ApiException {
        String param = request.params("admin_user_id");
        Long AdminUserID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_admin_user_id] [method: AdminUserController.deleteAdminUser]");
        }
        try {
            AdminUserID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_admin_user_id] [method: AdminUserController.deleteAdminUser]");
        }
        ResponseDTO responseDTO = adminUserService.deleteAdminUser(AdminUserID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object adminUserSearch(Request request, Response response) throws ApiException{

        UserFilters filters = new UserFilters();
        Pagination pagination = new Pagination();

        filters.setStatus(request.queryParams("status"));
        filters.setEmail(request.queryParams("email"));
        filters.setName(request.queryParams("name"));
        filters.setLast_name(request.queryParams("last_name"));
        filters.setDni(request.queryParams("dni"));

        pagination.setPage(request.queryParams("page") != null ? Integer.valueOf(request.queryParams("page")) : 1);
        pagination.setLimit(request.queryParams("limit") != null ? Integer.valueOf(request.queryParams("limit")) : 10);

        ResponseDTO<Search> responseDTO = adminUserService.adminUserSearch(filters, pagination);
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
