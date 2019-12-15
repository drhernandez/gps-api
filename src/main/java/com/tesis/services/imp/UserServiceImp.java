package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
import com.tesis.exceptions.ApiException;
import com.tesis.daos.UserDaoExt;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.jooq.tables.pojos.Vehicles;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.Pagination;
import com.tesis.models.ResponseDTO;
import com.tesis.models.Search;
import com.tesis.services.RecoveryService;
import com.tesis.services.UserService;

import com.tesis.utils.filters.UserFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.tesis.config.Constants.EXPIRATION_CHANGE_TIME;

@Singleton
public class UserServiceImp implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Inject
    UserDaoExt usersDao;

    @Inject
    VehicleDaoExt vehicleDao;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    RecoveryService recoveryService;

    public ResponseDTO<Users> activateUser(Long userId){
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        try {
            Users user = usersDao.fetchOneById(userId);
            if (user.getStatus().equals(Status.PENDING.toString()))
                initUserPasswordChange(user);
            user.setStatus(Status.ACTIVE.toString());
            usersDao.update(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error("No se pudo activar el usuario");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al activar el usuario.");
        }
        return responseDTO;
    }

    public ResponseDTO<Users> deactivateUser(Long userId){
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        try {
            Users user = usersDao.fetchOneById(userId);
            user.setStatus(Status.INACTIVE.toString());
            usersDao.update(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error("No se pudo modificar el usuario");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el usuario.");
        }

        return responseDTO;
    }

    public ResponseDTO<List<Users>> getUsers() {
        return new ResponseDTO(usersDao.findAllActives(), null);
    }

    public ResponseDTO<Users> getUsersByUserID(Long userID) {
        ResponseDTO<Users> responseDTO = new ResponseDTO(usersDao.fetchOneById(userID), null);
        return responseDTO;
    }

    public ResponseDTO<Users> getUsersByDeviceId(Long deviceId) {
        Vehicles vehicle = vehicleDao.fetchOne(com.tesis.jooq.tables.Vehicles.VEHICLES.DEVICE_ID, deviceId);
        ResponseDTO<Users> responseDTO = new ResponseDTO(usersDao.fetchOneById(vehicle.getUserId()), null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> createUser(Users user) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        try {
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            user.setStatus(Status.PENDING.toString());
            usersDao.insert(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error(String.format("No se pudo guardar el usuario %s", user.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el usuario.");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> updateUser(Long userID, Users newData) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();
        try {
            Users user = usersDao.fetchOneById(userID);
            user.setStatus(newData.getStatus());
            user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
            user.setDeletedAt(null);
            user.setPassword(passwordEncoder.encode(newData.getPassword()));
            user.setName(newData.getName());
            user.setLastName(newData.getLastName());
            user.setDni(newData.getDni());
            user.setAddress(newData.getAddress());
            user.setPhone(newData.getPhone());
            user.setEmail(newData.getEmail());
            usersDao.update(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error("No se pudo modificar el usuario");
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el usuario.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> deleteUser(Long userID) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();
        try {
            usersDao.deleteUserCascade(userID);
        }catch (Exception e){
            logger.error("No se pudo modificar el usuario. Motivo: " + e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar el usuario.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Search> userSearch(UserFilters filter, Pagination pagination) {

        ResponseDTO<Search> responseDTO = new ResponseDTO<>();
        responseDTO.model = new Search<>(usersDao.findByFilters(filter, pagination), pagination);

        return responseDTO;
    }

    private void initUserPasswordChange(Users user) throws ApiException {
        CredentialsDTO credentialsDTO = new CredentialsDTO(user.getEmail(), user.getPassword());
        ResponseDTO responseDTO = recoveryService.createRecoveryToken(credentialsDTO,
                Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()).plusDays(EXPIRATION_CHANGE_TIME)));
        if (responseDTO.error != null) {
            throw responseDTO.error;
        }
    }
}
