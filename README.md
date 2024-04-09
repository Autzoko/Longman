# Longman

## Data Source
```mysql
create database Longman;
use Longman;
create table lm_warehouse_manager(
    manager_id varchar(32) primary key,
    manager_name varchar(32) not null,
    manager_pwd varchar(64) not null,
    manager_level enum('main', 'vice', 'common') not null,
    last_login_datetime datetime not null,
    manager_email varchar(64));
create table user(
    user_id varchar(128) primary key ,
    user_name varchar(128) not null ,
    user_pwd varchar(128) not null ,
    user_phone varchar(20));
```