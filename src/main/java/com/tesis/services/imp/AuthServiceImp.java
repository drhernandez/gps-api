package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.AccessTokenDaoExt;
import com.tesis.daos.UserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AccessTokens;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AuthService;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Singleton
public class AuthServiceImp implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    @Inject
    UserDaoExt usersDao;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    AccessTokenDaoExt accessTokensDao;


    public Boolean checkUserCredentials(CredentialsDTO credentialsDTO){
        Users user = usersDao.fetchOneByEmail(credentialsDTO.getEmail());
        if (user == null)
            return false;

        return passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword());
    }

    public ResponseDTO<AccessTokens> checkAccessToken(CredentialsDTO credentialsDTO){
        ResponseDTO<AccessTokens> responseDTO = new ResponseDTO<>();
        Users user = usersDao.fetchOneByEmail(credentialsDTO.getEmail());
        AccessTokens token = accessTokensDao.fetchOneByUserId(user.getId());

        if(token != null) {
            try {
                validateAccessToken(token.getToken());
                responseDTO.setModel(token);
                return responseDTO;
            } catch (JwtException jwte) {
                accessTokensDao.delete(token);
                logger.info("Sesión expirada. Usuario %s. Creando nuevo token...", user.toString());
            }
        }

        try {
            AccessTokens newToken = createAccessToken(user);
            accessTokensDao.insert(newToken);
            logger.info("Sesión creada para el usuario %s", user.toString());
            responseDTO.setModel(newToken);
        } catch (Exception e) {
            logger.error(String.format("No se pudo crear el access token para el usuario %s", user.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el access token.");
        }

        return responseDTO;
    }


    public AccessTokens createAccessToken(Users user) throws JwtException {
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);

        JwtBuilder jwts = Jwts.builder().setHeaderParam("type", "access-token")
                .setSubject("User")
                .setExpiration(Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()))
                .setIssuedAt(new Date())

                .claim("userId",user.getId())
                .claim("userName", user.getName())
                .claim("userLastName", user.getLastName())
                .claim("userEmail", user.getEmail());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(System.getenv("ACCESS_JWT_KEY"));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

        return new AccessTokens(user.getId(), jwts.signWith(signingKey, SignatureAlgorithm.HS512).compact());
    }

    public Claims validateAccessToken(String jwt) throws JwtException {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(System.getenv("ACCESS_JWT_KEY")))
                .parseClaimsJws(jwt).getBody();
    }
}