-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema vk_schema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `vk_schema` ;

-- -----------------------------------------------------
-- Schema vk_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vk_schema` DEFAULT CHARACTER SET utf8 ;
USE `vk_schema` ;

-- -----------------------------------------------------
-- Table `vk_schema`.`persons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vk_schema`.`persons` ;

CREATE TABLE IF NOT EXISTS `vk_schema`.`persons` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
