CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE product
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255)       NOT NULL,
    fabricator   VARCHAR(50)        NOT NULL,
    product_code VARCHAR(29) UNIQUE NOT NULL,
    price        DECIMAL(10, 2)     NOT NULL,
    category_id  INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE SET NULL
);

CREATE TABLE customer
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(10)         NOT NULL
);

CREATE TABLE history
(
    id            SERIAL PRIMARY KEY,
    customer_id   INT                NOT NULL,
    category      VARCHAR(50)        NOT NULL,
    name          VARCHAR(255)       NOT NULL,
    fabricator    VARCHAR(50)        NOT NULL,
    product_code  VARCHAR(23) UNIQUE NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);




