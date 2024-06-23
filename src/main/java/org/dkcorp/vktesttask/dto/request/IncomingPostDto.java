package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record IncomingPostDto(
        @Schema(description = "ID of the user who created the post", example = "1")
        Long userId,

        @Schema(description = "Title of the post", example = "Introduction to RESTful APIs")
        String title,

        @Schema(description = "Body content of the post", example = "In this post, we discuss RESTful API principles and best practices.")
        String body
) {
}
