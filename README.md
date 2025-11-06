# 🏫 Sistema de Gestión de Beneficio — Mejores Bachilleres Sibaté

## 📘 Descripción General
Este proyecto es una aplicación desarrollada en **Java Spring Boot** cuyo objetivo principal es **gestionar los documentos y registros de los estudiantes beneficiarios del programa “Mejores Bachilleres” del municipio de Sibaté**.  

El sistema permite administrar usuarios, roles, autenticación, y manejo de estados del beneficio de cada estudiante, garantizando un control eficiente y seguro de la información.

---

## ⚙️ Tecnologías Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Maven**
- **JPA / Hibernate**
- **H2 / MySQL (configurable)**
- **Thymeleaf (interfaz web)**
- **Lombok**
- **Git / GitHub**

---

## 🧩 Estructura del Proyecto

gestion-beneficio/
│
├── pom.xml # Configuración del proyecto y dependencias Maven
├── src/main/java/com/davidag/gestion_beneficio/
│ ├── GestionBeneficioApplication.java # Clase principal (punto de entrada)
│ │
│ ├── Controller/ # Controladores REST y de páginas
│ │ ├── AuthController.java # Controlador de autenticación (login / registro)
│ │ └── PageController.java # Controlador de vistas principales
│ │
│ ├── Data/ # Clases DTO (Transferencia de Datos)
│ │ ├── LoginRequest.java
│ │ ├── RegisterRequest.java
│ │ ├── RegisterResponse.java
│ │ └── AuthResponse.java
│ │
│ ├── Enum/ # Enumeraciones del dominio
│ │ ├── EstadoBeneficiario.java # Define los posibles estados del beneficio
│ │ ├── TipoBeneficio.java # Clasifica los tipos de beneficio
│ │ ├── Rol.java # Roles del sistema (ADMIN, USER)
│ │ └── TipoDoc.java # Tipos de documentos de identidad
│ │
│ ├── Filter/
│ │ └── JwtFilter.java # Filtro JWT para validar tokens en cada solicitud
│ │
│ ├── Model/ # Entidades de la base de datos
│ │ ├── Beneficiario.java # Modelo principal del estudiante beneficiado
│ │ └── Usuario.java # Modelo de usuario del sistema
│ │
│ ├── Repo/ # Repositorios JPA
│ │ ├── RepoBeneficiario.java
│ │ └── RepoUsuario.java
│ │
│ └── Security/ # Configuración de seguridad
│ ├── SecurityConfig.java # Configuración general de Spring Security
│ ├── UserDetailsServiceIm.java # Servicio que carga los usuarios
│ └── UserPrincipal.java # Implementación personalizada de usuario autenticado
│
├── src/main/resources/
│ ├── application.properties # Configuración del entorno
│ └── templates/ # Vistas HTML (Thymeleaf)
│
└── HELP.md / README.md # Documentación del proyecto

## 🔐 Funcionalidades Principales
- **Autenticación y autorización mediante JWT**
- **Gestión de usuarios y roles (ADMIN / USER)**
- **Registro, edición y eliminación de beneficiarios**
- **Carga y administración de documentos**
- **Control de estados del beneficio (activo, suspendido, finalizado, etc.)**
- **Interfaz web simple con Thymeleaf**
- **Validación de sesiones seguras**

---

## 🚀 Ejecución del Proyecto

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/tuusuario/gestion-beneficio.git
