package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.domain.entity.CustomUser;
import org.dkcorp.vktesttask.exception.UserAlreadyExistsException;
import org.dkcorp.vktesttask.exception.UserNotFoundException;
import org.dkcorp.vktesttask.repository.CustomUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static org.dkcorp.vktesttask.domain.enums.Role.ROLE_ADMIN;
import static org.dkcorp.vktesttask.domain.enums.Role.ROLE_ALBUMS;
import static org.dkcorp.vktesttask.domain.enums.Role.ROLE_POSTS;
import static org.dkcorp.vktesttask.domain.enums.Role.ROLE_USERS;

@Service
@RequiredArgsConstructor
public class DefaultCustomUserService implements CustomUserService {
    private final CustomUserRepository customUserRepository;

    @Override
    public CustomUser save(CustomUser customUser) {
        return customUserRepository.save(customUser);
    }

    @Override
    public CustomUser create(CustomUser customUser) {
        if (customUserRepository.existsByUsername(customUser.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + customUser.getUsername() + " already exists");
        }

        if (customUserRepository.existsByEmail(customUser.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + customUser.getEmail() + " already exists");
        }

        return save(customUser);
    }

    @Override
    public CustomUser getByUsername(String username) {
        return customUserRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }


    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public CustomUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Needed for demonstration
     */
    @Deprecated
    public void getRoleAdmin() {
        var user = getCurrentUser();
        user.setRole(ROLE_ADMIN);
        save(user);
    }

    /**
     * Needed for demonstration
     */
    @Deprecated
    public void getRolePosts() {
        var user = getCurrentUser();
        user.setRole(ROLE_POSTS);
        save(user);
    }


    /**
     * Needed for demonstration
     */
    @Deprecated
    public void getRoleAlbums() {
        var user = getCurrentUser();
        user.setRole(ROLE_ALBUMS);
        save(user);
    }

    /**
     * Needed for demonstration
     */
    @Deprecated
    public void getRoleUsers() {
        var user = getCurrentUser();
        user.setRole(ROLE_USERS);
        save(user);
    }
}
