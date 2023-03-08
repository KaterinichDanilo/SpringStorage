DROP TABLE IF EXISTS clothes;
DROP TABLE IF EXISTS orders_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE clothes
(
    id bigserial PRIMARY KEY,
    title CHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    size CHAR(50),
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles
(
    id bigserial PRIMARY KEY,
    name CHAR(50)
);

CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    username CHAR(50) NOT NULL,
    password CHAR(250) NOT NULL,
    email CHAR(50) NOT NULL
);

CREATE TABLE users_roles (
                             user_id bigserial NOT NULL,
                             role_id bigserial NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (role_id) REFERENCES roles (id),
                             FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE orders
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status CHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE orders_item
(
    id bigserial PRIMARY KEY,
    order_id bigint NOT NULL,
    clothes_id bigint NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (clothes_id) REFERENCES clothes(id)
);

INSERT INTO clothes (title, price, size) VALUES ('coat', 10, 'M'), ('jeans', 20, 'L'), ('shirt', 40, 'XXL'),
                                                ('suit', 80, 'XL'), ('tie', 8.4, null), ('shawl', 9.5, null),
                                                ('gloves', 7, 'L'), ('cap', 4, 'S'), ('shirt', 35, 'XL'),
                                                ('shirt', 20, 'L'), ('shirt', 18.2, 'S'), ('gloves', 6.1, 'S'),
                                                ('shorts', 32, 'L'), ('shorts', 35.4, 'XL');

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, email) VALUES
                                                  ('user', '$2a$04$X2QbHVV5bUOY8N2522NRXuurFL9uK2dpwpYJxZuz.1tQjP6JjMhD.', 'user@gmail.com'),
                                                  ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i.', 'admin@gmail.com');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (2, 2);
