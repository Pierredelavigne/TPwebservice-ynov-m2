package com.example.springgrpcbank.service.impl;

import com.example.springgrpcbank.domain.Account;
import com.example.springgrpcbank.repository.AccountRepository;
import com.example.springgrpcbank.repository.CustomerRepository;
import com.example.springgrpcbank.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;

    @Override
    public Account create(Long customerId, String iban, double initialBalance){
        var customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: "+customerId));
        var acc = Account.builder().customer(customer).iban(iban).balance(initialBalance).build();
        return accountRepo.save(acc);
    }
    @Override
    public Account get(Long id){
        return accountRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found: "+id));
    }
    @Override
    public List<Account> listByCustomer(Long customerId){
        return accountRepo.findByCustomer_Id(customerId);
    }
}
