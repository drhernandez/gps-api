package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.AdminUserDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.enums.Status;
import com.tesis.jooq.tables.pojos.AdminUsers;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.AdminUserServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserServiceUnitTest extends UnitTestConfigs {

    @Mock
    AdminUserDaoExt adminUserDao;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AdminUserServiceImp adminUserService;

    @Test
    public void createAdminUserTest_ok(){

        AdminUsers user = new AdminUsers();
        user.setPassword("passTest");
        user.setName("Diego");

        Mockito.when(passwordEncoder.encode(any(String.class))).thenReturn("passEncoded");
        ResponseDTO<AdminUsers> responseDTO = adminUserService.createAdminUser(user);

        assertEquals(responseDTO.getModel().getName(), user.getName());
        assertEquals(responseDTO.getModel().getPassword(), "passEncoded");
        assertEquals(responseDTO.getModel().getStatus(), Status.ACTIVE.toString());
    }

    @Test
    public void createAdminUserTest_error(){
        AdminUsers user = new AdminUsers();
        user.setPassword(null);
        user.setName("Diego");

        Mockito.when(passwordEncoder.encode(null)).thenThrow(NullPointerException.class);
        ResponseDTO<AdminUsers> responseDTO = adminUserService.createAdminUser(user);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el usuario admin.");
    }

    @Test
    public void updateAdminUserTest_ok(){

        AdminUsers user = Mockito.mock(AdminUsers.class);
        user.setName("Diego");

        Mockito.when(adminUserDao.fetchOneById(any())).thenReturn(user);
        ResponseDTO<AdminUsers> responseDTO = adminUserService.updateAdminUser(1L, user);

        assertEquals(responseDTO.getModel().getName(), user.getName());
    }

    @Test
    public void updateAdminUserTest_error(){

        AdminUsers user = Mockito.mock(AdminUsers.class);
        user.setName("Diego");

        Mockito.when(adminUserDao.fetchOneById(any())).thenThrow(DataAccessException.class);
        ResponseDTO<AdminUsers> responseDTO = adminUserService.updateAdminUser(1L, user);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar el usuario admin.");
    }

    @Test
    public void deleteAdminUserTest_ok(){
        ResponseDTO responseDTO = adminUserService.deleteAdminUser(1L);
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteAdminUserTest_error(){
        Mockito.doThrow(DataAccessException.class).when(adminUserDao).deleteAdminUser(any());
        ResponseDTO<AdminUsers> responseDTO = adminUserService.deleteAdminUser(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar el usuario admin.");
    }
}
