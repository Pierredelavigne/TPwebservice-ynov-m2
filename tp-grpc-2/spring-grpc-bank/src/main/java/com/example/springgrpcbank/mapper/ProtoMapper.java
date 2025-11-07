package com.example.springgrpcbank.mapper;


import com.example.springgrpcbank.proto.CustomerRes;
import com.example.springgrpcbank.proto.AccountRes;
import com.example.springgrpcbank.proto.OperationRes;
import com.example.springgrpcbank.domain.*;

import com.example.springgrpcbank.proto.OperationType;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ProtoMapper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);

    public static CustomerRes toProto(Customer c){
        return CustomerRes.newBuilder()
                .setId(c.getId())
                .setFirstName(c.getFirstName())
                .setLastName(c.getLastName())
                .setEmail(c.getEmail())
                .build();
    }

    public static AccountRes toProto(Account a){
        return AccountRes.newBuilder()
                .setId(a.getId())
                .setIban(a.getIban())
                .setBalance(a.getBalance())
                .setCustomerId(a.getCustomer().getId())
                .build();
    }

    public static OperationRes toProto(Operation o){
        return OperationRes.newBuilder()
                .setId(o.getId())
                .setAccountId(o.getAccount().getId())
                .setType(switch (o.getType()){
                    case DEPOSIT -> OperationType.DEPOSIT;
                    case WITHDRAW -> OperationType.WITHDRAW;
                    case TRANSFER -> OperationType.TRANSFER;
                })
                .setAmount(o.getAmount())
                .setDescription(o.getDescription() == null ? "" : o.getDescription())
                .setCreatedAt(ISO.format(o.getCreatedAt()))
                .build();
    }
}
