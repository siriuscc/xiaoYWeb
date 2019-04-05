-- 数据库连接帐号：xiaoydba
-- 数据库连接密码：xiaoy1314

mybatis-generator:generate



/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/31 11:17:40                          */
/*==============================================================*/

-- 数据库
create database xiaoy charset=utf8;
grant all privileges on xiaoy.* to 'xiaoydba'@'%' identified by 'xiaoy1314';
grant all privileges on dtxz.* to 'xiaoydba'@'localhost' identified by 'xiaoy1314';

use xiaoy;

create table xy_user(

	user_id int not null auto_increment,
	username varchar(64) not null,
	passwd varchar(64) not null,
	email varchar(128) default '',
	phone varchar(20) default '',
	primary key (user_id)
)ENGINE=InnoDB;

/* 
insert into xy_user values(null,'sirius','12345','siriusing.cc@qq.com','13560482761');
insert into xy_user values(null,'sirius',md5('12345'),'sirius.cc@qq.com','13560482761'); */

-- 任务

create table xy_task(

	task_id int not null auto_increment,
	title varchar(64)not null,
	content text,
	start_time timestamp default now(),
	end_time timestamp default now(),
	delay_min	int,
	img_url varchar(255),
	user_id int not null,
	primary key (task_id),
	foreign key (user_id) references xy_user(user_id) 
)ENGINE=InnoDB;


-- 足迹
create table xy_note(
	note_id int not null auto_increment,
	content text ,
	start_time timestamp default now(),
	like_count int default 0,
	imgs json,
	user_id int not null,	
	primary key (note_id),
	foreign key (user_id) references xy_user(user_id) 
)ENGINE=InnoDB;




SELECT * FROM xy_task WHERE TO_DAYS(start_time) = TO_DAYS(NOW());  


-- 后台需要的数据表

create table xy_role(
	role_id int not null auto_increment,
	name varchar(64),
	primary key (role_id)
);



create table xy_staff(
	staff_id int not null auto_increment,
	name varchar(64),
	passwd varchar(64),
	role_id int,
	email varchar(128),

	primary key(staff_id),
	foreign key (role_id) references xy_role(role_id) 
);




create table xy_auth(
	auth_id int not null auto_increment,
	name varchar(64),
	filter varchar(64),
	primary key (auth_id)
);


create table xy_role_auth(
	role_id int not null,
	auth_id int not null,
	primary key(role_id,auth_id),
	foreign key (role_id) references xy_role(role_id),
	foreign key (auth_id) references xy_auth(auth_id)
);

insert into xy_role (role_id,name) values(null,'超级管理员');

insert into xy_staff(staff_id,name,passwd,email,role_id) values(null,'sirius','12345','sirising.cc@qq.com',1);



create table xy_location(
	loc_id int not null auto_increment,
	latitude float not null,
	longitude float not null,
	loc_time timestamp default now(),
	user_id int not null,
	primary key(loc_id),
	foreign key(user_id) references xy_user(user_id)
);




select  a.loc_id,a.latitude,a.longitude, a.loc_time,a.user_id from xy_location a
	where a.loc_id in(
		select max(loc_id)  from xy_location group by user_id
	);




select u.user_id,u.username,u.passwd,u.email,u.phone,
	c.loc_id,c.latitude,c.longitude,c.loc_time
	from xy_user u left join xy_location c
	on u.user_id =c.user_id and c.loc_id in(
		select max(loc_id)  from xy_location group by user_id
	);




select * from xy_location
	where loc_id in(
		select max(loc_id)  from xy_location group by user_id
	);














