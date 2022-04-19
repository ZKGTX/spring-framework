CREATE TABLE products (id serial, title varchar(100), price int);

INSERT INTO products (title, price) VALUES
 ('Bread', 40), ('Milk', 80), ('Cheese', 100), ('Garlic', 120), ('Potatoes', 50), ('Vodka', 150),
 ('Beef', 170), ('Tomatoes', 90), ('Oranges', 90), ('Lemonade', 35), ('Chocolate', 120),
 ('Chewing Gum', 25);

CREATE TABLE users (
    id serial,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id serial,
    name VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),

    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    constraint FK_ROLE_ID FOREIGN KEY (role_id) REFERENCES roles(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, name, email) VALUES
('admin', '$2a$12$hSSxBbcJ31vOpDqA/3qjzeLjVZG65zlmN.IQxZvUpV0eh5bSxu22C', 'Sergey Semenov', 'zerokikr@gmail.com');

INSERT INTO users_roles (user_id, role_id) VALUES (1,1), (1,2);

