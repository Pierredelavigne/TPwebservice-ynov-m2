package com.example.springgrpcbank.transport;

import com.example.springgrpcbank.proto.*;
import com.example.springgrpcbank.domain.Operation.OperationType;
import com.example.springgrpcbank.mapper.ProtoMapper;
import com.example.springgrpcbank.service.OperationService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class OperationGrpcService extends com.example.springgrpcbank.proto.OperationServiceGrpc.OperationServiceImplBase {
    private final OperationService service;

    @Override
    public void create(com.example.springgrpcbank.proto.OperationCreateReq req, StreamObserver<com.example.springgrpcbank.proto.OperationRes> out){
        var type = switch (req.getType()){
            case DEPOSIT -> OperationType.DEPOSIT;
            case WITHDRAW -> OperationType.WITHDRAW;
            case TRANSFER -> OperationType.TRANSFER;
            default -> throw new IllegalArgumentException("Unknown type");
        };
        var op = service.create(req.getAccountId(), type, req.getAmount(), req.getDescription());
        out.onNext(ProtoMapper.toProto(op));
        out.onCompleted();
    }

    @Override
    public void streamByAccount(com.example.springgrpcbank.proto.OperationsByAccountReq req, StreamObserver<com.example.springgrpcbank.proto.OperationRes> out){
        try(var stream = service.streamByAccount(req.getAccountId())){
            stream.map(ProtoMapper::toProto).forEach(out::onNext);
        }
        out.onCompleted();
    }
}