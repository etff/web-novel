insert into users (create_time, created_by, deleted, email, modified_by, name, password, role, update_time, user_type)
values ('2023-01-01 00:00:00', null, false, 'test1@test.com', null, 'test1',
        '$2a$10$ohFBZh88iFBlChRUg9UYRedSDjWtLBuOtelwdNNqSYbaHDmejzQv6', 'USER', '2023-01-01 00:00:00', 'GENERAL');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '1장', 100, 'SELLING', '2023-01-01 00:00:00', 1, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '30장', 2000, 'SELLING', '2023-01-01 00:00:00', 30, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '60장', 5800, 'SELLING', '2023-01-01 00:00:00', 60, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '90장', 8700, 'SELLING', '2023-01-01 00:00:00', 90, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '한 번에 구매하기', 8600, 'SELLING', '2023-01-01 00:00:00', null, 'TICKET');
insert into user_ticket(create_time, created_by, modified_by, update_time, user_id, ticket_count)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 1, 100);

insert into book(create_time, created_by, modified_by, update_time, title, book_status)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 'spring 비법', 'SELLING');

insert into episode(create_time, created_by, modified_by, update_time, book_id, book_status, title, ticket_price,
                    content, page)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 1, 'SELLING', 'ch1.객체지향', 1,
        '객체지향 프로그래밍이란 무엇인가?', 10);
insert into episode(create_time, created_by, modified_by, update_time, book_id, book_status, title, ticket_price,
                    content, page)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 1, 'SELLING', 'ch2.객체지향', 1,
        'test2', 10);
insert into episode(create_time, created_by, modified_by, update_time, book_id, book_status, title, ticket_price,
                    content, page)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 1, 'SELLING', 'ch3.객체지향', 1,
        'test3', 10);
insert into episode(create_time, created_by, modified_by, update_time, book_id, book_status, title, ticket_price,
                    content, page)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 1, 'SELLING', 'ch4.객체지향', 1,
        'test4', 10);

insert into user_book(create_time, created_by, modified_by, update_time, episode_id, page, user_id)
values ('2023-01-01 00:00:00', 'SYSTEM', 'SYSTEM', '2023-01-01 00:00:00', 1, 3, 1);
