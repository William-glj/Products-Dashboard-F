USE ProductsDB;


-- ------------------------------
/* Users
-- Inserción de valores máximos.
INSERT INTO users 
VALUES (4,'Shieda','Kayn',NULL,21,'Usuario','1234',NULL);
-- Inserción media.
INSERT INTO users (first_name,last_name,company_mail,age,rol,psswrd,mobile)
VALUES 
('Nombre','Apellido',NULL, 22,'Administrador','12345',NULL);

*/

-- Inserción mínima.
INSERT INTO users (first_name,last_name,age,rol,psswrd)
VALUES 
('Alberto','Rodriguéz', 44,DEFAULT,'1234');

-- Inserción Máxima
INSERT INTO users (first_name, last_name, company_mail, age, rol, psswrd, mobile)
VALUES 
('Ana', 'López', 'analopez@empresa.com', 28, 'Usuario', 'pass1234', 555123456),
('Carlos', 'Pérez', 'carlosperez@empresa.com', 35, 'Administrador', 'adminPass', 555234567),
('Lucía', 'Martínez', 'luciamartinez@empresa.com', 22, 'Invitado', 'guestPass', 555345678);

SELECT * FROM users;




-- ------------------ 

INSERT INTO log_user (id_user, time_moment, action_register)
VALUES
(1, NOW(), 'Usuario Ana López inició sesión'),
(2, NOW(), 'Carlos Pérez actualizó su contraseña'),
(3, NOW(), 'Lucía Martínez cerró sesión'),
(1, NOW(), 'Ana López modificó su perfil'),
(2, NOW(), 'Carlos Pérez añadió un nuevo usuario'),
(3, NOW(), 'Lucía Martínez cambió su rol a Usuario');

SELECT * FROM log_user;

SELECT 
  id_log,
  id_user,
  DATE_FORMAT(time_moment, '%d/%m/%Y %H:%i:%s') AS fecha_formateada,
  action_register
FROM log_user;

select * from log_user where id_user = 1;

DELETE FROM log_user
WHERE id_user = 1;


-- ------------------------------
INSERT INTO products (isbn, product_name, information, category)
VALUES
(9780140449136, 'El arte de la guerra', 'Libro clásico de estrategia de Sun Tzu.', 'Libros'),
(9780307465351, 'Arduino Starter Kit', 'Kit de inicio para proyectos electrónicos.', 'Electrónica'),
(9781491954249, 'Camiseta básica blanca', 'Camiseta de algodón unisex, talla M.', 'Ropa'),
(9780321125217, 'Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido.', 'Electrónica'),
(9780596009205, 'Pantalón deportivo', 'Pantalón de entrenamiento con bolsillos laterales.', 'Ropa');

INSERT INTO picture (isbn_picture, image)
VALUES
(9780140449136, '0xFFD8FFE000104A464946000101'), 
(9780307465351, '0xFFD8FFE000104A464946000102'),
(9781491954249, '0xFFD8FFE000104A464946000103'),
(9780321125217, '0xFFD8FFE000104A464946000104'),
(9780596009205, '0xFFD8FFE000104A464946000105');

INSERT INTO price (isbn_price, price, sale, taxes, coin)
VALUES
(9780140449136, 15.99, 2.00, 0.07, 'USD'),
(9780307465351, 49.99, 5.00, 0.12, 'USD'),
(9781491954249, 9.99, 0.00, 0.05, 'USD'),
(9780321125217, 79.99, 10.00, 0.15, 'USD'),
(9780596009205, 25.50, 2.50, 0.10, 'USD');

INSERT INTO providers (nickname, mail, phone, address)
VALUES
('TechWorld', 'contacto@techworld.com', '+1-555-123-4567', '742 Evergreen Terrace, Springfield'),
('BookHeaven', 'ventas@bookheaven.com', '+1-555-234-5678', '123 Main St, New York'),
('ModaExpress', 'soporte@modaexpress.com', '+1-555-345-6789', '45 Fashion Ave, Los Angeles'),
('ElectroMax', 'info@electromax.com', '+1-555-456-7890', '87 Silicon Rd, San Francisco'),
('DeportesYA', 'contacto@deportesya.com', '+1-555-567-8901', '99 Training Blvd, Miami');

INSERT INTO stock (isbn_stock, stock_available, stock_min, location, id_providers)
VALUES
(9780140449136, 120, 20, 'Almacén A - Estante 3', 2),  -- Libro (BookHeaven)
(9780307465351, 40, 5, 'Almacén B - Estante 7', 1),   -- Arduino (TechWorld)
(9781491954249, 200, 30, 'Almacén C - Estante 1', 3), -- Camiseta (ModaExpress)
(9780321125217, 60, 10, 'Almacén D - Estante 2', 4),  -- Auriculares (ElectroMax)
(9780596009205, 80, 15, 'Almacén E - Estante 5', 5);  -- Pantalón (DeportesYA)

select * from users;
select * from products;
select * from picture;
select * from price;
select * from providers;
select * from stock;


