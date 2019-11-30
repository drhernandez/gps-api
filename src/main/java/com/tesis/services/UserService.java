package com.tesis.services;

import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.utils.filters.UserFilters;

import java.util.List;

public interface UserService {

    ResponseDTO<List<Users>> getUsers();
    ResponseDTO<Users> activateUser(Long userId);
    ResponseDTO<Users> deactivateUser(Long userId);
    ResponseDTO<Users> getUsersByUserID(Long userID);
    ResponseDTO<Users> getUsersByDeviceId(Long deviceID);
    ResponseDTO<Users> createUser(Users user);
    ResponseDTO<Users> updateUser(Long userID, Users user);
    ResponseDTO<Users> deleteUser(Long userID);
    ResponseDTO<Search> userSearch(UserFilters userFilter, Pagination pagination);
}
