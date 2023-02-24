DROP TABLE IF EXISTS clothes;
DROP TABLE IF EXISTS orders_item;
DROP TABLE IF EXISTS orders;

CREATE TABLE clothes
(
    id bigserial PRIMARY KEY,
    title CHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    size CHAR(50),
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders
(
    id bigserial PRIMARY KEY,
    username CHAR(250) NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status CHAR(50) NOT NULL
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
                                                ('gloves', 7, 'L'), ('cap', 4, 'S');
