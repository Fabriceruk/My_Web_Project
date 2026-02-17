# E-Commerce REST API

A Spring Boot RESTful API for managing e-commerce products.

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## How to Run
1. Make sure PostgreSQL is running
2. Create a database called `ecommerce_db`
3. Update `application.properties` with your DB password
4. Run `RestfullApiAssignmentApplication.java`
5. API runs on `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /api/products/addProduct         | Create a product   |
| GET    | /api/products/getAllProducts     | Get all products   |
| GET    | /api/products/getProduct/{id}    | Get product by id  |
| PUT    | /api/products/updateProduct/{id} | Update a product   |
| DELETE | /api/products/deleteProduct/{id} | Delete a product   |

## Sample Request Body (POST / PUT)
json
{
    "id": 1,
    "name": "iPhone 15 Pro",
    "description": "Apple flagship smartphone",
    "price": 1199.99,
    "category": "Electronics",
    "stockQuantity": 50,
    "brand": "Apple"
}


## Author
FABRICE RUKUNDO 