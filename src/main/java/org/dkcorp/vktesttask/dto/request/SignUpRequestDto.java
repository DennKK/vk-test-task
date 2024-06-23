package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(
        @NotBlank(message = "Username cannot be blank")
        @Schema(description = "Username for sign-up", example = "johndoe")
        String username,

        @Email(message = "Email should be valid")
        @Schema(description = "Email address for sign-up", example = "john.doe@example.com")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Schema(description = "Password for sign-up (min length: 6)", example = "password123")
        String password
) {
}
