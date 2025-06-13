CREATE TABLE product_ingredient (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    CONSTRAINT fk_product_ingredient_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_ingredient_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id) ON DELETE CASCADE
);
