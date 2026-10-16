# FoodOrderingApp (backend)

REST backend for a food-ordering application: customers sign up and log in, manage delivery addresses, and browse restaurants, categories, items and payment methods. Built as a course project (upGrad / IIITB PG Diploma).

## Tech stack
Java, Spring Boot, Spring Data JPA / Hibernate, PostgreSQL, JWT (`com.auth0:java-jwt`), Swagger / Springfox (API models generated from OpenAPI specs via `swagger-codegen-maven-plugin`), JUnit + Mockito.

## Modules
| Module | Purpose |
|---|---|
| `FoodOrderingApp-api` | Controllers, generated request/response models, Swagger config, exception handler |
| `FoodOrderingApp-service` | Business services, DAOs, JPA entities, JWT and password hashing |
| `FoodOrderingApp-db` | SQL schema (`tables.sql`) and seed data (`insert.sql`) |

## Endpoints (context path `/api`)
- **Customer**: `POST /customer/signup`, `POST /customer/login` (Basic auth → JWT in `access-token` header), `POST /customer/logout`, `PUT /customer`, `PUT /customer/password`
- **Address**: `POST /address`, `GET /address/customer`, `DELETE /address/{address_id}`, `GET /states`
- **Restaurant**: `GET /restaurant`, `GET /restaurant/name/{name}`, `GET /restaurant/category/{category_id}`, `GET /api/restaurant/{restaurant_id}`, `PUT /restaurant/{restaurant_id}`
- **Category / Item / Payment**: `GET /category`, `GET /category/{category_id}`, `GET /item/restaurant/{restaurant_id}`, `GET /payment`

The OpenAPI specs in `FoodOrderingApp-api/src/main/resources/endpoints/` also describe order endpoints, and an `OrderControllerTest` exists, but there is no order controller in this codebase.

## How to run
1. Create a PostgreSQL database `restaurantdb` and load `FoodOrderingApp-db/src/main/resources/sql/tables.sql` then `insert.sql`.
2. Update credentials in `FoodOrderingApp-api/src/main/resources/application.yaml`.
3. From the repo root:
   ```bash
   mvn clean install
   cd FoodOrderingApp-api && mvn spring-boot:run   # Swagger UI at http://localhost:8080/api/swagger-ui.html
   ```

## Key concepts
Layered multi-module Maven build, contract-first API with Swagger codegen, JWT-based session tokens, salted password hashing, JPA entity mapping, centralised exception handling.
