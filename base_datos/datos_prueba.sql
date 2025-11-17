USE vehiculo_seguro;

-- Insertar seguros de prueba
INSERT INTO seguro_vehicular (aseguradora, nroPoliza) VALUES 
('Seguros Norte', 'POL-001'),
('Mapfre', 'POL-002'),
('Allianz', 'POL-003');

-- Insertar vehículos de prueba
INSERT INTO vehiculo (dominio, marca, modelo, anio, seguro_id) VALUES 
('ABC123', 'Toyota', 'Corolla', 2020, 1),
('DEF456', 'Ford', 'Focus', 2021, 2),
('GHI789', 'Honda', 'Civic', 2019, NULL);


-- visualizar tablas
select * from vehiculo;
select * from seguro_vehicular;