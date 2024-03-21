CREATE TABLE FXDeal(
        id UUID NOT NULL PRIMARY KEY,
        deal_unique_id VARCHAR(255) NOT NULL,
        from_currency_iso_code VARCHAR(3) NOT NULL,
        to_currency_iso_code VARCHAR(3) NOT NULL,
        deal_timestamp TIMESTAMP NOT NULL,
        deal_amount DECIMAL(18, 2) NOT NULL
)