**Postman - GraphQL Requests**

**URL:** `POST http://localhost:8080/graphql`
**Headers:** `Content-Type: application/json`

---

## CATEGORY

### Créer une catégorie
```json
{
  "query": "mutation { createCategory(input: { name: \"Électronique\", description: \"Appareils électroniques\" }) { id name description } }"
}
```

### Lister toutes les catégories
```json
{
  "query": "query { categories { id name description } }"
}
```

### Récupérer une catégorie par ID
```json
{
  "query": "query { category(id: 1) { id name description } }"
}
```

### Catégorie avec ses produits
```json
{
  "query": "query { category(id: 1) { id name description products { id name price } } }"
}
```

### Modifier une catégorie
```json
{
  "query": "mutation { updateCategory(id: 1, input: { name: \"High-Tech\", description: \"Produits high-tech\" }) { id name description } }"
}
```

### Supprimer une catégorie
```json
{
  "query": "mutation { deleteCategory(id: 1) }"
}
```

---

## PRODUCT

### Créer un produit
```json
{
  "query": "mutation { createProduct(input: { name: \"iPhone 15\", description: \"Smartphone Apple\", price: 999.99, stock: 50, categoryId: 1 }) { id name description price stock category { id name } } }"
}
```

### Lister tous les produits
```json
{
  "query": "query { products { id name description price stock } }"
}
```

### Produits avec catégorie
```json
{
  "query": "query { products { id name price category { id name } } }"
}
```

### Produits avec reviews
```json
{
  "query": "query { products { id name price reviews { id author rating } } }"
}
```

### Récupérer un produit par ID
```json
{
  "query": "query { product(id: 1) { id name description price stock category { id name } reviews { id author comment rating } } }"
}
```

### Produits par catégorie
```json
{
  "query": "query { productsByCategory(categoryId: 1) { id name price stock } }"
}
```

### Modifier un produit
```json
{
  "query": "mutation { updateProduct(id: 1, input: { name: \"iPhone 15 Pro\", description: \"Smartphone Apple Pro\", price: 1199.99, stock: 30, categoryId: 1 }) { id name description price stock } }"
}
```

### Supprimer un produit
```json
{
  "query": "mutation { deleteProduct(id: 1) }"
}
```

---

## REVIEW

### Créer une review
```json
{
  "query": "mutation { createReview(input: { author: \"Jean Dupont\", comment: \"Excellent produit !\", rating: 5, productId: 1 }) { id author comment rating createdAt product { id name } } }"
}
```

### Lister toutes les reviews
```json
{
  "query": "query { reviews { id author comment rating createdAt } }"
}
```

### Reviews avec produit
```json
{
  "query": "query { reviews { id author rating product { id name } } }"
}
```

### Récupérer une review par ID
```json
{
  "query": "query { review(id: 1) { id author comment rating createdAt product { id name } } }"
}
```

### Reviews par produit
```json
{
  "query": "query { reviewsByProduct(productId: 1) { id author comment rating createdAt } }"
}
```

### Modifier une review
```json
{
  "query": "mutation { updateReview(id: 1, input: { author: \"Jean Dupont\", comment: \"Très bon produit, je recommande !\", rating: 4, productId: 1 }) { id author comment rating } }"
}
```

### Supprimer une review
```json
{
  "query": "mutation { deleteReview(id: 1) }"
}
```

---

## REQUÊTES AVANCÉES

### Tout en une seule requête
```json
{
  "query": "query { categories { id name products { id name price reviews { author rating } } } }"
}
```

### Produit complet avec relations
```json
{
  "query": "query { product(id: 1) { id name description price stock category { id name description } reviews { id author comment rating createdAt } } }"
}
```