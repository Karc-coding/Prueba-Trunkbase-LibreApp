DROP DATABASE IF EXISTS libreapp;
CREATE DATABASE IF NOT EXISTS libreapp;

USE libreapp;


CREATE TABLE departments(
id INT UNSIGNED AUTO_INCREMENT,
name_department VARCHAR(50) NOT NULL,
CONSTRAINT departments_id_pk
	PRIMARY KEY(id),
CONSTRAINT departments_name_department_uk
	UNIQUE(name_department)
);

--

CREATE TABLE authors(
id INT UNSIGNED AUTO_INCREMENT,
first_name VARCHAR(50) DEFAULT NULL,
last_name VARCHAR(50) DEFAULT NULL,
nickname VARCHAR(60) NOT NULL,
CONSTRAINT authors_id_pk
	PRIMARY KEY(id)
);

--

CREATE TABLE categories(
id INT UNSIGNED AUTO_INCREMENT,
name_category VARCHAR(75) NOT NULL,
CONSTRAINT categories_id_pk
	PRIMARY KEY(id),
CONSTRAINT categories_name_category_uk
	UNIQUE(name_category)
);

-----

# INSERT INTO departments VALUES (ID, 'Nombre de Departamento');
# INSERT INTO departments VALUES (DEFAULT, 'Nombre de Departamento');

INSERT INTO departments
	VALUES	(DEFAULT, 'Amazonas'), (DEFAULT, 'Áncash'), (DEFAULT, 'Apurímac'), (DEFAULT, 'Arequipa'), (DEFAULT, 'Ayacucho'), (DEFAULT, 'Cajamarca'), (DEFAULT, 'Callao'),
			(DEFAULT, 'Cusco'), (DEFAULT, 'Huancavelica'), (DEFAULT, 'Huánuco'), (DEFAULT, 'Ica'), (DEFAULT, 'Junín'), (DEFAULT, 'La Libertad'), (DEFAULT, 'Lambayeque'),
            (DEFAULT, 'Lima Metropolitana'), (DEFAULT, 'Lima (departamento)'), (DEFAULT, 'Loreto'), (DEFAULT, 'Madre de Dios'), (DEFAULT, 'Moquegua'), (DEFAULT, 'Pasco'),
            (DEFAULT, 'Piura'), (DEFAULT, 'Puno'), (DEFAULT, 'San Martín'), (DEFAULT, 'Tacna'), (DEFAULT, 'Tumbes'), (DEFAULT, 'Ucayali');
            
--

# INSERT INTO authors VALUES (ID, 'Nombre', 'Apellido', 'Apodo del autor');
# INSERT INTO authors VALUES (DEFAULT, 'Nombre', 'Apellido', 'Apodo del autor');
# INSERT INTO authors VALUES (DEFAULT, NULL, NULL, 'Apodo del autor');

INSERT INTO authors
	VALUES	(DEFAULT, 'Albert', 'Flores', 'Khaliladonna'), (DEFAULT, 'Annette', 'Black', 'Annette Black'), (DEFAULT, 'Arlene', 'McCoy', 'Juliese'), (DEFAULT, 'Bessie', 'Cooper', 'Bessie Cooper'),
            (DEFAULT, 'Brooklyn', 'Simmons', 'Filber'), (DEFAULT, 'Cameron', 'Williamson', 'Marlon'), (DEFAULT, NULL, NULL, 'Lonatar'), (DEFAULT, 'Courtney', 'Henry', 'Therny'),
            (DEFAULT, NULL, NULL, 'Becky'), (DEFAULT, 'Darrell', 'Steward', 'Darrell Steward'), (DEFAULT, 'Devon', 'Lane', 'Devon Lane'), (DEFAULT, 'Dianne', 'Russell', 'Kunial'),
            (DEFAULT, 'Eleanor', 'Pena', 'Fikianna'), (DEFAULT, 'Esther', 'Howard', 'Tessiusa'), (DEFAULT, NULL, NULL, 'Coriann'), (DEFAULT, 'Guy', 'Hawkins', 'Guy Hawkins'),
            (DEFAULT, 'Jacob', 'Jones', 'Tarad'), (DEFAULT, 'Jane', 'Cooper', 'Ceyala'), (DEFAULT, 'Jenny', 'Wilson', 'Jenny Wilson'), (DEFAULT, 'Jerome', 'Bell', 'Heen'),
            (DEFAULT, 'Kathryn', 'Murphy', 'Kathryn Murphy'), (DEFAULT, 'Kristin', 'Watson', 'Koreya'), (DEFAULT, NULL, NULL, 'Nalianan'), (DEFAULT, 'Marvin', 'McKinney', 'Marvin MacKinney'),
            (DEFAULT, 'Ralph', 'Edwards', 'Thole'), (DEFAULT, 'Robert', 'Fox', 'Robert Fox'), (DEFAULT, NULL, NULL, 'Ronald Richards'), (DEFAULT, 'Savannah', 'Nguyen', 'Savannah'),
            (DEFAULT, 'Theresa', 'Webb', 'Theresa Webb'), (DEFAULT, 'Wade', 'Warren', 'Wade Warren'), (DEFAULT, 'Kevin', 'Rojas', 'Karc');

--

# INSERT INTO categories VALUES (ID, 'Nombre de categoria');
# INSERT INTO categories VALUES (DEFAULT, 'Nombre de categoria');

INSERT INTO categories
	VALUES	(DEFAULT, 'Action and adventure'), (DEFAULT, 'Alternate history'), (DEFAULT, 'Anthology'), (DEFAULT, 'Children'), (DEFAULT, 'Classic'), (DEFAULT, 'Comics'),
            (DEFAULT, 'Coming-of-age'), (DEFAULT, 'Crime'), (DEFAULT, 'Drama'), (DEFAULT, 'Fairytale'), (DEFAULT, 'Fantasy'), (DEFAULT, 'Graphic novel'), (DEFAULT, 'Historical fiction'),
            (DEFAULT, 'Horror'), (DEFAULT, 'Mystery'), (DEFAULT, 'Poetry'), (DEFAULT, 'Political thriller'), (DEFAULT, 'Romance'), (DEFAULT, 'Satire'), (DEFAULT, 'Science fiction'),
            (DEFAULT, 'Short stories'), (DEFAULT, 'Suspense'), (DEFAULT, 'Teens'), (DEFAULT, 'Thriller'), (DEFAULT, 'Western'), (DEFAULT, 'Young adults');

----------

CREATE TABLE books(
id VARCHAR(36) DEFAULT (UUID()),
title VARCHAR(125) NOT NULL,
serie VARCHAR(7) NOT NULL,
year_book CHAR(4) DEFAULT NULL,
categories_id INT UNSIGNED DEFAULT NULL,
price DOUBLE NOT NULL,
stock INT NOT NULL,
authors_id INT UNSIGNED DEFAULT NULL,
state ENUM('CREATED', 'ELIMINATE') NOT NULL,
CONSTRAINT books_id_pk
	PRIMARY KEY(id),
CONSTRAINT books_serie_uk
	UNIQUE(serie),
CONSTRAINT books_categories_id_fk
	FOREIGN KEY(categories_id)
		REFERENCES categories(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
CONSTRAINT books_authors_id_fk
	FOREIGN KEY(authors_id)
		REFERENCES authors(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

--

CREATE TABLE users(
id VARCHAR(36) DEFAULT (UUID()),
dni CHAR(8) NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL,
phone VARCHAR(20) DEFAULT NULL,
address VARCHAR(100) DEFAULT NULL,
date_birth DATE DEFAULT NULL,
date_register DATETIME DEFAULT CURRENT_TIMESTAMP(),
departments_id INT UNSIGNED NOT NULL,
CONSTRAINT users_id_pk
	PRIMARY KEY(id),
CONSTRAINT users_dni_uk
	UNIQUE(dni),
CONSTRAINT users_departments_id_fk
	FOREIGN KEY(departments_id)
		REFERENCES departments(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

--

CREATE TABLE book_sales(
id INT UNSIGNED AUTO_INCREMENT,
number_invoice VARCHAR(11) NOT NULL,
description_sales TEXT DEFAULT NULL,
date_register DATETIME DEFAULT CURRENT_TIMESTAMP(),
users_id VARCHAR(36) NOT NULL,
state ENUM('PENDIENTE', 'ACEPTADA', 'FACTURADA', 'DESPACHADA', 'RECHAZADO', 'ELIMINADO') NOT NULL,
CONSTRAINT book_sales_id_pk
	PRIMARY KEY(id),
CONSTRAINT book_sales_number_invoice_uk
	UNIQUE(number_invoice),
CONSTRAINT book_sales_users_id_fk
	FOREIGN KEY(users_id)
		REFERENCES users(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

--

CREATE TABLE book_sale_details(
id INT UNSIGNED AUTO_INCREMENT,
book_sales_id INT UNSIGNED NOT NULL,
books_id VARCHAR(36) NOT NULL,
price DOUBLE NOT NULL,
amount INT NOT NULL,
subtotal DOUBLE NOT NULL,
CONSTRAINT book_sale_details_id_pk
	PRIMARY KEY(id),
CONSTRAINT book_sale_details_book_sales_id_fk
	FOREIGN KEY(book_sales_id)
		REFERENCES book_sales(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
CONSTRAINT book_sale_details_book_id_fk
	FOREIGN KEY(books_id)
		REFERENCES books(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

-----
# IMPORTANTE: Los ID de tipo UUID() se autogeneran solos por lo que no hay necesidad de definirlos por si mismo, es este caso escribo yo mismo los valores ya que,
#             si no lo hiciera, requeriria que siempre cambie los ID en las FK cada vez que reinicie la BD


# INSERT INTO books VALUES (ID, 'Titulo de libro', 'Serie', 'Año', ID de categoria, Precio, Stock, ID de autor, 'ESTADO');

# INSERT INTO books VALUES (DEFAULT, 'Titulo de libro', 'VJ00000', '2021', 0, 0.00, 0, 0, 'ESTADO');
# INSERT INTO books VALUES (DEFAULT, 'Titulo de libro', 'VJ00000', NULL, NULL, 0.00, 0, NULL, 'ESTADO');

INSERT INTO books
	VALUES 	('372279c9-401a-11ec-815c-0a0027000006', 'Wuthering Heights', 'VJ21983', '1995', 13, 24.30, 75, NULL, '1'),
			('3ef1f294-401a-11ec-815c-0a0027000006', 'Are You There, Vodka? It\'s Me, Chelsea', 'VJ95847', '2014', NULL, 15.00, 150, 5, 'CREATED'),
			('47568f5d-401a-11ec-815c-0a0027000006', 'Great Expectations', 'VJ13090', NULL, 14, 54.20, 15, NULL, '2'),
			('4e0c8b2a-401a-11ec-815c-0a0027000006', 'Collected Fiction', 'VJ65481', '1991', 24, 25.10, 49, 15, 'CREATED'),
			('51e68c26-401a-11ec-815c-0a0027000006', 'To Kill a Mockingbird', 'VJ26548', NULL, NULL, 35.50, 30, NULL, 'ELIMINATE'),
			('573c22a6-401a-11ec-815c-0a0027000006', 'Where the Wild Things Are', 'VJ90982', NULL, 15, 45.20, 75, 8, 'CREATED'),
			('5b533367-401a-11ec-815c-0a0027000006', 'One Hundred Years of Solitude ', 'VJ25461', '1953', 7, 102.00, 174, 24, '1');
    
--

# INSERT INTO users VALUES (ID, 'DNI', 'Nombre', 'Apellido', 'Email', 'Telefono', 'Direccion', 'Fecha de nacimiento', Fecha de registro, ID de departamento);

# INSERT INTO users VALUES (DEFAULT, '99999999', 'Nombre', 'Apellido', 'email@example.com', 'Telefono', 'Direccion', '2021-01-01', DEFAULT, 0);
# INSERT INTO users VALUES (DEFAULT, '99999999', 'Nombre', 'Apellido', 'email@example.com', NULL, NULL, NULL, DEFAULT, 0);

INSERT INTO users
	VALUES 	('868cc51b-401a-11ec-815c-0a0027000006', '15462819', 'Soham', 'Hawkins', 'willie.jennings@example.com', '958421684', '30 Rue René Boulanger 75010 Paris', '2000-10-31', DEFAULT, 1),
			('8b96a78d-401a-11ec-815c-0a0027000006', '84751295', 'Kyle', 'Warner', 'ehagenes@hermann.org', '932156874', '3 rue Paul Bert 75011 Paris', NULL, DEFAULT, 8),
			('8f93f9f4-401a-11ec-815c-0a0027000006', '72022684', 'Arlene', 'Dyer', 'reece90@yahoo.com', '958421658', NULL, NULL, DEFAULT, 15),
			('9273a6ca-401a-11ec-815c-0a0027000006', '78481526', 'Max', 'Benton', 'gulgowski.edgar@gmail.com', NULL, '15 rue des Couronnes 75020 Paris', '1998-05-21', DEFAULT, 4),
			('958afcfe-401a-11ec-815c-0a0027000006', '63857425', 'Soham', 'Hamilton', 'kshlerin.german@ledner.com', NULL, '200 Quai de Valmy 75010 Paris', '1999-04-15', DEFAULT, 18),
			('995505b2-401a-11ec-815c-0a0027000006', '59482615', 'Arlene', 'Banks', 'ybreitenberg@gmail.com', '953164257', '18 rue de Belleville 75020 Paris', NULL, DEFAULT, 21),
			('9c55be94-401a-11ec-815c-0a0027000006', '45786598', 'Colleen', 'Washington', 'tevin44@gmail.com', '958475128', NULL, NULL, DEFAULT, 15);

--

# INSERT INTO book_sales VALUES (ID, 'Numero de Factura', 'Descripcion', Fecha de registro, 'ID de Usuario', 'ESTADO');

# INSERT INTO book_sales VALUES (DEFAULT, 'F-202100001', 'Descripcion', DEFAULT, 'ID de Usuario', 'ESTADO');
# INSERT INTO book_sales VALUES (DEFAULT, 'F-202100001', NULL, DEFAULT, 'ID de Usuario', 'ESTADO');

INSERT INTO book_sales
	VALUES 	(DEFAULT, 'F-202100001', 'Compra en dos partes', DEFAULT, '8f93f9f4-401a-11ec-815c-0a0027000006', '1'),
			(DEFAULT, 'F-202100002', NULL, DEFAULT, '9273a6ca-401a-11ec-815c-0a0027000006', 'DESPACHADA'),
            (DEFAULT, 'F-202100003', 'Compra con tarjeta de credito', DEFAULT, '9273a6ca-401a-11ec-815c-0a0027000006', 'DESPACHADA'),
            (DEFAULT, 'F-202100004', NULL, DEFAULT, '8f93f9f4-401a-11ec-815c-0a0027000006', '2'),
            (DEFAULT, 'F-202100005', 'Compra en 4 partes', DEFAULT, '9c55be94-401a-11ec-815c-0a0027000006', '2'),
            (DEFAULT, 'F-202100006', 'Compra de edicion coleccionista', DEFAULT, '9273a6ca-401a-11ec-815c-0a0027000006', '5'),
            (DEFAULT, 'F-202100007', 'Compra de un libro de 1°ra edicion', DEFAULT, '8f93f9f4-401a-11ec-815c-0a0027000006', 'FACTURADA'),
            (DEFAULT, 'F-202100008', NULL, DEFAULT, '995505b2-401a-11ec-815c-0a0027000006', '6'),
			(DEFAULT, 'F-202100009', 'Compra en efectivo', DEFAULT, '958afcfe-401a-11ec-815c-0a0027000006', 'ACEPTADA'),
            (DEFAULT, 'F-202100010', NULL, DEFAULT, '995505b2-401a-11ec-815c-0a0027000006', 'ACEPTADA');

--

# INSERT INTO book_sale_details VALUES (ID, ID de Book Sales, 'ID de Libro', Precio, Cantidad, Subtotal);
# INSERT INTO book_sale_details VALUES (DEFAULT, 0, 'ID de Libro', 0.00, 0, 0.00);

INSERT INTO book_sale_details
	VALUES 	(DEFAULT, 1, '4e0c8b2a-401a-11ec-815c-0a0027000006', 25.1, 10, 251),
            (DEFAULT, 1, '3ef1f294-401a-11ec-815c-0a0027000006', 15, 5, 75),
            (DEFAULT, 2, '51e68c26-401a-11ec-815c-0a0027000006', 35.5, 3, 106.5),
            (DEFAULT, 3, '47568f5d-401a-11ec-815c-0a0027000006', 54.2, 3, 162.6),
            (DEFAULT, 4, '51e68c26-401a-11ec-815c-0a0027000006', 35.5, 7, 248.5),
            (DEFAULT, 4, '4e0c8b2a-401a-11ec-815c-0a0027000006', 25.1, 5, 125.5),
            (DEFAULT, 5, '47568f5d-401a-11ec-815c-0a0027000006', 54.2, 7, 379.4),
            (DEFAULT, 5, '372279c9-401a-11ec-815c-0a0027000006', 24.3, 3, 72.9),
            (DEFAULT, 5, '3ef1f294-401a-11ec-815c-0a0027000006', 15, 2, 30),
            (DEFAULT, 6, '47568f5d-401a-11ec-815c-0a0027000006', 54.2, 1, 54.2),
            (DEFAULT, 7, '5b533367-401a-11ec-815c-0a0027000006', 102, 1, 102),
            (DEFAULT, 8, '3ef1f294-401a-11ec-815c-0a0027000006', 15, 3, 45),
            (DEFAULT, 9, '51e68c26-401a-11ec-815c-0a0027000006', 35.5, 2, 71),
            (DEFAULT, 9, '47568f5d-401a-11ec-815c-0a0027000006', 54.2, 2, 108.4),
            (DEFAULT, 10, '4e0c8b2a-401a-11ec-815c-0a0027000006', 25.1, 3, 75.3);

--

SELECT * FROM users;
SELECT * FROM departments;
---
SELECT * FROM books;
SELECT * FROM categories;
SELECT * FROM authors;
---
SELECT * FROM book_sales;
SELECT * FROM book_sale_details;

----------

DELIMITER $$
CREATE PROCEDURE SP_NUMBER_INVOICE(OUT out_invoice VARCHAR(11))
BEGIN
	DECLARE num_id INT;
    DECLARE cod VARCHAR(6);
    DECLARE cod_fac VARCHAR(11);
    
    SET cod := CONCAT('F-',YEAR(NOW()));
    SET num_id := (SELECT id FROM book_sales ORDER BY id DESC LIMIT 1);
    SET cod_fac := CONCAT(cod, RIGHT(CONCAT( REPEAT('0', 5), (num_id+1) ), 5));
    
    IF num_id IS NULL OR num_id = '' THEN
		SET out_invoice := (CONCAT(cod, RIGHT(CONCAT( REPEAT('0', 5), 1 ), 5)));
	ELSE
		SET out_invoice := cod_fac;
	END IF;
END$$
DELIMITER ;

SET @result := '';
CALL SP_NUMBER_INVOICE(@result);
SELECT @result;

----------

/*CREATE TABLE providers(
id VARCHAR(36) DEFAULT (UUID()),
razon_social CHAR(50) NOT NULL,
ruc VARCHAR(50) NOT NULL,
phone VARCHAR(20) NOT NULL,
address VARCHAR(100) NOT NULL,
departments_id INT UNSIGNED NOT NULL,
CONSTRAINT providers_id_pk
	PRIMARY KEY(id),
CONSTRAINT providers_ruc_uk
	UNIQUE(ruc),
CONSTRAINT providers_departments_id_fk
	FOREIGN KEY(departments_id)
		REFERENCES departments(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

CREATE TABLE orders(
id VARCHAR(36) DEFAULT (UUID()),
date_register DATETIME DEFAULT CURRENT_TIMESTAMP(),
date_entry DATE DEFAULT NULL,
state ENUM('PENDIENTE', 'ACEPTADA', 'FACTURADA', 'DESPACHADA', 'RECHAZADO') NOT NULL,
providers_id VARCHAR(36) NOT NULL,
CONSTRAINT orders_id_pk
	PRIMARY KEY(id),
CONSTRAINT orders_providers_id_fk
	FOREIGN KEY(providers_id)
		REFERENCES providers(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

CREATE TABLE order_details(
orders_id VARCHAR(36),
books_id VARCHAR(36),
price DOUBLE NOT NULL,
amount INT NOT NULL,
subtotal DOUBLE NOT NULL,
CONSTRAINT order_details_orders_id_books_id_pk
	PRIMARY KEY(orders_id,books_id),
CONSTRAINT orden_details_orders_id_fk
	FOREIGN KEY(orders_id)
		REFERENCES orders(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
CONSTRAINT orden_details_books_id_fk
	FOREIGN KEY(books_id)
		REFERENCES books(id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);*/

--

/*
USER LOGIN

'whitefish664', 'Fbhereicome'
'canarylarruped', '58949baby'
'datebookbully', 'JoManJII007'
'juncturedrench', '58949baby'
'mantraolympics', '25252gg'
'snashpurplish', '6666dgg'
'rheologymandarin', 'Love2workinf'
*/