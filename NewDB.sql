CREATE DATABASE testDB;
USE testDB;
DROP TABLE testtable;
CREATE TABLE testtable(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(20),
age INT,
favouriteColor VARCHAR(20)
);

DELIMITER //
CREATE PROCEDURE insert_info(IN my_name VARCHAR(20),IN my_age INT,IN my_favouriteColor VARCHAR(20))
BEGIN
INSERT INTO testtable(name, age, favouriteColor) VALUES (my_name, my_age, my_favouriteColor);
END;
//

SELECT * FROM testtable;