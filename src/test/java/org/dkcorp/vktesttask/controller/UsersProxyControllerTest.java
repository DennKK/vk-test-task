package org.dkcorp.vktesttask.controller;

import org.dkcorp.vktesttask.dto.request.IncomingAddressDto;
import org.dkcorp.vktesttask.dto.request.IncomingCompanyDto;
import org.dkcorp.vktesttask.dto.request.IncomingGeoDto;
import org.dkcorp.vktesttask.dto.request.IncomingUserDto;
import org.dkcorp.vktesttask.dto.response.AddressDto;
import org.dkcorp.vktesttask.dto.response.CompanyDto;
import org.dkcorp.vktesttask.dto.response.GeoDto;
import org.dkcorp.vktesttask.dto.response.UserDto;
import org.dkcorp.vktesttask.service.UsersProxyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsersProxyControllerTest {
    @Mock
    UsersProxyService service;

    @InjectMocks
    UsersProxyController controller;

    @Test
    @DisplayName("GET /api/users returns list of users")
    void handleGetAllUsers_ReturnsValidResponseEntity() {
        // given
        List<UserDto> expectedUsers = List.of(

                new UserDto(1L,
                        "John Doe",
                        "JD",
                        "example@example.com",
                        new AddressDto(
                                "1234",
                                "Main St",
                                "Springfield",
                                "12345-6789",
                                new GeoDto("40.7128", "74.0060")
                        ),
                        "1-770-736-8031 x56442",
                        "www.example.com",
                        new CompanyDto(
                                "Romaguera-Crona",
                                "Multi-layered client-server neural-net",
                                "harness real-time e-markets")
                )
        );

        doReturn(expectedUsers).when(service).getAllUsers();

        // when
        List<UserDto> actualUsers = controller.getAllUsers();

        // then
        assertNotNull(actualUsers);
        assertEquals(expectedUsers, actualUsers);
        assertEquals(1, actualUsers.size());
        verify(service, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("GET /api/users/{userId} returns user by id")
    void handleGetUserById_ReturnsValidResponseEntity() {
        // given
        UserDto expectedUser = new UserDto(1L,
                "John Doe",
                "JD",
                "example@example.com",
                new AddressDto(
                        "1234",
                        "Main St",
                        "Springfield",
                        "12345-6789",
                        new GeoDto("40.7128", "74.0060")
                ),
                "1-770-736-8031 x56442",
                "www.example.com",
                new CompanyDto(
                        "Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );

        doReturn(expectedUser).when(service).getUserById(1L);

        // when
        UserDto actualUser = controller.getUserById(1L);

        // then
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
        verify(service, times(1)).getUserById(1L);
    }

    @Test
    @DisplayName("POST /api/users creates new user")
    void handleCreateUser_ReturnsValidResponseEntity() {
        // given
        IncomingUserDto incomingUser = new IncomingUserDto(
                "John Doe",
                "JD",
                "example@example.com",
                new IncomingAddressDto(
                        "1234",
                        "Main St",
                        "Springfield",
                        "12345-6789",
                        new IncomingGeoDto("40.7128", "74.0060")
                ),
                "1-770-736-8031 x56442",
                "www.example.com",
                new IncomingCompanyDto(
                        "Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );

        UserDto expectedUser = new UserDto(1L,
                "John Doe",
                "JD",
                "example@example.com",
                new AddressDto(
                        "1234",
                        "Main St",
                        "Springfield",
                        "12345-6789",
                        new GeoDto("40.7128", "74.0060")
                ),
                "1-770-736-8031 x56442",
                "www.example.com",
                new CompanyDto(
                        "Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );

        doReturn(expectedUser).when(service).createUser(incomingUser);

        // when
        UserDto actualUser = controller.createUser(incomingUser);

        // then
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
        ArgumentCaptor<IncomingUserDto> dtoCaptor = ArgumentCaptor.forClass(IncomingUserDto.class);
        verify(service, times(1)).createUser(dtoCaptor.capture());
        assertEquals(incomingUser, dtoCaptor.getValue());
    }

    @Test
    @DisplayName("PUT /api/users/{userId} updates user by id")
    void handleUpdateUser_ReturnsValidResponseEntity() {
        // given
        IncomingUserDto incomingUser = new IncomingUserDto(
                "John Doe",
                "JD",
                "example@example.com",
                new IncomingAddressDto(
                        "1234",
                        "Main St",
                        "Springfield",
                        "12345-6789",
                        new IncomingGeoDto("40.7128", "74.0060")
                ),
                "1-770-736-8031 x56442",
                "www.example.com",
                new IncomingCompanyDto(
                        "Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );

        UserDto expectedUser = new UserDto(1L,
                "John Doe",
                "JD",
                "example@example.com",
                new AddressDto(
                        "1234",
                        "Main St",
                        "Springfield",
                        "12345-6789",
                        new GeoDto("40.7128", "74.0060")
                ),
                "1-770-736-8031 x56442",
                "www.example.com",
                new CompanyDto(
                        "Romaguera-Crona",
                        "Multi-layered client-server neural-net",
                        "harness real-time e-markets")
        );

        doReturn(expectedUser).when(service).updateUser(1L, incomingUser);

        // when
        UserDto actualUser = controller.updateUser(1L, incomingUser);

        // then
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<IncomingUserDto> dtoCaptor = ArgumentCaptor.forClass(IncomingUserDto.class);
        verify(service, times(1)).updateUser(idCaptor.capture(), dtoCaptor.capture());
        assertEquals(1L, idCaptor.getValue());
        assertEquals(incomingUser, dtoCaptor.getValue());
    }

    @Test
    @DisplayName("DELETE /api/users/{userId} deletes user by id")
    void handleDeleteUser_ReturnsValidResponseEntity() {
        // when
        controller.deleteUser(1L);

        // then
        verify(service, times(1)).deleteUser(1L);
    }
}