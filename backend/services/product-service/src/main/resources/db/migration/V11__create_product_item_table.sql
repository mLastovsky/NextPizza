CREATE TABLE product_item (
    id BIGSERIAL PRIMARY KEY,
    size BIGINT,
    price DECIMAL(5,2) NOT NULL,
    dough_type_id BIGINT,
    product_id BIGINT NOT NULL
);
