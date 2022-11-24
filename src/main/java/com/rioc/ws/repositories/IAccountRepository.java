package com.rioc.ws.repositories;

import com.rioc.ws.models.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.first_name = ?1 AND a.last_name = ?2")
    List<Account> findAccountByName(String first_name, String last_name);

    @Query("SELECT a FROM Account a")
    List<Account> getAllAccounts();

    @Query("SELECT a FROM Account a WHERE a.accountId = ?1")
    int getAccountByAccountId();


}
