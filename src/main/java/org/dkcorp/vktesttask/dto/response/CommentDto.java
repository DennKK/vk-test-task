package org.dkcorp.vktesttask.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CommentDto(
        @Schema(description = "ID of the post this comment belongs to", example = "1")
        Long postId,

        @Schema(description = "Unique identifier of the comment", example = "101")
        Long id,

        @Schema(description = "Name of the commenter", example = "John Doe")
        String name,

        @Schema(description = "Email address of the commenter", example = "john.doe@example.com")
        String email,

        @Schema(description = "Comment body", example = "This is a great post!")
        String body
) {
}
