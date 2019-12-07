package com.tesis.services.imp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tesis.daos.AdminAccessTokenDaoExt;
import com.tesis.daos.AdminUserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.AdminAccessTokens;
import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.CredentialsDTO;
import com.tesis.models.ResponseDTO;
import com.tesis.services.AuthAdminService;
import io.jsonwebtoken.*;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.tesis.config.Constants.JWT_KEY;


@Singleton
public class AuthAdminServiceImp implements AuthAdminService {

    Logger logger = LoggerFactory.getLogger(AuthAdminServiceImp.class);

    @Inject
    AdminUserDaoExt AdminUsersDao;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    AdminAccessTokenDaoExt adminAccessTokensDao;


    public Boolean checkAdminUserCredentials(CredentialsDTO credentialsDTO){
        AdminUsers adminUser = AdminUsersDao.fetchOneByEmail(credentialsDTO.getEmail());
        if (adminUser == null)
            return false;

        return passwordEncoder.matches(credentialsDTO.getPassword(), adminUser.getPassword());
    }

    public ResponseDTO<AdminAccessTokens> getOrCreateAdminAccessToken(CredentialsDTO credentialsDTO){
        ResponseDTO<AdminAccessTokens> responseDTO = new ResponseDTO<>();
        AdminUsers adminUser = AdminUsersDao.fetchOneByEmail(credentialsDTO.getEmail());
        AdminAccessTokens token = adminAccessTokensDao.fetchOneByUserId(adminUser.getId());

        if(token != null) {
            try {
                validateAccessToken(token.getToken());
                responseDTO.setModel(token);
                return responseDTO;
            } catch (Exception e) {
                adminAccessTokensDao.delete(token);
                logger.info("Sesión expirada. Usuario administrador %s. Creando nuevo token...", adminUser.toString());
            }
        }

        try {
            AdminAccessTokens newToken = generateAccessToken(adminUser);
            adminAccessTokensDao.insert(newToken);
            logger.info("Sesión creada para el usuario administrador %s", adminUser.toString());
            responseDTO.setModel(newToken);
        } catch (Exception e) {
            logger.error(String.format("No se pudo crear el access token para el usuario administrador %s", adminUser.toString()));
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar el access token.");
        }

        return responseDTO;
    }


    private AdminAccessTokens generateAccessToken(AdminUsers adminUser) throws JwtException {
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);

        JwtBuilder jwts = Jwts.builder().setHeaderParam("type", "access-token")
                .setSubject("User")
                .setExpiration(Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()))
                .setIssuedAt(new Date())

                .claim("userId",adminUser.getId())
                .claim("userName", adminUser.getName())
                .claim("userLastName", adminUser.getLastName())
                .claim("userEmail", adminUser.getEmail());

        Key signingKey = getSigningKey();

        return new AdminAccessTokens(adminUser.getId(), jwts.signWith(signingKey, SignatureAlgorithm.HS512).compact());
    }

    @Override
    public void checkAdminAccessToken(String jwt) throws ApiException {
        try {
            Claims adminClaims = decodeAccessToken(jwt);
            Long adminId = adminClaims.get("userId", Long.class);
            AdminUsers adminUser = AdminUsersDao.fetchOneById(adminId);
            if (adminUser == null || !adminUser.getEmail().equals(adminClaims.get("userEmail", String.class)))
                throw new ApiException(ErrorCodes.unauthorized.toString(), "Usuario administrador no autorizado.");

        } catch (Exception e) {
            logger.error("Usuario administrador no autorizado.");
            throw new ApiException(ErrorCodes.unauthorized.toString(), "Usuario administrador no autorizado.");
        }
    }

    @Override
    public boolean checkAdminUserPermissions(String jwt, Long idRequired) {
        try {
            validateAccessToken(jwt);
        } catch (ApiException e) {
            return false;
        }

        // validate admin permissions scope
        // ...
        return true;
    }


    private void validateAccessToken(String jwt) throws ApiException {
        Key signingKey = getSigningKey();
        Jwts.parser().setSigningKey(signingKey).parse(jwt);
        AdminAccessTokens accessTokens = adminAccessTokensDao.fetchOne(com.tesis.jooq.tables.AdminAccessTokens.ADMIN_ACCESS_TOKENS.TOKEN, jwt);
        if (accessTokens == null)
            throw new ApiException("401", ErrorCodes.unauthorized.name() , HttpStatus.UNAUTHORIZED_401);
    }

    private Key getSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_KEY);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    private Claims decodeAccessToken(String jwt) throws JwtException {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_KEY))
                .parseClaimsJws(jwt).getBody();
    }
}
