CREATE TABLE suggested_products
(
    id         bigserial not null primary key,
    user_id    bigint    not null references users (id),
    product_id bigint    not null references products (id)
);