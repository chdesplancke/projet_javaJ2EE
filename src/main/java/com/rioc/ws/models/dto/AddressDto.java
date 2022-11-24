package com.rioc.ws.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data  @NoArgsConstructor  @AllArgsConstructor
@Builder
public class AddressDto {
    private int number;
    private String street;
    private String city;
    private String zip_code;
    private String country;


}
