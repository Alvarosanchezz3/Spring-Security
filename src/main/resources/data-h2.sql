-- Las 3 contraseñas son '12345678'
INSERT INTO "user" (username, name, password, role) VALUES ('sergio01', 'sergio rivas', '$2y$10$8a3o3tZjZEyRsYhFh0R9quHGxPAWxHFH4j9V6SXwsPm/C/TS39LVu', 'CUSTOMER');
INSERT INTO "user" (username, name, password, role) VALUES ('jperez', 'Juan pérez', '$2y$10$dQbSq/rd0M5RzQWxpPtWXewdeV5/ntJXP63BjaLYLIc1IuXCiJRda', 'ASSISTANT_ADMINISTRATOR');
INSERT INTO "user" (username, name, password, role) VALUES ('alvarosan', 'Álvaro Sánchez', '$2y$10$afxA3RrxKd2/CXe7biHxhOwpSg8oYMuBz0klGcobeDw9AUEg1886i', 'ADMINISTRATOR');

INSERT INTO category (name, status) VALUES ('Electrónicos', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Artículos para el Hogar', 'ENABLED');

INSERT INTO product (name, price, status, category_id) VALUES ('iPhone 12', 999.99, 'ENABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('AirPods Pro', 249.99, 'ENABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('iPad Pro', 799.99, 'DISABLED', 1);

INSERT INTO product (name, price, status, category_id) VALUES ('Camisa Polo', 29.99, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Jeans', 39.99, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Zapatos de Vestir', 59.99, 'ENABLED', 2);

INSERT INTO product (name, price, status, category_id) VALUES ('Pelota de Fútbol', 19.99, 'ENABLED', 3);
INSERT INTO product (name, price, status, category_id) VALUES ('Raqueta de Tenis', 79.99, 'DISABLED', 3);

INSERT INTO product (name, price, status, category_id) VALUES ('Aspiradora Robótica', 299.99, 'ENABLED', 4);
INSERT INTO product (name, price, status, category_id) VALUES ('Licuadora', 49.99, 'ENABLED', 4);