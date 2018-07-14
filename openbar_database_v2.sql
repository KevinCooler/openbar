
--create database openbar;
drop table bar_drink;
drop table purchase_order;
drop table drink;
drop table customer;
drop table bar;

create table bar(
        bar_id serial,
        name varchar(100) not null,
        account_number varchar (16) not null,
        
        constraint pk_bar_id primary key (bar_id)
);

create table customer(
        email varchar (50),
        credit_card_number varchar (16) not null,
        name varchar(35) not null,
        
        constraint pk_email primary key (email)
);

create table drink(
        drink_id serial,
        category varchar(35) check (category in ('Beer', 'Wine', 'Liquor')),
        type varchar(35) not null,
        brand varchar(35) not null,
        name varchar(35) not null,
        
        constraint pk_drink_id primary key (drink_id)
);

create table bar_drink(
        drink_id int not null,
        bar_id int not null,
        price decimal(4, 2) not null,
        is_available boolean default true,
        is_special boolean default false,
        
        constraint fk_drink_id foreign key (drink_id) references drink (drink_id),
        constraint fk_bar_id foreign key (bar_id) references bar (bar_id)
);

create table purchase_order(
        purchase_order_id serial,
        drink_id int not null,
        email varchar(100) not null,
        bar_id int not null,
        date_time timestamp default now(),
        status varchar (35) check (status in ('', 'Complete', 'In Process')),
        quantity int default 1,
        comment varchar (200),
        
        constraint pk_purchase_order_id primary key (purchase_order_id),
        constraint fk_drink_id foreign key (drink_id) references drink (drink_id),
        constraint fk_email foreign key (email) references customer (email),
        constraint fk_bar_id foreign key (bar_id) references bar (bar_id)
);

insert into customer (email, credit_card_number, name) values('kcooler@gmail.com', '12345678901234', 'Kevin');
insert into customer (email, credit_card_number, name) values('arizkallah@gmail.com', '23456789012345', 'Andrew');
insert into customer (email, credit_card_number, name) values('dstauffer@gmail.com', '34567890123456', 'Doug');
insert into customer (email, credit_card_number, name) values('bstephas@gmail.com', '45678901234567', 'Bridget');
insert into customer (email, credit_card_number, name) values('scooler@gmail.com', '56789012345678', 'Sam');
insert into customer (email, credit_card_number, name) values('sfunk@gmail.com', '67890123456789', 'Steve');

insert into bar (name, account_number) values('Pint House', '8');
insert into bar (name, account_number) values('Standard Hall', '2');
insert into bar (name, account_number) values('Food Hall', '5');
insert into bar (name, account_number) values('Seventh Son', '7');

insert into drink (category, type, brand, name) values ('Beer', 'Lager', 'Yuengling', 'Yuengling');
insert into drink (category, type, brand, name) values ('Beer', 'IPA', 'Seventh Son', 'Scientist');
insert into drink (category, type, brand, name) values ('Beer', 'Lager', 'Budweiser', 'Budweiser');
insert into drink (category, type, brand, name) values ('Beer', 'Lager', 'Budweiser', 'Bud Light');
insert into drink (category, type, brand, name) values ('Beer', 'Lager', 'Miller', 'Miller Light');
insert into drink (category, type, brand, name) values ('Beer', 'Lager', 'Miller', 'High Life');
insert into drink (category, type, brand, name) values ('Beer', 'Wheat', 'Seventh Son', 'Cougar');
insert into drink (category, type, brand, name) values ('Beer', 'Nitro', 'Guinness', 'Guinness');
insert into drink (category, type, brand, name) values ('Beer', 'IPA', 'Sierra Nevada', 'Torpedo');
insert into drink (category, type, brand, name) values ('Beer', 'Mexican Lager', 'Corona', 'Corona Light');
insert into drink (category, type, brand, name) values ('Beer', 'Mexican Lager', 'Corona', 'Corona Extra');
insert into drink (category, type, brand, name) values ('Beer', 'Wheat', 'Blue Moon', 'Blue Moon');
insert into drink (category, type, brand, name) values ('Beer', 'Lager', 'Pabst', 'PBR');
insert into drink (category, type, brand, name) values ('Wine', 'White', 'Mascota Vineyards', 'Unánime');
insert into drink (category, type, brand, name) values ('Wine', 'White', 'Olema', 'Olema');
insert into drink (category, type, brand, name) values ('Wine', 'Red', 'Chateau Pierre de Montignac', 'Chateau Pierre de Montignac');
insert into drink (category, type, brand, name) values ('Wine', 'Red', 'Josh Cellars Family Reserve', 'Pinot Noir');
insert into drink (category, type, brand, name) values ('Wine', 'Red', 'Verada Tri-County', 'Cabernet Sauvignon');
insert into drink (category, type, brand, name) values ('Liquor', 'Bourbon', 'Blantons', 'Blantons');
insert into drink (category, type, brand, name) values ('Liquor', 'Whiskey', 'Jack Daniels', 'Jack Daniels');
insert into drink (category, type, brand, name) values ('Liquor', 'Whiskey', 'Jack Daniels', 'Jack Daniels Green Apple');
insert into drink (category, type, brand, name) values ('Liquor', 'Whiskey', 'Jack Daniels', 'Jack Daniels Honey');
insert into drink (category, type, brand, name) values ('Liquor', 'Bourbon', 'Jim Beam', 'Jim Beam Single Barrel');
insert into drink (category, type, brand, name) values ('Liquor', 'Vodka', 'Titos', 'Titos');
insert into drink (category, type, brand, name) values ('Liquor', 'Vodka', 'Absolute', 'Absolute Citron');
insert into drink (category, type, brand, name) values ('Liquor', 'Tequilla', 'Patron', 'Patron Gold');
insert into drink (category, type, brand, name) values ('Liquor', 'Gin', 'Gin', 'Hendricks');
insert into drink (category, type, brand, name) values ('Liquor', 'Rum', 'Bacardi', 'Bacardi White');
insert into drink (category, type, brand, name) values ('Liquor', 'Rum', 'Captain Morgan', 'Captain Morgan');
insert into drink (category, type, brand, name) values ('Liquor', 'Brandy', 'Hennessy', 'Hennessy');

insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 1, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 2, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 3, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 4, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 5, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 6, 3, false, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 7, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 8, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 9, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 10, 5, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 11, 5, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 12, 5, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 13, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 14, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 15, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 16, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 17, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 18, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 19, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 20, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (1, 21, 8, true, false);

insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 10, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 11, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 12, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 13, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 14, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 15, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 16, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 17, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 18, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 19, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 20, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 21, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 22, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 23, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 24, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 25, 2, false, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 26, 2, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 27, 3, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 28, 7, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 29, 7, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 30, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (2, 1, 3, true, false);

insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 2, 3, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 3, 4, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 5, 3, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 6, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 7, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 8, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 19, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 20, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 21, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 22, 5, false, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 23, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 24, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 25, 6, false, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 26, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 27, 7, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 28, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 29, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 30, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (3, 1, 3, true, false);

insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 12, 3, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 13, 4, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 15, 3, true, true);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 16, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 17, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 8, 4, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 19, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 20, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 21, 5, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 22, 5, false, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 23, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 24, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 25, 6, false, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 26, 6, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 27, 7, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 5, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 4, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 3, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 1, 3, true, false);
insert into bar_drink (bar_id, drink_id, price, is_available, is_special) values (4, 2, 3, true, false);

insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (11, 'kcooler@gmail.com', 1, 'Complete', 1, 'with a lime');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (1, 'kcooler@gmail.com', 1, 'Complete', 2);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (4, 'dstauffer@gmail.com', 1, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (19, 'kcooler@gmail.com', 1, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (6, 'arizkallah@gmail.com', 1, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (20, 'scooler@gmail.com', 1, 'Complete', 1, 'with soda');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (3, 'kcooler@gmail.com', 1, 'Complete', 3);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (9, 'bstephas@gmail.com', 1, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (10, 'arizkallah@gmail.com', 1, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (19, 'sfunk@gmail.com', 1, 'Complete', 10, 'shots');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (8, 'kcooler@gmail.com', 1, 'In Process', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'arizkallah@gmail.com', 1, 'In Process', 2, 'with coke');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (12, 'dstauffer@gmail.com', 1, 'In Process', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'bstephas@gmail.com', 1, '', 1, 'with tonic');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (13, 'kcooler@gmail.com', 1, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (18, 'sfunk@gmail.com', 1, '', 4);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (15, 'kcooler@gmail.com', 1, '', 2, 'with orange juice');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (16, 'sfunk@gmail.com', 1, '', 1, 'neat');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (17, 'scooler@gmail.com', 1, '', 2, 'with diet');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (14, 'kcooler@gmail.com', 1, '', 1);

insert into purchase_order (drink_id, email, bar_id, status, quantity) values (23, 'kcooler@gmail.com', 2, 'Complete', 3);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (29, 'bstephas@gmail.com', 2, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (20, 'arizkallah@gmail.com', 2, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (19, 'sfunk@gmail.com', 2, 'Complete', 10, 'shots');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (28, 'kcooler@gmail.com', 2, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'arizkallah@gmail.com', 1, 'Complete', 2, 'with coke');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (22, 'dstauffer@gmail.com', 2, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'bstephas@gmail.com', 2, 'Complete', 1, 'with tonic');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (23, 'kcooler@gmail.com', 2, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (18, 'sfunk@gmail.com', 2, 'In Process', 4);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (15, 'kcooler@gmail.com', 2, 'In Process', 2, 'with orange juice');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (16, 'sfunk@gmail.com', 2, '', 1, 'neat');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (17, 'scooler@gmail.com', 2, '', 2, 'with diet');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (14, 'kcooler@gmail.com', 2, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (19, 'kcooler@gmail.com', 2, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (26, 'arizkallah@gmail.com', 2, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (20, 'scooler@gmail.com', 2, '', 1, 'with soda');


insert into purchase_order (drink_id, email, bar_id, status, quantity) values (2, 'kcooler@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (6, 'arizkallah@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (20, 'scooler@gmail.com', 3, 'Complete', 1, 'with soda');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (23, 'kcooler@gmail.com', 3, 'Complete', 3);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (29, 'bstephas@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (20, 'arizkallah@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (19, 'sfunk@gmail.com', 3, 'Complete', 10, 'shots');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (8, 'kcooler@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'arizkallah@gmail.com', 3, 'Complete', 2, 'with coke');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (2, 'dstauffer@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'bstephas@gmail.com', 3, 'Complete', 1, 'with tonic');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (23, 'kcooler@gmail.com', 3, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (18, 'sfunk@gmail.com', 3, 'In Process', 4);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (15, 'kcooler@gmail.com', 3, 'In Process', 2, 'with orange juice');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (16, 'sfunk@gmail.com', 3, '', 1, 'neat');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (17, 'scooler@gmail.com', 3, '', 2, 'with diet');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (14, 'kcooler@gmail.com', 3, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (11, 'kcooler@gmail.com', 3, '', 1, 'with a lime');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (1, 'kcooler@gmail.com', 3, '', 2);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (4, 'dstauffer@gmail.com', 3, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (19, 'kcooler@gmail.com', 3, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (6, 'bstephas@gmail.com', 3, '', 1);

insert into purchase_order (drink_id, email, bar_id, status, quantity) values (23, 'kcooler@gmail.com', 4, 'Complete', 3);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (29, 'bstephas@gmail.com', 4, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (20, 'arizkallah@gmail.com', 4, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (19, 'sfunk@gmail.com', 4, 'Complete', 10, 'shots');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (28, 'kcooler@gmail.com', 4, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (8, 'kcooler@gmail.com', 4, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'arizkallah@gmail.com', 4, 'Complete', 2, 'with coke');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (2, 'dstauffer@gmail.com', 4, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'bstephas@gmail.com', 4, 'Complete', 1, 'with tonic');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (23, 'kcooler@gmail.com', 4, 'Complete', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (18, 'sfunk@gmail.com', 4, 'In Process', 4);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (21, 'bstephas@gmail.com', 4, '', 1, 'with tonic');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (13, 'kcooler@gmail.com', 4, '', 1);
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (18, 'sfunk@gmail.com', 4, '', 4);
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (15, 'kcooler@gmail.com', 4, '', 2, 'with orange juice');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (16, 'sfunk@gmail.com', 4, '', 1, 'neat');
insert into purchase_order (drink_id, email, bar_id, status, quantity, comment) values (17, 'scooler@gmail.com', 4, '', 2, 'with diet');
insert into purchase_order (drink_id, email, bar_id, status, quantity) values (14, 'dstauffer@gmail.com', 4, '', 1);

