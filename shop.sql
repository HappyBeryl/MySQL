-- 创建数据库 
create database shop;

-- 选择数据库 
use shop;

-- 创建数据库表 
-- 商品 
create table if not exists goods 
( 
	goods_id int comment '商品编号', 
	goods_name varchar(32) comment '商品名称', 
	unitprice int comment '单价，单位分', 
	category varchar(12) comment '商品分类', 
	provider varchar(64) comment '供应商名称' 
);

-- 客户
 create table if not exists customer 
 ( 
	customer_id int comment '客户编号', 
	name varchar(32) comment '客户姓名',
	address varchar(256) comment '客户地址',
	email varchar(64) comment '电子邮箱',
	sex bit comment '性别', 
	card_id varchar(18) comment '身份证'
);

-- 购买
 create table if not exists purchase 
 ( 
	order_id int comment '订单号',
	customer_id int comment '客户编号',
	goods_id int comment '商品编号', 
	nums int comment '购买数量' 
);