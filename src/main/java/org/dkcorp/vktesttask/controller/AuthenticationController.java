package org.dkcorp.vktesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.SignInRequestDto;
import org.dkcorp.vktesttask.dto.request.SignUpRequestDto;
import org.dkcorp.vktesttask.dto.response.AuthResponseDto;
import org.dkcorp.vktesttask.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Operations related to authentication")
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in", description = "Sign in to the system")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequestDto signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Sign up", description = "Sign up to the system")
    public AuthResponseDto signUp(@RequestBody SignUpRequestDto signUpRequest) {
        return authService.signUp(signUpRequest);
    }
}
