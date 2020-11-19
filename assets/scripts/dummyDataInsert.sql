-- PERSONA
INSERT INTO taller.persona (dni, nombre, apellidos, telefono) VALUES 
('12345678A', 'Pepe', 'Morales Cifuentes', '000111222'),
('12345678B', 'Rocio', 'Sanchez Mel', '222111000'),
('12345678C', 'Josue', 'Martin Boletus', '333222111'),
('12345678D', 'Alberto', 'Leal Contador', '000000000'),
('87654321A', 'Luis', 'Rodriguez Roedor', '332210000'),
('87654321B', 'Marisa', 'De los Ángeles', '123456789'),
('87654321C', 'Takumi', 'Reparte Tofu', '123456781'),
('12345679X', 'Dominic', 'Toreto', '993456991'),
('0005694R', 'Morty', 'Repara Ciclos', '993456991');

-- CONCESIONARIO
INSERT INTO taller.concesionario (cod_conce, nombre) VALUES 
(1, 'Todo Ruedas');

-- EMPLEADO
INSERT INTO taller.empleado (cod_empleado, dni, cod_conce, usuario, contrasena) VALUES 
(1, '12345678A', 1, 'ventas', MD5('usuario')),
(2, '12345678B', 1, 'mecanico', MD5('usuario')),
(3, '12345678C', 1, 'mecanicojefe', MD5('usuario')),
(4, '12345678D', 1, 'jefe', MD5('usuario')),
(5, '12345679X', 1, 'dominic', MD5('usuario')),
(6, '0005694R', 1, 'mecanicociclo', MD5('usuario'));

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

-- MECANICO 
INSERT INTO taller.mecanico (cod_mecanico, cod_mecanico_jefe, cod_especialidad, cod_empleado) VALUES 
(1, 1, 1, 3),
(2, 1, 2, 2),
(3, 1, 1, 5),
(4, 1, 3, 6);

-- VENTAS
INSERT INTO taller.ventas (cod_ventas, cod_empleado) VALUES 
(1, 1);

-- VEHÍCULO
INSERT INTO taller.vehiculo (num_bastidor, cod_ventas, cod_cliente, cod_conce, tipo_vehiculo, marca, modelo, combustible, precio) VALUES 
('1234567890', 1, 2, 1, 'Coche', 'Subaru', 'WRX STI', 'Gasolina', 37000),
('9876543210', 1, 3, 1, 'Coche', 'Nissan', '370Z Nismo', 'Gasolina', 35000),
('4328457431', 1, 1, 1, 'Motocicleta', 'Honda', 'CBR 1000', 'Gasolina', 7900),
('5486079685', 1, 1, 1, 'Ciclomotor', 'Piaggio', 'SH', 'Gasolina', 2900),
('0006079000', 1, 3, 1, 'Ciclomotor', 'Piaggio', 'ZIP', 'Gasolina', 1000),
('9996079999', 1, 2, 1, 'Motocicleta', 'Yamaha', 'SR', 'Gasolina', 4900);

-- REPARACION
INSERT INTO taller.reparacion (cod_reparacion, cod_mecanico, num_bastidor, fecha_entrada, fecha_salida, piezas) VALUES
(1, 1, '1234567890', '2021-10-9', '2021-10-11', 'Turbo K04 híbrido, downpipe, pintura blue rally'),
(2, 2, '9996079999', '2021-10-9', '2021-10-29', '4 Bujías, silenciador don silencioso'),
(3, 4, '0006079000', '2021-10-9', '2021-10-28', 'Tirar a la basura esta sh*t');

-- COCHE
INSERT INTO taller.coche (mat_coche, num_bastidor) VALUES
('1122ABC', '1234567890'),
('3322DEF', '9876543210');

-- MOTOCICLETA
INSERT INTO taller.motocicleta (mat_moto, num_bastidor) VALUES
('4455ABC', '4328457431'),
('1122POL', '9996079999');

-- CICLOMOTOR
INSERT INTO taller.ciclomotor (mat_ciclo, num_bastidor) VALUES
('4455DEF', '5486079685'),
('0099SHT', '0006079000');

-- PROPUESTA
INSERT INTO taller.propuesta (cod_propuesta, cod_cliente, cod_ventas, num_bastidor, fecha_validez) VALUES
(1, 1, 1, '9876543210', '2021-10-11'),
(2, 2, 1, '5486079685', '2021-12-17'),
(3, 3, 1, '9876543210', '2021-11-19');




