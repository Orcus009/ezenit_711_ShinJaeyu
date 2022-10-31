-- CREATE TABLE

use my_rental_car;
CREATE TABLE user (
id varchar(10) primary key,
pw varchar(20) not null,
name varchar(10) not null,
phNum varchar(20) not null,
address varchar(50) not null,
driveCode varchar(30) not null
)

--insert

insert into user values("aaa", "root1111", "Mark", "010-1111-2222", "Dongjak-gu", "02-35-442623-14");