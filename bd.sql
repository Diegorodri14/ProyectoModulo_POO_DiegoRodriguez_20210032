//Secuencias
CREATE SEQUENCE sq_libro
START WITH 1 INCREMENT BY 1;

DROP SEQUENCE sq_libro;

CREATE SEQUENCE sq_autor
START WITH 1 INCREMENT BY 1;

DROP SEQUENCE sq_autor;

//Tablas

//Autores
CREATE TABLE autores(
    id                  INT          PRIMARY KEY,
    nombre              VARCHAR(100) NOT NULL,
    apellido            VARCHAR(100) NOT NULL,
    nacionalidad        VARCHAR(50)  NULL,
    fecha_nacimiento    DATE         NULL
);
SELECT * FROM autores

//Libro
CREATE TABLE libros(
    id              INT          PRIMARY KEY,
    titulo          VARCHAR(200) NOT NULL,
    isbn            VARCHAR(20)  NOT NULL UNIQUE,
    año_publicado   INT          NULL,
    genero          VARCHAR(50)  NOT NULL,
    autor_id        INT          NOT NULL,
    CONSTRAINT fk_autor_libro FOREIGN KEY (id) REFERENCES autores(id)
);

SELECT * FROM libros

// Triggers para las secuencias
CREATE OR REPLACE TRIGGER secuencialibro
BEFORE INSERT ON libros
FOR EACH ROW
BEGIN
    SELECT sq_libro.NEXTVAL INTO :new.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER secuenciautores
BEFORE INSERT ON autores
FOR EACH ROW
BEGIN
    SELECT sq_autor.NEXTVAL INTO :new.id FROM dual;
END;
/


//Inserciones

//Autores
INSERT INTO autores(nombre, apellido, nacionalidad, fecha_nacimiento)VALUES
('Ivan','Martinez','Salvadoreño','12/11/2007');
INSERT INTO autores(nombre, apellido, nacionalidad, fecha_nacimiento)VALUES
('Maria','Juarez','Nicaraguense','04/05/1985');
INSERT INTO autores(nombre, apellido, nacionalidad, fecha_nacimiento)VALUES
('Alvaro','Diaz','Puertoriqueño','19/12/1998');

//Libros
INSERT INTO libros(titulo, isbn, año_publicado, genero, autor_id)VALUES
('El pricipito','1-4028-9462-7',2019,'Comedia',1);
INSERT INTO libros(titulo, isbn, año_publicado, genero, autor_id)VALUES
('Habitos Atomicos','5-6351-0723-8',2020,'Enseñanza',2);
INSERT INTO libros(titulo, isbn, año_publicado, genero, autor_id)VALUES
('El lorax','0-1234-5678-9',2018,'Terror',3);

