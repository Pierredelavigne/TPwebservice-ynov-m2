package org.example.servergrpc.service;


import io.grpc.stub.StreamObserver;
import org.example.grpc.LoginRequest;
import org.example.grpc.LoginResponse;
import net.devh.boot.grpc.server.service.GrpcService;


import java.util.UUID;


@GrpcService
public class AuthServiceImpl extends org.example.grpc.AuthServiceGrpc.AuthServiceImplBase {


    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {


        LoginResponse loginResponse;

        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {

            loginResponse = LoginResponse.newBuilder()
                    .setSuccess(true)
                    .setToken("token - " + UUID.randomUUID().toString().substring(0, 8))
                    .setMessage("Login Successful")
                    .build();

        } else {
            loginResponse = LoginResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Invalid credentials")
                    .setToken("")
                    .build();
        }

        responseObserver.onNext(loginResponse);
        responseObserver.onCompleted();


    }
}
