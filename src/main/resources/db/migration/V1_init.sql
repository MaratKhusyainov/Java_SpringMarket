CREATE TABLE order_items
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `product_id` bigint(15) NOT NULL,
    `quantity` INT,
    `price_per_product`    INT,
    `price`    INT(11),
    PRIMARY KEY (`id`)
);


create table users (
                       id                      INT NOT NULL AUTO_INCREMENT,
                       username                varchar(255) not null unique,
                       password                varchar(255) not null,
                       email                   varchar(255) unique,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp,
                       primary key (id)
);

create table roles (
                       id                      int NOT NULL AUTO_INCREMENT,
                       name                    varchar(255) not null unique,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp,
                       primary key (id)
);

create table orders (
                        order_id                      int NOT NULL AUTO_INCREMENT,
                        user_id                       int NOT NULL references users (id),

                        primary key (order_id)
);


create table items_orders (
                              order_id                      int NOT NULL references orders (order_id),
                              order_items_id                int NOT NULL references order_items (id)
);


CREATE TABLE users_roles (
                             user_id           int not null references users (id),
                             role_id           int not null references roles (id),
                             primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('bob', '$2y$12$t1iQr7DnYBeDxGbRKlAUyOW.6HlPbnvYuK5hJU3g/xp1lkh4Mrvq.', 'bob_johnson@gmail.com'),
('john', '$2y$12$t1iQr7DnYBeDxGbRKlAUyOW.6HlPbnvYuK5hJU3g/xp1lkh4Mrvq.', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

CREATE TABLE `products_table`
(
    `product_id` bigint(15)   NOT NULL AUTO_INCREMENT,
    `title_fld`  varchar(255) NOT NULL,
    `cost_fld`   int(11)   DEFAULT NULL,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp,

    PRIMARY KEY (`product_id`)
);
insert into products_table (title_fld, cost_fld) values ('Product 1', 100),
                                                        ('Product 2', 200),
                                                        ('Product 3', 300),
                                                        ('Product 4', 400),
                                                        ('Product 5', 500),
                                                        ('Product 6', 600),
                                                        ('Product 7', 700),
                                                        ('Product 8', 800),
                                                        ('Product 9', 900),
                                                        ('Product 10', 1000),
                                                        ('Product 11', 1100),
                                                        ('Product 12', 1200),
                                                        ('Product 13', 1300),
                                                        ('Product 14', 1400),
                                                        ('Product 15', 1500),
                                                        ('Product 16', 1600),
                                                        ('Product 17', 1700),
                                                        ('Product 18', 1800),
                                                        ('Product 19', 1900),
                                                        ('Product 20', 2000);
