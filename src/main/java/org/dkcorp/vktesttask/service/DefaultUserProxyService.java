package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingUserDto;
import org.dkcorp.vktesttask.dto.response.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserProxyService implements UsersProxyService {
    private final WebClient webClient;
    private final String FORWARD_SLASH = "/";
    private final String USERS_PREFIX = "/users";

    @Override
    public List<UserDto> getAllUsers() {
        return webClient.get()
                .uri(USERS_PREFIX)
                .retrieve().bodyToFlux(UserDto.class)
                .collectList()
                .block();
    }

    @Override
    public UserDto getUserById(Long id) {
        return webClient.get()
                .uri(USERS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public UserDto createUser(IncomingUserDto incomingUserDto) {
        return webClient.post()
                .uri(USERS_PREFIX)
                .bodyValue(incomingUserDto)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public UserDto updateUser(Long id, IncomingUserDto incomingUserDto) {
        return webClient.put()
                .uri(USERS_PREFIX + FORWARD_SLASH + id)
                .bodyValue(incomingUserDto)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public void deleteUser(Long id) {
        webClient.delete()
                .uri(USERS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
