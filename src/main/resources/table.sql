CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         category_id INT,
                         FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          username VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          role VARCHAR(10) NOT NULL
);