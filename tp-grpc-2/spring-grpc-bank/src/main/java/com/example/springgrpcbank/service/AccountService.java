package com.example.springgrpcbank.service;

import com.example.springgrpcbank.domain.Account;

import java.util.List;

public interface AccountService {
    Account create(Long customerId, String iban, double initialBalance);
    Account get(Long id);
    List<Account> listByCustomer(Long customerId);
}
