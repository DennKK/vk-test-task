package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.domain.entity.CustomUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService {
    CustomUser save(CustomUser customUser);

    CustomUser create(CustomUser customUser);

    CustomUser getByUsername(String username);

    UserDetailsService userDetailsService();

    CustomUser getCurrentUser();
}
