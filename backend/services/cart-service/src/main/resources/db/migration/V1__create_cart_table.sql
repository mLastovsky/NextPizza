CREATE TABLE cart (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(50),
    total_amount DECIMAL NOT NULL,
    token VARCHAR(128) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);
