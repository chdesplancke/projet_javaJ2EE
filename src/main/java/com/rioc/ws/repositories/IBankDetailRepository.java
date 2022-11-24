package com.rioc.ws.repositories;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBankDetailRepository extends JpaRepository<BankDetail, Integer> {

    @Query("SELECT a FROM BankDetail a WHERE a.iban = ?1")
    List<BankDetail> findBankDetailByIban(String iban);

    @Query("SELECT a FROM BankDetail a WHERE a.account_id = ?1")
    List<BankDetail> findBankDetailByAccount_id(int account_id);
}
