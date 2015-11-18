use springmvc;

create table t_dept (
    id int primary key auto_increment,
    name varchar(200)
);


create table t_employee (
    id int primary key auto_increment,
    age int ,
    name varchar(200),
    dept_id int references t_dept(id)
);



CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
);
