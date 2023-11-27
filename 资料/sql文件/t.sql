CREATE TABLE t_class (
	cid INT NOT NULL,
	cname VARCHAR(32)
);
CREATE TABLE t_stu (
	sid INT NOT NULL,
	sname VARCHAR(32),
	cid INT  
);
SELECT * FROM t_class;
SELECT * FROM t_stu;

DROP TABLE t_stu;

INSERT INTO t_class VALUES(301, "高三一班");
INSERT INTO t_class VALUES(302, "高三二班");

INSERT INTO t_stu VALUES(1, "张三",301);
INSERT INTO t_stu VALUES(2, "李四",301);
INSERT INTO t_stu VALUES(3, "王五",301);
INSERT INTO t_stu VALUES(4, "jack",302);
INSERT INTO t_stu VALUES(5, "mary",302);