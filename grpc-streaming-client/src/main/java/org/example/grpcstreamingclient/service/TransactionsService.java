package org.example.grpcstreamingclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.proto.Transaction;
import org.example.proto.TransactionFilter;
import org.example.proto.TransactionServiceGrpc;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionsService {

    @GrpcClient("transactions")
    private TransactionServiceGrpc.TransactionServiceBlockingStub stub;

    private TransactionFilter buildRequest(long accountId, String fromDate, String toDate, double minAmount) {
        if (accountId <= 0) throw new IllegalArgumentException("accountId must be > 0");
        if (!StringUtils.hasText(fromDate)) throw new IllegalArgumentException("fromDate is required");
        if (!StringUtils.hasText(toDate)) throw new IllegalArgumentException("toDate is required");

        return TransactionFilter.newBuilder()
                .setAccountId(accountId)
                .setFromDate(fromDate)
                .setToDate(toDate)
                .setMinAmount(minAmount)
                .build();
    }

    //** Fournit l'Iterator sur le flux gRPC (utile pour SSE). *//*
    public Iterator<Transaction> stream(long accountId, String fromDate, String toDate, double minAmount) {
        var req = buildRequest(accountId, fromDate, toDate, minAmount);
        log.info("Calling gRPC StreamTransactions: accountId={}, from={}, to={}, minAmount={}",
                accountId, fromDate, toDate, minAmount);
        return stub.streamTransactions(req);
    }

    //** Renvoie les N premiers éléments sous forme de liste (endpoint JSON). *//*
    public List<Transaction> sample(long accountId, String fromDate, String toDate, double minAmount, int limit) {
        var it = stream(accountId, fromDate, toDate, minAmount);
        var out = new ArrayList<Transaction>(Math.max(limit, 1));
        int c = 0;
        while (it.hasNext() && c < limit) {
            out.add(it.next());
            c++;
        }
        return out;
    }
}
