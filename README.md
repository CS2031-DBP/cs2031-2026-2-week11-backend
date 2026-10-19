# CS2031 DBP — Backend Semana 11

API REST de referencia para el laboratorio de la semana 11 del curso **Desarrollo Basado en Plataformas (CS2031)** — UTEC, ciclo 2026-2.

Este backend **ya está completamente implementado**. El objetivo del lab es conectarlo con un frontend React; los estudiantes no necesitan modificar este proyecto.

---

## Stack

| Tecnología | Versión |
|------------|---------|
| Java | 26 |
| Spring Boot | 4.1.1 |
| Spring Data JPA | (incluido en Spring Boot) |
| Base de datos | H2 in-memory |

---

## Prerrequisitos

- JDK 26 instalado. Verificar con:
  ```bash
  java -version
  ```

---

## Cómo ejecutar

```bash
./mvnw spring-boot:run
```

El servidor queda disponible en `http://localhost:8080`.

La consola H2 (para inspeccionar la base de datos en el navegador) está en:
`http://localhost:8080/h2-console`

Parámetros de conexión para H2 Console:
- **JDBC URL:** `jdbc:h2:mem:labdb`
- **User:** `sa`
- **Password:** *(vacío)*

> La base de datos es in-memory: los datos se pierden al detener el servidor y se recrean automáticamente al volver a arrancar.

---

## Estructura del proyecto

```
src/main/java/lab/week11/backend/
├── student/
│   ├── domain/          Student.java, StudentService.java
│   ├── application/     StudentController.java
│   ├── infrastructure/  StudentRepository.java
│   └── exception/       NotFoundException.java
├── course/
│   ├── domain/          Course.java, CourseService.java
│   ├── application/     CourseController.java
│   ├── infrastructure/  CourseRepository.java
│   └── exception/       CourseNotFoundException.java
├── auth/                AuthController.java, RegisterRequest.java, AuthResponse.java
├── config/              CorsConfig.java
├── DataInitializer.java
├── GlobalHandleExceptions.java
└── LabWeek11BackendApplication.java
```

| Package | Responsabilidad |
|---------|----------------|
| `student` | Entidad `Student` con sus campos y relación ManyToMany con `Course`; CRUD completo y lógica de matrícula |
| `course` | Entidad `Course` (nombre, descripción, créditos); CRUD completo |
| `auth` | Endpoint de registro de nuevos estudiantes |
| `config` | Configuración de CORS |

---

## Datos semilla

Al arrancar, `DataInitializer` inserta automáticamente los siguientes registros:

### Cursos

| id | Nombre | Descripción | Créditos |
|----|--------|-------------|----------|
| 1 | Desarrollo Basado en Plataformas | Curso de desarrollo full-stack con Spring Boot y React | 4 |
| 2 | Algoritmos y Estructuras de Datos | Fundamentos de algoritmia y estructuras de datos | 4 |
| 3 | Bases de Datos | Diseño y gestión de bases de datos relacionales | 3 |

### Estudiantes

| id | Nombre | Email | Teléfono | Edad | Cursos matriculados |
|----|--------|-------|----------|------|---------------------|
| 1 | Ana García | ana.garcia@utec.edu.pe | 987654321 | 20 | DBP, AED |
| 2 | Carlos López | carlos.lopez@utec.edu.pe | 912345678 | 21 | DBP, Bases de Datos |
| 3 | Lucía Martínez | lucia.martinez@utec.edu.pe | 998877665 | 19 | *(ninguno)* |

---

## Endpoints

### Estudiantes (`/student`)

| Método | URL | Descripción | Body (JSON) |
|--------|-----|-------------|-------------|
| `GET` | `/student` | Listar todos los estudiantes | — |
| `GET` | `/student/{id}` | Obtener un estudiante por id | — |
| `POST` | `/student` | Crear un estudiante | Ver abajo |
| `PUT` | `/student/{id}` | Actualizar un estudiante (reemplaza todos los campos) | Ver abajo |
| `DELETE` | `/student/{id}` | Eliminar un estudiante | — |
| `GET` | `/student/{id}/courses` | Listar los cursos de un estudiante | — |
| `POST` | `/student/{studentId}/enroll/{courseId}` | Matricular estudiante en un curso | — |
| `DELETE` | `/student/{studentId}/enroll/{courseId}` | Desmatricular estudiante de un curso | — |

**Body para `POST /student` y `PUT /student/{id}`:**

```json
{
  "firstname": "Ana",
  "lastname": "García",
  "email": "ana.garcia@utec.edu.pe",
  "phone": "987654321",
  "age": 20,
  "description": "Estudiante de ingeniería de software",
  "password": "pass123"
}
```

> Los endpoints `POST` retornan **201 Created** con el recurso creado en el body y la URL en el header `Location`.

---

### Cursos (`/course`)

| Método | URL | Descripción | Body (JSON) |
|--------|-----|-------------|-------------|
| `GET` | `/course` | Listar todos los cursos | — |
| `GET` | `/course/{id}` | Obtener un curso por id | — |
| `POST` | `/course` | Crear un curso | Ver abajo |
| `PUT` | `/course/{id}` | Actualizar un curso | Ver abajo |
| `DELETE` | `/course/{id}` | Eliminar un curso | — |

**Body para `POST /course` y `PUT /course/{id}`:**

```json
{
  "name": "Desarrollo Basado en Plataformas",
  "description": "Curso de desarrollo full-stack con Spring Boot y React",
  "credits": 4
}
```

> Los endpoints `POST` retornan **201 Created** con el recurso creado en el body y la URL en el header `Location`.

---

### Autenticación (`/auth`)

| Método | URL | Descripción | Body (JSON) |
|--------|-----|-------------|-------------|
| `POST` | `/auth/register` | Registrar un nuevo estudiante | Ver abajo |

**Body para `POST /auth/register`:**

```json
{
  "firstname": "Lucía",
  "lastname": "Martínez",
  "email": "lucia.martinez@utec.edu.pe",
  "phone": "998877665",
  "age": 19,
  "description": "Estudiante de primer año",
  "password": "pass789"
}
```

**Respuesta:**

```json
{
  "token": "registered-lucia.martinez@utec.edu.pe"
}
```

---

## CORS

El backend acepta peticiones desde **cualquier origen** (`*`) con los métodos `GET`, `POST`, `PUT`, `DELETE` y `OPTIONS`. Esto permite conectarlo directamente desde el frontend React corriendo en `http://localhost:5173` sin configuración adicional.

> **Nota:** El campo `password` se incluye en las respuestas JSON. Esto es intencional para simplificar el lab; en una aplicación real se ocultaría con `@JsonIgnore`.

---

## Propósito en el lab

Este repositorio es la **implementación de referencia** del backend. Los estudiantes:

1. Clonan este repositorio y lo ejecutan con `./mvnw spring-boot:run`.
2. Verifican que los datos semilla están disponibles (por ejemplo, `GET http://localhost:8080/student`).
3. Implementan el **frontend React** en el repositorio separado del lab, conectándolo a esta API.

No es necesario modificar ningún archivo de este proyecto.
