package com.example.springgrpcbank.repository;

import com.example.springgrpcbank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
