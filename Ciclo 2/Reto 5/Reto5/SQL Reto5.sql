-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema reto_5
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema reto_5
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reto_5` DEFAULT CHARACTER SET utf8mb4 ;
USE `reto_5` ;

-- -----------------------------------------------------
-- Table `reto_5`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reto_5`.`usuarios` (
  `usuario` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `fecha_registro` TIMESTAMP NOT NULL,
  PRIMARY KEY (`usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reto_5`.`operadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reto_5`.`operadores` (
  `id_operador` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_operador`),
  UNIQUE INDEX `descripcion_UNIQUE` (`descripcion` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reto_5`.`base`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reto_5`.`base` (
  `idbase` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idbase`),
  UNIQUE INDEX `descripcion_UNIQUE` (`descripcion` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reto_5`.`sesiones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reto_5`.`sesiones` (
  `id_sesion` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(20) NOT NULL,
  `fecha_inicio_sesion` TIMESTAMP NOT NULL,
  `fecha_cierre_sesion` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_sesion`),
  INDEX `usuario_idx` (`usuario` ASC) VISIBLE,
  CONSTRAINT `usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `reto_5`.`usuarios` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reto_5`.`operaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reto_5`.`operaciones` (
  `id_operacion` INT NOT NULL AUTO_INCREMENT,
  `operador` INT NOT NULL,
  `base` INT NOT NULL,
  `resultado` DOUBLE NOT NULL,
  `fecha_operacion` TIMESTAMP NOT NULL,
  `sesion` INT NOT NULL,
  PRIMARY KEY (`id_operacion`),
  INDEX `operador_idx` (`operador` ASC) VISIBLE,
  INDEX `base_idx` (`base` ASC) VISIBLE,
  INDEX `sesion_idx` (`sesion` ASC) VISIBLE,
  CONSTRAINT `operador`
    FOREIGN KEY (`operador`)
    REFERENCES `reto_5`.`operadores` (`id_operador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `base`
    FOREIGN KEY (`base`)
    REFERENCES `reto_5`.`base` (`idbase`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sesion`
    FOREIGN KEY (`sesion`)
    REFERENCES `reto_5`.`sesiones` (`id_sesion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reto_5`.`operandos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reto_5`.`operandos` (
  `id_operando` INT NOT NULL AUTO_INCREMENT,
  `id_operacion` INT NOT NULL,
  `operando` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_operando`),
  INDEX `operacion_idx` (`id_operacion` ASC) VISIBLE,
  CONSTRAINT `operacion`
    FOREIGN KEY (`id_operacion`)
    REFERENCES `reto_5`.`operaciones` (`id_operacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
