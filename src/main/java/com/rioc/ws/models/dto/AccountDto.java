package com.rioc.ws.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class AccountDto {
    @Size(min = 2, max = 20, message = "first name doesn't respect size") @NotEmpty
    private String first_name;
    @Size(min = 2, max = 20, message = "last name doesn't respect size") @NotEmpty
    private String last_name;

    private AddressDto address_id;

}
