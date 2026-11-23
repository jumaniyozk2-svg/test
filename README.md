# Dasturchi

Spring Boot 3 loyihasi: JWT bilan register/login, PostgreSQL, Hibernate, DataLoader.

## Texnologiyalar
- Java 17 (Amazon Corretto 17)
- Maven
- Spring Boot 3.2.5 (Web, Data JPA, Security, Validation)
- PostgreSQL + Hibernate
- JWT (jjwt 0.12.5)

## Ishga tushirish

1. PostgreSQL da bazani yarating:
   ```sql
   CREATE DATABASE dasturchi_db;
   ```
2. `src/main/resources/application.properties` faylida `spring.datasource.username` va `spring.datasource.password` ni o'zingiznikiga moslang.
3. Ishga tushiring:
   ```bash
   mvn spring-boot:run
   ```

Jadvallar Hibernate (`ddl-auto=update`) orqali avtomatik yaratiladi.

## DataLoader
Dastur ishga tushganda quyidagi foydalanuvchilar yaratiladi (agar yo'q bo'lsa):

| username | parol    | rol        |
|----------|----------|------------|
| admin    | admin123 | ROLE_ADMIN |
| user     | user123  | ROLE_USER  |

## API

### Register
```
POST /api/auth/register
Content-Type: application/json

{
  "fullName": "Ali Valiyev",
  "username": "ali",
  "password": "secret123"
}
```

### Login
```
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```
Javob:
```json
{
  "token": "eyJhbGciOi...",
  "tokenType": "Bearer",
  "username": "admin",
  "role": "ROLE_ADMIN"
}
```

### Himoyalangan endpointlar
Header: `Authorization: Bearer <token>`

- `GET /api/user/me` — har qanday login qilgan foydalanuvchi
- `GET /api/admin/hello` — faqat ADMIN

## Muhim
`app.jwt.secret` ni productionda albatta o'zgartiring (Base64, kamida 32 bayt).
