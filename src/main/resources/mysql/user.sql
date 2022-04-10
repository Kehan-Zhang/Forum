CREATE DATABASE IF NOT EXISTS forum;

ALTER DATABASE forum
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON forum.* TO 'forum'@'%' IDENTIFIED BY 'forum';