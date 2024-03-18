INSERT INTO user (username, name, password, role) VALUES ('sergio01', 'sergio rivas', '$2y$10$8a3o3tZjZEyRsYhFh0R9quHGxPAWxHFH4j9V6SXwsPm/C/TS39LVu', 'CUSTOMER');
INSERT INTO user (username, name, password, role) VALUES ('jperez', 'Juan pérez', '$2y$10$kiL/7mOx3IaDDV4OMGT3Ju9xahXbgTYON.hg35/v.0.6zQSXR2Ify.', 'ASSISTANT_ADMINISTRATOR');
INSERT INTO user (username, name, password, role) VALUES ('alvarosan', 'Álvaro Sánchez', '$2y$10$afxA3RrxKd2/CXe7biHxhOwpSg8oYMuBz0klGcobeDw9AUEg1886i', 'ADMINISTRATOR');

INSERT INTO category (name, status) VALUES ('Electrónica', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Hogar', 'ENABLED');

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