ALTER TABLE seller ALTER COLUMN id RESTART WITH 6;
INSERT INTO seller (id, name, profile_image_url)
VALUES (1, 'clug_1', 'clug_1_profile'),
       (2, 'clug_2', 'clug_2_profile'),
       (3, 'clug_3', 'clug_3_profile'),
       (4, 'clug_4', 'clug_4_profile'),
       (5, 'clug_5', 'clug_5_profile');

ALTER TABLE item ALTER COLUMN id RESTART WITH 1;
INSERT INTO item (discount_percentage, like_count, origin_price, seller_id, item_name)
VALUES (29, 10, 29900, 1, '나시 티셔츠'),
       (15, 5, 39900, 2, '청바지'),
       (10, 8, 19900, 3, '캐주얼 셔츠'),
       (20, 15, 49900, 4, '스니커즈'),
       (25, 12, 79900, 5, '가죽 지갑'),
       (12, 7, 24900, 1, '스마트폰 케이스'),
       (18, 9, 34900, 2, '스포츠 슈즈'),
       (30, 20, 59900, 3, '모자'),
       (22, 14, 44900, 4, '후드 티셔츠'),
       (28, 18, 89900, 5, '컴퓨터 가방'),
       (17, 11, 27900, 1, '목걸이'),
       (21, 13, 32900, 2, '반지'),
       (23, 16, 69900, 3, '캠핑용 의자'),
       (27, 19, 52900, 4, '커피 메이커'),
       (14, 6, 17900, 5, '선글라스'),
       (26, 17, 76900, 1, '헤드폰'),
       (19, 8, 41900, 2, '블루투스 스피커'),
       (24, 15, 89900, 3, '디지털 카메라'),
       (16, 10, 29900, 4, '여행용 가방'),
       (13, 7, 25900, 5, '비치 타올');

ALTER TABLE image_url ALTER COLUMN id RESTART WITH 21;
INSERT INTO image_url (item_id, url)
VALUES (1, 'image'),
       (2, 'image'),
       (3, 'image'),
       (4, 'image'),
       (5, 'image'),
       (6, 'image'),
       (7, 'image'),
       (8, 'image'),
       (9, 'image'),
       (10, 'image'),
       (11, 'image'),
       (12, 'image'),
       (13, 'image'),
       (14, 'image'),
       (15, 'image'),
       (16, 'image'),
       (17, 'image'),
       (18, 'image'),
       (19, 'image'),
       (20, 'image');