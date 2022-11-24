package com.rioc.ws.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BANKDETAIL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANKDETAIL_ID", unique = true, nullable = false)
    private int bankdetail_id;

    @Column(name = "iban")
    private String iban;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account_id;

    public int getBankdetail_id() {
        return bankdetail_id;
    }

    public void setBankdetail_id(int bankdetail_id) {
        this.bankdetail_id = bankdetail_id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Account getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Account account) {
        this.account_id = account;
    }
}
