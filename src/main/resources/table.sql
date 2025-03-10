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
    category_id  INT                NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE SET NULL
);

CREATE TABLE user_cust
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(10)         NOT NULL,
    wallet   DECIMAL(10, 2)      NOT NULL DEFAULT 0 CHECK (wallet >= 0)

);

CREATE TABLE history
(
    id            SERIAL PRIMARY KEY,
    user_id       INT          NOT NULL,
    category      VARCHAR(50)  NOT NULL,
    name          VARCHAR(255) NOT NULL,
    fabricator    VARCHAR(50)  NOT NULL,
    product_code  VARCHAR(29)  NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_cust (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS user_product
(
    user_id    INT NOT NULL,
    product_id INT,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES user_cust (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);




