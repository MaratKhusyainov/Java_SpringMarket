
CREATE TABLE `products_table`
(
    `product_id` bigint(15) NOT NULL AUTO_INCREMENT,
    `title_fld`  varchar(255) NOT NULL,
    `cost_fld`   int(11) DEFAULT NULL,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp,
    PRIMARY KEY (`product_id`)
) ;
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