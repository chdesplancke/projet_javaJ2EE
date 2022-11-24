package com.rioc.ws.repositories;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAddressRepository extends JpaRepository<Address, Integer> {

    //@Query("SELECT a FROM Address a WHERE a.account_id = ?1")
    //List<Address> findAddressByUser(int account_id);
}
