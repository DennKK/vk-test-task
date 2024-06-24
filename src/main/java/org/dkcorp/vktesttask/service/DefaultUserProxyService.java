package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingUserDto;
import org.dkcorp.vktesttask.dto.response.UserDto;
import org.dkcorp.vktesttask.exception.CustomClientException;
import org.dkcorp.vktesttask.exception.CustomServerException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserProxyService implements UsersProxyService {
    private final WebClient webClient;
    private final String FORWARD_SLASH = "/";
    private final String USERS_PREFIX = "/users";

    @Override
    @Cacheable(value = "usersCache", key = "'allUsers'")
    public List<UserDto> getAllUsers() {
        return webClient.get()
                .uri(USERS_PREFIX)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve users: Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve users: Server error")))
                .bodyToFlux(UserDto.class)
                .collectList()
                .block();
    }

    @Override
    @Cacheable(value = "usersCache", key = "'usesid:' + #id")
    public UserDto getUserById(Long id) {
        return webClient.get()
                .uri(USERS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve user with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve user with id " + id + ": Server error")))
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    public UserDto createUser(IncomingUserDto incomingUserDto) {
        return webClient.post()
                .uri(USERS_PREFIX)
                .bodyValue(incomingUserDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to create user: Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to create user: Server error")))
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    public UserDto updateUser(Long id, IncomingUserDto incomingUserDto) {
        return webClient.put()
                .uri(USERS_PREFIX + FORWARD_SLASH + id)
                .bodyValue(incomingUserDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to update user with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to update user with id " + id + ": Server error")))
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    public void deleteUser(Long id) {
        webClient.delete()
                .uri(USERS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to delete user with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to delete user with id " + id + ": Server error")))
                .bodyToMono(Void.class)
                .block();
    }
}
