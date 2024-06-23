package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record IncomingCommentDto(
        @Schema(description = "Name of the commenter", example = "John Doe")
        String name,

        @Schema(description = "Email address of the commenter", example = "john.doe@example.com")
        String email,

        @Schema(description = "Comment body", example = "This is a great post!")
        String body
) {
}
