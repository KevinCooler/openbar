/*
drop table category;
drop table type;
drop table brand;
drop table name;
drop table mixer;

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

        constraint fk_category foreign key (category) references category (category),
        constraint fk_type foreign key (type) references type (type),
        constraint fk_brand foreign key (brand) references brand (brand),
        constraint fk_name foreign key (name) references name (name),
        constraint fk_mixer foreign key (mixer) references mixer (mixer)
        
*/

/*
insert into mixer (mixer) values ('Tonic');
insert into mixer (mixer) values ('Soda');
insert into mixer (mixer) values ('Gingerale');
insert into mixer (mixer) values ('Coke');
insert into mixer (mixer) values ('Diet Coke');
insert into mixer (mixer) values ('Sprite');
insert into mixer (mixer) values ('Orange Juice');
insert into mixer (mixer) values ('Cranberry Juice');
*/

/*
insert into type (type) values ('IPA');
insert into type (type) values ('Lager');
insert into type (type) values ('Pale Ale');
insert into type (type) values ('Porter');
insert into type (type) values ('Stout');
insert into type (type) values ('Wheat');
insert into type (type) values ('Cider');
insert into type (type) values ('Saison');
insert into type (type) values ('Ale');

insert into brand (brand) values ('Budweiser');
insert into brand (brand) values ('Miller');
insert into brand (brand) values ('Seventh Son');
insert into brand (brand) values ('Bells');
insert into brand (brand) values ('New Belgium');
insert into brand (brand) values ('Guinness');
insert into brand (brand) values ('Corona');
insert into brand (brand) values ('Coors');
insert into brand (brand) values ('Yuengling');
insert into brand (brand) values ('Pabst');

insert into name (name, price) values ('Bud Light', 3);
insert into name (name, price) values ('Coors Light', 3);
insert into name (name, price) values ('Budweiser', 3);
insert into name (name, price) values ('Miller Light', 3);
insert into name (name, price) values ('Miller High Life', 3);
insert into name (name, price) values ('Corona Light', 4);
insert into name (name, price) values ('Corona Extra', 4);
insert into name (name, price) values ('Scientist', 5);
insert into name (name, price) values ('Cougar', 5);
insert into name (name, price) values ('PBR', 2);
insert into name (name, price) values ('Oberon', 5);
insert into name (name, price) values ('Two Hearted', 5);
*/
/*
insert into type (type) values ('Red');
insert into type (type) values ('Rose');
insert into type (type) values ('White');

insert into brand (brand) values ('Mascota Vineyards');
insert into brand (brand) values ('Olema');
insert into brand (brand) values ('Chateau Pierre de Montignac');
insert into brand (brand) values ('Josh Cellars Family Reserve ');
insert into brand (brand) values ('Verada Tri-County');

insert into name (name, price) values ('Unánime', 10);
insert into name (name, price) values ('Olema', 9);
insert into name (name, price) values ('Chateau Pierre de Montignac', 8);
insert into name (name, price) values ('Pinot Noir', 7);
insert into name (name, price) values ('Cabernet Sauvignon', 6);
*/
/*
insert into type (type) values ('Whiskey');
insert into type (type) values ('Bourbon');
insert into type (type) values ('Vodka');
insert into type (type) values ('Tequilla');
insert into type (type) values ('Gin');
insert into type (type) values ('Rum');
insert into type (type) values ('Brandy');

insert into brand (brand) values ('Titos');
insert into brand (brand) values ('Blantons');
insert into brand (brand) values ('Jack Daniels');
insert into brand (brand) values ('Captain Morgan');
insert into brand (brand) values ('Patron');
insert into brand (brand) values ('Bacardi');
insert into brand (brand) values ('Jim Beam');
insert into brand (brand) values ('Absolute');
insert into brand (brand) values ('Beefeater');
insert into brand (brand) values ('Hendricks');
insert into brand (brand) values ('Hennessy');

insert into name (name, price) values ('Titos', 5);
insert into name (name, price) values ('Blantons', 7);
insert into name (name, price) values ('Jack Daniels', 5);
insert into name (name, price) values ('Jack Daniels Green Apple', 5);
insert into name (name, price) values ('Jack Daniels Honey', 5);
insert into name (name, price) values ('Captain Morgan', 5);
insert into name (name, price) values ('Silver', 7);
insert into name (name, price) values ('Gold', 7);
insert into name (name, price) values ('Jim Beam Apple', 5);
insert into name (name, price) values ('Citron', 5);
insert into name (name, price) values ('Beefeater', 5);
insert into name (name, price) values ('Hennessy', 6);
insert into name (name, price) values ('Hendricks', 7);
*/
--create database openbar;
drop table purchase_order;
drop table drink;
drop table customer;
drop table employee;

create table customer(
        email varchar (50),
        credit_card_number varchar (16) not null,
        name varchar(35) not null,
        
        constraint pk_email primary key (email)
);

create table employee(
        employee_id serial,
        first_name varchar(35) not null,
        last_name varchar(35) not null,
        
        constraint pk_employee_id primary key (employee_id)
);

create table drink(
        drink_id serial,
        category varchar(35) check (category in ('Beer', 'Wine', 'Liquor')),
        type varchar(35) not null,
        brand varchar(35) not null,
        name varchar(35) not null,
        price decimal(4, 2) not null,
        is_available boolean default true,
        
        constraint pk_drink_id primary key (drink_id)
);

create table purchase_order(
        purchase_order_id serial,
        drink_id int not null,
        date_time timestamp default now(),
        customer_email varchar(100) not null,
        filled_by_id int,
        quantity int default 1,
        comment varchar (200),
        
        
        constraint pk_purchase_order_id primary key (purchase_order_id),
        constraint fk_drink_id foreign key (drink_id) references drink (drink_id),
        constraint fk_customer_email foreign key (customer_email) references customer (email),
        constraint fk_filled_by_id foreign key (filled_by_id) references employee (employee_id)
);

insert into employee(first_name, last_name) values ('Anders', 'Miller');
insert into employee(first_name, last_name) values ('Brandon', 'Nazek');
insert into employee(first_name, last_name) values ('James', 'Graves');

insert into customer (email, credit_card_number, name) values('kcooler@gmail.com', '12345678901234', 'Kevin');
insert into customer (email, credit_card_number, name) values('arizkallah@gmail.com', '23456789012345', 'Andrew');
insert into customer (email, credit_card_number, name) values('dstauffer@gmail.com', '34567890123456', 'Doug');
insert into customer (email, credit_card_number, name) values('bstephas@gmail.com', '45678901234567', 'Bridget');
insert into customer (email, credit_card_number, name) values('scooler@gmail.com', '56789012345678', 'Sam');
insert into customer (email, credit_card_number, name) values('sfunk@gmail.com', '67890123456789', 'Steve');


insert into drink (category, type, brand, name, price) values ('Beer', 'Lager', 'Yuengling', 'Yuengling', 3);
insert into drink (category, type, brand, name, price) values ('Beer', 'IPA', 'Seventh Son', 'Scientist', 6);
insert into drink (category, type, brand, name, price) values ('Beer', 'Lager', 'Budweiser', 'Budweiser', 3);
insert into drink (category, type, brand, name, price) values ('Beer', 'Lager', 'Budweiser', 'Bud Light', 3);
insert into drink (category, type, brand, name, price) values ('Beer', 'Lager', 'Miller', 'Miller Light', 3);
insert into drink (category, type, brand, name, price) values ('Beer', 'Lager', 'Miller', 'High Life', 3);
insert into drink (category, type, brand, name, price) values ('Beer', 'Wheat', 'Seventh Son', 'Cougar', 6);
insert into drink (category, type, brand, name, price) values ('Beer', 'Nitro', 'Guinness', 'Guinness', 5);
insert into drink (category, type, brand, name, price) values ('Beer', 'IPA', 'Sierra Nevada', 'Torpedo', 6);
insert into drink (category, type, brand, name, price) values ('Beer', 'Mexican Lager', 'Corona', 'Corona Light', 4);
insert into drink (category, type, brand, name, price) values ('Beer', 'Mexican Lager', 'Corona', 'Corona Extra', 4);
insert into drink (category, type, brand, name, price) values ('Beer', 'Wheat', 'Blue Moon', 'Blue Moon', 5);
insert into drink (category, type, brand, name, price) values ('Beer', 'Lager', 'Pabst', 'PBR', 2);
insert into drink (category, type, brand, name, price) values ('Wine', 'White', 'Mascota Vineyards', 'Unánime', 10);
insert into drink (category, type, brand, name, price) values ('Wine', 'White', 'Olema', 'Olema', 9);
insert into drink (category, type, brand, name, price) values ('Wine', 'Red', 'Chateau Pierre de Montignac', 'Chateau Pierre de Montignac', 8);
insert into drink (category, type, brand, name, price) values ('Wine', 'Red', 'Josh Cellars Family Reserve', 'Pinot Noir', 7);
insert into drink (category, type, brand, name, price, is_available) values ('Wine', 'Red', 'Verada Tri-County', 'Cabernet Sauvignon', 6, false);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Bourbon', 'Blantons', 'Blantons', 10);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Whiskey', 'Jack Daniels', 'Jack Daniels', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Whiskey', 'Jack Daniels', 'Jack Daniels Green Apple', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Whiskey', 'Jack Daniels', 'Jack Daniels Honey', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Bourbon', 'Jim Beam', 'Jim Beam Single Barrel', 7);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Vodka', 'Titos', 'Titos', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Vodka', 'Absolute', 'Absolute Citron', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Tequilla', 'Patron', 'Patron Gold', 7);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Gin', 'Gin', 'Hendricks', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Rum', 'Bacardi', 'Bacardi White', 6);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Rum', 'Captain Morgan', 'Captain Morgan', 5);
insert into drink (category, type, brand, name, price) values ('Liquor', 'Brandy', 'Hennessy', 'Hennessy', 7);


insert into purchase_order (drink_id, customer_email, quantity, comment) values (11, 'kcooler@gmail.com', 1, 'with a lime');
insert into purchase_order (drink_id, customer_email, quantity) values (1, 'kcooler@gmail.com', 2);
insert into purchase_order (drink_id, customer_email, quantity) values (4, 'dstauffer@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity) values (19, 'kcooler@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity) values (6, 'arizkallah@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity, comment) values (22, 'scooler@gmail.com', 1, 'with soda');
insert into purchase_order (drink_id, customer_email, quantity) values (3, 'kcooler@gmail.com', 3);
insert into purchase_order (drink_id, customer_email, quantity) values (9, 'bstephas@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity) values (10, 'arizkallah@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity, comment) values (29, 'sfunk@gmail.com', 10, 'shots');
insert into purchase_order (drink_id, customer_email, quantity) values (8, 'kcooler@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity, comment) values (28, 'arizkallah@gmail.com', 2, 'with coke');
insert into purchase_order (drink_id, customer_email, quantity) values (12, 'dstauffer@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity, comment) values (24, 'bstephas@gmail.com', 1, 'with tonic');
insert into purchase_order (drink_id, customer_email, quantity) values (21, 'kcooler@gmail.com', 1);
insert into purchase_order (drink_id, customer_email, quantity) values (18, 'sfunk@gmail.com', 4);
insert into purchase_order (drink_id, customer_email, quantity, comment) values (23, 'kcooler@gmail.com', 2, 'with orange juice');
insert into purchase_order (drink_id, customer_email, quantity, comment) values (23, 'sfunk@gmail.com', 1, 'neat');
insert into purchase_order (drink_id, customer_email, quantity, comment) values (23, 'scooler@gmail.com', 2, 'with diet');
insert into purchase_order (drink_id, customer_email, quantity) values (14, 'kcooler@gmail.com', 1);





