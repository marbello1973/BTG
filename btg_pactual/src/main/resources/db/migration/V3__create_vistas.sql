-- Insertar datos en la tabla Clientes
INSERT INTO cliente (nombre, apellido, ciudad, saldo, email, telefono) VALUES
    ('Juan', 'Gonzalez', 'Cali', '500000.00', 'juan@gmail.com', '3512578954'),
    ('Ana', 'Martinez', 'Medellín', '500000.00', 'ana@gmail.com', '253657894'),
    ('Pedro', 'Lopez', 'Bogotá', '500000.00', 'pedro@gmail.com', '2546778963'),
    ('Luis', 'Hernandez', 'Cartagena', '500000.00', 'luis@gmail.com', '236589745'),
    ('María', 'Diaz', 'Barranquilla', '500000.00', 'maria@gmail.com', '23658974565');

-- Insertar datos en la tabla Productos
INSERT INTO producto (nombre, tipo_producto, monto_minimo, cliente_id ) VALUES
    ('FPV_BTG_PACTUAL_RECAUDADORA', 'FPV', 500000.00, 1),
    ('FPV_BTG_PACTUAL_ECOPETROL', 'FPV', 500000.00, 2),
    ('DEUDAPRIVADA', 'FIC', 500000.00, 3),
    ('FDO-ACCIONES', 'FIC', 500000.00, 4),
    ('FPV_BTG_PACTUAL_DINAMICA', 'FPV', 500000.00, 5);

-- Insertar datos en la tabla Sucursal
INSERT INTO sucursal (nombre, ciudad) VALUES
    ('Sucursal 1', 'Cali'),
    ('Sucursal 2', 'Medellín'),
    ('Sucursal 3', 'Bogotá'),
    ('Sucursal 4', 'Barranquilla');

-- Insertar datos en la tabla Visitas
INSERT INTO visitas (id_sucursal, id_cliente, fechaVisita) VALUES
    (1, 1, '2024-10-01'),
    (2, 2, '2024-10-02'),
    (3, 3, '2024-10-03');

-- isertar datos en las tablas intermedias
INSERT INTO cliente_sucursal (cliente_id, sucursal_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- insertar datos en tabla intermedia
INSERT INTO producto_sucursal (producto_id, sucursal_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);


