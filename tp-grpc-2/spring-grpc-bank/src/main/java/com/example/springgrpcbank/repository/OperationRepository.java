package com.example.springgrpcbank.repository;

import com.example.springgrpcbank.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Stream<Operation> findByAccount_IdOrderByCreatedAtDesc(Long accountId);
}
