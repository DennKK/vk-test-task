package org.dkcorp.vktesttask.dto.response;

public record UserDto(
        Long id,
        String name,
        String username,
        String email,
        AddressDto address,
        String phone,
        String website,
        CompanyDto company
) {
}
