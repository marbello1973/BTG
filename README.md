
# BTG - Sistema de Gestión de Transacciones

Este proyecto implementa un sistema de gestión de transacciones para clientes, donde se pueden realizar aperturas de productos, cancelaciones, consultas de historial, notificaciones por email o SMS, y consultas de clientes asociados a productos en sucursales específicas.

## Descripción del Proyecto

El sistema permite a los clientes gestionar sus productos financieros a través de diferentes sucursales. Las funcionalidades incluyen apertura de productos, cancelación de productos, consultas de historial de transacciones, y envío de notificaciones a los clientes. Además, se proporciona una API RESTful que permite a los administradores y usuarios interactuar con la base de datos de clientes y productos.

## Herramientas Necesarias

Para ejecutar este proyecto correctamente, necesitarás las siguientes herramientas:

- **Java 21**: El proyecto está desarrollado utilizando la versión 21 de Java.
- **Spring Boot**: Framework principal para la creación de la API.
- **Base de datos MySQL**: El sistema utiliza MySQL como base de datos relacional.
- **MySQL Workbench**: Herramienta para gestionar la base de datos MySQL.
- **Maven**: Administrador de dependencias y herramienta de construcción del proyecto.
- **Flyway**: Herramienta para el control de versiones de las migraciones de base de datos.

### Configuración del Proyecto

#### 1. Configuración de la Base de Datos

Asegúrate de tener MySQL instalado y configurado. Deberás crear una base de datos con el nombre `btg`.

```sql
CREATE DATABASE btg;
```

Una vez creada la base de datos, debes configurar tu archivo `application.yml` con las credenciales de tu base de datos.

### 2. Configuración del archivo `application.yml`

El archivo `application.yml` debe estar configurado de la siguiente manera:

```yaml
spring: 
  datasource:
    url: jdbc:mysql://localhost:3306/btg
    username: ${TU_USERNAME}
    password: ${TU_PASSWORD}
    driver_class-name: com.mysql.cj.jdbc.Driver
  jpa:
    database-platform: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

> **Nota**: Cambia `${TUL_USERNAME}` por tu nombre de usuario y `${TUL_PASSWORD}` por tu contraseña de MySQL.

#### 3. Ejecución de Migraciones con Flyway

El proyecto utiliza **Flyway** para gestionar las migraciones de la base de datos. Asegúrate de que Flyway está configurado en tu entorno, y ejecuta las migraciones con el siguiente comando:

```bash
mvn flyway:migrate
```

## API Endpoints

### 1. Apertura de Producto
Realiza la apertura de un producto para un cliente.

- **POST** `http://localhost:8080/transaccion/apertura?clienteId=1&productoId=2`

#### JSON de ejemplo:
```json
{
    "clienteId": 1,
    "productoId": 2,
    "monto": 100000,
    "tipoTransaccion": "APERTURA"
}
```

### 2. Cancelación de Producto
Cancela un producto para un cliente.

- **POST** `http://localhost:8080/transaccion/cancelar?clienteId=1&productoId=1`

#### JSON de ejemplo:
```json
{
    "idCliente": 1,
    "idProducto": 2,
    "monto": 100000,
    "tipoTransaccion": "CANCELACION"
}
```

### 3. Consultar Historial de Transacciones
Obtén el historial de transacciones de un cliente.

- **GET** `http://localhost:8080/transaccion/historial/1`

### 4. Notificaciones por Email o SMS
Envía una notificación por email o SMS a un cliente.

- **POST** `http://localhost:8080/transaccion/notificacion?clienteId=1&productoId=4&tipoNotificacion=APERTURA`

### 5. Consultar Clientes por Sucursal
Obtén los nombres de los clientes que tienen productos en las sucursales que visitan.

- **GET** `http://localhost:8080/cliente/clientes-sucursales`

### 6. Consultar Detalles del Cliente
Obtén los detalles de un cliente por su ID.

- **GET** `http://localhost:8080/cliente/clientes-sucursales/{id}`

### Otros Endpoints

- **Consultar todos los usuarios registrados**  
  **GET** `http://localhost:8080/cliente`

- **Consultar todas las sucursales**  
  **GET** `http://localhost:8080/sucursal`

- **Consultar todos los productos**  
  **GET** `http://localhost:8080/producto`
