create table if not exists books
(
    id        serial primary key,
    book_name varchar(20) not null,
    author    varchar(40) not null,
    price     numeric(8, 2),
    unique (book_name, author)
);


insert into books (book_name, author, price)
values ('enterprise java ', 'Tenanebom', 30.45);

insert into books (book_name, author, price)
values ('pointers with c ', 'Kanetkar', 5.50);

insert into books (book_name, author, price)
values ('microservices', 'lourben', 100.34);

