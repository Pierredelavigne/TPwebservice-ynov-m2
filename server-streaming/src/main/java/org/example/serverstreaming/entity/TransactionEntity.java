package org.example.serverstreaming.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private String label;


}