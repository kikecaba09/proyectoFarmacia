CREATE DATABASE botica;

USE botica;

-- Tabla Categoria
CREATE TABLE categoria (
                           idCategoria INT PRIMARY KEY AUTO_INCREMENT,
                           nombre VARCHAR(50) NOT NULL
);

-- Tabla Laboratorio
CREATE TABLE laboratorio (
                             idLaboratorio INT PRIMARY KEY AUTO_INCREMENT,
                             nombre VARCHAR(100) NOT NULL,
                             direccion VARCHAR(255),
                             telefono VARCHAR(20)
);

-- Tabla Usuario
CREATE TABLE usuario (
                         idUsuario INT PRIMARY KEY AUTO_INCREMENT,
                         nombre VARCHAR(100) NOT NULL,
                         apellido VARCHAR(100) NOT NULL,
                        edad INT not null,
                        email varchar(100) not null,
                         usuario VARCHAR(50) NOT NULL,
                         contrasena VARCHAR(255) NOT NULL,
                         rol ENUM('ADMIN', 'VENDEDOR') NOT NULL
);

-- Tabla Medicamento
CREATE TABLE medicamento (
                             idMedicamento INT PRIMARY KEY AUTO_INCREMENT,
                             nombre VARCHAR(100) NOT NULL,
                             descripcion TEXT,
                             idCategoria INT,
                             idLaboratorio INT,
                             fecha_ingreso DATE NOT NULL,
                             fecha_caducidad DATE NOT NULL,
                             precio_actual DECIMAL(10, 2) NOT NULL,
                             stock INT NOT NULL,
                             FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria) ON DELETE SET NULL ON UPDATE CASCADE,
                             FOREIGN KEY (idLaboratorio) REFERENCES laboratorio(idLaboratorio) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabla Venta
CREATE TABLE venta (
                       idVenta INT PRIMARY KEY AUTO_INCREMENT,
                       fecha DATE NOT NULL,
                       total DECIMAL(10, 2) NOT NULL,
                       idUsuario INT,
                       FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabla DetalleVenta
CREATE TABLE detalle_venta (
                               idDetalleVenta INT PRIMARY KEY AUTO_INCREMENT,
                               idVenta INT,
                               idMedicamento INT,
                               cantidad INT NOT NULL,
                               precio_unitario DECIMAL(10, 2) NOT NULL,
                               FOREIGN KEY (idVenta) REFERENCES venta(idVenta) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (idMedicamento) REFERENCES medicamento(idMedicamento) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla Precio
CREATE TABLE precio (
                        idPrecio INT PRIMARY KEY AUTO_INCREMENT,
                        idMedicamento INT,
                        precio DECIMAL(10, 2) NOT NULL,
                        fecha_actualizacion DATE NOT NULL,
                        FOREIGN KEY (idMedicamento) REFERENCES medicamento(idMedicamento) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla Alerta (Para los medicamentos próximos a vencer)
CREATE TABLE alerta (
                        idAlerta INT PRIMARY KEY AUTO_INCREMENT,
                        idMedicamento INT,
                        fecha_alerta DATE NOT NULL,
                        descripcion TEXT,
                        FOREIGN KEY (idMedicamento) REFERENCES medicamento(idMedicamento) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla Reporte (Generación de reportes de ventas)
CREATE TABLE reporte (
                         idReporte INT PRIMARY KEY AUTO_INCREMENT,
                         tipo_reporte VARCHAR(50) NOT NULL,
                         fecha_generacion DATE NOT NULL,
                         idUsuario INT,
                         FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario) ON DELETE SET NULL ON UPDATE CASCADE
);


-- Insertar más datos en la tabla Categoria
INSERT INTO categoria (nombre)
VALUES
    ('Antipiréticos'),
    ('Antisépticos'),
    ('Analgésicos'),
    ('Antibióticos'),
    ('Antiinflamatorios'),
    ('Antialérgicos'),
    ('Antihistamínicos'),
    ('Antidepresivos');

-- Insertar más datos en la tabla Laboratorio
INSERT INTO laboratorio (nombre, direccion, telefono)
VALUES
    ('Novartis', 'Avenida Principal 101, Ciudad A', '945678123'),
    ('GlaxoSmithKline', 'Calle Moderna 203, Ciudad B', '986543217'),
    ('Sanofi', 'Boulevard Central 456, Ciudad C', '932145876'),
    ('Abbott', 'Plaza Norte 789, Ciudad D', '912546378'),
    ('Merck', 'Via de la Salud 321, Ciudad E', '987321654'),
    ('AstraZeneca', 'Avenida del Sol 654, Ciudad F', '956213478');

-- Insertar más datos en la tabla Usuario (para poder referenciar en ventas)
INSERT INTO usuario (nombre, apellido, edad, email, usuario, contrasena, rol)
VALUES
    ('Carlos', 'Lopez', 40, 'carlos.lopez@example.com', 'carlosl', 'pass789', 'VENDEDOR'),
    ('Ana', 'Martinez', 32, 'ana.martinez@example.com', 'anam', 'pass012', 'ADMIN'),
    ('Luis', 'Diaz', 28, 'luis.diaz@example.com', 'luisd', 'pass345', 'VENDEDOR'),
    ('Sofia', 'Garcia', 35, 'sofia.garcia@example.com', 'sofiag', 'pass678', 'ADMIN'),
    ('Pedro', 'Ramirez', 27, 'pedro.ramirez@example.com', 'pedror', 'pass910', 'VENDEDOR');

-- Insertar más datos en la tabla Medicamento
INSERT INTO medicamento (nombre, descripcion, idCategoria, idLaboratorio, fecha_ingreso, fecha_caducidad, precio_actual, stock)
VALUES
    ('Ibuprofeno 400mg', 'Analgésico y antiinflamatorio', 5, 1, '2024-01-15', '2025-01-15', 12.50, 300),
    ('Paracetamol 500mg', 'Analgésico utilizado para reducir la fiebre', 1, 2, '2024-02-10', '2026-02-10', 8.30, 500),
    ('Amoxicilina 500mg', 'Antibiótico para infecciones bacterianas', 4, 3, '2024-03-01', '2026-03-01', 15.75, 450),
    ('Loratadina 10mg', 'Antihistamínico para alergias', 6, 1, '2024-05-01', '2025-05-01', 5.00, 250),
    ('Aspirina 100mg', 'Analgésico y antipirético', 3, 2, '2024-04-20', '2025-04-20', 10.25, 600),
    ('Ciprofloxacina 500mg', 'Antibiótico de amplio espectro', 4, 3, '2024-05-15', '2026-05-15', 18.90, 350),
    ('Diclofenaco 50mg', 'Antiinflamatorio no esteroide', 5, 4, '2024-06-01', '2025-06-01', 13.40, 400),
    ('Omeprazol 20mg', 'Inhibidor de la bomba de protones', 2, 5, '2024-07-01', '2026-07-01', 6.75, 500),
    ('Clonazepam 2mg', 'Ansiolítico y anticonvulsivo', 7, 6, '2024-08-10', '2025-08-10', 20.15, 150),
    ('Fluoxetina 20mg', 'Antidepresivo inhibidor selectivo de la recaptación de serotonina', 8, 5, '2024-09-05', '2026-09-05', 25.50, 300);

-- Insertar más datos en la tabla Precio
INSERT INTO precio (idMedicamento, precio, fecha_actualizacion)
VALUES
    (1, 12.50, '2024-01-15'),
    (2, 8.30, '2024-02-10'),
    (3, 15.75, '2024-03-01'),
    (4, 5.00, '2024-05-01'),
    (5, 10.25, '2024-04-20'),
    (6, 18.90, '2024-05-15'),
    (7, 13.40, '2024-06-01'),
    (8, 6.75, '2024-07-01'),
    (9, 20.15, '2024-08-10'),
    (10, 25.50, '2024-09-05');

-- Insertar más datos en la tabla Venta
INSERT INTO venta (fecha, total, idUsuario)
VALUES
    ('2024-06-01', 58.75, 1),
    ('2024-06-15', 34.60, 2),
    ('2024-07-01', 95.30, 3),
    ('2024-07-10', 48.20, 4),
    ('2024-07-20', 125.45, 5);

-- Insertar más datos en la tabla DetalleVenta
INSERT INTO detalle_venta (idVenta, idMedicamento, cantidad, precio_unitario)
VALUES
    (1, 1, 3, 12.50),  -- Venta de 3 unidades de Ibuprofeno
    (1, 2, 1, 8.30),   -- Venta de 1 unidad de Paracetamol
    (2, 3, 2, 15.75),  -- Venta de 2 unidades de Amoxicilina
    (2, 4, 3, 5.00),   -- Venta de 3 unidades de Loratadina
    (3, 5, 5, 10.25),  -- Venta de 5 unidades de Aspirina
    (3, 6, 2, 18.90),  -- Venta de 2 unidades de Ciprofloxacina
    (4, 7, 1, 13.40),  -- Venta de 1 unidad de Diclofenaco
    (4, 8, 2, 6.75),   -- Venta de 2 unidades de Omeprazol
    (5, 9, 4, 20.15),  -- Venta de 4 unidades de Clonazepam
    (5, 10, 2, 25.50); -- Venta de 2 unidades de Fluoxetina

-- Insertar más datos en la tabla Alerta (Medicamentos próximos a caducar)
INSERT INTO alerta (idMedicamento, fecha_alerta, descripcion)
VALUES
    (1, '2024-12-01', 'Ibuprofeno próximo a vencer'),
    (4, '2024-04-01', 'Loratadina próximo a vencer'),
    (7, '2025-05-01', 'Diclofenaco próximo a vencer'),
    (9, '2025-08-01', 'Clonazepam próximo a vencer');

-- Insertar más datos en la tabla Reporte (para generar informes de ventas)
INSERT INTO reporte (tipo_reporte, fecha_generacion, idUsuario)
VALUES
    ('Reporte Mensual de Ventas', '2024-07-01', 2),
    ('Reporte Anual de Ventas', '2024-12-31', 4),
    ('Reporte Semanal de Ventas', '2024-07-20', 5);
USE botica;

-- Insertar más datos en la tabla Categoria
INSERT INTO categoria (nombre)
VALUES
    ('Antipiréticos'),
    ('Antisépticos'),
    ('Analgésicos'),
    ('Antibióticos'),
    ('Antiinflamatorios'),
    ('Antialérgicos'),
    ('Antihistamínicos'),
    ('Antidepresivos');

-- Insertar más datos en la tabla Laboratorio
INSERT INTO laboratorio (nombre, direccion, telefono)
VALUES
    ('Novartis', 'Avenida Principal 101, Ciudad A', '945678123'),
    ('GlaxoSmithKline', 'Calle Moderna 203, Ciudad B', '986543217'),
    ('Sanofi', 'Boulevard Central 456, Ciudad C', '932145876'),
    ('Abbott', 'Plaza Norte 789, Ciudad D', '912546378'),
    ('Merck', 'Via de la Salud 321, Ciudad E', '987321654'),
    ('AstraZeneca', 'Avenida del Sol 654, Ciudad F', '956213478');

-- Insertar más datos en la tabla Usuario (para poder referenciar en ventas)
INSERT INTO usuario (nombre, apellido, edad, email, usuario, contrasena, rol)
VALUES
    ('Carlos', 'Lopez', 40, 'carlos.lopez@example.com', 'carlosl', 'pass789', 'Vendedor'),
    ('Ana', 'Martinez', 32, 'ana.martinez@example.com', 'anam', 'pass012', 'Administrador'),
    ('Luis', 'Diaz', 28, 'luis.diaz@example.com', 'luisd', 'pass345', 'Vendedor'),
    ('Sofia', 'Garcia', 35, 'sofia.garcia@example.com', 'sofiag', 'pass678', 'Administrador'),
    ('Pedro', 'Ramirez', 27, 'pedro.ramirez@example.com', 'pedror', 'pass910', 'Vendedor'),
    ('Victor Enrique', 'Cabanillas Rojas', 23, 'kikecabanillas0003@gmail.com', 'victor', '123456', 'Administrador');

-- Insertar más datos en la tabla Medicamento
INSERT INTO medicamento (nombre, descripcion, idCategoria, idLaboratorio, fecha_ingreso, fecha_caducidad, precio_actual, stock)
VALUES
    ('Ibuprofeno 400mg', 'Analgésico y antiinflamatorio', 5, 1, '2024-01-15', '2025-01-15', 12.50, 300),
    ('Paracetamol 500mg', 'Analgésico utilizado para reducir la fiebre', 1, 2, '2024-02-10', '2026-02-10', 8.30, 500),
    ('Amoxicilina 500mg', 'Antibiótico para infecciones bacterianas', 4, 3, '2024-03-01', '2026-03-01', 15.75, 450),
    ('Loratadina 10mg', 'Antihistamínico para alergias', 6, 1, '2024-05-01', '2025-05-01', 5.00, 250),
    ('Aspirina 100mg', 'Analgésico y antipirético', 3, 2, '2024-04-20', '2025-04-20', 10.25, 600),
    ('Ciprofloxacina 500mg', 'Antibiótico de amplio espectro', 4, 3, '2024-05-15', '2026-05-15', 18.90, 350),
    ('Diclofenaco 50mg', 'Antiinflamatorio no esteroide', 5, 4, '2024-06-01', '2025-06-01', 13.40, 400),
    ('Omeprazol 20mg', 'Inhibidor de la bomba de protones', 2, 5, '2024-07-01', '2026-07-01', 6.75, 500),
    ('Clonazepam 2mg', 'Ansiolítico y anticonvulsivo', 7, 6, '2024-08-10', '2025-08-10', 20.15, 150),
    ('Fluoxetina 20mg', 'Antidepresivo inhibidor selectivo de la recaptación de serotonina', 8, 5, '2024-09-05', '2026-09-05', 25.50, 300);

-- Insertar más datos en la tabla Precio
INSERT INTO precio (idMedicamento, precio, fecha_actualizacion)
VALUES
    (1, 12.50, '2024-01-15'),
    (2, 8.30, '2024-02-10'),
    (3, 15.75, '2024-03-01'),
    (4, 5.00, '2024-05-01'),
    (5, 10.25, '2024-04-20'),
    (6, 18.90, '2024-05-15'),
    (7, 13.40, '2024-06-01'),
    (8, 6.75, '2024-07-01'),
    (9, 20.15, '2024-08-10'),
    (10, 25.50, '2024-09-05');

-- Insertar más datos en la tabla Venta
INSERT INTO venta (fecha, total, idUsuario)
VALUES
    ('2024-06-01', 58.75, 1),
    ('2024-06-15', 34.60, 2),
    ('2024-07-01', 95.30, 3),
    ('2024-07-10', 48.20, 4),
    ('2024-07-20', 125.45, 5);

-- Insertar más datos en la tabla DetalleVenta
INSERT INTO detalle_venta (idVenta, idMedicamento, cantidad, precio_unitario)
VALUES
    (1, 1, 3, 12.50),  -- Venta de 3 unidades de Ibuprofeno
    (1, 2, 1, 8.30),   -- Venta de 1 unidad de Paracetamol
    (2, 3, 2, 15.75),  -- Venta de 2 unidades de Amoxicilina
    (2, 4, 3, 5.00),   -- Venta de 3 unidades de Loratadina
    (3, 5, 5, 10.25),  -- Venta de 5 unidades de Aspirina
    (3, 6, 2, 18.90),  -- Venta de 2 unidades de Ciprofloxacina
    (4, 7, 1, 13.40),  -- Venta de 1 unidad de Diclofenaco
    (4, 8, 2, 6.75),   -- Venta de 2 unidades de Omeprazol
    (5, 9, 4, 20.15),  -- Venta de 4 unidades de Clonazepam
    (5, 10, 2, 25.50); -- Venta de 2 unidades de Fluoxetina

-- Insertar más datos en la tabla Alerta (Medicamentos próximos a caducar)
INSERT INTO alerta (idMedicamento, fecha_alerta, descripcion)
VALUES
    (1, '2024-12-01', 'Ibuprofeno próximo a vencer'),
    (4, '2024-04-01', 'Loratadina próximo a vencer'),
    (7, '2025-05-01', 'Diclofenaco próximo a vencer'),
    (9, '2025-08-01', 'Clonazepam próximo a vencer');

-- Insertar más datos en la tabla Reporte (para generar informes de ventas)
INSERT INTO reporte (tipo_reporte, fecha_generacion, idUsuario)
VALUES
    ('Reporte Mensual de Ventas', '2024-07-01', 2),
    ('Reporte Anual de Ventas', '2024-12-31', 4),
    ('Reporte Semanal de Ventas', '2024-07-20', 5);

