package com.example.springgrpcbank.service.impl;



import com.example.springgrpcbank.domain.Customer;
import com.example.springgrpcbank.repository.CustomerRepository;
import com.example.springgrpcbank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;

    @Override
    public Customer create(String firstName, String lastName, String email){
        return repo.save(Customer.builder().firstName(firstName).lastName(lastName).email(email).build());
    }
    @Override
    public Customer get(Long id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found: "+id));
    }
    @Override
    public List<Customer> list(){ return repo.findAll(); }
}
