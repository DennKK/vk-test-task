package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInRequestDto(
        @NotBlank(message = "Username cannot be blank")
        @Schema(description = "Username for sign-in", example = "johndoe")
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Schema(description = "Password for sign-in (min length: 6)", example = "password123")
        String password
) {
}
