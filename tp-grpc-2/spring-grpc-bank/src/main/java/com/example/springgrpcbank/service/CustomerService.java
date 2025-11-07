package com.example.springgrpcbank.service;

import com.example.springgrpcbank.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(String firstName, String lastName, String email);
    Customer get(Long id);
    List<Customer> list();
}
