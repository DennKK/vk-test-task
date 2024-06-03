package org.dkcorp.vktesttask.dto.request;

public record IncomingUserDto(
        Long id,
        String name,
        String username,
        String email,
        IncomingAddressDto address,
        String phone,
        String website,
        IncomingCompanyDto company
) {
}
