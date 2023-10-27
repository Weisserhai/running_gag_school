USE mysql;

DROP DATABASE IF EXISTS db;
CREATE DATABASE db CHARACTER SET utf8;

CREATE USER 'running_gag'@'localhost';
GRANT ALL PRIVILEGES ON deine_datenbank.* TO 'dein_benutzername'@'localhost';
FLUSH PRIVILEGES;