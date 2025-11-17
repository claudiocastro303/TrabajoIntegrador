-- Crear base de datos
CREATE DATABASE vehiculo_seguro;
USE vehiculo_seguro;

-- Crear tabla seguro_vehicular
CREATE TABLE seguro_vehicular ( 
    id INT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE, 
    aseguradora VARCHAR(80) NOT NULL, 
    nroPoliza VARCHAR(50) UNIQUE
);

-- Crear tabla vehiculo 
CREATE TABLE vehiculo ( 
    id INT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE, 
    dominio VARCHAR(10) NOT NULL UNIQUE, 
    marca VARCHAR(50) NOT NULL, 
    modelo VARCHAR(50) NOT NULL, 
    anio INT NOT NULL,
    seguro_id INT NULL,
    FOREIGN KEY (seguro_id) REFERENCES seguro_vehicular(id) ON DELETE SET NULL
);

-- Índices para mejor performance
CREATE INDEX idx_vehiculo_dominio ON vehiculo(dominio);
CREATE INDEX idx_vehiculo_marca ON vehiculo(marca);
CREATE INDEX idx_vehiculo_eliminado ON vehiculo(eliminado);
CREATE INDEX idx_seguro_eliminado ON seguro_vehicular(eliminado);


-- =========================================================
-- ==========================================================

-- visualizar tablas
select * from vehiculo;
select * from seguro_vehicular;

-- Eliminar todos los registros de las tablas
DELETE FROM vehiculo;
DELETE FROM seguro_vehicular;