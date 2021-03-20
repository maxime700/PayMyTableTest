//Ce fichier est utilisé au démarrage de l'application pour charger la base de donnée en mémoire
DROP TABLE IF EXISTS SHORT_URL;

CREATE TABLE SHORT_URL
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(250) NOT NULL
);

INSERT INTO SHORT_URL (url)
VALUES ('http://test1.com'),
       ('http://test2.com'),
       ('http://test3.com');
