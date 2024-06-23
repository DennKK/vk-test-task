package org.dkcorp.vktesttask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record IncomingAddressDto(
        @Schema(description = "Street name", example = "123 Main St")
        String street,

        @Schema(description = "Suite or apartment number", example = "Apt 101")
        String suite,

        @Schema(description = "City name", example = "New York")
        String city,

        @Schema(description = "ZIP code", example = "10001")
        String zipcode,

        @Schema(description = "Geographical location")
        IncomingGeoDto geo
) {
}
