# DiseStockBackend

Backend del sistema de gestión de inventario **DiseStock**, desarrollado como parte del proyecto formativo del programa de Análisis y Desarrollo de Software.

DiseStock busca facilitar la gestión de materiales, usuarios e inventario mediante una arquitectura backend basada en servicios REST.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Jakarta Validation
- IntelliJ IDEA
- Git y GitHub

## Arquitectura del proyecto

El backend está organizado mediante una arquitectura por capas:

- **Model:** representa las entidades del sistema.
- **Repository:** gestiona el acceso y persistencia de los datos.
- **Service:** contiene la lógica de negocio.
- **Controller:** expone los endpoints de la API REST.
- **Exception:** gestiona errores y validaciones de forma centralizada.

## Módulos implementados

### Gestión de usuarios

Permite realizar operaciones relacionadas con los usuarios del sistema.

Funciones implementadas:

- Registrar usuarios.
- Consultar usuarios.
- Actualizar información.
- Eliminar usuarios.
- Validar los datos recibidos.

Endpoint principal:

```text
/api/usuarios