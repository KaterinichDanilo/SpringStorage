DROP TABLE IF EXISTS clothes;

CREATE TABLE clothes
(
    id bigserial PRIMARY KEY,
    title CHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    size CHAR(50),
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO clothes (title, price, size) VALUES ('coat', 10, 'M'), ('jeans', 20, 'L'), ('shirt', 40, 'XXL'),
                                                ('suit', 80, 'XL'), ('tie', 8.4, null), ('shawl', 9.5, null),
                                                ('gloves', 7, 'L'), ('cap', 4, 'S');
