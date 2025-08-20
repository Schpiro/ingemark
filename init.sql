CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    code CHAR(10) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    price_eur NUMERIC(10,2) NOT NULL CHECK (price_eur >= 0),
    price_usd NUMERIC(10,2) NOT NULL CHECK (price_usd >= 0),
    is_available BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE conversion_rate (
    id SERIAL PRIMARY KEY,
    entry_date DATE NOT NULL,
    average_rate DECIMAL(12,6) NOT NULL
);

INSERT INTO conversion_rate (entry_date, average_rate) VALUES
('2025-08-20', 1.168200),
('2025-08-19', 1.168200),
('2025-08-18', 0.167300);

INSERT INTO product (code, name, price_eur, price_usd, is_available) VALUES
('0000000001', 'Apple', 0.99, 1.09, TRUE),
('0000000002', 'Banana', 1.29, 1.42, TRUE),
('0000000003', 'Orange', 1.59, 1.75, FALSE);