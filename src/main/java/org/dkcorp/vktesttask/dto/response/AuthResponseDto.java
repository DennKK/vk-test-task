package org.dkcorp.vktesttask.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponseDto(
        @Schema(description = "Username of the authenticated user", example = "john_doe")
        String username,

        @Schema(description = "Email address of the authenticated user", example = "john.doe@example.com")
        String email
) {
}
