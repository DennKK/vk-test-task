package org.dkcorp.vktesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlbumDto(
        @Schema(description = "ID of the user who owns the album", example = "1")
        Long userId,

        @Schema(description = "ID of the album", example = "101")
        Long id,

        @Schema(description = "Title of the album", example = "Vacation Photos")
        String title
) {
}
