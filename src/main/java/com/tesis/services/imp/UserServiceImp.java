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
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZoneId;
import java.util.Date;
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

    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    public Boolean checkCredentials(CredentialsDTO credentialsDTO){
        Users user = usersDao.fetchOneByEmail(credentialsDTO.getEmail());
        if (user == null)
            return false;

        return passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword());
    }

    public ResponseDTO<AccessTokens> checkToken(CredentialsDTO credentialsDTO){
        ResponseDTO<AccessTokens> responseDTO = new ResponseDTO<>();
        Users user = usersDao.fetchOneByEmail(credentialsDTO.getEmail());
        AccessTokens token = accessTokensDao.fetchOneById(user.getId());

        if(token != null) {
            try {
                validateJWT(token.getToken());
                responseDTO.setModel(token);
                return responseDTO;
            } catch (JwtException jwte) {
                accessTokensDao.delete(token);
                logger.info("Sesión expirada. Usuario %s. Creando nuevo token...", user.toString());
            }
        }

        try {
            AccessTokens newToken = createJWT(user);
            accessTokensDao.insert(newToken);
            logger.info("Sesión creada para el usuario %s", user.toString());
            responseDTO.setModel(newToken);
        } catch (Exception e) {
            logger.error(String.format("No se pudo crear el access token para el usuario %s", user.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el access token.");
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

    public static AccessTokens createJWT(Users user) throws JwtException {
//        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);
        LocalDateTime expirationDate = LocalDateTime.now().plusSeconds(10);

        JwtBuilder jwts = Jwts.builder().setHeaderParam("type", "access-token")
                .setSubject("User")
                .setExpiration(Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()))
                .setIssuedAt(new Date())

                .claim("userId",user.getId())
                .claim("userName", user.getName())
                .claim("userLastName", user.getLastName())
                .claim("userEmail", user.getEmail());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

        return new AccessTokens(user.getId(), jwts.signWith(signingKey, SignatureAlgorithm.HS256).compact());
    }

    public static Claims validateJWT(String jwt) throws JwtException {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
