package org.dkcorp.vktesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PhotoDto(
        @Schema(description = "Идентификатор альбома, к которому относится фотография", example = "1")
        Long albumId,

        @Schema(description = "Идентификатор фотографии", example = "100")
        Long id,

        @Schema(description = "Заголовок фотографии", example = "Семейный отдых")
        String title,

        @Schema(description = "URL фотографии", example = "http://example.com/photo.jpg")
        String url,

        @Schema(description = "URL миниатюры фотографии", example = "http://example.com/thumbnail.jpg")
        String thumbnailUrl
) {
}
