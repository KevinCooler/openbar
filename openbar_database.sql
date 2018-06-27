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
        
        constraint pk_drink_id primary key (drink_id),
        constraint fk_order_id foreign key (order_id) references purchase_order (order_id)
);

