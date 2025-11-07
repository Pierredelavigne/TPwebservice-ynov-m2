package com.example.springgrpcbank.domain;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "operations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType type;
    @Column(nullable = false)
    private Double amount;
    private String description;
    @Column(nullable = false)
    private Instant createdAt;

    public enum OperationType {DEPOSIT, WITHDRAW, TRANSFER}

}