package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.domain.entity.CustomUser;
import org.dkcorp.vktesttask.dto.request.SignInRequestDto;
import org.dkcorp.vktesttask.dto.request.SignUpRequestDto;
import org.dkcorp.vktesttask.dto.response.AuthResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.dkcorp.vktesttask.domain.enums.Role.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserService customUserService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Void> signIn(SignInRequestDto request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password());
        authenticationManager.authenticate(authenticationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public AuthResponseDto signUp(SignUpRequestDto request) {
        CustomUser customUser = CustomUser.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))

                /*
                 * When registering a new user,
                 * he is automatically assigned the ADMIN role,
                 * this is necessary for DEMONSTRATION,
                 * as the api user is supposed to have all api capabilities
                 */
                .role(ROLE_ADMIN)
                .build();

        customUserService.create(customUser);
        return new AuthResponseDto(request.username(), request.email());
    }
}
