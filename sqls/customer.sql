
create table if not exists customer
(id serial primary key , name text not null unique , isLoggedIn boolean default false);


insert into customer (name) values ('mike');
insert into customer (name) values ('bob');
insert into customer (name) values ('tom');
insert into customer (name) values ('hanks');
insert into customer (name) values ('lilly');
insert into customer (name) values ('arnold');
insert into customer (name) values ('smith');
insert into customer (name) values ('paul');
insert into customer (name) values ('john');
insert into customer (name) values ('pence');

update customer set isLoggedIn = true
where customer.id in (2,4,6);
