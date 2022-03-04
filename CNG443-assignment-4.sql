CREATE USER IF NOT EXISTS 'cng443user'@'localhost' IDENTIFIED BY '1234';
CREATE DATABASE IF NOT EXISTS `restmanapp`;
GRANT ALL PRIVILEGES ON `hms`.* TO 'cng443user'@'localhost';

CREATE TABLE `restmanapp`.`customer` ( 
  `customerID` INT(10) NOT NULL ,  
  `name` VARCHAR(50) NOT NULL ,  `gender` VARCHAR(10) NOT NULL ,  
  `dateOfBirth` VARCHAR(10) NOT NULL ,  `registrationDate` VARCHAR(10) NOT NULL ,  
  `creditCardNumber` VARCHAR(20) NOT NULL ,  
  `expirationDate` VARCHAR(10) NOT NULL ,   
  `cvvNumber` VARCHAR(3) NOT NULL 
  ) ENGINE = InnoDB;

ALTER TABLE `customer` ADD PRIMARY KEY( `customerID`);

CREATE TABLE `restmanapp`.`booking` ( 
 `customerID` INT(10) NOT NULL ,  
 `bookingID` INT(10) NOT NULL ,  
 `bookingDate` VARCHAR(10) NOT NULL 
 ) ENGINE = InnoDB;

ALTER TABLE `booking` ADD CONSTRAINT `fk_customerID_customer` FOREIGN KEY (`customerID`) REFERENCES `customer`(`customerID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `booking` ADD PRIMARY KEY( `bookingID`);

CREATE TABLE `restmanapp`.`inrestorder` ( 
  `bookingID` INT(10) NOT NULL ,  
  `orderID` INT(10) NOT NULL ,  
  `orderDate` VARCHAR(10) NOT NULL ,  
  `orderDetails` VARCHAR(50) NOT NULL ,  
  `extraNotes` VARCHAR(50) NOT NULL ,  
  `tableNumber` INT(5) NOT NULL 
 ) ENGINE = InnoDB;

ALTER TABLE `inrestorder` ADD CONSTRAINT `fk_bookingID_booking` FOREIGN KEY (`bookingID`) REFERENCES `booking`(`bookingID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

INSERT INTO `customer` (`customerID`, `name`, `gender`, `dateOfBirth`, `registrationDate`, `creditCardNumber`, `expirationDate`, `cvvNumber`) VALUES ('1001', 'Syd Barrett', 'M', '07/07/1946', '11/01/2021', '1234 1234 1234 1234', '04/2020', '420');
INSERT INTO `customer` (`customerID`, `name`, `gender`, `dateOfBirth`, `registrationDate`, `creditCardNumber`, `expirationDate`, `cvvNumber`) VALUES ('1002', 'Roger Waters', 'M', '06/09/1943', '12/01/2021', '3456 3456 3456 3456', '04/2020', '420');
INSERT INTO `customer` (`customerID`, `name`, `gender`, `dateOfBirth`, `registrationDate`, `creditCardNumber`, `expirationDate`, `cvvNumber`) VALUES ('1003', 'David Gilmour', 'M', '06/03/1946', '13/01/2021', '4567 4567 4567 4567', '04/2020', '420');


INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1001', '1', '11/01/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1001', '2', '11/02/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1001', '3', '11/03/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1002', '4', '12/01/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1002', '5', '12/02/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1002', '6', '12/03/2022');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1003', '7', '13/03/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1003', '8', '13/02/2021');
INSERT INTO `booking` (`customerID`, `bookingID`, `bookingDate`) VALUES ('1003', '9', '13/03/2021');


INSERT INTO `inrestorder` (`bookingID`, `orderID`, `orderDate`, `orderDetails`, `extraNotes`, `tableNumber`) VALUES ('1', '1', '11/04/2021', 'With Salt', 'I\'m hungry', '1');
INSERT INTO `inrestorder` (`bookingID`, `orderID`, `orderDate`, `orderDetails`, `extraNotes`, `tableNumber`) VALUES ('4', '4', '12/04/2021', 'Without Salt', 'I\'m hungry', '4');
INSERT INTO `inrestorder` (`bookingID`, `orderID`, `orderDate`, `orderDetails`, `extraNotes`, `tableNumber`) VALUES ('7', '7', '13/04/2021', 'Without Hot', 'I\'m hungry', '7');


