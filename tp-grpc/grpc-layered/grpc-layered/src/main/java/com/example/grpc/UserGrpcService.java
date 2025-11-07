package com.example.grpc;

import com.example.entity.User;
import com.example.service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserService userService;

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userService.createUser(request.getName(), request.getEmail());
        
        UserResponse response = UserResponse.newBuilder()
            .setId(user.getId())
            .setName(user.getName())
            .setEmail(user.getEmail())
            .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        userService.getUserById(request.getId()).ifPresentOrElse(
            user -> {
                UserResponse response = UserResponse.newBuilder()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            },
            () -> responseObserver.onError(new RuntimeException("User not found"))
        );
    }

    @Override
    public void getAllUsers(Empty request, StreamObserver<UserListResponse> responseObserver) {
        List<User> users = userService.getAllUsers();
        
        UserListResponse.Builder builder = UserListResponse.newBuilder();
        users.forEach(user -> 
            builder.addUsers(UserResponse.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .build())
        );
        
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
        boolean success = userService.deleteUser(request.getId());
        
        DeleteUserResponse response = DeleteUserResponse.newBuilder()
            .setSuccess(success)
            .setMessage(success ? "User deleted" : "User not found")
            .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
