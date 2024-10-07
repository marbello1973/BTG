-- Crear la base de datos y usarla
CREATE DATABASE IF NOT EXISTS BTG;
USE BTG;

-- Crear tablas
CREATE TABLE IF NOT EXISTS cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100),
    saldo DECIMAL(10,2),
    email VARCHAR(50),
    telefono VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo_producto VARCHAR(50),
    monto_minimo DECIMAL(10,2),
    cliente_id INT,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)

);

CREATE TABLE IF NOT EXISTS sucursal (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS visitas (
    id_sucursal INT,
    id_cliente INT,
    fechaVisita DATE NOT NULL,
    CONSTRAINT fk_sucursal_visitas FOREIGN KEY (id_sucursal) REFERENCES sucursal(id),
    CONSTRAINT fk_cliente_visitas FOREIGN KEY (id_cliente) REFERENCES cliente(id)

);

CREATE TABLE IF NOT EXISTS cliente_sucursal (
    cliente_id INT,
    sucursal_id INT
);

CREATE TABLE IF NOT EXISTS producto_sucursal (
    producto_id INT,
    sucursal_id INT
);


--
--CREATE TABLE IF NOT EXISTS inscripcion (
--    id_producto INT,
--    id_cliente INT,
--    PRIMARY KEY (id_producto, id_cliente),
--    CONSTRAINT fk_producto_inscripcion FOREIGN KEY (id_producto) REFERENCES producto(id),
--    CONSTRAINT fk_cliente_inscripcion FOREIGN KEY (id_cliente) REFERENCES cliente(id)
--);
--
--CREATE TABLE IF NOT EXISTS disponibilidad (
--    id_sucursal INT,
--    id_producto INT,
--    PRIMARY KEY (id_sucursal, id_producto),
--    CONSTRAINT fk_sucursal_disp FOREIGN KEY (id_sucursal) REFERENCES sucursal(id),
--    CONSTRAINT fk_producto_disp FOREIGN KEY (id_producto) REFERENCES producto(id)
--);



