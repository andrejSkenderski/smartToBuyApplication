CREATE TABLE users
(
    user_id    bigserial primary key,
    first_name text,
    last_name  text,
    mobile     text,
    email      text,
    password   text
);

CREATE TABLE categories
(
    category_id bigserial primary key,
    name        text
);

CREATE TABLE products
(
    product_id  bigserial primary key,
    name        text,
    description text,
    price       int,
    category_id bigint references Categories (category_id)
);

CREATE TABLE purchases
(
    purchase_id     bigserial primary key,
    user_id         bigint references users (user_id),
    product_id      bigint references products (product_id),
    purchase_date   timestamp,
    purchase_amount int
);