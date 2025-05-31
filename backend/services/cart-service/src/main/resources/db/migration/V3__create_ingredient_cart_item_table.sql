CREATE TABLE ingredient_cart_item (
    id BIGSERIAL PRIMARY KEY,
    ingredient_id BIGINT NOT NULL,
    cart_item_id BIGINT NOT NULL,
    CONSTRAINT fk_cart_item_id FOREIGN KEY (cart_item_id) REFERENCES cart_item (id) ON DELETE CASCADE
);
