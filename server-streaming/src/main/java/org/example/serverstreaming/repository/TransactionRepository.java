package org.example.serverstreaming.repository;

import org.example.serverstreaming.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.stream.Stream;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("""
    select t from TransactionEntity t
    where t.accountId = :accountId
      and t.timestamp between :from and :to
      and (:minAmount <= 0 or t.amount >= :minAmount)
    order by t.timestamp asc
  """)
    Stream<TransactionEntity> streamByFilter(Long accountId, Instant from, Instant to, double minAmount);
}