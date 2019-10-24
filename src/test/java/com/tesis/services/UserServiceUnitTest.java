package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.UserDaoExt;
import com.tesis.daos.VehicleDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.pojos.Users;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.UserServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest extends UnitTestConfigs {


    @Mock
    UserDaoExt usersDao;

    @Mock
    VehicleDaoExt vehicleDao;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImp userService;

    @Test
    public void createUserTest_ok(){
        Users user = new Users();
        user.setPassword("passTest");
        user.setName("Diego");

        Mockito.when(passwordEncoder.encode("passTest")).thenReturn("passEncoded");
        ResponseDTO<Users> responseDTO = userService.createUser(user);

        assertEquals(responseDTO.getModel().getName(), user.getName());
        assertEquals(responseDTO.getModel().getPassword(), "passEncoded");
    }

    @Test
    public void createUserTest_error(){
        Users user = new Users();
        user.setPassword(null);
        user.setName("Diego");

        Mockito.when(passwordEncoder.encode(null)).thenThrow(NullPointerException.class);
        ResponseDTO<Users> responseDTO = userService.createUser(user);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar el usuario.");
    }

    @Test
    public void getUsersByUserIDTest_ok(){
        Users user = mock(Users.class);
        user.setName("Diego");

        Mockito.when(usersDao.fetchOneById(any())).thenReturn(user);
        ResponseDTO<Users> responseDTO = userService.getUsersByUserID(new Random().nextLong());

        assertEquals(responseDTO.getModel().getName(), user.getName());
    }

    @Test
    public void getUsersByUserIDTest_error(){
        Mockito.when(usersDao.fetchOneById(any())).thenReturn(null);
        ResponseDTO<Users> responseDTO = userService.getUsersByUserID(new Random().nextLong());

        assertEquals(responseDTO.getModel(), null);
    }

    @Test
    public void updateUserTest_ok(){
        Users user = mock(Users.class);
        user.setName("Diego");

        Mockito.when(usersDao.fetchOneById(any())).thenReturn(user);
        ResponseDTO<Users> responseDTO = userService.updateUser(new Random().nextLong(), user);

        assertEquals(responseDTO.getModel().getName(), user.getName());
    }
    @Test
    public void updateUserTest_error(){
        Users user = mock(Users.class);
        user.setName("Diego");

        Mockito.when(usersDao.fetchOneById(any())).thenThrow(DataAccessException.class);
        ResponseDTO<Users> responseDTO = userService.updateUser(new Random().nextLong(), user);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar el usuario.");
    }

    @Test
    public void deleteUser_ok(){
        ResponseDTO<Users> responseDTO = userService.deleteUser(new Random().nextLong());
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteUser_error(){
        doThrow(DataAccessException.class).when(usersDao).deleteUserCascade(any());
        ResponseDTO<Users> responseDTO = userService.deleteUser(new Random().nextLong());

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar el usuario.");

    }

}
