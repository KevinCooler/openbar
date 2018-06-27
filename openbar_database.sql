--create database openbar;
drop table drink;
drop table purchase_order;

create table purchase_order(
        order_id serial,
        date_time timestamp not null,
        customer_name varchar(100) not null,
        is_complete boolean default false,
        
        constraint pk_order_id primary key (order_id)
);

create table drink(
        drink_id serial,
        order_id int not null,
        name varchar(200) not null,
        quantity int default 1,
        comment varchar (200),
        
        constraint pk_drink_id primary key (drink_id),
        constraint fk_order_id foreign key (order_id) references purchase_order (order_id)
);

insert into purchase_order (date_time, customer_name) values (now(), 'Kevin Cooler');
insert into purchase_order (date_time, customer_name) values (now(), 'Andrew Rizkallah');
insert into purchase_order (date_time, customer_name) values (now(), 'Doug Stauffer');
insert into purchase_order (date_time, customer_name) values (now(), 'Steve Funk');

insert into drink (order_id, name) values (1, 'CBC IPA');
insert into drink (order_id, name, quantity) values (2, 'Bud Light', 4);
insert into drink (order_id, name, comment) values (3, 'Corona', 'bottle with lime');
insert into drink (order_id, name) values (3, 'PBR draft');
insert into drink (order_id, name) values (4, 'whiskey gingerale');
insert into drink (order_id, name, comment) values (4, 'double Jameson', 'neat');
insert into drink (order_id, name, quantity) values (4, 'tequila shot', 2);

update purchase_order set is_complete = true where order_id = 1;

select * from drink full outer join purchase_order po on po.order_id = drink.order_id; 

