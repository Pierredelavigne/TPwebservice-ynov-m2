package org.example.serverstreaming;

import org.example.serverstreaming.entity.TransactionEntity;
import org.example.serverstreaming.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner seed(TransactionRepository repo) {
        return args -> {
            if (repo.count() > 0) return;

            var base = LocalDateTime.of(2025, 1, 1, 9, 0).toInstant(ZoneOffset.UTC);
            List<TransactionEntity> data = List.of(
                    tx(1L, base.plusSeconds(10), 120.50, "EUR", "Paiement CB"),
                    tx(1L, base.plusSeconds(3600), 990.00, "EUR", "Virement salaire"),
                    tx(1L, base.plusSeconds(7200), -45.90, "EUR", "Uber"),
                    tx(2L, base.plusSeconds(1800), 200.00, "EUR", "Remboursement"),
                    tx(1L, base.plusSeconds(10800), -12.00, "EUR", "Café")
            );
            repo.saveAll(data);
        };
    }

    private static TransactionEntity tx(Long accountId, Instant ts, Double amount, String currency, String label) {
        var e = new TransactionEntity();
        e.setAccountId(accountId);
        e.setTimestamp(ts);
        e.setAmount(amount);
        e.setCurrency(currency);
        e.setLabel(label);
        return e;
    }
}
