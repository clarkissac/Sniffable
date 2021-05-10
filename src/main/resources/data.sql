DROP TABLE IF EXISTS Sniffers;

CREATE TABLE Sniffers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  passwort VARCHAR(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  dog_name VARCHAR(250) NOT NULL,
  obj OTHER NULL
);

INSERT INTO Sniffers (username, passwort, first_name, last_name, dog_name, obj) VALUES
  ( 'MAXMUSTER','test','Max', 'Mustermann', 'Raz',null),
