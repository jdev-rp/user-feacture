INSERT INTO USERS (USER_ID, PASSWORD, NICKNAME, NAME, EMAIL, PHONE_NUMBER, ROLE_TYPE, ENABLED, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES
(1, '$2a$10$UXmPV0Ibz/QkxcB6/YIGT./Ty/21ZcBEnDEp8OThU1GCEL/K6s3Z6', '일반사용자닉', '일반이름', 'user@gmail1.com', '01012341234', 'USER', 'Y', CURRENT_TIMESTAMP , 0, CURRENT_TIMESTAMP, 0),
(2, '$2a$10$jkFIfiAkCQH/eaOl0GdGse.8DR8fH610pXrBW0y09dQQaj7mQfgdW', '관리자사용자닉', '관리자이름', 'admin@gmail1.com', '01043214321', 'ADMIN', 'Y', CURRENT_TIMESTAMP , 0, CURRENT_TIMESTAMP, 0);