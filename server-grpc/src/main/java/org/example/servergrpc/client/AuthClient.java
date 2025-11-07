package org.example.servergrpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.AuthServiceGrpc;
import org.example.grpc.LoginRequest;
import org.example.grpc.LoginResponse;

public class AuthClient {


    public static void main(String[] args) {
        ManagedChannel channel =
                ManagedChannelBuilder.forAddress("localhost", 9090)
                        .usePlaintext()
                        .build();
        AuthServiceGrpc.AuthServiceBlockingStub stub = AuthServiceGrpc.newBlockingStub(channel);
        LoginRequest request = LoginRequest.newBuilder().setUsername("admin").setPassword("password").build();
        LoginResponse response = stub.login(request);
        System.out.println("Login Response: " + response);
        channel.shutdown();
    }




}
