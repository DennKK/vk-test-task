package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record IncomingGeoDto(
        @Schema(description = "Latitude coordinate", example = "37.7749")
        String lat,

        @Schema(description = "Longitude coordinate", example = "-122.4194")
        String lng
) {
}
