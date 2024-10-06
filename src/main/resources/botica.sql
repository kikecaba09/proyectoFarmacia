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
