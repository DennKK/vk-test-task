package org.dkcorp.vktesttask.dto.response;

public record AddressDto(
        String street,
        String suite,
        String city,
        String zipcode,
        GeoDto geo
) {
}
