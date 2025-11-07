package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}



/* PS C:\Users\mohamed> grpcurl -plaintext -d '{\"name\":\"Alice\",\"email\":\"alice@example.com\"}' localhost:9090 user.UserService/CreateUser
{
  "id": "1",
  "name": "Alice",
  "email": "alice@example.com"
} */

/* grpcurl -plaintext -d '{\"id\":1}' localhost:9090 user.UserService/GetUser */


/* grpcurl -plaintext -d '{}' localhost:9090 user.UserService/GetAllUsers */


/* grpcurl -plaintext -d '{\"id\":1}' localhost:9090 user.UserService/DeleteUser */
