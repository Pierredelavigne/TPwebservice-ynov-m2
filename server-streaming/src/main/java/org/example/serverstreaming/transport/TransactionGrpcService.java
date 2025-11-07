package org.example.serverstreaming.transport;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import org.example.proto.Transaction;
import org.example.proto.TransactionFilter;
import org.example.proto.TransactionServiceGrpc;
import org.example.serverstreaming.service.impl.TransactionServiceImpl;

@GrpcService
public class TransactionGrpcService extends TransactionServiceGrpc.TransactionServiceImplBase {

    private final TransactionServiceImpl domainService;

    public TransactionGrpcService(TransactionServiceImpl domainService) {
        this.domainService = domainService;
    }

    @Override
    public void streamTransactions(TransactionFilter request, StreamObserver<Transaction> out) {
        try {
            if (request.getAccountId() <= 0 || request.getFromDate().isBlank() || request.getToDate().isBlank()) {
                throw new IllegalArgumentException("accountId/fromDate/toDate are required");
            }

            // ✅ On passe un Consumer<Transaction> : out::onNext
            domainService.streamByFilter(
                    request.getAccountId(),
                    request.getFromDate(),
                    request.getToDate(),
                    request.getMinAmount(),
                    out::onNext
            );
            out.onCompleted();

        } catch (IllegalArgumentException e) {
            out.onError(Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            e.printStackTrace();
            out.onError(Status.INTERNAL.withDescription("Streaming failed: " + e.getClass().getSimpleName())
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}
