DROP TABLE IF EXISTS Sniffers;

CREATE TABLE Sniffers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  passwort VARCHAR(250) NULL,
  dog_name VARCHAR(250) NOT NULL,
  obj OTHER NULL
);

INSERT INTO Sniffers (first_name, last_name, dog_name) VALUES
  ('Max', 'Mustermann', 'Raz'),
  ('Bill', 'Gates', 'Doge'),
  ('Folrunsho', 'Alakija', 'Hugo');
