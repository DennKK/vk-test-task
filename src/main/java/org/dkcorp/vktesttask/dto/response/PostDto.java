package org.dkcorp.vktesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PostDto(
        @Schema(description = "ID of the user who created the post", example = "1")
        Long userId,

        @Schema(description = "Unique identifier of the post", example = "101")
        Long id,

        @Schema(description = "Title of the post", example = "Introduction to RESTful APIs")
        String title,

        @Schema(description = "Body content of the post", example = "In this post, we discuss RESTful API principles and best practices.")
        String body
) {
}
