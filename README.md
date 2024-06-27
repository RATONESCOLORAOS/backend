# Backend de la aplicación
---
## Descripción
En este repositorio encontrarás todo el backend, que se encarga de procesar los datos, manejar la lógica del negocio y asegurar que todo funcione correctamente desde el servidor.
## Justificación de la tecnología empleada
---
Elegimos Java debido a su arquitectura orientada a objetos, que es bastante robusta. Además, utilizamos Spring Boot porque facilita mucho el desarrollo del backend y ofrece herramientas útiles para la gestión de la seguridad.
## Consideraciones
---
El archivo configura la conexión a una base de datos MySQL local. Es necesario ajustar los parámetros de autenticación en **application.properties** para conectarse al servidor correctamente. Para desplegar el backend, recomendamos usar **IntelliJ IDEA**, específicamente la versión 2023.1.
## Estructura del proyecto
---
- **config/**
  - Aquí está la configuración de la aplicación, incluyendo manejo de autenticación, seguridad y CORS.

- **controller/**
  - Este directorio contiene los controladores que manejan las solicitudes relacionadas con autenticación, carritos de compras, productos en carritos y usuarios.

- **dto/**
  - En esta carpeta se encuentran los DTOs (Data Transfer Objects), que se utilizan para transferir datos entre las diferentes capas del sistema. Incluye carritos, productos en carritos, y solicitudes de creación y actualización de carritos, además de autenticación.

- **exceptions/**
  - Aquí se maneja de forma global las excepciones para los controladores de la aplicación.

- **models/**
  - Contiene las definiciones de los modelos que representan las entidades principales del sistema, como carritos de compras, productos en carritos, productos de supermercado y usuarios.

- **repository/**
  - Este directorio incluye las interfaces de repositorio para interactuar con la base de datos. Aquí se realizan operaciones CRUD y consultas personalizadas para carritos, productos en carritos, productos de supermercado y usuarios.

- **services/**
  - Los servicios encapsulan la lógica de negocio de la aplicación, gestionando la autenticación, los carritos de compras, los productos en carritos, los productos de supermercado y los usuarios.