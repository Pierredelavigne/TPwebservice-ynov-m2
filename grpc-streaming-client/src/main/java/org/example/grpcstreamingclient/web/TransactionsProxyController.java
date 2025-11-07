package org.example.grpcstreamingclient.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.example.grpcstreamingclient.service.TransactionsService;
import org.example.proto.Transaction;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionsProxyController {

    private final TransactionsService service;

    // 1) JSON: renvoie les N premières transactions du stream (photo ponctuelle) */
    @GetMapping("/sample")
    public List<Transaction> sample(
            @RequestParam @Min(1) long accountId,
            @RequestParam @NotBlank String fromDate,
            @RequestParam @NotBlank String toDate,
            @RequestParam(defaultValue = "0") double minAmount,
            @RequestParam(defaultValue = "10") @Min(1) int limit
    ) {
        return service.sample(accountId, fromDate, toDate, minAmount, limit);
    }

    //** 2) SSE: stream temps réel vers le navigateur (text/event-stream) *//*
    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream(
            @RequestParam @Min(1) long accountId,
            @RequestParam @NotBlank String fromDate,
            @RequestParam @NotBlank String toDate,
            @RequestParam(defaultValue = "0") double minAmount,
            @RequestParam(defaultValue = "0") int max // 0 = illimité
    ) {
        var emitter = new SseEmitter(0L); // pas de timeout
        new Thread(() -> {
            try {
                Iterator<Transaction> it = service.stream(accountId, fromDate, toDate, minAmount);
                int sent = 0;
                while (it.hasNext() && (max == 0 || sent < max)) {
                    emitter.send(it.next());
                    sent++;
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }, "sse-grpc-relay").start();

        return emitter;
    }
}