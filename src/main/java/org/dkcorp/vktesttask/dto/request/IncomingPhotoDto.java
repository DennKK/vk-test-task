package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record IncomingPhotoDto(
        @Schema(description = "Заголовок фотографии", example = "Семейный отдых")
        String title,

        @Schema(description = "URL фотографии", example = "http://example.com/photo.jpg")
        String url,

        @Schema(description = "URL миниатюры фотографии", example = "http://example.com/thumbnail.jpg")
        String thumbnailUrl
) {
}
