-- 创建库
create database if not exists my_project;
-- 切换库
use my_project;
-- 用户表
create table user
(
    id           bigint(20) auto_increment comment 'id' primary key,
    username     varchar(256)                           null comment '用户昵称',
    user_account  varchar(256)                           not null comment '账号',
    role          varchar(256)                                not null comment 'user / admin',
    user_password varchar(512)                           not null comment '密码',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin comment '用户';

-- 商品表
create table product
(
    id                  bigint auto_increment comment 'id' primary key comment '商品id',
    `name`              varchar(256)                           not null comment '商品名称',
    description         varchar(256)                           null comment '商品描述',
    price               DECIMAL(8,2)                           not null comment '商品单价',
    url                 varchar(256)                            not null comment '商品图片url',
    inventory           bigint(20)                                   NOT NULL comment '商品库存',
    create_time         datetime      default CURRENT_TIMESTAMP  NOT NULL comment '创建时间',
    update_time         datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP NOT NULL comment '更新时间',
    is_deleted         int(11)                                NOT NULL DEFAULT '0' COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin comment '商品';

-- 订单表
create table `order`(
    id           bigint auto_increment comment 'id' primary key COMMENT '订单id',
    `number`     varchar(50)   DEFAULT NULL COMMENT '订单号',
    product_id   varchar(512)  DEFAULT NULL COMMENT '商品id',
    order_time    datetime     default CURRENT_TIMESTAMP not null comment '下单时间',
    user_id      bigint(20)    NOT NULL COMMENT '下单用户id',
    product_number bigint(20)  NOT NULL COMMENT '购买数量',
    update_time   datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    status       tinyint       NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
    `method`     tinyint       NOT NULL COMMENT '支付方式 1微信，2支付宝',
    order_price  DECIMAL(8,2)   not null comment '订单总价'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin comment '订单';

-- 购物车
CREATE TABLE shopping_cart (
     id    bigint(20) NOT NULL primary key COMMENT '主键',
     user_id bigint(20) NOT NULL COMMENT '用户id',
     product_id bigint  DEFAULT NULL COMMENT '商品id',
     url   varchar(256)  not null comment '商品图片url',
     name varchar(50)  DEFAULT NULL COMMENT '商品名称',
     `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
     price DECIMAL(8,2) not null comment '价格'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='购物车';