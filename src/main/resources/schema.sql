CREATE TABLE producto(
    id   LONG      NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
     descripcion VARCHAR(600) NOT NULL,
    cantidad Integer(6) NOT NULL,
    foto VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
