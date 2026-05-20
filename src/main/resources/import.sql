-- Usuarios
INSERT INTO usuarios (id, email, password_hash, nombre, telefono, rol, activo) VALUES (1, 'admin@aureum.com', '$2a$10$hash', 'Administrador', '3001234567', 'ADMIN', true);
INSERT INTO usuarios (id, email, password_hash, nombre, telefono, rol, activo) VALUES (2, 'carlos@aureum.com', '$2a$10$hash', 'Carlos Mendez', '3009876543', 'BARBERO', true);
INSERT INTO usuarios (id, email, password_hash, nombre, telefono, rol, activo) VALUES (3, 'juan@aureum.com', '$2a$10$hash', 'Juan Perez', '3007654321', 'BARBERO', true);
INSERT INTO usuarios (id, email, password_hash, nombre, telefono, rol, activo) VALUES (4, 'cliente1@gmail.com', '$2a$10$hash', 'Pedro Gomez', '3001111111', 'CLIENTE', true);

-- Servicios
INSERT INTO servicios (id, nombre, descripcion, duracion_minutos, precio_base, activo) VALUES (1, 'Corte Clásico', 'Corte de cabello clásico con tijera', 30, 25000, true);
INSERT INTO servicios (id, nombre, descripcion, duracion_minutos, precio_base, activo) VALUES (2, 'Corte + Barba', 'Corte de cabello más arreglo de barba', 60, 45000, true);
INSERT INTO servicios (id, nombre, descripcion, duracion_minutos, precio_base, activo) VALUES (3, 'Afeitado Clásico', 'Afeitado con navaja y toalla caliente', 45, 35000, true);
INSERT INTO servicios (id, nombre, descripcion, duracion_minutos, precio_base, activo) VALUES (4, 'Corte Infantil', 'Corte para niños menores de 12 años', 30, 20000, false);

-- Barberos
INSERT INTO barberos (id, usuario_id, porcentaje_comision, activo) VALUES (1, 2, 35, true);
INSERT INTO barberos (id, usuario_id, porcentaje_comision, activo) VALUES (2, 3, 30, true);

-- Horarios laborales (Lunes=1 a Sábado=6)
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (1, 1, 1, '08:00', '17:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (2, 1, 2, '08:00', '17:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (3, 1, 3, '08:00', '17:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (4, 1, 4, '08:00', '17:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (5, 1, 5, '08:00', '17:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (6, 1, 6, '09:00', '14:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (7, 2, 1, '10:00', '19:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (8, 2, 2, '10:00', '19:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (9, 2, 3, '10:00', '19:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (10, 2, 4, '10:00', '19:00');
INSERT INTO horarios_laborales (id, barbero_id, dia_semana, hora_inicio, hora_fin) VALUES (11, 2, 5, '10:00', '19:00');
