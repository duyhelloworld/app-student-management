-- Active: 1675493335826@@127.0.0.1@3306@
CREATE DATABASE App_Console;
USE App_Console;
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(30),
    full_name VARCHAR(60),
    sex BIT,
    dob DATE,
    address VARCHAR(20),
    number_phone VARCHAR(10)
);

-- CREATE TABLE Product (
--     id INT PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(30),
--     price BIGINT
-- );

INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "duydeptrai", "Pham Duc Duy", 1, "2003-12-23","Ha Nam", "096676451");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "happier", "Hoang Hanh Phuc", 1, "1996-01-03", "Ha Noi", "0364556438");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "noFriends191", "Hoang Thanh Hai", 1, "2001-08-10", "Ha Tinh", "0334234345");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "phobendz", "Nguyen Trung Thanh", 1, "2003-01-04", "Vinh Phuc", "0345569123");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "thuycute", "Le Kim Thuy", 0, "2005-10-26", "Ha Tinh", "0779347906");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "cuccucdangyeu", "Hoang Quoc Thang", 1, "2004-12-04", "Ha Noi", "0975335067");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "fb88_nhacai", "Pham Manh Quan", 1, "1996-02-21", "Hoa Binh", "0988234096");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "alicecuato", "Pham Thi Phuong", 0, "2001-09-30", "Ha Noi", "0955693621");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "alicecuato2", "Pham Thi Phuong", 0, "2001-09-30", "Ha Noi", "0955693621" );
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "youaremylife", "Hoang Hanh Phuc", 1, "2007-10-20", "Hoa Binh", "0569294261");
INSERT INTO `App_Console`.`User` VALUES(DEFAULT, "bff_ga_con", "Le Kim Anh", 0, "2000-03-17", "Vinh Phuc", "0789234016");



INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );
INSERT INTO `App_Console`.`Product` VALUES(DEFAULT, );

