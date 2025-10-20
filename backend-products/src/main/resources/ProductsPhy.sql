DROP DATABASE IF EXISTS ProductsDB;
CREATE DATABASE ProductsDB;
USE ProductsDB;

/*
Tablas en el schema:
Tabla usuarios
Tabla logs
Tabla productos
Tabla de Imagenes
Tabla Proveedores
Tabla de stack
*/
-- Tablas de usuarios
-- ---------------------------------------------------------------------------------------------------------------------------------------------
-- La tabla refleja los datos más génericos y indispensables de un usuario en una empresa.
-- This its a simple table with the most generic data of a user.

DROP TABLE IF EXISTS users;
CREATE TABLE users(
id_user INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(60) NOT NULL DEFAULT 'Nombre',
last_name VARCHAR(60) NOT NULL DEFAULT 'Apellido',
company_mail VARCHAR(255) UNIQUE,
age INT NOT NULL,
rol ENUM('Invitado', 'Usuario', 'Administrador') DEFAULT 'Usuario',
psswrd VARCHAR(300) NOT NULL, 
mobile INT
);

-- Para evitar conflictos, siempre que un usuario interactue con alguna tabla o algo por el estilo se guardará un log de la misma.
-- Log-out of users table

CREATE TABLE log_user (
id_log INT AUTO_INCREMENT PRIMARY KEY,
id_user INT,
time_moment DATETIME, 
action_register TEXT,
FOREIGN KEY (id_user) REFERENCES users(id_user)
);




-- -------------------------
-- Triggers para usuarios
--


DELIMITER $$
DROP TRIGGER IF EXISTS make_mails$$
CREATE TRIGGER make_mails BEFORE INSERT
ON users
FOR EACH ROW
BEGIN
	IF NEW.company_mail IS NULL OR NEW.company_mail = '' THEN
	SET NEW.company_mail = lower(trim(
    concat( NEW.first_name, NEW.last_name, NEW.id_user, '@ejemplo.com') 
    ));
	END IF;
    
END $$
DELIMITER ;


DELIMITER $$
DROP TRIGGER IF EXISTS make_encrypt_psswrd$$
CREATE TRIGGER make_encrypt_psswrd BEFORE INSERT
ON users
FOR EACH ROW
BEGIN
        SET NEW.psswrd = SHA2(NEW.psswrd, 256);
END$$
DELIMITER ;





-- Tablas de productos
-- ---------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS products;
CREATE TABLE products (
isbn BIGINT PRIMARY KEY,
product_name VARCHAR(200) NOT NULL DEFAULT 'Nombre',
information TEXT,
category ENUM('Ninguna','Electrónica','Ropa','Libros') DEFAULT 'Ninguna'
);

DROP TABLE IF EXISTS picture;
CREATE TABLE picture(
isbn_picture BIGINT PRIMARY KEY,
image VARBINARY(1000) NOT NULL,
FOREIGN KEY (isbn_picture) REFERENCES products(isbn)
);

DROP TABLE IF EXISTS price;
CREATE TABLE price (
isbn_price BIGINT PRIMARY KEY,
price DECIMAL(10,2) NOT NULL,
sale DECIMAL(10,2) DEFAULT 0.00,
taxes DECIMAL(5,2) DEFAULT 0.00,
coin VARCHAR(10) DEFAULT 'USD',
FOREIGN KEY (isbn_price) REFERENCES products(isbn)
);

DROP TABLE IF EXISTS providers;
CREATE TABLE providers (
id INT AUTO_INCREMENT PRIMARY KEY,
nickname VARCHAR(80),
mail VARCHAR(200) UNIQUE,
phone VARCHAR(40),
address VARCHAR(255)
);

DROP TABLE IF EXISTS stock;
CREATE TABLE stock (
isbn_stock BIGINT PRIMARY KEY,
stock_available INT UNSIGNED NOT NULL DEFAULT 0,
stock_min INT UNSIGNED NOT NULL DEFAULT 0,
location VARCHAR(200),
id_providers INT NOT NULL,
FOREIGN KEY (isbn_stock) REFERENCES products(isbn),
FOREIGN KEY (id_providers) REFERENCES providers(id)
);







