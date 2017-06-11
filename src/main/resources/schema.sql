CREATE TABLE 'users' (
    'id' INT AUTOINCREMENT,
    'email' VARCHAR(255),
    'username' VARCHAR(255),
    'password' VARCHAR(255),
    PRIMARY KEY ('id'),
    UNIQUE KEY ('username'))
)