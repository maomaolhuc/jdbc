CREATE TABLE `student`
(
    `sid`     int(0) NULL DEFAULT NULL,
    `sname`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `pwd`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `sex`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


INSERT INTO `student`
VALUES (1010, '刘德华', '123', '男', '中国香港');


create table goods
(
    id         int primary key auto_increment,
    goods_name varchar(32)   not null,
    goods_num  int default 0 not null,
    remark     varchar(256) null
);

insert into goods
values (null, '苹果', 108, '产地山东'),
       (null, '桔子', 270, '产地湖南'),
       (null, '香蕉', 180, '产地海南'),
       (null, '榴梿', 96, '产地广东');