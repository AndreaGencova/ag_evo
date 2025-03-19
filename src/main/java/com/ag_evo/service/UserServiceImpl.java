package com.ag_evo.service;

import com.ag_evo.model.UserRequest;
import com.ag_evo.model.UserResponse;
import com.ag_evo.model.Users;
import com.ag_evo.repository.UsersRepository;
import com.ag_evo.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PolicyService policyService;

    @Override
    public UserResponse addUser(UserRequest user) {
        return userMapper.fromEntity(usersRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public List<UserResponse> getUsers() {
        return userMapper.listFromEntity(usersRepository.findAll());
    }

    @Override
    public UserResponse getUserByName(String name) {
        Users user = usersRepository.findUsersByName(name);
        UserResponse response = userMapper.fromEntity(user);
        response.setPolicies(policyService.resolvePolicies(user));
        return response;
    }

    @Override
    public UserResponse updateUserByName(String name, UserRequest user) {
        Users userToUpdate = usersRepository.findUsersByName(name);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setBirthDate(user.getBirthDate());
        userToUpdate.setOrgUnits(user.getOrgUnits());
       return userMapper.fromEntity(usersRepository.save(userToUpdate));

    }
}
