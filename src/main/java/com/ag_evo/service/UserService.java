package com.ag_evo.service;

import com.ag_evo.model.UserRequest;
import com.ag_evo.model.UserResponse;
import java.util.List;

public interface UserService {
    public UserResponse addUser(UserRequest user);
    public List<UserResponse> getUsers();
    public UserResponse getUserByName(String name);
    public UserResponse updateUserByName(String name, UserRequest user);
}
