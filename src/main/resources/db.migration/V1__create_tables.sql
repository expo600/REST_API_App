CREATE TABLE IF NOT EXISTS rest_api_db.users
(
    id        INTEGER      NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS rest_api_db.files
(
    id        INTEGER      NOT NULL AUTO_INCREMENT,
    file_name VARCHAR(100) NOT NULL,
    file_path VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS rest_api_db.events
(
    id      INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    file_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (file_id) REFERENCES files (id)

);