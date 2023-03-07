CREATE TABLE users
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    mobile     text not null,
    email      text not null,
    password   text not null
);

CREATE TABLE product_categories
(
    id   bigserial primary key,
    name text not null
);

CREATE TABLE products
(
    id          bigserial primary key,
    name        text                                               not null,
    description text default null,
    price       double precision                                   not null,
    category_id bigint references product_categories (id) not null
);

CREATE TABLE purchases
(
    id              bigserial primary key,
    user_id         bigint references users (id)       not null,
    product_id      bigint references products (id) not null,
    purchase_date   timestamp                               not null,
    purchase_amount double precision                        not null
);