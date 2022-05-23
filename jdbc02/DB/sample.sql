create
database tzjdbc;

use
tzjdbc;

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



create table orders
(
    id        varchar(32)   not null,
    goods_id  int           not null,
    order_num int default 0 not null,
    primary key (id)
);



create procedure p8(in n int, out total int)
begin
	declare
num int default 0;
	set
total := 0;
	while
num <= n do
		set num := num + 1;
		set
total := total + num;
end while;
end;


