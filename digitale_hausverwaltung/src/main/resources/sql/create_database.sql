USE mysql;

DROP DATABASE IF EXISTS db;
CREATE DATABASE db CHARACTER SET utf8;

CREATE USER 'running_gag'@'localhost';
GRANT ALL PRIVILEGES ON db.* TO 'running_gag'@'localhost';
FLUSH PRIVILEGES;