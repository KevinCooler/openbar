--create database openbar;
drop table drink;
drop table category;
drop table type;
drop table brand;
drop table name;
drop table mixer;
drop table purchase_order;
drop table customer;


create table customer(
        email varchar (50),
        credit_card_number int not null,
        name varchar(35) not null,
        
        constraint pk_email primary key (email)
);

create table purchase_order(
        purchase_order_id serial,
        drink_id int not null
        date_time timestamp not null,
        email varchar(100) not null,
        is_complete boolean default false,
        quantity int default 1,
        
        constraint pk_purchase_order_id primary key (purchase_order_id),
        constraint fk_drink_id foreign key (drink_id) references drink (drink_id),
        constraint fk_email foreign key (email) references customer (email)
);

create table category(
        category varchar(35),
        process_seconds int default 30,
        
        constraint pk_category primary key (category)
);

create table type(
        type varchar(35),
        
        constraint pk_type primary key (type)
);

create table brand(
        brand varchar(35),
        
        constraint pk_brand primary key (brand)
);

create table name(
        name varchar(35),
        
        constraint pk_name primary key (name)
);

create table mixer(
        mixer varchar(35),
        
        constraint pk_mixer primary key (mixer)
);

create table drink(
        drink_id serial,
        category varchar(35) not null,
        type varchar(35) not null,
        brand varchar(35) not null,
        name varchar(35) not null,
        mixer varchar(35),
        comment varchar (200),
        
        constraint pk_drink_id primary key (drink_id),
        constraint fk_category foreign key (category) references category (category),
        constraint fk_type foreign key (type) references type (type),
        constraint fk_brand foreign key (brand) references brand (brand),
        constraint fk_name foreign key (name) references name (name),
        constraint fk_mixer foreign key (mixer) references mixer (mixer)
);



