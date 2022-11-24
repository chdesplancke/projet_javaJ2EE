package com.rioc.ws.controllers;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IAddressMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Address;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AddressDto;
import com.rioc.ws.services.account.IAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Gestion des utilisateurs")
@RestController
public class AccountController {

    private IAccountService service;

    public AccountController(IAccountService service) {
        super();
        this.service = service;
    }

    @PostMapping("/accounts") @ApiOperation(value = "Création de compte")
    public ResponseEntity<Account> postAccount(@RequestBody @Valid AccountDto account, BindingResult result){
        if(!result.hasErrors()){
            return new ResponseEntity<>(service.postAccount(account), HttpStatus.CREATED);
        }
        System.out.println(result.getAllErrors());
        throw new ApiException("erreur", HttpStatus.PRECONDITION_FAILED);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccounts(){
        return new ResponseEntity<>(service.getAccounts(), HttpStatus.CREATED);
    }

    @DeleteMapping("/accounts")
    public ResponseEntity<Account> deleteAccount(AccountDto account){
        return new ResponseEntity<>(service.deleteAccount(account), HttpStatus.OK);
    }
}
