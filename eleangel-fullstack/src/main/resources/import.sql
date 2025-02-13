-- CREACIÓN DE MODULOS
INSERT INTO module (name, base_path) VALUES ('PRODUCT', '/products');
INSERT INTO module (name, base_path) VALUES ('CATEGORY', '/categories');
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');

-- CREACIÓN DE OPERACIONES
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PRODUCTS','', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PRODUCT','/[0-9]*', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PRODUCT','', 'POST', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PRODUCT','/[0-9]*', 'PUT', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PRODUCT','/[0-9]*/disabled', 'PUT', false, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CATEGORIES','', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_CATEGORY','/[0-9]*', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_CATEGORY','', 'POST', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_CATEGORY','/[0-9]*', 'PUT', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_CATEGORY','/[0-9]*/disabled', 'PUT', false, 2);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, 3);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, 4);

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('ASSISTANT_ADMINISTRATOR');
INSERT INTO role (name) VALUES ('ADMINISTRATOR');
INSERT INTO role (name) VALUES ('GUARDIA');
INSERT INTO role (name) VALUES ('OPERADOR');

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 15);

INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 15);

INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 5);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 10);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 15);


/*para demo siman*/
INSERT INTO granted_permission (role_id, operation_id) VALUES (4, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (4, 6);

INSERT INTO granted_permission (role_id, operation_id) VALUES (5, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (5, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (5, 6);

-- CREACIÓN DE USUARIOS

-- INSERT INTO "user" (username, name, password, role) VALUES ('elenilson', 'Elenilson angel', '$2a$10$D4eIqjgn2jh/Mmku7WjDlu5SwW9Jv0yfyyP8pAFnPrcg/mIAPY8M.', 'CUSTOMER');
-- INSERT INTO "user" (username, name, password, role) VALUES ('eleangel', 'Eduardo Angel', '$2a$10$5L9drAStxCmzoDc6eNp6pey.5/jLd.cu5V8B.e6X/irR5YlsgyV6C', 'ASSITANT');
-- INSERT INTO "user" (username, name, password, role) VALUES ('ele19901', 'Elenilson Rivas', '$2a$10$wyYgeRisuiwVhEVJgS/S8O04c.rUFVaVlDh2zZtjJFkBRu9R3aRWu', 'ADMINISTRATOR');


-- TODOS LOS PASSWORD SON: Root1234.$
-- INSERT INTO "user" (username, name, password, role_id) VALUES ('elenilson', 'Elenilson angel', '$2a$10$D4eIqjgn2jh/Mmku7WjDlu5SwW9Jv0yfyyP8pAFnPrcg/mIAPY8M.', 1);
INSERT INTO "user" (username, name, password, role_id) VALUES ('eleangel', 'Eduardo Angel', '$2a$10$wyYgeRisuiwVhEVJgS/S8O04c.rUFVaVlDh2zZtjJFkBRu9R3aRWu', 2);
INSERT INTO "user" (username, name, password, role_id) VALUES ('ele19901', 'Elenilson Rivas', '$2a$10$wyYgeRisuiwVhEVJgS/S8O04c.rUFVaVlDh2zZtjJFkBRu9R3aRWu', 3);
INSERT INTO "user" (username, name, password, role_id) VALUES ('guardiaele', 'Guardia Rivas', '$2a$10$wyYgeRisuiwVhEVJgS/S8O04c.rUFVaVlDh2zZtjJFkBRu9R3aRWu', 4);
INSERT INTO "user" (username, name, password, role_id) VALUES ('operador', 'Operador Angel', '$2a$10$wyYgeRisuiwVhEVJgS/S8O04c.rUFVaVlDh2zZtjJFkBRu9R3aRWu', 5);


-- CREACIÓN DE CATEGORIAS
INSERT INTO category (name, status) VALUES ('Electrónica', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Hogar', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Toda poderosa MAC', 'ENABLED');

-- CREACIÓN DE PRODUCTOS
INSERT INTO product (name, price, status, category_id) VALUES ('Smartphone', 500.00, 'ENABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Auriculares Bluetooth', 50.00, 'DISABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Tablet', 300.00, 'ENABLED', 1);

INSERT INTO product (name, price, status, category_id) VALUES ('Camiseta', 25.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Pantalones', 35.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Zapatos', 45.00, 'ENABLED', 2);

INSERT INTO product (name, price, status, category_id) VALUES ('Balón de Fútbol', 20.00, 'ENABLED', 3);
INSERT INTO product (name, price, status, category_id) VALUES ('Raqueta de Tenis', 80.00, 'DISABLED', 3);

INSERT INTO product (name, price, status, category_id) VALUES ('Aspiradora', 120.00, 'ENABLED', 4);
INSERT INTO product (name, price, status, category_id) VALUES ('Licuadora', 50.00, 'ENABLED', 4);

INSERT INTO product (name, price, status, category_id) VALUES ('MACBOOK PRO LATE 2021', 1550.00, 'ENABLED', 5);

-- CREACION DE CLIENTES

INSERT INTO cliente (nombre, correo) VALUES
                                         ('Juan Pérez', 'juan.perez@example.com'),
                                         ('María Gómez', 'maria.gomez@example.com'),
                                         ('Carlos López', 'carlos.lopez@example.com'),
                                         ('Ana Martínez', 'ana.martinez@example.com'),
                                         ('Luis Rodríguez', 'luis.rodriguez@example.com');

-- CREACION DE VENTAS
INSERT INTO venta (fecha, cliente_id) VALUES
                                          ('2023-10-01 12:34:56', 1),
                                          ('2023-10-02 14:22:10', 2),
                                          ('2023-10-03 09:15:30', 3),
                                          ('2023-10-04 16:45:00', 4),
                                          ('2023-10-05 11:10:20', 5);

-- INSERTANDO DETALLE DE VENTA
INSERT INTO detalle_venta (cantidad, total, venta_id, producto_id) VALUES
                                                                       (1, 999.99, 1, 1),
                                                                       (2, 99.98, 1, 2),
                                                                       (1, 89.99, 2, 3),
                                                                       (1, 199.99, 3, 4),
                                                                       (1, 149.99, 4, 5);