-- PERSONA
INSERT INTO taller.persona (dni, nombre, apellidos, telefono) VALUES 
('12345678A', 'Pepe', 'Morales Cifuentes', '000111222'),
('12345678B', 'Rocio', 'Sanchez Mel', '222111000'),
('12345678C', 'Josue', 'Martin Boletus', '333222111'),
('12345678D', 'Alberto', 'Leal Contador', '000000000'),
('87654321A', 'Luis', 'Rodriguez Roedor', '332210000'),
('87654321B', 'Marisa', 'De los Ángeles', '123456789'),
('87654321C', 'Takumi', 'Reparte Tofu', '123456781');

-- CONCESIONARIO
INSERT INTO taller.concesionario (cod_conce, nombre) VALUES 
(1, 'Todo Ruedas');

-- EMPLEADO
INSERT INTO taller.empleado (cod_empleado, dni, cod_conce, usuario, contrasena) VALUES 
(1, '12345678A', 1, 'ventas', 'usuario'),
(2, '12345678B', 1, 'mecanico', 'usuario'),
(3, '12345678C', 1, 'mecanicojefe', 'usuario'),
(4, '12345678D', 1, 'jefe', 'usuario');

-- CLIENTE
INSERT INTO taller.cliente (cod_cliente, dni) VALUES 
(1, '87654321A'),
(2, '87654321B'),
(3, '87654321C');

-- JEFE
INSERT INTO taller.jefe (cod_jefe, cod_empleado) VALUES 
(1, 4);

-- ESPECIALIDAD
INSERT INTO taller.especialidad (cod_especialidad, nombre_esp) VALUES 
(1, 'coche'),
(2, 'motocicleta'),
(3, 'ciclomotor');

-- MECANICO Arreglar error con cod_empleado_jefe (relación reflexiva)
/* INSERT INTO taller.mecanico (cod_mecanico, cod_empleado_jefe, cod_especialidad, cod_empleado) VALUES 
(1, 1, 1, 3),
(2, 1, 2, 2); */

-- VENTAS
INSERT INTO taller.ventas (cod_ventas, cod_empleado) VALUES 
(1, 1);

-- VEHÍCULO
INSERT INTO taller.vehiculo (num_bastidor, cod_ventas, cod_cliente, cod_conce, marca, modelo, combustible, precio) VALUES 
('1234567890', 1, 2, 1, 'Subaru', 'WRX STI', 'Gasolina', 37000),
('9876543210', 1, 3, 1, 'Nissan', '370Z Nismo', 'Gasolina', 35000),
('4328457431', 1, 1, 1, 'Honda', 'CRX 1000', 'Gasolina', 6900),
('5486079685', 1, 1, 1, 'Piaggio', 'SH', 'Gasolina', 2900);

-- REPARACION
INSERT INTO taller.reparacion (cod_reparacion, cod_mecanico, num_bastidor, piezas) VALUES
(1, 1, '1234567890', 'Turbo K04 híbrido, downpipe, pintura rally blue');

-- COCHE
INSERT INTO taller.coche (mat_coche, num_bastidor) VALUES
('1122ABC', '1234567890'),
('3322DEF', '9876543210');

-- MOTOCICLETA
INSERT INTO taller.motocicleta (mat_moto, num_bastidor) VALUES
('4455ABC', '4328457431');

-- CICLOMOTOR
INSERT INTO taller.ciclomotor (mat_ciclo, num_bastidor) VALUES
('4455DEF', '5486079685');

-- PROPUESTA
INSERT INTO taller.propuesta (cod_propuesta, cod_cliente, cod_ventas, num_bastidor, fecha_validez) VALUES
(1, 1, 1, '9876543210', '2021-10-11');




