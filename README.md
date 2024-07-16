# ForoHub

 ForoHub es un software enfocado a la discusión en línea diseñada para facilitar el intercambio de conocimientos y la colaboración entre estudiantes y profesionales en el campo de la tecnología y la informática.

## Características principales

- **Sistema de discusiones**: Los usuarios pueden crear, ver, actualizar y eliminar discusiones sobre diversos temas relacionados con la tecnología.
- **Comentarios**: Cada discusión puede recibir comentarios de otros usuarios, fomentando el diálogo y el intercambio de ideas.
- **Categorías**: Las discusiones están organizadas por categorías, que incluyen temas como programación, desarrollo web, bases de datos, seguridad informática, entre otros.
- **Autenticación y autorización**: Sistema de registro y login de usuarios con JWT (JSON Web Tokens) para garantizar la seguridad de las interacciones.
- **API RESTful**: Toda la funcionalidad está disponible a través de una API RESTful, facilitando la integración con diferentes clientes.

## Tecnologías utilizadas

- Java con Spring Boot
- Spring Security para la autenticación y autorización
- JPA/Hibernate para la persistencia de datos
- Base de datos relacional (compatible con MySQL/PostgreSQL)
- Swagger/OpenAPI para la documentación de la API
- JWT para la gestión de tokens de autenticación

## Estructura del proyecto

El proyecto sigue una arquitectura en capas, con separación clara de responsabilidades:

- `controller`: Maneja las solicitudes HTTP y define los endpoints de la API.
- `service`: Contiene la lógica de negocio de la aplicación.
- `repository`: Interfaces para el acceso a datos.
- `entity`: Define las entidades JPA que representan las tablas de la base de datos.
- `dto`: Objetos de transferencia de datos para la comunicación entre capas.
- `infra`: Configuraciones de infraestructura, incluyendo seguridad y documentación de la API.

## Configuración y ejecución

1. Asegúrate de tener Java JDK 11 o superior instalado.
2. Configura la base de datos en `application.properties`.
3. Ejecuta las migraciones de la base de datos.
4. Compila y ejecuta la aplicación usando Maven
