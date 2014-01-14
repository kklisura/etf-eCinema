SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `eCinemaDB` ;
CREATE SCHEMA IF NOT EXISTS `eCinemaDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `eCinemaDB` ;

-- -----------------------------------------------------
-- Table `eCinemaDB`.`Contents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Contents` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Contents` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(128) NOT NULL,
  `actors` VARCHAR(256) NULL,
  `director` VARCHAR(45) NULL,
  `year` INT NOT NULL,
  `length` INT NOT NULL,
  `description` VARCHAR(1024) NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Tags` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `eCinemaDB`.`Tags` (`name` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`States`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`States` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`States` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `shortName` VARCHAR(45) NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Languages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Languages` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Languages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `langugage` VARCHAR(45) NOT NULL,
  `states_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Languages_States1`
    FOREIGN KEY (`states_id`)
    REFERENCES `eCinemaDB`.`States` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Languages_States1_idx` ON `eCinemaDB`.`Languages` (`states_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Subtitles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Subtitles` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Subtitles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `languages_id` INT NOT NULL,
  `contents_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Subtitles_Languages`
    FOREIGN KEY (`languages_id`)
    REFERENCES `eCinemaDB`.`Languages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subtitles_Sadrzaji1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Subtitles_Languages_idx` ON `eCinemaDB`.`Subtitles` (`languages_id` ASC);

CREATE INDEX `fk_Subtitles_Sadrzaji1_idx` ON `eCinemaDB`.`Subtitles` (`contents_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`AudioSynchronizations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`AudioSynchronizations` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`AudioSynchronizations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contents_id` INT NOT NULL,
  `languages_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_AudioSynchronizations_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AudioSynchronizations_Languages1`
    FOREIGN KEY (`languages_id`)
    REFERENCES `eCinemaDB`.`Languages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_AudioSynchronizations_Contents1_idx` ON `eCinemaDB`.`AudioSynchronizations` (`contents_id` ASC);

CREATE INDEX `fk_AudioSynchronizations_Languages1_idx` ON `eCinemaDB`.`AudioSynchronizations` (`languages_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Users` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `dateOfBirth` DATE NOT NULL,
  `states_id` INT NOT NULL,
  `password` CHAR(40) NOT NULL,
  `salt` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Users_States1`
    FOREIGN KEY (`states_id`)
    REFERENCES `eCinemaDB`.`States` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Users_States1_idx` ON `eCinemaDB`.`Users` (`states_id` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `eCinemaDB`.`Users` (`email` ASC);

CREATE UNIQUE INDEX `username_UNIQUE` ON `eCinemaDB`.`Users` (`username` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Cinemas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Cinemas` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Cinemas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `address` VARCHAR(128) NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`CinemaHalls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`CinemaHalls` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`CinemaHalls` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `rows` INT NOT NULL,
  `cols` INT NOT NULL,
  `seatMatrix` VARCHAR(1024) NOT NULL,
  `cinemas_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_CinemaHalls_Cinemas1`
    FOREIGN KEY (`cinemas_id`)
    REFERENCES `eCinemaDB`.`Cinemas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CinemaHalls_Cinemas1_idx` ON `eCinemaDB`.`CinemaHalls` (`cinemas_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`ProjectionTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`ProjectionTypes` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`ProjectionTypes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Projections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Projections` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Projections` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` DATETIME NOT NULL,
  `pricePerSeat` DECIMAL NULL,
  `contents_id` INT NOT NULL,
  `cinemahalls_id` INT NOT NULL,
  `projectiontypes_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Projections_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Projections_CinemaHalls1`
    FOREIGN KEY (`cinemahalls_id`)
    REFERENCES `eCinemaDB`.`CinemaHalls` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Projections_ProjectionTypes1`
    FOREIGN KEY (`projectiontypes_id`)
    REFERENCES `eCinemaDB`.`ProjectionTypes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Projections_Contents1_idx` ON `eCinemaDB`.`Projections` (`contents_id` ASC);

CREATE INDEX `fk_Projections_CinemaHalls1_idx` ON `eCinemaDB`.`Projections` (`cinemahalls_id` ASC);

CREATE INDEX `fk_Projections_ProjectionTypes1_idx` ON `eCinemaDB`.`Projections` (`projectiontypes_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Receipts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Receipts` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Receipts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `totalPrice` DECIMAL NOT NULL,
  `discount` DECIMAL NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`ReservationTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`ReservationTypes` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`ReservationTypes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Reservations` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Reservations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `projections_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `receipts_id` INT NOT NULL,
  `reservationtypes_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Reservations_Projections1`
    FOREIGN KEY (`projections_id`)
    REFERENCES `eCinemaDB`.`Projections` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservations_Users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `eCinemaDB`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservations_Receipts1`
    FOREIGN KEY (`receipts_id`)
    REFERENCES `eCinemaDB`.`Receipts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservations_ReservationTypes1`
    FOREIGN KEY (`reservationtypes_id`)
    REFERENCES `eCinemaDB`.`ReservationTypes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Reservations_Projections1_idx` ON `eCinemaDB`.`Reservations` (`projections_id` ASC);

CREATE INDEX `fk_Reservations_Users1_idx` ON `eCinemaDB`.`Reservations` (`users_id` ASC);

CREATE INDEX `fk_Reservations_Receipts1_idx` ON `eCinemaDB`.`Reservations` (`receipts_id` ASC);

CREATE INDEX `fk_Reservations_ReservationTypes1_idx` ON `eCinemaDB`.`Reservations` (`reservationtypes_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Seats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Seats` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Seats` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `row` INT NOT NULL,
  `col` INT NOT NULL,
  `cinemahalls_id` INT NOT NULL,
  `reservations_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Seats_CinemaHalls1`
    FOREIGN KEY (`cinemahalls_id`)
    REFERENCES `eCinemaDB`.`CinemaHalls` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Seats_Reservations1`
    FOREIGN KEY (`reservations_id`)
    REFERENCES `eCinemaDB`.`Reservations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Seats_CinemaHalls1_idx` ON `eCinemaDB`.`Seats` (`cinemahalls_id` ASC);

CREATE INDEX `fk_Seats_Reservations1_idx` ON `eCinemaDB`.`Seats` (`reservations_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`UserActionTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`UserActionTypes` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`UserActionTypes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(64) NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `type_UNIQUE` ON `eCinemaDB`.`UserActionTypes` (`type` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`UserActions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`UserActions` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`UserActions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `users_id` INT NOT NULL,
  `useractiontypes_id` INT NOT NULL,
  `time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_UserActions_Users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `eCinemaDB`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserActions_UserActionTypes1`
    FOREIGN KEY (`useractiontypes_id`)
    REFERENCES `eCinemaDB`.`UserActionTypes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_UserActions_Users1_idx` ON `eCinemaDB`.`UserActions` (`users_id` ASC);

CREATE INDEX `fk_UserActions_UserActionTypes1_idx` ON `eCinemaDB`.`UserActions` (`useractiontypes_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Comments` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(1024) NOT NULL,
  `contents_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Comments_Users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `eCinemaDB`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Comments_Users1_idx` ON `eCinemaDB`.`Comments` (`users_id` ASC);

CREATE INDEX `fk_Comments_Contents1_idx` ON `eCinemaDB`.`Comments` (`contents_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Groups` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Groups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(128) NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `eCinemaDB`.`Groups` (`name` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`UserActionComments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`UserActionComments` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`UserActionComments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comments_id` INT NOT NULL,
  `useractions_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_UserActionComment_Comments1`
    FOREIGN KEY (`comments_id`)
    REFERENCES `eCinemaDB`.`Comments` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserActionComment_UserActions1`
    FOREIGN KEY (`useractions_id`)
    REFERENCES `eCinemaDB`.`UserActions` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_UserActionComment_Comments1_idx` ON `eCinemaDB`.`UserActionComments` (`comments_id` ASC);

CREATE INDEX `fk_UserActionComment_UserActions1_idx` ON `eCinemaDB`.`UserActionComments` (`useractions_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`UserActionContents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`UserActionContents` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`UserActionContents` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contents_id` INT NOT NULL,
  `useractions_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_UserActionContent_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserActionContent_UserActions1`
    FOREIGN KEY (`useractions_id`)
    REFERENCES `eCinemaDB`.`UserActions` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_UserActionContent_Contents1_idx` ON `eCinemaDB`.`UserActionContents` (`contents_id` ASC);

CREATE INDEX `fk_UserActionContent_UserActions1_idx` ON `eCinemaDB`.`UserActionContents` (`useractions_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`ContentMarks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`ContentMarks` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`ContentMarks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mark` INT NOT NULL,
  `contents_id` INT NOT NULL,
  `useractions_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ContentMarks_UserActions1`
    FOREIGN KEY (`useractions_id`)
    REFERENCES `eCinemaDB`.`UserActions` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ContentMarks_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ContentMarks_UserActions1_idx` ON `eCinemaDB`.`ContentMarks` (`useractions_id` ASC);

CREATE INDEX `fk_ContentMarks_Contents1_idx` ON `eCinemaDB`.`ContentMarks` (`contents_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`UserGroups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`UserGroups` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`UserGroups` (
  `users_id` INT NOT NULL,
  `groups_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `groups_id`),
  CONSTRAINT `fk_UserGroup_Users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `eCinemaDB`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserGroup_Groups1`
    FOREIGN KEY (`groups_id`)
    REFERENCES `eCinemaDB`.`Groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_UserGroup_Users1_idx` ON `eCinemaDB`.`UserGroups` (`users_id` ASC);

CREATE INDEX `fk_UserGroup_Groups1_idx` ON `eCinemaDB`.`UserGroups` (`groups_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Roles` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(128) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`GroupRoles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`GroupRoles` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`GroupRoles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `groups_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_GroupRoles_Groups1`
    FOREIGN KEY (`groups_id`)
    REFERENCES `eCinemaDB`.`Groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GroupRoles_Roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `eCinemaDB`.`Roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_GroupRoles_Groups1_idx` ON `eCinemaDB`.`GroupRoles` (`groups_id` ASC);

CREATE INDEX `fk_GroupRoles_Roles1_idx` ON `eCinemaDB`.`GroupRoles` (`roles_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Resources`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Resources` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Resources` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eCinemaDB`.`UserRoles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`UserRoles` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`UserRoles` (
  `users_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `roles_id`),
  CONSTRAINT `fk_UserRoles_Users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `eCinemaDB`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserRoles_Roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `eCinemaDB`.`Roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_UserRoles_Users1_idx` ON `eCinemaDB`.`UserRoles` (`users_id` ASC);

CREATE INDEX `fk_UserRoles_Roles1_idx` ON `eCinemaDB`.`UserRoles` (`roles_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`PrivilegeTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`PrivilegeTypes` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`PrivilegeTypes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `eCinemaDB`.`PrivilegeTypes` (`name` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`Privileges`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`Privileges` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`Privileges` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `allow` TINYINT(1) NOT NULL DEFAULT TRUE,
  `resources_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  `privilegetypes_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Privileges_Resources1`
    FOREIGN KEY (`resources_id`)
    REFERENCES `eCinemaDB`.`Resources` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Privileges_Roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `eCinemaDB`.`Roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Privileges_PrivilegeTypes1`
    FOREIGN KEY (`privilegetypes_id`)
    REFERENCES `eCinemaDB`.`PrivilegeTypes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Privileges_Resources1_idx` ON `eCinemaDB`.`Privileges` (`resources_id` ASC);

CREATE INDEX `fk_Privileges_Roles1_idx` ON `eCinemaDB`.`Privileges` (`roles_id` ASC);

CREATE INDEX `fk_Privileges_PrivilegeTypes1_idx` ON `eCinemaDB`.`Privileges` (`privilegetypes_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`TopContents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`TopContents` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`TopContents` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(64) NOT NULL,
  `shortDescription` VARCHAR(256) NOT NULL,
  `contents_id` INT NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_TopContents_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_TopContents_Contents1_idx` ON `eCinemaDB`.`TopContents` (`contents_id` ASC);


-- -----------------------------------------------------
-- Table `eCinemaDB`.`ContentTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eCinemaDB`.`ContentTags` ;

CREATE TABLE IF NOT EXISTS `eCinemaDB`.`ContentTags` (
  `tags_id` INT NOT NULL,
  `contents_id` INT NOT NULL,
  CONSTRAINT `fk_ContentTags_Tags1`
    FOREIGN KEY (`tags_id`)
    REFERENCES `eCinemaDB`.`Tags` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ContentTags_Contents1`
    FOREIGN KEY (`contents_id`)
    REFERENCES `eCinemaDB`.`Contents` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ContentTags_Tags1_idx` ON `eCinemaDB`.`ContentTags` (`tags_id` ASC);

CREATE INDEX `fk_ContentTags_Contents1_idx` ON `eCinemaDB`.`ContentTags` (`contents_id` ASC);

USE `eCinemaDB` ;

-- -----------------------------------------------------
-- procedure register_content_watch
-- -----------------------------------------------------

USE `eCinemaDB`;
DROP procedure IF EXISTS `eCinemaDB`.`register_content_watch`;

DELIMITER $$
USE `eCinemaDB`$$
CREATE PROCEDURE `register_content_watch` (IN user_id INT, IN content_id INT)
BEGIN
	
	INSERT INTO UserActions (users_id, useractiontypes_id)
	VALUES (user_id, (SELECT id FROM UserActionTypes WHERE type = 'CONTENT_WATCH'));
	
	INSERT INTO UserActionContents (contents_id, useractions_id)
	VALUES (content_id, LAST_INSERT_ID());

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure register_content_mark
-- -----------------------------------------------------

USE `eCinemaDB`;
DROP procedure IF EXISTS `eCinemaDB`.`register_content_mark`;

DELIMITER $$
USE `eCinemaDB`$$
CREATE PROCEDURE `register_content_mark` (IN user_id INT, IN content_id INT, IN mark INT)
BEGIN

	INSERT INTO UserActions (users_id, useractiontypes_id)
	VALUES(user_id, (SELECT id FROM UserActionTypes WHERE type = 'CONTENT_MARK'));

	INSERT INTO ContentMarks (mark, contents_id, useractions_id)
	VALUES (mark, content_id, LAST_INSERT_ID());

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure register_login
-- -----------------------------------------------------

USE `eCinemaDB`;
DROP procedure IF EXISTS `eCinemaDB`.`register_login`;

DELIMITER $$
USE `eCinemaDB`$$
CREATE PROCEDURE `register_login` (IN user_id INT)
BEGIN
	
	INSERT INTO UserActions (users_id, useractiontypes_id)
	VALUES (user_id, (SELECT id FROM UserActionTypes WHERE type = 'LOGIN'));

END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Contents`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Contents` (`id`, `title`, `actors`, `director`, `year`, `length`, `description`, `updatedAt`, `createdAt`) VALUES (1, 'Ničija zemlja', 'Branko Djuric, Rene Bitorajac, Filip Sovagovic ', 'Danis Tanovic', 2001, 98, 'Bosnia and Herzegovina during 1993 at the time of the heaviest fighting between the two warring sides. Two soldiers from opposing sides in the conflict, Nino and Ciki, become trapped in no man\'s land, whilst a third soldier becomes a living booby trap.', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Contents` (`id`, `title`, `actors`, `director`, `year`, `length`, `description`, `updatedAt`, `createdAt`) VALUES (2, 'Devils on the Doorstep', 'Wen Jiang, Kenya Sawada, Yihong Jiang', 'Wen Jiang', 2000, 139, 'During the Japanese occupation of China, two prisoners are dumped in a peasant\'s home in a small town. The owner is bullied into keeping the prisoners until the next New Year, at which time they will be collected. The village leaders convene to interrogate the prisoners. The townspeople then struggle to accommodate the prisoners. One is a bellicose Japanese nationalist, the other a nervous translator. Will the townspeople manage to keep the prisoners until the New Year?', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Contents` (`id`, `title`, `actors`, `director`, `year`, `length`, `description`, `updatedAt`, `createdAt`) VALUES (3, 'The Lord of the Rings: The Fellowship of the Ring', 'Elijah Wood, Ian McKellen, Orlando Bloom', 'Peter Jackson', 2001, 178, 'A meek hobbit of The Shire and eight companions set out on a journey to Mount Doom to destroy the One Ring and the dark lord Sauron.', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Contents` (`id`, `title`, `actors`, `director`, `year`, `length`, `description`, `updatedAt`, `createdAt`) VALUES (4, 'A Beautiful Mind', 'Russell Crowe, Ed Harris, Jennifer Connelly', 'Ron Howard', 2001, 135, 'After a brilliant but asocial mathematician accepts secret work in cryptography, his life takes a turn to the nightmarish.', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Tags`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Tags` (`id`, `name`, `updatedAt`, `createdAt`) VALUES (1, 'Local', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Tags` (`id`, `name`, `updatedAt`, `createdAt`) VALUES (2, 'Foreign', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Tags` (`id`, `name`, `updatedAt`, `createdAt`) VALUES (3, 'Family', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Tags` (`id`, `name`, `updatedAt`, `createdAt`) VALUES (4, 'Children', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Tags` (`id`, `name`, `updatedAt`, `createdAt`) VALUES (5, 'Series', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`States`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`States` (`id`, `name`, `shortName`, `updatedAt`, `createdAt`) VALUES (1, 'Bosnia and Herzegovina', 'BA', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`States` (`id`, `name`, `shortName`, `updatedAt`, `createdAt`) VALUES (2, 'Croatia', 'HR', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`States` (`id`, `name`, `shortName`, `updatedAt`, `createdAt`) VALUES (3, 'Slovenia', 'SLO', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`States` (`id`, `name`, `shortName`, `updatedAt`, `createdAt`) VALUES (4, 'Serbia', 'RS', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Languages`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Languages` (`id`, `langugage`, `states_id`, `updatedAt`, `createdAt`) VALUES (1, 'Bosnian', 1, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Languages` (`id`, `langugage`, `states_id`, `updatedAt`, `createdAt`) VALUES (2, 'Croatian', 2, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Languages` (`id`, `langugage`, `states_id`, `updatedAt`, `createdAt`) VALUES (3, 'Serbian (latin)', 4, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Languages` (`id`, `langugage`, `states_id`, `updatedAt`, `createdAt`) VALUES (4, 'Serbian (cyrlic)', 4, '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Users`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Users` (`id`, `lastName`, `firstName`, `username`, `email`, `phone`, `dateOfBirth`, `states_id`, `password`, `salt`, `updatedAt`, `createdAt`) VALUES (1, 'Public', 'Joe', 'joe.public', 'joe.public@example.com', '+38760111222', '1990-10-27', 1, '967e11678defcbc00e780d48b56aa42f34c37dac', 7199304, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Users` (`id`, `lastName`, `firstName`, `username`, `email`, `phone`, `dateOfBirth`, `states_id`, `password`, `salt`, `updatedAt`, `createdAt`) VALUES (2, 'Huseinovic', 'Naida', 'nhuseinovic', 'nhuseinovic1@etf.unsa.ba', '+38761000111', '2000-09-08', 1, '967e11678defcbc00e780d48b56aa42f34c37dac', 7199304, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Users` (`id`, `lastName`, `firstName`, `username`, `email`, `phone`, `dateOfBirth`, `states_id`, `password`, `salt`, `updatedAt`, `createdAt`) VALUES (3, 'Kapic', 'Maida', 'mkapic', 'mkapic1@etf.unsa.ba', '+38762123456', '1990-08-08', 1, '967e11678defcbc00e780d48b56aa42f34c37dac', 7199304, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Users` (`id`, `lastName`, `firstName`, `username`, `email`, `phone`, `dateOfBirth`, `states_id`, `password`, `salt`, `updatedAt`, `createdAt`) VALUES (4, 'Kenan', 'Klisura', 'kklisura', 'kklisura1@etf.unsa.ba', '+38762123456', '1990-10-27', 1, '967e11678defcbc00e780d48b56aa42f34c37dac', 7199304, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Users` (`id`, `lastName`, `firstName`, `username`, `email`, `phone`, `dateOfBirth`, `states_id`, `password`, `salt`, `updatedAt`, `createdAt`) VALUES (5, 'Test', 'Test', 'test', 'test@test.com', '+38762123456', '1990-10-27', 1, '967e11678defcbc00e780d48b56aa42f34c37dac', 7199304, '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Cinemas`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Cinemas` (`id`, `name`, `address`, `updatedAt`, `createdAt`) VALUES (1, 'CinemaCity', 'Behind BBI, Sarajevo', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`CinemaHalls`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`CinemaHalls` (`id`, `title`, `rows`, `cols`, `seatMatrix`, `cinemas_id`, `updatedAt`, `createdAt`) VALUES (1, 'S1', 7, 11, '00', 1, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`CinemaHalls` (`id`, `title`, `rows`, `cols`, `seatMatrix`, `cinemas_id`, `updatedAt`, `createdAt`) VALUES (2, 'S2', 7, 11, '00', 1, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`CinemaHalls` (`id`, `title`, `rows`, `cols`, `seatMatrix`, `cinemas_id`, `updatedAt`, `createdAt`) VALUES (3, 'S3', 5, 9, '00', 1, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`CinemaHalls` (`id`, `title`, `rows`, `cols`, `seatMatrix`, `cinemas_id`, `updatedAt`, `createdAt`) VALUES (4, 'S4', 10, 12, '00', 1, '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`ProjectionTypes`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`ProjectionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (1, 'Premiere', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`ProjectionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (2, 'Matinee', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Projections`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Projections` (`id`, `time`, `pricePerSeat`, `contents_id`, `cinemahalls_id`, `projectiontypes_id`, `updatedAt`, `createdAt`) VALUES (1, '2014-01-14', 2, 1, 1, 1, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Projections` (`id`, `time`, `pricePerSeat`, `contents_id`, `cinemahalls_id`, `projectiontypes_id`, `updatedAt`, `createdAt`) VALUES (2, '2014-01-15', 2, 1, 2, 1, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Projections` (`id`, `time`, `pricePerSeat`, `contents_id`, `cinemahalls_id`, `projectiontypes_id`, `updatedAt`, `createdAt`) VALUES (3, '2014-01-15', 2, 2, 3, 1, '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`UserActionTypes`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`UserActionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (1, 'REGISTRATION', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`UserActionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (2, 'COMMENT', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`UserActionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (3, 'LOGIN', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`UserActionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (4, 'CONTENT_MARK', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`UserActionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (5, 'CONTENT_WATCH', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`UserActionTypes` (`id`, `type`, `updatedAt`, `createdAt`) VALUES (6, 'RESERVATION', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Groups`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Groups` (`id`, `name`, `description`, `updatedAt`, `createdAt`) VALUES (1, 'All Users', 'All users.', '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`Groups` (`id`, `name`, `description`, `updatedAt`, `createdAt`) VALUES (2, 'Guest', 'Just guest users.', '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`UserGroups`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`UserGroups` (`users_id`, `groups_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`Roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`Roles` (`id`, `name`, `description`) VALUES (1, 'TestRole', 'Test role.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`TopContents`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`TopContents` (`id`, `title`, `shortDescription`, `contents_id`, `updatedAt`, `createdAt`) VALUES (1, 'LOTR', 'Will the Frodo do it again? Find out now!', 3, '2013-11-21', '2013-11-21');
INSERT INTO `eCinemaDB`.`TopContents` (`id`, `title`, `shortDescription`, `contents_id`, `updatedAt`, `createdAt`) VALUES (2, 'Beautiful Mind', 'Watch Russell Crowe at his best', 4, '2013-11-21', '2013-11-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eCinemaDB`.`ContentTags`
-- -----------------------------------------------------
START TRANSACTION;
USE `eCinemaDB`;
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (1, 1);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (2, 1);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (1, 2);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (2, 2);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (3, 2);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (4, 3);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (5, 3);
INSERT INTO `eCinemaDB`.`ContentTags` (`tags_id`, `contents_id`) VALUES (1, 4);

COMMIT;

USE `eCinemaDB`;

DELIMITER $$

USE `eCinemaDB`$$
DROP TRIGGER IF EXISTS `eCinemaDB`.`add_registration_to_action` $$
USE `eCinemaDB`$$
CREATE TRIGGER `add_registration_to_action` AFTER INSERT ON `Users` FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
BEGIN
	INSERT INTO UserActions(users_id, useractiontypes_id)
	VALUES (NEW.id, (SELECT id FROM UserActionTypes WHERE type = 'REGISTRATION'));
END$$


USE `eCinemaDB`$$
DROP TRIGGER IF EXISTS `eCinemaDB`.`add_reservation_to_user_action` $$
USE `eCinemaDB`$$
CREATE TRIGGER `add_reservation_to_user_action` AFTER INSERT ON `Reservations` FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
BEGIN
	INSERT INTO UserActions (users_id, useractiontypes_id)
	VALUES (NEW.users_id, (SELECT id FROM UserActionTypes WHERE type = 'RESERVATION'));
END$$


USE `eCinemaDB`$$
DROP TRIGGER IF EXISTS `eCinemaDB`.`add_to_user_actions` $$
USE `eCinemaDB`$$
CREATE TRIGGER `add_to_user_actions` AFTER INSERT ON `Comments` FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
BEGIN
	INSERT INTO UserActions (users_id, useractiontypes_id)
	VALUES (NEW.users_id, (SELECT id FROM UserActionTypes WHERE type = 'COMMENT'));

	INSERT INTO UserActionComments (comments_id, useractions_id)
	VALUES (NEW.id, LAST_INSERT_ID());
END$$


DELIMITER ;
