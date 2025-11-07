package com.example.client;

// Import des classes générées à partir du fichier .proto
// Ces classes contiennent les messages (CreateUserRequest, UserResponse, etc.)
// et le stub client (UserServiceGrpc) pour appeler le serveur gRPC.
import com.example.grpc.*;

// Import des classes nécessaires à la gestion du canal de communication gRPC
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserClient {
    public static void main(String[] args) {

        // ✅ Création d’un canal de communication vers le serveur gRPC
        // "localhost" → le serveur tourne en local
        // 9090 → port gRPC du serveur (configuré dans l’app Spring Boot)
        // usePlaintext() → on désactive SSL/TLS (utile en développement)
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext()
            .build();

        // ✅ Création d’un stub bloquant
        // Le stub agit comme un proxy vers le service distant gRPC.
        // Le mode "blocking" signifie que chaque appel attend la réponse avant de continuer.
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        System.out.println("=== Test gRPC User Service ===\n");

        // -------------------------------------------------------------
        // 1️⃣ CRÉATION D’UTILISATEURS
        // -------------------------------------------------------------
        System.out.println("1. Création d'utilisateurs");

        // Appel distant de la méthode gRPC CreateUser
        // On construit un message CreateUserRequest à l’aide du Builder
        UserResponse user1 = stub.createUser(CreateUserRequest.newBuilder()
            .setName("Alice")
            .setEmail("alice@example.com")
            .build());
        // La réponse contient l’objet UserResponse envoyé par le serveur
        System.out.println("✓ User créé: " + user1.getName() + " (ID: " + user1.getId() + ")");

        // Deuxième utilisateur
        UserResponse user2 = stub.createUser(CreateUserRequest.newBuilder()
            .setName("Bob")
            .setEmail("bob@example.com")
            .build());
        System.out.println("✓ User créé: " + user2.getName() + " (ID: " + user2.getId() + ")");

        // -------------------------------------------------------------
        // 2️⃣ RÉCUPÉRATION D’UN UTILISATEUR PAR ID
        // -------------------------------------------------------------
        System.out.println("\n2. Récupération d'un utilisateur");

        // Appel à la méthode gRPC GetUser avec un ID (ici celui d’Alice)
        UserResponse fetchedUser = stub.getUser(GetUserRequest.newBuilder()
            .setId(user1.getId())
            .build());
        System.out.println("✓ User récupéré: " + fetchedUser.getName() + " - " + fetchedUser.getEmail());

        // -------------------------------------------------------------
        // 3️⃣ LISTE DE TOUS LES UTILISATEURS
        // -------------------------------------------------------------
        System.out.println("\n3. Liste de tous les utilisateurs");

        // Appel à GetAllUsers (ne prend pas de paramètres → message vide)
        UserListResponse allUsers = stub.getAllUsers(Empty.newBuilder().build());

        // Parcours et affichage des utilisateurs reçus
        allUsers.getUsersList().forEach(u ->
            System.out.println("  - " + u.getName() + " (" + u.getEmail() + ")")
        );

        // -------------------------------------------------------------
        // 4️⃣ SUPPRESSION D’UN UTILISATEUR
        // -------------------------------------------------------------
        System.out.println("\n4. Suppression d'un utilisateur");

        // Appel de DeleteUser avec l’ID du deuxième utilisateur (Bob)
        DeleteUserResponse deleteResponse = stub.deleteUser(DeleteUserRequest.newBuilder()
            .setId(user2.getId())
            .build());
        System.out.println("✓ " + deleteResponse.getMessage());

        // -------------------------------------------------------------
        // 5️⃣ VÉRIFICATION APRÈS SUPPRESSION
        // -------------------------------------------------------------
        System.out.println("\n5. Liste après suppression");

        // On reliste tous les utilisateurs pour confirmer la suppression
        UserListResponse remainingUsers = stub.getAllUsers(Empty.newBuilder().build());
        System.out.println("Nombre d'utilisateurs restants: " + remainingUsers.getUsersCount());

        // -------------------------------------------------------------
        // 🔚 FERMETURE DU CANAL
        // -------------------------------------------------------------
        // Ferme proprement la connexion avec le serveur gRPC
        channel.shutdown();
        System.out.println("\n✓ Tests terminés");
    }
}
