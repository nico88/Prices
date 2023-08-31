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