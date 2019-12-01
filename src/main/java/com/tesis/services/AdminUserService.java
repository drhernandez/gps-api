package com.tesis.services;

import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.utils.filters.UserFilters;

import java.util.List;

public interface AdminUserService {

    ResponseDTO<List<AdminUsers>> getAdminUsers();
    ResponseDTO<AdminUsers> getAdminUsersByAdminUserID(Long userID);
    ResponseDTO<AdminUsers> createAdminUser(AdminUsers user);
    ResponseDTO<AdminUsers> updateAdminUser(Long userID, AdminUsers user);
    ResponseDTO<AdminUsers> deleteAdminUser(Long userID);
    ResponseDTO<Search> adminUserSearch(UserFilters userFilter, Pagination pagination);
}
