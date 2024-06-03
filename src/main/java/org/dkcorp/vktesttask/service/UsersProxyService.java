package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.request.IncomingUserDto;
import org.dkcorp.vktesttask.dto.response.UserDto;

import java.util.List;

public interface UsersProxyService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(IncomingUserDto incomingUserDto);

    UserDto updateUser(Long id, IncomingUserDto incomingUserDto);

    void deleteUser(Long id);
}
