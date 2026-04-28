CREATE TABLE IF NOT EXISTS user_data (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BIT(1) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_data_username (username)
);

CREATE TABLE IF NOT EXISTS user_role (
    user_id BIGINT NOT NULL,
    roles VARCHAR(255),
    CONSTRAINT fk_user_role_user_data FOREIGN KEY (user_id) REFERENCES user_data (id) ON DELETE CASCADE
);

INSERT INTO user_data (username, password, active)
SELECT 'admin', 'admin', b'1'
WHERE NOT EXISTS (
    SELECT 1 FROM user_data WHERE username = 'admin'
);

INSERT INTO user_role (user_id, roles)
SELECT u.id, 'DIRECTOR'
FROM user_data u
WHERE u.username = 'admin'
  AND NOT EXISTS (
      SELECT 1 FROM user_role ur WHERE ur.user_id = u.id AND ur.roles = 'DIRECTOR'
  );
