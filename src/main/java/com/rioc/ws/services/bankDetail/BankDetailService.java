package com.rioc.ws.services.bankDetail;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IAccountMapper;
import com.rioc.ws.mappers.IBankDetailMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.BankDetailDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.repositories.IBankDetailRepository;
import com.rioc.ws.services.account.AccountService;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankDetailService implements IBankDetailService {

    private IBankDetailRepository repository;
    private IAccountRepository accountRepository;
    private IBankDetailMapper mapper;
    private IAccountMapper accountMapper;

    public BankDetailService(IBankDetailRepository repository, IBankDetailMapper mapper, IAccountMapper accountMapper, IAccountRepository accountRepository){
        super();
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.repository = repository;
    }

    public BankDetail postBankDetail(BankDetailDto bankDetailDto) {
        BankDetail bankDetail = mapper.bankDetailDtotoBankDetail(bankDetailDto);
        System.out.println(bankDetail);
        Optional<Account> account = accountRepository.findById(bankDetailDto.getAccount());

        if(account.isEmpty()){
            throw new ApiException("Cannot get user", HttpStatus.CONFLICT);
        }

        IBANCheckDigit checkDigit = new IBANCheckDigit();
        if(!checkDigit.isValid(bankDetail.getIban())){
            throw new ApiException("Wrong Iban format", HttpStatus.CONFLICT);
        }

        bankDetail.setAccount_id(account.get());
        return repository.save(bankDetail);

    }

}
