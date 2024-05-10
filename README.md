# Practico de Microservicio con arquitectura Hexagonal

Este proyecto es un microservicio desarrollado con Java 17, Spring Boot y Spring WebFlux.

## Requisitos previos

Para ejecutar este proyecto, necesitarás tener instalado lo siguiente en tu máquina:

- Java 17
- Maven

## Ejecución del proyecto

Para ejecutar este proyecto, sigue estos pasos:

1. Clona el repositorio en tu máquina local.
2. Navega hasta el directorio del proyecto.
3. Ejecuta el comando `mvn spring-boot:run` para iniciar la aplicación.

## Endpoints del API

A continuación, se presentan los comandos `curl` para interactuar con los endpoints del API:

**Nota:** Reemplaza `{baseUrl}` con la URL base de tu aplicación (por ejemplo, `localhost:8080` si estás ejecutando la aplicación localmente).

- **Obtener todos los clientes desde BD A**

  ```bash
  curl --location 'http://localhost:8080/customers'
  ```

- **Obtener todos los clientes desde BD B**

  ```bash
  curl --location 'http://localhost:8080/customers/B'
  ```
  
- **Obtener un cliente por ID BD A**

  ```bash
  curl --location 'http://localhost:8080/customers/1'
  ```

- **Crear un nuevo cliente GUARDA en BD A** 

  ```bash
  curl --location 'http://localhost:8080/customers' \
  --header 'Content-Type: application/json' \
  --data '{
  "customerId": "1",
  "name": "User Test BD A",
  "counter": 1,
  "loyal":false,
  "new":true
  }'
  ```

- **Crear un cliente frecuente GUARDA en BD B**

  ```bash
  curl --location 'http://localhost:8080/customers' \
  --header 'Content-Type: application/json' \
  --data '{
  "customerId": "1",
  "name": "User Test BD B",
  "counter": 1,
  "loyal":true,
  "new":false
  }'
  ```
  
- **Actualizar un cliente**

  ```bash
  curl --location --request PUT 'http://localhost:8080/customers/1' \
  --header 'Content-Type: application/json' \
  --data '{
  "customerId": "1",
  "name": "Nuevo Nombre",
  "counter": 1,
  "loyal":false,
  "new":true
  }'
  ```

- **Eliminar un cliente**

  ```bash
  curl --location --request DELETE 'http://localhost:8080/customers/1'
  ```

## Requisitos Funcionales y No Funcionales

### Requisitos Funcionales

1. **RF1:** Conexión a Base de Datos Reactiva - ✅ <span style="color:green">Implementado</span>
2. **RF2:** Manejo de Errores - ✅ <span style="color:green">Implementado</span>
3. **RF3:** Lógica de Negocio con Drools(opcional) - <span style="color:gray">Enunciado, No implementado</span>
4. **RF4:** Repositorios de Datos A y B - ✅ <span style="color:green">Implementado</span>
5. **RF5:** Reintentos y Circuit Breaker - <span style="color:gray">Enunciado, No implementado</span>

### Requisitos No Funcionales

1. **RNF1:** Arquitectura Hexagonal - ✅ <span style="color:green">Implementado</span>
2. **RNF2:** Código y Estructura del Proyecto - ✅ <span style="color:green">Implementado</span>
3. **RNF3:** Pruebas Unitarias - ✅ <span style="color:green">Implementado</span>
4. **RNF4:** Documentación - ✅ <span style="color:green">Implementado</span>



