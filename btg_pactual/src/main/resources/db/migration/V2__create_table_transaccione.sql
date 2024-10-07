-- Crear tabla transaccion
CREATE TABLE transaccion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_producto INT NOT NULL,
    tipo_transaccion VARCHAR(20) NOT NULL,  -- Valores: APERTURA o CANCELACION
    monto DECIMAL(15, 2) NOT NULL,
    fecha_transaccion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transaccion_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    CONSTRAINT fk_transaccion_producto FOREIGN KEY (id_producto) REFERENCES producto(id)
);
