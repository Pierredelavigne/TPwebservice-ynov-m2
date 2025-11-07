package com.example.springgrpcbank.transport;


import com.example.springgrpcbank.mapper.ProtoMapper;
import com.example.springgrpcbank.service.CustomerService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CustomerGrpcService extends com.example.springgrpcbank.proto.CustomerServiceGrpc.CustomerServiceImplBase {
    private final CustomerService service;

    @Override
    public void create(com.example.springgrpcbank.proto.CustomerCreateReq req, StreamObserver<com.example.springgrpcbank.proto.CustomerRes> out){
        var c = service.create(req.getFirstName(), req.getLastName(), req.getEmail());
        out.onNext(ProtoMapper.toProto(c));
        out.onCompleted();
    }

    @Override
    public void get(com.example.springgrpcbank.proto.CustomerId req, StreamObserver<com.example.springgrpcbank.proto.CustomerRes> out){
        var c = service.get(req.getId());
        out.onNext(ProtoMapper.toProto(c));
        out.onCompleted();
    }

    @Override
    public void list(com.example.springgrpcbank.proto.Empty req, StreamObserver<com.example.springgrpcbank.proto.CustomerRes> out){
        service.list().forEach(c -> out.onNext(ProtoMapper.toProto(c)));
        out.onCompleted();
    }
}
