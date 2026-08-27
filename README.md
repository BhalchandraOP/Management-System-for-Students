# Student Registration — Spring Boot Learning Project

This repository documents my progression through the Spring ecosystem by continuously extending a small Student Registration application. Rather than starting a new project for every concept, I kept building on the same codebase — MVC → persistence → security → REST → JWT → OAuth2 — so I could see how each new piece actually affected the rest of the system, instead of learning things in isolation.

This is **not** a production-ready system. It's closer to an engineering learning journal with working code attached.

---

## About This Project

A Student Registration application built with Spring Boot, extended in phases while I learned Spring MVC, Spring Data JPA, Spring Security, REST APIs, JWT authentication, and OAuth2 concepts. It has two interfaces into the same backend:

- **A JSP-based web app** (form login, session-based) for registering, viewing, updating, and deleting students through a browser.
- **A REST API** (JWT/Bearer-token secured) for the same operations, testable via Postman.

Having both was intentional — it let me compare stateful session auth against stateless token auth on the same domain model.

---

## Learning Journey

```text
Phase 1 — Spring MVC
   ↓
Phase 2 — JPA + Hibernate + MySQL
   ↓
Phase 3 — Spring Security (authentication)
   ↓
Phase 4 — Role-based & method security
   ↓
Phase 5 — Refactor into Controller → Service → Repository
   ↓
Phase 6 — REST APIs
   ↓
Phase 7 — JWT authentication
   ↓
Phase 8 — Securing REST APIs with JWT
   ↓
Phase 9 — OAuth2 concepts (explored, not built)
```

### 1. Spring MVC — first exposure
Started with controllers and JSP views. Learned request mapping, form handling, and the basic controller → view flow.

### 2. Introduced Spring Data JPA
Added entities and repositories, connected the app to MySQL, and learned how Hibernate handles persistence under the hood using methods like `findById()`, `findAll()`, `save()`, `deleteById()`.

### 3. Added database persistence
Moved from in-memory data to a real `studentdb` MySQL schema for both student and user records.

### 4. Introduced Spring Security
Added authentication with a custom login page and learned how the security filter chain sits in front of MVC requests.

### 5. Added role-based authorization
Introduced `ADMIN`, `FACULTY`, and `STUDENT` roles and started restricting operations per role using `hasRole()` / `hasAnyRole()` and later `@PreAuthorize`.

### 6. Refactored into Controller → Service → Repository
Initially, some database and business logic lived directly inside controllers. As I learned about separation of concerns, I pulled that logic into a `StudentService` and let `StudentRepo` own persistence — a much clearer picture of what each layer is actually responsible for.

### 7. Built REST APIs
Added a parallel REST interface (`/api/students`) alongside the JSP app, using `ResponseEntity` and proper HTTP status codes instead of view redirects.

### 8. Implemented JWT authentication
Added `JwtService` for token generation/validation and a custom `JwtAuthenticationFilter` to authenticate stateless requests via `Authorization: Bearer <token>`.

### 9. Secured REST APIs with JWT
Wired the JWT filter into the security chain alongside the existing form-login setup, so the same app supports both session-based MVC auth and stateless REST auth.

### 10. Explored OAuth2
Read into OAuth2 actors, the Authorization Code flow, access tokens vs ID tokens, scopes, PKCE, and how OAuth2 differs from OIDC — mainly to understand how JWT fits into a bigger picture, not to build a full Authorization Server.

### 11. Current state
Working MVC app + working JWT-secured REST API, sharing the same student/user data, with role-based access control on both.

---

## What I Implemented vs. What I Explored

**Implemented (working code in this repo):**
- Spring MVC with JSP views
- Spring Data JPA + MySQL persistence
- Spring Security (form login for MVC)
- Role-based & method-level security (`@PreAuthorize`)
- Controller → Service → Repository architecture
- REST APIs for student CRUD
- JWT generation, validation, and a custom authentication filter
- JWT-protected REST endpoints
- Postman-based API testing

**Explored / learned conceptually (not built here):**
- OAuth2 actors and roles
- Authorization Code Flow
- Access tokens vs. ID tokens vs. authorization codes
- Scopes and PKCE
- OAuth2 vs. OIDC
- Authorization Server vs. Resource Server responsibilities

Keeping this distinction honest matters more to me than making the README sound more advanced than the code actually is.

---

## Key Learning Takeaways

- The difference between authentication and authorization, and why they're separate concerns.
- How Spring Security filters participate in request processing before a controller ever runs.
- The relationship between `Authentication` and `SecurityContext`.
- Why JWT is stateless, and what that trades off against session-based login.
- How a Bearer token actually travels from client → filter → `SecurityContext` → controller.
- Why `401` and `403` represent genuinely different failures (not authenticated vs. not authorized).
- What Controller, Service, and Repository are each actually responsible for — and why mixing them gets messy fast.
- The difference between JWT (a token format) and OAuth2 (an authorization framework).
- The difference between OAuth2 and OIDC.
- The role of an Authorization Server vs. a Resource Server.

## Concepts That Took Longer to Click

- Controller vs. Service responsibilities
- Authentication vs. Authorization
- Authentication vs. SecurityContext
- Roles vs. Authorities
- JWT vs. OAuth2
- Access Token vs. ID Token
- Authorization Code vs. Access Token
- Stateful vs. stateless authentication
- 401 vs. 403

None of these are hard to define individually — they got confusing because they interact, and it took building the actual filter chain to see how.

---

## Technologies Used

Java · Spring Boot · Spring MVC · Spring Data JPA · Hibernate · Spring Security · JWT · REST · JSP · MySQL · Maven · Postman · Eclipse IDE

---

## Architecture

```text
Client
 │
 ├── Browser / JSP  ──────────────┐
 │                                │
 └── Postman / REST Client        │
          │                       │
          ▼                       ▼
     Controller Layer  ←──────────┘
          │
          ▼
      Service Layer
          │
          ▼
    Repository Layer
          │
          ▼
       MySQL Database
```

## Security Flow

**MVC (session-based):**
```text
Browser → Protected MVC URL → Not authenticated?
   → Login Page → Username + Password
   → Spring Security → Authentication
   → Protected MVC Page
```

**REST API (stateless, JWT):**
```text
Username + Password → AuthenticationManager → Authentication
   → JwtService → JWT Token

HTTP Request → Authorization Header → Bearer Token
   → JwtAuthenticationFilter → Validate JWT
   → Load UserDetails → SecurityContext
   → Authorization → REST Controller
```

## User Roles

| Operation           | ADMIN | FACULTY | STUDENT |
| ------------------- | :---: | :-----: | :-----: |
| View Students       |   ✅   |    ✅    |    ❌    |
| View Single Student |   ✅   |    ✅    |    ❌    |
| Add Student         |   ✅   |    ✅    |    ❌    |
| Update Student      |   ✅   |    ✅    |    ❌    |
| Delete Student      |   ✅   |    ❌    |    ❌    |

---

## REST API Endpoints

Base URL: `/api/students`

| Method | Endpoint              | Required Roles   |
| ------ | ---------------------- | ---------------- |
| GET    | `/api/students`         | ADMIN, FACULTY    |
| GET    | `/api/students/{id}`    | ADMIN, FACULTY    |
| POST   | `/api/students`         | ADMIN, FACULTY    |
| PUT    | `/api/students/{id}`    | ADMIN, FACULTY    |
| DELETE | `/api/students/{id}`    | ADMIN             |

Example — add a student:
```json
POST /api/students
{
    "sid": 106,
    "sname": "Rahul",
    "sbranch": "Computer",
    "semester": 5
}
```

Obtain a JWT first via:
```http
POST /auth/login
```
then use it as `Authorization: Bearer <token>` on subsequent requests.

**Security responses:**
- `401 Unauthorized` — no valid authentication present
- `403 Forbidden` — authenticated, but role doesn't permit the action
- `404 Not Found` — student ID doesn't exist

---

## Testing With Postman

```text
1. Login → 2. Receive JWT → 3. Copy JWT → 4. Set as Bearer Token
5. Call REST API → 6. Spring Security authenticates
7. Role is checked → 8. Response returned
```

Default test accounts (auto-created on first run if missing — **change or remove before deploying anywhere beyond localhost**):

| Username | Password    | Role    |
| -------- | ----------- | ------- |
| admin    | admin123    | ADMIN   |
| faculty  | faculty123  | FACULTY |
| student  | student123  | STUDENT |

---

## Running the Project

**Prerequisites:** JDK, MySQL, Git, Eclipse (Maven can run through Eclipse or standalone)

```bash
git clone https://github.com/BhalchandraOP/Management-System-for-Students.git
cd Management-System-for-Students
```

Import into Eclipse: `File → Import → Maven → Existing Maven Projects`, select the cloned folder.

Create the database:
```sql
CREATE DATABASE studentdb;
```

## Configuration

This project uses environment variables for sensitive configuration.

Before running the application, configure:

JWT_SECRET

> Never commit real secrets. Use an `application.properties.example` with placeholders and `.gitignore` the real file.

Run: `Run As → Spring Boot App`, then visit `http://localhost:8080`.

---

## Project Structure

```text
src/main/java/com.bhalchandra.studentregistrationmvc/
 ├── config/          → SecurityConfig.java
 ├── controller/       → StudentController, SController, AuthController
 ├── model/
 ├── repo/             → StudentRepo
 ├── service/          → StudentService, JwtService
 └── security/         → JwtAuthenticationFilter, RestAuthenticationEntryPoint, RestAccessDeniedHandler

src/main/resources/
 └── application.properties
```

---

## Current Limitations

- This is a learning project, not a production-ready system.
- Some choices favor understanding a concept clearly over "best practice" architecture.
- OAuth2 was explored conceptually after JWT was already working, not implemented as a full flow.
- Error handling, input validation, automated tests, and production configuration are minimal.

## Possible Next Improvements

- Global REST exception handling
- Validation with `@Valid`
- DTOs instead of exposing entities directly
- Refresh tokens
- Unit/integration tests
- API documentation (Swagger/OpenAPI)
- Pagination, sorting, search/filter
- Externalized, production-ready configuration

---

## Author

**Bhalchandra Rana**
GitHub: [https://github.com/BhalchandraOP](https://github.com/BhalchandraOP)

## License

Created primarily as a learning and educational project.