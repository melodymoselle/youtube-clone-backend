CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  username VARCHAR(45) UNIQUE NOT NULL,
  password VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE videos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL UNIQUE,
  filename VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL
);

INSERT INTO users(email,username,password,enabled)
VALUES ( 'email', 'melmo','word',true);
INSERT INTO users(email,username,password,enabled)
VALUES ( 'email', 'ted','ball',true);

INSERT INTO user_roles (username, role)
VALUES ('melmo', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('melmo', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('ted', 'ROLE_USER');

INSERT INTO videos(title,filename,username)
VALUES ('some cool video', 'somefile', 'melmo');
INSERT INTO videos(title,filename,username)
VALUES ('a cooler video', 'somefile', 'ted');