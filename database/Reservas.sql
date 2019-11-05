-- MySQL Script generated by MySQL Workbench
-- Tue Sep  3 02:13:22 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema reservas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema reservas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reservas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `reservas` ;

-- -----------------------------------------------------
-- Table `reservas`.`states`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`states` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `code` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `state_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cities_states1_idx` (`state_id` ASC) VISIBLE,
  CONSTRAINT `fk_cities_states1`
    FOREIGN KEY (`state_id`)
    REFERENCES `reservas`.`states` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `zip_code` VARCHAR(9) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `number` VARCHAR(45) NULL,
  `complement` VARCHAR(255) NULL,
  `neighborhood` VARCHAR(255) NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_addresses_cities1_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_addresses_cities1`
    FOREIGN KEY (`city_id`)
    REFERENCES `reservas`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `birth_date` DATE NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `fk_users_addresses_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_addresses`
    FOREIGN KEY (`address_id`)
    REFERENCES `reservas`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `flight_id` INT NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  `approved` TINYINT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `reservas`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seat` VARCHAR(3) NOT NULL,
  `value` FLOAT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_orders1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `reservas`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;