package com.example.springgrpcbank.service.impl;

import com.example.springgrpcbank.domain.Operation;
import com.example.springgrpcbank.repository.AccountRepository;
import com.example.springgrpcbank.repository.OperationRepository;
import com.example.springgrpcbank.service.OperationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepository opRepo;
    private final AccountRepository accRepo;

    @Override
    public Operation create(Long accountId, Operation.OperationType type, double amount, String description){
        var acc = accRepo.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found: "+accountId));
        // règle simple: WITHDRAW nécessite solde suffisant
        if(type == Operation.OperationType.WITHDRAW && acc.getBalance() < amount){
            throw new IllegalStateException("Insufficient balance");
        }
        // mise à jour du solde
        double newBalance = switch (type){
            case DEPOSIT -> acc.getBalance() + amount;
            case WITHDRAW -> acc.getBalance() - amount;
            case TRANSFER -> acc.getBalance() - amount; // côté débiteur; côté créditeur traité ailleurs
        };
        acc.setBalance(newBalance);

        var op = Operation.builder()
                .account(acc)
                .type(type)
                .amount(amount)
                .description(description)
                .createdAt(Instant.now())
                .build();

        accRepo.save(acc);
        return opRepo.save(op);
    }

    @Override
    public Stream<Operation> streamByAccount(Long accountId){
        return opRepo.findByAccount_IdOrderByCreatedAtDesc(accountId);
    }
}
