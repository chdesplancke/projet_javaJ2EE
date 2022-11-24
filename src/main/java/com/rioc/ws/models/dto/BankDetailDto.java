package com.rioc.ws.models.dto;

import com.rioc.ws.models.dao.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class BankDetailDto {
    private int bankdetail_id;
    private String iban;
    private int account;
}
