# 📅 API de ForoHub - Spring Boot

Este proyecto es una API REST construida con **Spring Boot** para gestionar un foro.  
Permite registrar, listar, listar por id, actualizar y eliminar un tópico de manera sencilla.

---

## 🚀 Tecnologías usadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL 
- Maven

---
## 🔐 Autenticación y Seguridad

Este proyecto utiliza **JWT (JSON Web Token)** para la autenticación y autorización de los usuarios.

### 📌 Registro de usuario
Antes de poder iniciar sesión, el usuario debe estar registrado. Una vez registrado, el usuario debe autenticarse con sus credenciales en el siguiente endpoint:
`POST /auth/login`  
- **Body (JSON):**
```json
{
    "email": "admin@admin.com",
    "password": "admin123"
}
```
Respuesta: 200 OK con el token.
- **Body (JSON):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

El token JWT devuelto en el login debe ser enviado en cada petición a los endpoints protegidos mediante el header:
Authorization: Bearer <tu_token>


## 📌 Endpoints disponibles

### 🔹 Registrar un tópico
`POST /topicos`  
- **Body (JSON):**
```json
{
  "titulo": "Ejemplo 1",
  "mensaje": "Primera prueba",
  "cursoId": 1
}
```
Respuesta: 201 Created con el tópico registrado.
- **Body (JSON):**
```json
{
    "id": 1,
    "titulo": "Ejemplo 1",
    "mensaje": "Primera prueba",
    "fechaCreacion": "2025-08-18T20:18:26.2549245",
    "estado": true,
    "nombreAutor": "Admin",
    "nombreCurso": "Infra I"
}
```

### 🔹 Listar todos los tópicos
`GET /topicos`  
Respuesta: 200 OK con la lista de tópicos.
- **Body (JSON):**
```json
{
    "content": [
        {
            "id": 1,
            "titulo": "Ejemplo 1",
            "mensaje": "Primera prueba",
            "fechaCreacion": "2025-08-18T20:18:26",
            "estado": true,
            "nombreAutor": "Admin",
            "nombreCurso": "Infra I"
        }
    ],
    "pageable": {
        ...
}
```

### 🔹 Detalle de un tópico por id
`GET /topicos/{id}`  
Respuesta: 200 OK con el detalle del tópico.
- **Body (JSON):**
```json

{
  "id": 1,
  "titulo": "Ejemplo 1",
  "mensaje": "Primera prueba",
  "fechaCreacion": "2025-08-18T20:18:26",
  "estado": true,
  "nombreAutor": "Admin",
  "nombreCurso": "Infra I"
}
 
```

### 🔹 Actualizar un tópico
`PUT /topicos`  
- **Body (JSON):**
```json
{
  "id": 1,
  "titulo": "Ejemplo 1",
  "mensaje": "Primera prueba de mensaje"
}
```
Respuesta: 200 OK con el detalle del tópico.
- **Body (JSON):**
```json
{
    "id": 1,
    "titulo": "Ejemplo 1",
    "mensaje": "Primera prueba de mensaje",
    "fechaCreacion": "2025-08-18T20:20:04",
    "estado": true,
    "nombreAutor": "Admin",
    "nombreCurso": "Infra I"
}
```
### 🔹 Eliminar un tópico 
`DELETE /topicos/{id}`  
Respuesta: 204 No content.

## 🚀 Cómo probar la aplicación

1. Clona este repositorio:  
   ```bash
   git clone https://github.com/SaraCarol/Challenge-ForoHub.git
   ```
2. Entra en el directorio del proyecto:
```bash
cd Challenge-ForoHub
 ```
3. Configura tu base de datos (MySQL) y actualiza el archivo application.properties con tus credenciales.
4. Ejecuta la aplicación con Maven:
```bash
   mvn spring-boot:run
```
