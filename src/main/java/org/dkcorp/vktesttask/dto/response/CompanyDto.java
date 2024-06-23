package org.dkcorp.vktesttask.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CompanyDto(
        @Schema(description = "Name of the company", example = "ACME Corporation")
        String name,

        @Schema(description = "Catchphrase of the company", example = "Making the world a better place")
        String catchPhrase,

        @Schema(description = "Business slogan of the company", example = "Innovative solutions for everyone")
        String bs
) {
}
