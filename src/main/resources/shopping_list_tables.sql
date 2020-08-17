CREATE SCHEMA shoppinglist;

CREATE TABLE shopping_cart
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id               BIGINT AUTO_INCREMENT,
    name             VARCHAR(30)    NOT NULL,
    product_category VARCHAR(40)    NOT NULL,
    price            DECIMAL(30, 2) NOT NULL,
    discount         DECIMAL(30, 1),
    description      VARCHAR(250),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE products_shopping_cart (
    product_id BIGINT,
    shopping_cart_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id)
);