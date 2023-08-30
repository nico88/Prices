CREATE TABLE IF NOT EXISTS brand (
  brand_id BIGINT PRIMARY KEY,
  brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS prices (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  brand_id BIGINT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  price_list INT NOT NULL,
  product_id BIGINT NOT NULL,
  priority INT NOT NULL,
  price DOUBLE NOT NULL,
  curr VARCHAR(10) NOT NULL,
  FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
);

CREATE INDEX idx_brand_id ON brand (brand_id);
CREATE INDEX idx_product_id ON prices (product_id);
CREATE INDEX idx_start_date ON prices (start_date);
CREATE INDEX idx_end_date ON prices (end_date);