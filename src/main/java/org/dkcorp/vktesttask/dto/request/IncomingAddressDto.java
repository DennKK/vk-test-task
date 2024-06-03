package org.dkcorp.vktesttask.dto.request;

public record IncomingAddressDto(
        String street,
        String suite,
        String city,
        String zipcode,
        IncomingGeoDto geo
) {
}
