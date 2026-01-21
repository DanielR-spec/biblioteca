# Biblioteca Digital - Arquitectura Hexagonal (Spring Boot + H2)

Proyecto de prueba técnica implementando **Arquitectura Hexagonal (Ports & Adapters)** con **Spring Boot**, **H2 en memoria**, **JPA/Hibernate**, **Validación**, **Swagger/OpenAPI**, **Tests (JUnit5/Spring Boot Test)** y **Docker**.

---

## 1) Stack
- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA (Hibernate)
- H2 (in-memory)
- Bean Validation (jakarta.validation)
- Spring Boot Test (JUnit5)
- JaCoCo (reporte de cobertura)
- Springdoc OpenAPI (Swagger UI)
- Docker / Docker Compose

---

## 2) Arquitectura (Hexagonal)
La arquitectura separa responsabilidades y mantiene dependencias apuntando hacia adentro.

- **Domain Layer** (`com.biblioteca.domain`)
  - Entidades puras (Libro, Usuario, Prestamo)
  - Value Objects (ISBN)
  - Excepciones de negocio
  - Puertos (interfaces) de repositorio: `LibroRepository`, `UsuarioRepository`, `PrestamoRepository`

- **Application Layer** (`com.biblioteca.application`)
  - Casos de uso (Use Cases) y DTOs (commands/queries/results)
  - Servicios de aplicación que orquestan reglas de negocio
  - Transacciones a nivel caso de uso (`@Transactional`)

- **Infrastructure Layer** (`com.biblioteca.infrastructure`)
  - Adaptadores JPA (entities + spring data repos + adapters)
  - Controladores REST
  - DTOs de entrada/salida para API
  - Manejo global de errores (`GlobalExceptionHandler`)

---

## 3) Estructura de paquetes
src/main/java/com/biblioteca/
├── domain/
│ ├── model/
│ │ ├── Libro.java
│ │ ├── Usuario.java
│ │ ├── Prestamo.java
│ │ └── valueobjects/
│ │ └── ISBN.java
│ ├── repository/
│ │ ├── LibroRepository.java
│ │ ├── UsuarioRepository.java
│ │ └── PrestamoRepository.java
│ └── exception/
│ ├── LibroNoEncontradoException.java
│ └── LibroNoDisponibleException.java
├── application/
│ ├── usecase/
│ │ ├── PrestarLibroUseCase.java
│ │ ├── DevolverLibroUseCase.java
│ │ └── BuscarLibrosUseCase.java
│ ├── dto/
│ │ ├── PrestarLibroCommand.java
│ │ ├── DevolverLibroCommand.java
│ │ ├── BuscarLibrosQuery.java
│ │ ├── PrestamoResult.java
│ │ ├── DevolucionResult.java
│ │ └── ListadoLibrosResult.java
│ └── service/
│ ├── PrestarLibroService.java
│ ├── DevolverLibroService.java
│ └── BuscarLibrosService.java
└── infrastructure/
├── persistence/
│ ├── entity/
│ │ ├── LibroEntity.java
│ │ ├── UsuarioEntity.java
│ │ └── PrestamoEntity.java
│ ├── jpa/
│ │ ├── SpringDataLibroRepository.java
│ │ ├── SpringDataUsuarioRepository.java
│ │ └── SpringDataPrestamoRepository.java
│ └── adapter/
│ ├── LibroJpaRepository.java
│ ├── UsuarioJpaRepository.java
│ └── PrestamoJpaRepository.java
└── rest/
├── controller/
│ ├── LibroController.java
│ ├── PrestamoController.java
│ └── UsuarioController.java
├── dto/
│ ├── LibroRequest.java
│ ├── PrestamoRequest.java
│ └── PrestamoResponse.java
└── exception/
└── GlobalExceptionHandler.java

## 4) Requisitos previos
- Java 17 instalado
- Maven 3.9+ instalado

Verificar:
```bash
java -version
mvn -version

5) Ejecutar el proyecto
5.1 Ejecutar tests
mvn test

5.2 Ejecutar la aplicación
mvn spring-boot:run


La app levanta en:

http://localhost:8080

6) H2 Console

H2 Console habilitada para inspeccionar la base en memoria.

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:biblioteca

User: sa

Password: (vacío)

7) Swagger / OpenAPI

Documentación interactiva:

http://localhost:8080/swagger-ui.html

8) Configuración de base de datos

Base de datos: H2 en memoria

DDL: create-drop (crea tablas al iniciar, borra al apagar)

Datos iniciales (opcional): src/main/resources/data.sql

Archivo principal:

src/main/resources/application.yml

12) Docker
12.1 Construir y levantar con docker compose
docker compose up --build

12.2 Acceso

API: http://localhost:8080

Swagger: http://localhost:8080/swagger-ui.html

13) Decisiones de diseño (resumen)

Casos de uso orquestan transacciones: @Transactional en servicios de aplicación.

Dominio no depende de frameworks.

Infraestructura implementa puertos (repositorios) mediante adapters JPA.

Validación en DTOs de entrada (REST) y constraints en entidades JPA.

Excepciones de dominio para reglas de negocio y handler global para traducir a HTTP.

14) Roadmap / mejoras (si hubiera más tiempo)

DTOs completos para Usuario (en vez de aceptar dominio directo en controller)

Versionado optimista para concurrencia (campo @Version en LibroEntity)

Outbox pattern para eventos de dominio

Testcontainers para BD real (PostgreSQL) en integración

## Diagrama de Arquitectura Hexagonal (ASCII)

```txt
                          ┌───────────────────────────────────────────┐
                          │                ADAPTERS                   │
                          │                                           │
                          │  ┌─────────────────┐  ┌─────────────────┐ │
     HTTP Requests  ─────▶│  │ REST Controllers│  │ (Opcional) CLI │ 
                          │  └───────┬────────┘   └─────────────────┘ │
                          │          │                                │
                          └──────────┼────────────────────────────────┘
                                     │ Calls Use Cases
                                     ▼
┌──────────────────────────────────────────────────────────────────────────┐
│                               APPLICATION                                │
│                                                                          │
│  Use Cases (Ports In):                                                   │
│  - PrestarLibroUseCase                                                   │
│  - DevolverLibroUseCase                                                  │
│  - BuscarLibrosUseCase                                                   │
│                                                                          │
│  Implementaciones (Services):                                            │
│  - PrestarLibroService  (@Transactional)                                 │
│  - DevolverLibroService (@Transactional)                                 │
│  - BuscarLibrosService                                                   │
│                                                                          │
│  DTOs (Commands / Queries / Results):                                    │
│  - PrestarLibroCommand / DevolverLibroCommand / BuscarLibrosQuery        │
│  - PrestamoResult / DevolucionResult / ListadoLibrosResult               │
└───────────────────────────────────────────────┬──────────────────────────┘
                                                │ Uses Domain Ports
                                                ▼
┌──────────────────────────────────────────────────────────────────────────┐
│                                  DOMAIN                                  │
│                                                                          │
│  Entities (Business Rules):                                              │
│  - Libro (prestar, devolver, isValid)                                    │
│  - Prestamo (devolver)                                                   │
│  - Usuario                                                               │
│                                                                          │
│  Value Objects:                                                          │
│  - ISBN (inmutable, valida 13 dígitos)                                   │
│                                                                          │
│  Exceptions:                                                             │
│  - LibroNoEncontradoException                                            │
│  - LibroNoDisponibleException                                            │
│                                                                          │
│  Ports Out (Repositories interfaces):                                    │
│  - LibroRepository / UsuarioRepository / PrestamoRepository              │
└───────────────────────────────────────────────┬──────────────────────────┘
                                                │ Implemented by Adapters
                                                ▼
┌──────────────────────────────────────────────────────────────────────────┐
│                              INFRASTRUCTURE                              │
│                                                                          │
│  Persistence (JPA):                                                      │
│  - Entities: LibroEntity / UsuarioEntity / PrestamoEntity                │
│  - SpringData Repos: JpaRepository                                       │
│  - Adapters: LibroJpaRepository / UsuarioJpaRepository / PrestamoJpaRepo │
│                                                                          │
│  Mapping:                                                                │
│  - toDomain() / toEntity()                                               │
│                                                                          │
│  Cross-cutting:                                                          │
│  - GlobalExceptionHandler (ProblemDetail)                                │
└──────────────────────────────────────────────────────────────────────────┘