drop table if exists customerDetails;

create table customerDetails
(
    id serial primary key,
    customerId int not null,
    email varchar(20) not null,
    phone varchar(12) not null,
    address varchar(40) not null,
    constraint fk_customer
        foreign key (customerId) references customer(id)
);


insert into customerDetails(email, customerId, phone, address)
VALUES('email@email.com',1, '123456789','1234 street1 nc, usa');

insert into customerDetails(email, customerId,phone, address)
VALUES('test@yahoo.com',2, '9986759000','62526 will street MI, USA');

insert into customerDetails(email, customerId,phone, address)
VALUES('email@yahoo.com',3, 'some phone','62526 will street MI, USA');


insert into customerDetails(email, customerId,phone, address)
VALUES('test@email.com',4, '6262627','62526 will street MI, USA');

insert into customerDetails(email, customerId,phone, address)
VALUES('test@gmail.com',11, '827282','62526 will street MI, USA');

insert into customerDetails(email, customerId,phone, address)
VALUES('test@hotmail.com',5, '8989000','62526 will street MI, USA');

