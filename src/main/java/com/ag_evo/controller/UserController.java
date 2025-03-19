package com.ag_evo.controller;
import com.ag_evo.model.UserRequest;
import com.ag_evo.model.UserResponse;
import com.ag_evo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    @NonNull
    private final UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody UserRequest user) {
        return userService.addUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/users", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByName(@RequestParam(value = "name") String name) {
        return userService.getUserByName(name);
    }

    @PutMapping("/users/{name}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUserByName(@PathVariable("name") String name, @RequestBody UserRequest user) {
        return userService.updateUserByName(name, user);
    }
}
