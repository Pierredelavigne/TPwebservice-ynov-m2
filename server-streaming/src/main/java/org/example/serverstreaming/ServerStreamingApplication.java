package org.example.serverstreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerStreamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerStreamingApplication.class, args);
    }

}


/*
grpcurl -plaintext -d '{"accountId":1, "fromDate":"2025-01-01", "toDate":"2025-01-02", "minAmount":0}' localhost:9090 transactions.TransactionService/StreamTransactions*/

/*grpcurl -plaintext -d '{\"accountId\":1, \"fromDate\":\"2025-01-01\", \"toDate\":\"2025-01-02\", \"minAmount\":0}' localhost:9090 transactions.TransactionService/StreamTransactions */


/*grpcurl -v -plaintext -d '{"accountId":1,"fromDate":"2025-01-01","toDate":"2025-01-02","minAmount":0}' \
localhost:9090 transactions.TransactionService/StreamTransactions*/
