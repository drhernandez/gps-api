package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.AdminUserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.AdminUserService;
import com.tesis.services.RecoveryService;
import com.tesis.utils.filters.UserFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static com.tesis.config.Constants.EXPIRATION_CHANGE_TIME;

@Singleton
public class AdminUserServiceImp implements AdminUserService {

    Logger logger = LoggerFactory.getLogger(AdminUserServiceImp.class);

    @Inject
    AdminUserDaoExt adminuUersDao;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    RecoveryService recoveryService;

    public ResponseDTO<List<AdminUsers>> getAdminUsers() {
        return new ResponseDTO(adminuUersDao.findAllActives(), null);
    }

    public ResponseDTO<AdminUsers> getAdminUsersByAdminUserID(Long adminUserID) {
        ResponseDTO<AdminUsers> responseDTO = new ResponseDTO(adminuUersDao.fetchOneById(adminUserID), null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<AdminUsers> createAdminUser(AdminUsers adminUser) {
        ResponseDTO<AdminUsers> responseDTO = new ResponseDTO<>();

        try {
            adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
            adminUser.setStatus(Status.ACTIVE.toString());
            adminuUersDao.insert(adminUser);
            responseDTO.model = adminUser;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el usuario admin %s", adminUser.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el usuario admin.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<AdminUsers> updateAdminUser(Long adminUserID, AdminUsers newData) {
        ResponseDTO<AdminUsers> responseDTO = new ResponseDTO<>();
        try {
            AdminUsers adminUser = adminuUersDao.fetchOneById(adminUserID);
            adminUser.setStatus(newData.getStatus());
            adminUser.setLastUpdated(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
            adminUser.setDeletedAt(null);
            adminUser.setPassword(passwordEncoder.encode(newData.getPassword()));
            adminUser.setName(newData.getName());
            adminUser.setLastName(newData.getLastName());
            adminUser.setDni(newData.getDni());
            adminUser.setAddress(newData.getAddress());
            adminUser.setPhone(newData.getPhone());
            adminUser.setEmail(newData.getEmail());
            adminuUersDao.update(adminUser);
            responseDTO.model = adminUser;
        } catch (Exception e) {
            logger.error("No se pudo modificar el usuario admin");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el usuario admin.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<AdminUsers> deleteAdminUser(Long adminUserID) {
        ResponseDTO<AdminUsers> responseDTO = new ResponseDTO<>();
        try {
            adminuUersDao.deleteAdminUser(adminUserID);
        }catch (Exception e){
            logger.error("No se pudo modificar el usuario admin. Motivo: " + e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el usuario admin.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Search> adminUserSearch(UserFilters filter, Pagination pagination) {

        ResponseDTO<Search> responseDTO = new ResponseDTO<>();
        responseDTO.model = new Search<>(adminuUersDao.findByFilters(filter, pagination), pagination);

        return responseDTO;
    }

    private void initUserPasswordChange(AdminUsers adminUser) throws ApiException {
        CredentialsDTO credentialsDTO = new CredentialsDTO(adminUser.getEmail(), adminUser.getPassword());
        ResponseDTO responseDTO = recoveryService.createRecoveryToken(credentialsDTO,
                Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(EXPIRATION_CHANGE_TIME)));
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }
    }
}
