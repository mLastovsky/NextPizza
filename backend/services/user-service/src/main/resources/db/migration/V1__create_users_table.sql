CREATE TABLE users (
    id UUID PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    email VARCHAR(60) UNIQUE NOT NULL,
    password VARCHAR(60),
    role VARCHAR(10) NOT NULL,
    provider VARCHAR(30),
    provider_id VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
