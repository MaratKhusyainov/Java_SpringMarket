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
('Peter', '$2y$12$t1iQr7DnYBeDxGbRKlAUyOW.6HlPbnvYuK5hJU3g/xp1lkh4Mrvq.', 'peter_parker@gmail.com'),
('Mark', '$2y$12$t1iQr7DnYBeDxGbRKlAUyOW.6HlPbnvYuK5hJU3g/xp1lkh4Mrvq.', 'mark_hamill@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

CREATE TABLE `products_table`
(
    `product_id` bigint(15)   NOT NULL AUTO_INCREMENT,
    `title_field`  varchar(255) NOT NULL,
    `cost_field`   int(11)   DEFAULT NULL,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp,

    PRIMARY KEY (`product_id`)
);
insert into products_table (title_field, cost_field)
values ('Product 1', 100),
       ('Product 2', 200),
       ('Product 3', 300);

create table orders (
                        id                      bigint NOT NULL AUTO_INCREMENT,
                        owner_id                bigint references users (id),
                        price                   int,
                        created_at              timestamp default current_timestamp,
                        updated_at              timestamp default current_timestamp,
                        address                  varchar(255),
                        primary key(id)
);

create table order_items (
                             id                      int  NOT NULL AUTO_INCREMENT,
                             order_id                bigint references orders (id),
                             product_id              bigint(15) references product_tbl (product_id),
                             title                   varchar(255),
                             quantity                int,
                             price_per_product       int,
                             price                   int,
                             created_at              timestamp default current_timestamp,
                             updated_at              timestamp default current_timestamp,
                             primary key(id)
);

create table carts (
                       id                      binary(16),
                       price                   int,
                       primary key(id)
);

create table cart_items (
                            id                int NOT NULL AUTO_INCREMENT,
                            cart_id           binary(16) references carts (id),
                            product_id        bigint references product_tbl (product_id),
                            title             varchar(255),
                            quantity          int,
                            price_per_product int,
                            price             int,
                            created_at        timestamp default current_timestamp,
                            updated_at        timestamp default current_timestamp,
                            primary key(id)
);