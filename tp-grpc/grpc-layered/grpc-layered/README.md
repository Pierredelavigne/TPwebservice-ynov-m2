# gRPC Architecture en Couches

## Structure

```
src/main/java/com/example/
├── entity/
│   └── User.java                    # Entité JPA
├── repository/
│   └── UserRepository.java          # Repository Spring Data
├── service/
│   ├── UserService.java             # Interface Service
│   └── impl/
│       └── UserServiceImpl.java     # Implémentation Service
├── grpc/
│   └── UserGrpcService.java         # Endpoint gRPC
├── client/
│   └── UserClient.java              # Client de test
└── Application.java                 # Main
```

## Technologies

- **Spring Boot** : Framework
- **Spring Data JPA** : Persistance
- **H2 Database** : Base en mémoire
- **gRPC** : Communication
- **Lombok** : Réduction code

## Démarrer

### Terminal 1 : Serveur
```bash
mvn clean install
mvn spring-boot:run
```

### Terminal 2 : Client
```bash
java -cp "target/classes;target/generated-sources/protobuf/java;%USERPROFILE%\.m2\repository\io\grpc\grpc-protobuf\1.58.0\*;%USERPROFILE%\.m2\repository\io\grpc\grpc-stub\1.58.0\*;%USERPROFILE%\.m2\repository\io\grpc\grpc-api\1.58.0\*;%USERPROFILE%\.m2\repository\com\google\protobuf\protobuf-java\3.24.0\*;%USERPROFILE%\.m2\repository\com\google\guava\guava\32.1.2-jre\*" com.example.client.UserClient
```

Ou lancez directement depuis votre IDE.

## Operations gRPC

- `CreateUser` : Créer un utilisateur
- `GetUser` : Récupérer par ID
- `GetAllUsers` : Lister tous
- `DeleteUser` : Supprimer par ID

## Couches

1. **Entity** : Modèle de données (JPA)
2. **Repository** : Accès base de données
3. **Service** : Logique métier (interface)
4. **ServiceImpl** : Implémentation logique
5. **gRPC Service** : Exposition via gRPC
