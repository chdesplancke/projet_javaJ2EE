package com.rioc.ws.services.account;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IAccountMapper;
import com.rioc.ws.mappers.IAddressMapper;
import com.rioc.ws.mappers.IAddressMapperImpl;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Address;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AddressDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.repositories.IBankDetailRepository;
import org.apache.commons.validator.routines.checkdigit.*;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class AccountService implements IAccountService{

    private IAccountRepository repository;
    private IBankDetailRepository bankDetailRepository;
    private IAccountMapper mapper;
    private IAddressMapper addressMapper;

    public AccountService(IAccountRepository repository, IAccountMapper mapper, IAddressMapper addressMapper, IBankDetailRepository bankDetailRepository){
        super();
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.repository = repository;
        this.bankDetailRepository = bankDetailRepository;
    }

    public Account postAccount(AccountDto account) {
        if (!repository.findAccountByName(account.getFirst_name(), account.getLast_name()).isEmpty()){
            throw new ApiException("ACCOUNT ALREADY EXIST", HttpStatus.CONFLICT);
        }
        if(!verifyAddress(account.getAddress_id())){
            throw new ApiException("Invalid address", HttpStatus.CONFLICT);
        }
        return repository.save(mapper.accountDtoToAccount(account));
    }

    public List<Account> getAccounts() {
        return repository.findAll();
    }

    public Account deleteAccount(AccountDto account) {
        Account acc = mapper.accountDtoToAccount(account);
        List<Account> accounts = repository.findAccountByName(account.getFirst_name(), account.getLast_name());
        if(accounts.isEmpty()){
            throw new ApiException("Account doesn't exist", HttpStatus.NO_CONTENT);
        }
        for (Account a : accounts) {
            repository.delete(a);
        }
        /*
        List<BankDetail> bankDetails;
        for (Account a : accounts) {
            repository.delete(a);
            bankDetails = bankDetailRepository.findBankDetailByAccount_id(a.getAccountId());
            if(!bankDetails.isEmpty()){
                for (BankDetail bankDetail : bankDetails) {
                    bankDetailRepository.delete(bankDetail);
                }
            }
        }

         */
        return acc;
    }

    public boolean verifyAddress(AddressDto addressDto){
        Address address = addressMapper.addressDtoToAddress(addressDto);
        try {
            String data = String.join("+", String.valueOf(address.getNumber()), address.getStreet());
            data = data.replace(" ", "+");
            URL urlForGetRequest = new URL("https://api-adresse.data.gouv.fr/search/?q="
                    + data
                    + "&postcode="
                    + address.getZip_code()
                    + "&autocomplete=0"
                    + "&limit=1"
            );
            String readLine;

            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                JSONObject obj = new JSONObject(response.toString());
                return !obj.getJSONArray("features").isEmpty();
            } else {
                System.out.println(conection);
            }
            return false;
        }catch (ApiException | IOException ex){
            throw new ApiException("Unnable to contact Government API", HttpStatus.REQUEST_TIMEOUT);
        }

    }
}
