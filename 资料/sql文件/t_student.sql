CREATE TABLE t_student(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(32),
	age INT,
	height DOUBLE,
	birth DATE, 
	sex CHAR(1));

DROP TABLE t_student;

INSERT INTO t_student VALUES(NULL, "张三", 20, 1.80, "1980-1-1", "男")
INSERT INTO t_student VALUES(NULL, "李四", 18, 1.60, "1980-1-2", "女");

SELECT * FROM t_student;