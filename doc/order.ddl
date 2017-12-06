DROP TABLE IF EXISTS jpay_order_shipping;
DROP TABLE IF EXISTS jpay_user;
DROP TABLE IF EXISTS jpay_record;
DROP TABLE IF EXISTS jpay_order_item;
DROP TABLE IF EXISTS jpay_order;

/**********************************/
/* Table Name: 订单表 */
/**********************************/
CREATE TABLE jpay_order(
		id                            		BIGINT(20)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
		create_time                   		DATETIME		 NULL  COMMENT '创建时间',
		update_time                   		DATETIME		 NULL  COMMENT '更新时间',
		status                        		SMALLINT(3)		 NULL  COMMENT '数据状态',
		plat                          		SMALLINT(3)		 NULL  COMMENT '平台',
		order_no                      		VARCHAR(64)		 NOT NULL COMMENT '订单编号',
		payment                       		DECIMAL(10,2)		 NULL  COMMENT '实付金额',
		payment_type                  		SMALLINT(2)		 NULL  COMMENT '支付类型',
		payment_channel               		SMALLINT(2)		 NULL  COMMENT '支付渠道',
		post_fee                      		DECIMAL(8,2)		 NULL  COMMENT '邮费',
		pay_status                    		SMALLINT(3)		 NULL  COMMENT '付款状态',
		end_time                      		DATETIME		 NULL  COMMENT '交易完成时间',
		close_time                    		DATETIME		 NULL  COMMENT '交易关闭时间',
		shipping_name                 		VARCHAR(50)		 NULL  COMMENT '物流名称',
		shipping_code                 		VARCHAR(64)		 NULL  COMMENT '物流单号',
		user_id                       		BIGINT(20)		 NULL  COMMENT '用户id',
		buyer_message                 		VARCHAR(200)		 NULL  COMMENT '买家留言',
		buyer_nick                    		VARCHAR(50)		 NULL  COMMENT '买家昵称',
		buyer_rate                    		SMALLINT(2)		 NULL  COMMENT '买家是否评价',
		out_user_no                   		VARCHAR(64)		 NULL  COMMENT '外部用户标识'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='订单表';

/**********************************/
/* Table Name: 订单详情表 */
/**********************************/
CREATE TABLE jpay_order_item(
		id                            		BIGINT(20)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
		create_time                   		DATETIME		 NULL  COMMENT '创建时间',
		update_time                   		DATETIME		 NULL  COMMENT '更新时间',
		status                        		SMALLINT(3)		 NULL  COMMENT '数据状态',
		item_no                       		VARCHAR(64)		 NULL  COMMENT '商品编号',
		order_id                      		BIGINT(20)		 NULL  COMMENT '订单id',
		order_no                      		VARCHAR(64)		 NULL  COMMENT '订单号',
		num                           		INT(10)		 NULL  COMMENT '商品购买数量',
		title                         		VARCHAR(200)		 NULL  COMMENT '商品标题',
		price                         		DECIMAL(10,2)		 NULL  COMMENT '商品单价',
		total_fee                     		DECIMAL(10,2)		 NULL  COMMENT '商品总金额',
		pic_url                       		VARCHAR(225)		 NULL  COMMENT '商品图片地址',
		detail                        		VARCHAR(512)		 NULL  COMMENT '商品详情'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='订单详情表';

/**********************************/
/* Table Name: 支付流水表 */
/**********************************/
CREATE TABLE jpay_record(
		id                            		BIGINT(20)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
		create_time                   		DATETIME		 NULL  COMMENT '创建时间',
		update_time                   		DATETIME		 NULL  COMMENT '更新时间',
		status                        		SMALLINT(3)		 NULL  COMMENT '数据状态',
		order_no                      		VARCHAR(64)		 NULL  COMMENT '订单编号',
		item_no                       		VARCHAR(64)		 NULL  COMMENT '商品编号',
		total_amount                  		DECIMAL(10,2)		 NULL  COMMENT '支付总金额',
		trade_no                      		VARCHAR(64)		 NULL  COMMENT '外部交易流水号',
		user_id                       		BIGINT(20)		 NULL  COMMENT '用户id',
		out_user_no                   		VARCHAR(64)		 NULL  COMMENT '外部用户标识',
		payment_channel               		SMALLINT(2)		 NULL  COMMENT '支付渠道平台',
		plat                          		SMALLINT(3)		 DEFAULT 0		 NULL  COMMENT '平台'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='支付流水表';

/**********************************/
/* Table Name: 用户表 */
/**********************************/
CREATE TABLE jpay_user(
		id                            		BIGINT(20)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
		create_time                   		DATETIME		 NULL  COMMENT '创建时间',
		update_time                   		DATETIME		 NULL  COMMENT '更新时间',
		status                        		SMALLINT(3)		 NULL  COMMENT '数据状态',
		nickname                      		VARCHAR(50)		 NULL  COMMENT '用户昵称',
		sex                           		SMALLINT(2)		 NULL  COMMENT '性别'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='用户表';

/**********************************/
/* Table Name: 订单配送表 */
/**********************************/
CREATE TABLE jpay_order_shipping(
		id                            		BIGINT(20)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
		create_time                   		DATETIME		 NULL  COMMENT '创建时间',
		update_time                   		DATETIME		 NULL  COMMENT '更新时间',
		status                        		SMALLINT(3)		 NULL  COMMENT '数据状态',
		order_id                      		BIGINT(20)		 NULL  COMMENT '订单id',
		order_no                      		VARCHAR(64)		 NULL  COMMENT '订单号',
		receiver_name                 		VARCHAR(30)		 NULL  COMMENT '收货人姓名',
		receiver_phone                		VARCHAR(20)		 NULL  COMMENT '收货人电话',
		receiver_mobile               		VARCHAR(20)		 NULL  COMMENT '收货人手机号码',
		province                      		VARCHAR(50)		 NULL  COMMENT '省份',
		province_code                 		VARCHAR(20)		 NULL  COMMENT '省代码',
		city                          		VARCHAR(20)		 NULL  COMMENT '城市',
		city_code                     		VARCHAR(20)		 NULL  COMMENT '城市代码',
		address                       		VARCHAR(500)		 NULL  COMMENT '详细地址',
		postcode                      		VARCHAR(10)		 NULL  COMMENT '邮编'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='订单配送表';


CREATE INDEX IDX_jpay_order_1 ON jpay_order (order_no);
CREATE INDEX IDX_jpay_order_2 ON jpay_order (shipping_code);
CREATE INDEX IDX_jpay_order_3 ON jpay_order (status);
CREATE INDEX IDX_jpay_order_4 ON jpay_order (user_id);

CREATE INDEX IDX_jpay_order_item_1 ON jpay_order_item (order_id);
CREATE INDEX IDX_jpay_order_item_2 ON jpay_order_item (order_no);

CREATE INDEX IDX_jpay_record_1 ON jpay_record (order_no);
CREATE INDEX IDX_jpay_record_2 ON jpay_record (item_no);
CREATE INDEX IDX_jpay_record_3 ON jpay_record (trade_no);
CREATE INDEX IDX_jpay_record_4 ON jpay_record (user_id);
CREATE INDEX IDX_jpay_record_5 ON jpay_record (out_user_no);

CREATE INDEX IDX_jpay_order_shipping_1 ON jpay_order_shipping (order_id);
CREATE INDEX IDX_jpay_order_shipping_2 ON jpay_order_shipping (order_no);

