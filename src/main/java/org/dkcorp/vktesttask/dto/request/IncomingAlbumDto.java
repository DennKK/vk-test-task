package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record IncomingAlbumDto(
        @Schema(description = "ID of the user who owns the album", example = "1")
        Long userId,

        @Schema(description = "Title of the album", example = "Vacation Photos")
        String title
) {
}
