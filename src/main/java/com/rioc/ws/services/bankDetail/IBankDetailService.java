package com.rioc.ws.services.bankDetail;

import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.BankDetailDto;

public interface IBankDetailService {
    BankDetail postBankDetail(BankDetailDto account);

}
