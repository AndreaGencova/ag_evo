package com.ag_evo.utils;

import com.ag_evo.model.Users;
import com.ag_evo.model.UserRequest;
import com.ag_evo.model.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

@Component
public class UserMapper {

    public Users toEntity(UserRequest user) {
        Users users = new Users();
        BeanUtils.copyProperties(user, users);
        users.setRegisteredOn(now());
        return users;
    }

    public UserResponse fromEntity(Users user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        userResponse.setName((user.getFirstName() !=null && user.getLastName() != null) ? user.getFirstName().charAt(0) + user.getLastName() : "");
        return userResponse;
    }

    public List<UserResponse> listFromEntity(List<Users> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> {userResponses.add(fromEntity(user));});
        return userResponses;
    }
}
