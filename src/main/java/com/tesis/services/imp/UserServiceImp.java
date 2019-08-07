package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.daos.UserDaoExt;
import com.tesis.daos.AccessTokenDaoExt;
import com.tesis.jooq.tables.pojos.AccessTokens;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.List;
import java.time.LocalDateTime;

@Singleton
public class UserServiceImp implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Inject
    UserDaoExt usersDao;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    AccessTokenDaoExt accessTokensDao;

    public Boolean checkCredentials(CredentialsDTO credentialsDTO){
        Users user = usersDao.fetchOneByEmail(credentialsDTO.getEmail());
        if (user == null)
            return false;

        return passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword());
    }

    public Boolean checkToken(CredentialsDTO credentialsDTO){
        Users user = usersDao.fetchOneByEmail(credentialsDTO.getEmail());
        AccessTokens token = accessTokensDao.fetchOneById(user.getId());

        if(token != null)
            return true; // return token

        else {
            JwtBuilder jwts = Jwts.builder().setHeaderParam("type", "access-token");

        }

        return false;
    }

    public ResponseDTO<List<Users>> getUsers() {
        return new ResponseDTO(usersDao.findAllActives(), null);
    }

    public ResponseDTO<Users> getUsersByUserID(Long userID) {
        ResponseDTO<Users> responseDTO = new ResponseDTO(usersDao.fetchOneById(userID), null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> createUser(Users user) {
        ResponseDTO<Users> responseDTO = new ResponseDTO<>();

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        Users user = usersDao.fetchOneById(userID);
        user.setLastUpdated(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
        user.setDeletedAt(null);
        user.setPassword(newData.getPassword());
        user.setName(newData.getName());
        user.setLastName(newData.getLastName());
        user.setDni(newData.getDni());
        user.setAddress(newData.getAddress());
        user.setPhone(newData.getPhone());
        user.setEmail(newData.getEmail());
        try {
            usersDao.update(user);
            responseDTO.model = user;
        } catch (Exception e) {
            logger.error(String.format("No se pudo modificar el usuario %s", user.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar el usuario.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Users> deleteUser(Long userID) {

        usersDao.deleteUserCascade(userID);

        ResponseDTO<Users> responseDTO = new ResponseDTO<>();
        return responseDTO;
    }
}
