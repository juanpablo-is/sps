/**
 * Author:  Juan Pablo
 * Created: 22/03/2020
 */
-- ==============  ELIMINAR TABLAS ============== 
DROP TABLE HISTORIAL;
DROP TABLE RESERVA;
DROP TABLE USUARIO;
DROP TABLE PLAZA;
DROP TABLE CLIENTE;
DROP TABLE MOVILIDAD;
DROP TABLE PERSONA;

-- ==============  CREACIÓN TABLAS ==============
CREATE TABLE PERSONA(
    CEDULA INTEGER NOT NULL PRIMARY KEY,
    NOMBRE VARCHAR(30) NOT NULL,
    CONTRASENIA VARCHAR(100) NOT NULL,
    CORREO VARCHAR(30) NOT NULL,
    TELEFONO CHAR(10)
);

CREATE TABLE USUARIO(
    PLACA CHAR(6) NOT NULL PRIMARY KEY,
    ID_PERSONA INTEGER NOT NULL,
    MARCA VARCHAR(15),
    ID_PROPIEDAD VARCHAR(20) NOT NULL,
    --TRUE = CARRO | FALSE = MOTO
    TIPO_VEHICULO BOOLEAN NOT NULL
);

CREATE TABLE CLIENTE(
    ID VARCHAR(20) NOT NULL PRIMARY KEY,
    ID_PERSONA INTEGER NOT NULL,
    CARROS INTEGER NOT NULL,
    MOTOS INTEGER NOT NULL,
    DIRECCION VARCHAR(50),
    LATITUD DECIMAL(11,7),
    LONGITUD DECIMAL(11,7),
    INICIO CHAR(5) NOT NULL,
    FIN CHAR(5) NOT NULL,
    NOMBRE VARCHAR(30) NOT NULL,
    PRECIO DOUBLE NOT NULL
);

CREATE TABLE MOVILIDAD(
    ID VARCHAR(20) NOT NULL PRIMARY KEY,
    ID_PERSONA INTEGER NOT NULL,
    EMPRESA VARCHAR(30) NOT NULL
);

CREATE TABLE PLAZA(
    ID VARCHAR(24) NOT NULL,
    ID_CLIENTE VARCHAR(20) NOT NULL,
    --TRUE = CARRO | FALSE = MOTO
    TIPO_VEHICULO BIT default 1 NOT NULL,
    --TRUE = CUBIERTO | FALSE = NOCUBIERTO
    CUBIERTO BIT default 1 NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE RESERVA(
    ID INT not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    ID_USUARIO CHAR(6),
    ID_PLAZA VARCHAR(24) NOT NULL,    
    DIA CHAR(10) NOT NULL,
    ENTRADA CHAR(8) NOT NULL,
    ESTADO BOOLEAN NOT NULL
);

CREATE TABLE HISTORIAL(
    ID INT not null primary key
            GENERATED ALWAYS AS IDENTITY
            (START WITH 1, INCREMENT BY 1),
    ID_RESERVA INT NOT NULL,
    SALIDA TIMESTAMP NOT NULL,
    PRECIO DOUBLE NOT NULL
);

-- ==============  ALTERAR TABLAS 'LLAVES FOREANAS'    ==============
ALTER TABLE USUARIO ADD CONSTRAINT ID_PERSONA_USUARIO FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(CEDULA);

ALTER TABLE CLIENTE ADD CONSTRAINT ID_PERSONA_CLIENTE FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(CEDULA);

ALTER TABLE MOVILIDAD ADD CONSTRAINT ID_PERSONA_MOVILIDAD FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(CEDULA);

ALTER TABLE PLAZA ADD CONSTRAINT ID_CLIENTE_PLAZA FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID);

ALTER TABLE RESERVA ADD CONSTRAINT USUARIO_RESERVA FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(PLACA);

ALTER TABLE RESERVA ADD CONSTRAINT PLAZA_RESERVA FOREIGN KEY (ID_PLAZA) REFERENCES PLAZA(ID);

ALTER TABLE HISTORIAL ADD CONSTRAINT RESERVA_HISTORIAL FOREIGN KEY (ID_RESERVA) REFERENCES RESERVA(ID);