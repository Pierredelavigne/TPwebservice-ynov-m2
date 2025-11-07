package org.example.serverstreaming.service.impl;

import org.example.serverstreaming.entity.TransactionEntity;
import org.example.serverstreaming.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.example.proto.Transaction; // messages générés

@Service
public class TransactionServiceImpl {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    // ✅ NOUVELLE signature avec 5e paramètre "sink"
    @Transactional(readOnly = true)
    public void streamByFilter(long accountId,
                               String fromDate,
                               String toDate,
                               double minAmount,
                               Consumer<Transaction> sink) {

        Instant from = LocalDate.parse(fromDate).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant to   = LocalDate.parse(toDate).plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).minusSeconds(1);

        try (Stream<TransactionEntity> s = repository.streamByFilter(accountId, from, to, minAmount)) {
            s.map(this::toProto).forEach(sink); // itération DANS la transaction
        }
    }

    // Mapping Entité -> Proto
    private Transaction toProto(TransactionEntity e) {
        return Transaction.newBuilder()
                .setId(e.getId())
                .setAccountId(e.getAccountId())
                .setTimestamp(e.getTimestamp().toString())
                .setAmount(e.getAmount())
                .setCurrency(e.getCurrency())
                .setLabel(e.getLabel())
                .build();
    }
}
