DROP TABLE IF EXISTS Sniffers;

CREATE TABLE Sniffers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  dog_name VARCHAR(250) NOT NULL
);

INSERT INTO billionaires (first_name, last_name, dog_name) VALUES
  ('Max', 'Mustermann', 'Raz', 'Rex)',
  ('Bill', 'Gates', 'Doge', 'ScoobyDoo'),
  ('Folrunsho', 'Alakija', 'Hugo', 'Tessi');