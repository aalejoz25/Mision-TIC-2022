CREATE SCHEMA IF NOT EXISTS `misiontic_unab` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci ;
USE `misiontic_unab` ;

-- -----------------------------------------------------
-- Table `misiontic_unab`.`reto4_ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `misiontic_unab`.`reto4_ciudad` (
  `ciudad_id` INT(11) NOT NULL,
  `descripcion` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ciudad_id`));



-- -----------------------------------------------------
-- Table `misiontic_unab`.`reto4_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `misiontic_unab`.`reto4_usuario` (
  `usuario` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `clave` VARCHAR(32) NOT NULL,
  `fecha_registro` TIMESTAMP NOT NULL,
  PRIMARY KEY (`usuario`));



-- -----------------------------------------------------
-- Table `misiontic_unab`.`reto4_estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `misiontic_unab`.`reto4_estudiante` (
  `estudiante_id` INT(11) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `ciudad` INT(11) NOT NULL,
  `usuario` VARCHAR(16) NOT NULL,
  `activo` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`estudiante_id`),
  INDEX `ciudad_fk` (`ciudad` ASC) VISIBLE,
  INDEX `usuario_fk` (`usuario` ASC) VISIBLE,
  CONSTRAINT `ciudad_fk`
    FOREIGN KEY (`ciudad`)
    REFERENCES `misiontic_unab`.`reto4_ciudad` (`ciudad_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `usuario_fk`
    FOREIGN KEY (`usuario`)
    REFERENCES `misiontic_unab`.`reto4_usuario` (`usuario`));





-- -----------------------------------------------------
-- Table `misiontic_unab`.`reto4_profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `misiontic_unab`.`reto4_profesor` (
  `profesor_id` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`profesor_id`),
  INDEX `usuario_profesor_fk` (`usuario` ASC) VISIBLE,
  CONSTRAINT `usuario_profesor_fk`
    FOREIGN KEY (`usuario`)
    REFERENCES `misiontic_unab`.`reto4_usuario` (`usuario`)
    ON DELETE SET NULL
    ON UPDATE SET NULL);


INSERT INTO misiontic_unab.reto4_ciudad
VALUES (1,"BOGOTÁ"),
(2,"BUCARAMANGA"),
(3,"CALI"),
(4,"MEDELLÍN"),
(5,"BARRANQUILLA"),
(6,"CARTAGENA"),
(7,"YOPAL"),
(9,"SANTA MARTA"),
(10,"LETICIA");

INSERT INTO misiontic_unab.reto4_usuario
VALUES ("jarteaga","jart@gmaill.com","adidas","2016-01-01 09:00:00"),
("jmoreno","juancarlos@gmail.com","adidas","2005-06-02 09:00:00"),
("lpulido","laura@gmail.com","adidas","2003-09-17 09:00:00"),
("marenas","manuel@gmail.com","adidas","2001-01-01 09:00:00"),
("mduarte","mario@gmail.com","adidas","2001-12-20 09:00:00"),
("mmelendez","martin@gmail.com","adidas","2017-02-03 09:40:00"),
("nmartinez","noel@gmail.com","adidas","2011-04-05 07:00:00"),
("sruiz","sammy@gmail.com","adidas","2015-05-01 07:30:00"),
("zdimarco","zoe@gmail.com","adidas","2000-01-01 09:00:00");

INSERT INTO misiontic_unab.reto4_estudiante
VALUES (1,"Laura","Pulido",2,"lpulido",1),
(2,"Zoe","Dimarco",1,"zdimarco",1),
(3,"Mario","Duarte",3,"mduarte",0);

INSERT INTO misiontic_unab.reto4_profesor
VALUES (1,"Sammy","Ruiz","sruiz"),
(2,"Andrés","Jácome",NULL),
(3,"Martín","Melendez","mmelendez"),
(4,"Samuel","Vega",NULL),
(5,"Manuel","Arenas","marenas"),
(6,"Juan","Arteaga","jarteaga");



SELECT RANK() OVER (ORDER BY reto4_usuario.fecha_registro DESC) AS CONSECUTIVO, 
(CASE 
WHEN reto4_ciudad.ciudad_id IN (SELECT reto4_estudiante.ciudad FROM reto4_ciudad) THEN reto4_ciudad.descripcion
ELSE 'BOGOTÁ' END )  AS CIUDAD,
UPPER(reto4_usuario.usuario) AS LOGIN_DE_USUARIO,

(CASE 
WHEN reto4_usuario.usuario IN (SELECT reto4_profesor.usuario FROM reto4_profesor) THEN 'PROFESOR'
WHEN reto4_usuario.usuario IN (SELECT reto4_estudiante.usuario FROM reto4_estudiante) AND reto4_estudiante.activo IN (1) THEN 'ESTUDIANTE'
WHEN reto4_usuario.usuario IN (SELECT reto4_estudiante.usuario FROM reto4_estudiante) AND reto4_estudiante.activo IN (0) THEN 'EGRESADO'
ELSE 'N/A' END ) 
AS CATEGORIA,

(CASE 
WHEN reto4_usuario.usuario IN (SELECT reto4_profesor.usuario FROM reto4_profesor) THEN UPPER(CONCAT(reto4_profesor.nombre," ",reto4_profesor.apellido))
WHEN reto4_usuario.usuario IN (SELECT reto4_estudiante.usuario FROM reto4_estudiante) THEN UPPER(CONCAT(reto4_estudiante.nombre," ",reto4_estudiante.apellido))
ELSE 'NO ASIGNADO' END ) 
AS NOMBRE_COMPLETO,

reto4_usuario.fecha_registro AS FECHA
FROM reto4_usuario LEFT JOIN reto4_estudiante ON reto4_usuario.usuario=reto4_estudiante.usuario LEFT JOIN reto4_profesor ON reto4_usuario.usuario=reto4_profesor.usuario LEFT JOIN reto4_ciudad ON reto4_estudiante.ciudad = reto4_ciudad.ciudad_id;
