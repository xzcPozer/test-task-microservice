CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    sur_name VARCHAR(255),
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE subscriptions
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users_subscriptions
(
    user_id BIGINT NOT NULL,
    sub_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, sub_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (sub_id) REFERENCES subscriptions (id) ON DELETE CASCADE
);