package org.dkcorp.vktesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        @Schema(description = "Unique identifier for the user", example = "1")
        Long id,

        @Schema(description = "Name of the user", example = "John Doe")
        String name,

        @Schema(description = "Username of the user", example = "johndoe")
        String username,

        @Schema(description = "Email address of the user", example = "john.doe@example.com")
        String email,

        @Schema(description = "Address information of the user")
        AddressDto address,

        @Schema(description = "Phone number of the user", example = "123-456-7890")
        String phone,

        @Schema(description = "Website URL of the user", example = "https://johndoe.com")
        String website,

        @Schema(description = "Company information of the user")
        CompanyDto company
) {
}
