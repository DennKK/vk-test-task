package org.dkcorp.vktesttask.dto.request;

import org.dkcorp.vktesttask.dto.response.GeoDto;

public record IncomingAddressDto(
        String street,
        String suite,
        String city,
        String zipcode,
        GeoDto geo
) {
}
