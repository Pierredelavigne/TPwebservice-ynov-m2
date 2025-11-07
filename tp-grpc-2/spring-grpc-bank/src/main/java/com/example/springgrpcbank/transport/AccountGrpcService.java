package com.example.springgrpcbank.transport;


import com.example.springgrpcbank.mapper.ProtoMapper;
import com.example.springgrpcbank.service.AccountService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
@RequiredArgsConstructor
public class AccountGrpcService extends com.example.springgrpcbank.proto.AccountServiceGrpc.AccountServiceImplBase {
    private final AccountService service;

    @Override
    public void create(com.example.springgrpcbank.proto.AccountCreateReq req, StreamObserver<com.example.springgrpcbank.proto.AccountRes> out){
        var a = service.create(req.getCustomerId(), req.getIban(), req.getInitialBalance());
        out.onNext(ProtoMapper.toProto(a));
        out.onCompleted();
    }

    @Override
    public void get(com.example.springgrpcbank.proto.AccountId req, StreamObserver<com.example.springgrpcbank.proto.AccountRes> out){
        var a = service.get(req.getId());
        out.onNext(ProtoMapper.toProto(a));
        out.onCompleted();
    }

    @Override
    public void listByCustomer(com.example.springgrpcbank.proto.AccountsByCustomerReq req, StreamObserver<com.example.springgrpcbank.proto.AccountRes> out){
        service.listByCustomer(req.getCustomerId()).forEach(a -> out.onNext(ProtoMapper.toProto(a)));
        out.onCompleted();
    }
}