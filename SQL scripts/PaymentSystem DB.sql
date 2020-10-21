-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema payment_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema payment_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `payment_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `payment_system` ;

-- -----------------------------------------------------
-- Table `payment_system`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payment_system`.`clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NULL DEFAULT NULL,
  `last_name` VARCHAR(20) NULL DEFAULT NULL,
  `birth_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `payment_system`.`cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payment_system`.`cards` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(16) NULL DEFAULT NULL,
  `balance` DECIMAL(11,2) NULL DEFAULT NULL,
  `expiry_date` DATE NULL DEFAULT NULL,
  `client_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `card_number` (`card_number` ASC) VISIBLE,
  INDEX `client_id` (`client_id` ASC) VISIBLE,
  CONSTRAINT `cards_ibfk_1`
    FOREIGN KEY (`client_id`)
    REFERENCES `payment_system`.`clients` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
