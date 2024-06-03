package org.dkcorp.vktesttask.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingUserDto;
import org.dkcorp.vktesttask.dto.response.UserDto;
import org.dkcorp.vktesttask.service.UsersProxyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations related to users")
public class UsersProxyController {
    private final UsersProxyService usersProxyService;

    @GetMapping
    @Operation(summary = "Fetch all users", description = "Fetch all users from the server")
    public List<UserDto> getAllUsers() {
        return usersProxyService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Fetch user", description = "Fetch user by its id")
    public UserDto getUserById(@PathVariable Long userId) {
        return usersProxyService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user", description = "Create new user")
    public UserDto createUser(@RequestBody IncomingUserDto incomingUserDto) {
        return usersProxyService.createUser(incomingUserDto);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Update user by its id")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody IncomingUserDto incomingUserDto) {
        return usersProxyService.updateUser(userId, incomingUserDto);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Delete user by its id")
    public void deleteUser(@PathVariable Long userId) {
        usersProxyService.deleteUser(userId);
    }
}
