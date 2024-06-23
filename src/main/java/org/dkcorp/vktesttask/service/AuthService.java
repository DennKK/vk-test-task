package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.request.SignInRequestDto;
import org.dkcorp.vktesttask.dto.request.SignUpRequestDto;
import org.dkcorp.vktesttask.dto.response.AuthResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Void> signIn(SignInRequestDto signInRequest);

    AuthResponseDto signUp(SignUpRequestDto signUpRequest);
}
