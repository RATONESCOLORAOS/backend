# Backend de la aplicación
---
## Descripción
Este repositorio contiene el backend encargado de procesar los datos, gestionar la lógica de negocio y asegurar el funcionamiento correcto de la aplicación desde el servidor.
## Justificación de la tecnología empleada
---
Hemos optado por utilizar Java por su arquitectura orientada a objetos. Por su parte, Spring Boot simplifica el desarrollo del back, además de ofrecer herramientas para la gestión de seguridad.
## Consideraciones
---
El archivo configura la conexión a una base de datos MySQL local. Es necesario modificar los parámetros de autentificación en **application.properties** para acceder correctamente al servidor.
Para el correcto despliegue del back-end es necesario utilizar **IntelliJ IDEA**, concretamente la versión 2023.1.
## Estructura del proyecto
---
- **config/**
  - Configuración de la aplicación, manejo de autenticación, seguridad y CORS.

- **controller/**
  - Controladores para manejar las solicitudes relacionadas con autenticación, carritos de compras, productos en carritos y usuarios.

- **dto/**
  - DTOs (Data Transfer Objects) para transferir datos entre capas del sistema, incluyendo carritos, productos en carritos, solicitudes de creación y actualización de carritos, y autenticación.

- **exceptions/**
  - Manejo global de excepciones para los controladores de la aplicación.

- **models/**
  - Definiciones de modelos para representar entidades principales del sistema, como carritos de compras, productos en carritos, productos de supermercado y usuarios.

- **repository/**
  - Interfaces de repositorio para interactuar con la base de datos, incluyendo operaciones CRUD y consultas personalizadas para carritos, productos en carritos, productos de supermercado y usuarios.

- **services/**
  - Servicios que encapsulan la lógica de negocio de la aplicación, incluyendo autenticación, gestión de carritos de compras, productos en carritos, productos de supermercado y usuarios.