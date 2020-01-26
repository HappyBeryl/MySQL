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
							DROP TABLE IF EXISTS exam_result; CREATE TABLE exam_result ( id INT, name VARCHAR(20), 
							chinese DECIMAL(3,1),
							math DECIMAL(3,1),
							english DECIMAL(3,1) 
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