CREATE TABLE cart_items
(
    id      SERIAL PRIMARY KEY,
    user_id BIGINT      NOT NULL,
    status  INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE cart_item_products
(
    id SERIAL PRIMARY KEY,
    cart_item_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    FOREIGN KEY (cart_item_id) REFERENCES cart_items (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
