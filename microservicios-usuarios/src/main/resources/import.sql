INSERT INTO usuarios (nombre, apellido, email, password, username, enabled) VALUES ('admin', 'admin', 'admin@admin.com', '$2a$10$3' , 'admin', 1);
INSERT INTO usuarios (nombre, apellido, email, password, username, enabled) VALUES ('test', 'test', 'test@test.com', 'test' , 'test', 1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');


INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);