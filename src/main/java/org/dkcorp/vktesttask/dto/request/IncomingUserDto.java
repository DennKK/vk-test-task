package org.dkcorp.vktesttask.dto.request;

public record IncomingUserDto(
        String name,
        String username,
        String email,
        IncomingAddressDto address,
        String phone,
        String website,
        IncomingCompanyDto company
) {
}
