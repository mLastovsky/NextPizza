CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    image_url VARCHAR(128),
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id)
);
