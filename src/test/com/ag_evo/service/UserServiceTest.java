package com.ag_evo.service;

import com.ag_evo.model.UserRequest;
import com.ag_evo.model.UserResponse;
import com.ag_evo.model.Users;
import com.ag_evo.repository.UsersRepository;
import com.ag_evo.utils.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void addUser_OK(){
       // when(usersRepository.save(any(Users.class))).thenReturn(getUser());
        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(getUser());
        when(userMapper.fromEntity(any(Users.class))).thenReturn(getUserResponse());

        UserResponse result = userService.addUser(new UserRequest());

        verify(usersRepository, times(1)).save(any(Users.class));
        Assertions.assertNotNull(result);
        Assertions.assertEquals("lastName", result.getLastName());
    }

    @Test
    public void getUsers_OK(){
        when(usersRepository.findAll()).thenReturn(Collections.singletonList(getUser()));
        List<UserResponse> result = userService.getUsers();

        verify(usersRepository, times(1)).findAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void getUserByName_OK() {
        when(usersRepository.findUsersByName(anyString())).thenReturn(getUser());
        UserResponse result = userService.getUserByName(anyString());

        verify(usersRepository, times(1)).findUsersByName(anyString());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("test@test.com", result.getEmail());
    }

    private Users getUser() {
        Users user = new Users();
        user.setLastName("lastName");
        user.setEmail("test@test.com");
        user.setOrgUnits(Collections.singletonList("testing"));
        user.setBirthDate(LocalDate.of(2000, 05, 25));
        user.setRegisteredOn(LocalDate.of(2025, 03, 15));
        return user;
    }

    private UserResponse getUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setName("flastName");
        userResponse.setLastName("lastName");
        userResponse.setEmail("test@test.com");
        userResponse.setOrgUnits(Collections.singletonList("testing"));
        userResponse.setBirthDate(LocalDate.of(2000, 05, 25));
        userResponse.setRegisteredOn(LocalDate.of(2025, 03, 15));
        userResponse.setPolicies(Collections.singletonList("underaged"));
        return userResponse;
    }
}
