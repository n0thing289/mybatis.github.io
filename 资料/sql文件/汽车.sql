-- 用到的sql语句
CREATE DATABASE powernode CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
--
CREATE TABLE t_car(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	car_num VARCHAR(32),
	brand VARCHAR(32),
	guide_price DECIMAL(10,2),
	produce_time CHAR(10),
	car_type VARCHAR(32)
);
--
DROP TABLE t_car;
--
INSERT INTO t_car VALUES(NULL,"100","宝马520Li",41.00,"2023-11-12", "燃油车");
INSERT INTO t_car VALUES(NULL, "101", "奔驰E300L",54.00,"2023-11-12","新能源");

--
SELECT * FROM t_car;
