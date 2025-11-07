package com.example.springgrpcbank.service;

import com.example.springgrpcbank.domain.Operation;

import java.util.stream.Stream;

public interface OperationService {
    Operation create(Long accountId, Operation.OperationType type, double amount, String description);
    Stream<Operation> streamByAccount(Long accountId);
}
