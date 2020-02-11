--Create

DROP TABLE IF EXISTS student; 
CREATE TABLE student ( id INT,
				       sn INT comment '学号',
					   name VARCHAR(20) comment '姓名',
					   qq_mail VARCHAR(20) comment 'QQ邮箱'
					  );
					  
INSERT INTO student VALUES (1, 100, '哪吒', NULL);
INSERT INTO student VALUES (2, 101, '孙悟空', '11111');
INSERT INTO student (id, sn, name) VALUES 
							(102, 20001, '曹孟德'), 
							(103, 20002, '孙仲谋');
CREATE DATABASE Example;
USE Example;
CREATE TABLE exam_result ( id INT, name VARCHAR(20), 
							chinese INT,
							math INT,
							english INT 
							);
-- 插入测试数据 
INSERT INTO exam_result (id,name, chinese, math, english) VALUES 
 (1,'A', 67, 98, 56),
 (2,'B', 87, 78, 77),
 (3,'C', 88, 98, 90), 
 (4,'D', 82, 84, 67), 
 (5,'E', 55, 85, 45),
 (6,'F', 70, 73, 78),
 (7,'G', 75, 65, 30);
 
 -- 表达式不包含字段 
 SELECT id, name, 10 FROM exam_result; 
 -- 表达式包含一个字段 
 SELECT id, name, english + 10 FROM exam_result; 
 -- 表达式包含多个字段
 SELECT id, name, chinese + math + english FROM exam_result;
 
 SELECT id, name, chinese + math + english AS 总分 FROM exam_result;
 
 SELECT DISTINCT math FROM exam_result;
 
 SELECT name, chinese + english + math FROM exam_result ORDER BY chinese + english + math DESC;
 SELECT name, chinese + english + math total FROM exam_result ORDER BY total DESC;
 
 -- 将A的数学成绩变更为 80 分 
 UPDATE exam_result SET math = 80 WHERE name = 'A';
 
 -- 将总成绩倒数前三的 3 位同学的数学成绩加上 30 分 
 UPDATE exam_result SET math = math + 30 ORDER BY chinese + math + english LIMIT 3; 
 
 -- 将所有同学的语文成绩更新为原来的 2 倍
 UPDATE exam_result SET chinese = chinese * 2;
 
 -- 删除A同学的考试成绩 
 DELETE FROM exam_result WHERE name = 'A'; 
 -- 删除整张表数据
  DELETE FROM exam_result;
  
  -- 单行插入 
  INSERT INTO 表(字段1, ..., 字段N) VALUES (value1, ..., value N);
  -- 多行插入 
  INSERT INTO 表(字段1, ..., 字段N) VALUES (value1, ...), (value2, ...), (value3, ...);
  
  -- 全列查询 
  SELECT * FROM 表 
  -- 指定列查询 
  SELECT 字段1,字段2... FROM 表 
  -- 查询表达式字段 
  SELECT 字段1+100,字段2+字段3 FROM 表 
  -- 别名 
  SELECT 字段1 别名1, 字段2 别名2 FROM 表 
  -- 去重DISTINCT 
  SELECT distinct 字段 FROM 表 
  -- 排序
  ORDER BY SELECT * FROM 表 ORDER BY 排序字段 
  -- 条件查询WHERE： 
  -- (1)比较运算符 (2)BETWEEN ... AND ... (3)IN (4)IS NULL (5)LIKE (6)AND (7)OR (8)NOT 
  SELECT * FROM 表 WHERE 条件
  
  DELETE FROM 表 WHERE 条件
 
 