package com.ag_evo.controller;


import org.junit.Test;
import com.ag_evo.model.UserRequest;
import com.ag_evo.model.UserResponse;
import com.ag_evo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    final private UserRequest USER_ADD = new UserRequest("firstName", "lastName", "email@test.com", Collections.singletonList("testing"), LocalDate.of(2000, 05, 25));

    @Test
    public void addUser_OK() throws Exception {
        final String url = "/api/users";
        UserResponse userResponse = getUser();

        when(userService.addUser(any())).thenReturn(userResponse);
        ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(USER_ADD))).andExpect(status().isCreated());

        UserResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(USER_ADD.getFirstName().charAt(0) + USER_ADD.getLastName(), response.getName());
    }

    @Test
    public void addUser_err() throws Exception {
        final String url = "/api/users";
        when(userService.addUser(any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(USER_ADD))).andExpect(status().isBadRequest());
    }

    @Test
    public void getUsers_OK() throws Exception {
        final String url = "/api/users";
        when(userService.getUsers()).thenReturn(Collections.singletonList(getUser()));
        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getUserByName_OK() throws Exception {
        final String url = "/api/users?name=flastName";
        when(userService.getUserByName(anyString())).thenReturn(getUser());
        ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        UserResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("flastName", response.getName());
    }

    @Test
    public void getUserByName_err() throws Exception {
        final String url = "/api/users?name=flastName";
        when(userService.getUserByName(anyString())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void updateUserByName_OK() throws Exception {
        final String url = "/api/users/flastName";
        UserResponse userResponse = getUser();
        userResponse.setFirstName("new");

        when(userService.updateUserByName(anyString(), any(UserRequest.class))).thenReturn(userResponse);
        ResultActions result = mockMvc.perform(put(url).param("name","flastName" ).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(USER_ADD))).andExpect(status().isOk());

        UserResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("new", response.getFirstName());
    }

    private UserResponse getUser() {
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
