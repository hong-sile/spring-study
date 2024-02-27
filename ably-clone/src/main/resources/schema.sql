-- seller 테이블
CREATE TABLE IF NOT EXISTS seller
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    name              VARCHAR(255),
    profile_image_url VARCHAR(255)
);

-- item 테이블
CREATE TABLE IF NOT EXISTS item
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    discount_percentage INTEGER NOT NULL,
    like_count          BIGINT,
    origin_price        BIGINT,
    seller_id           BIGINT,
    item_name           VARCHAR(255),
    FOREIGN KEY (seller_id) REFERENCES seller (id)
);

-- image_url 테이블
CREATE TABLE IF NOT EXISTS image_url
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    item_id BIGINT,
    url     VARCHAR(255),
    FOREIGN KEY (item_id) REFERENCES item (id)
);
