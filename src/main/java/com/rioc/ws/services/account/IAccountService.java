package com.rioc.ws.services.account;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Address;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AddressDto;

import java.util.List;

public interface IAccountService {
    Account postAccount(AccountDto account);
    Account deleteAccount(AccountDto account);
    List<Account> getAccounts();
    boolean verifyAddress(AddressDto addressDto);
}
