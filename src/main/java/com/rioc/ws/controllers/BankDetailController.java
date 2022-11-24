package com.rioc.ws.controllers;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IBankDetailMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.BankDetailDto;
import com.rioc.ws.repositories.IBankDetailRepository;
import com.rioc.ws.services.account.IAccountService;
import com.rioc.ws.services.bankDetail.IBankDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Gestion des informations bancaires")
@RestController
public class BankDetailController {

    private IBankDetailService service;
    private IBankDetailRepository bankDetailRepository;

    public BankDetailController(IBankDetailService service, IBankDetailRepository bankDetailRepository) {
        super();
        this.service = service;
        this.bankDetailRepository = bankDetailRepository;
    }

    @PostMapping("/bankdetail") @ApiOperation(value = "Création des informations bancaires")
    public ResponseEntity<BankDetail> postBankDetail(@RequestBody @Valid BankDetailDto bankDetailDto, BindingResult result){
        if(result.hasErrors()){
            throw new ApiException("erreur", HttpStatus.PRECONDITION_FAILED);
        }

        if (!bankDetailRepository.findBankDetailByIban(bankDetailDto.getIban()).isEmpty()){
            throw new ApiException("Iban already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.postBankDetail(bankDetailDto), HttpStatus.CREATED);
    }
}
