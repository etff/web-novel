insert into users (create_time, created_by, deleted, email, modified_by, name, password, role, update_time, user_type)
values (now(), null, false, 'test1@test.com', null, 'test1',
        '$2a$10$ohFBZh88iFBlChRUg9UYRedSDjWtLBuOtelwdNNqSYbaHDmejzQv6', 'USER', now(), 'GENERAL');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values (now(), 'SYSTEM', 'SYSTEM', '1장', 100, 'SELLING', now(), 1, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values (now(), 'SYSTEM', 'SYSTEM', '30장', 2000, 'SELLING', now(), 30, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values (now(), 'SYSTEM', 'SYSTEM', '60장', 5800, 'SELLING', now(), 60, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values (now(), 'SYSTEM', 'SYSTEM', '90장', 8700, 'SELLING', now(), 90, 'TICKET');
insert into product (create_time, created_by, modified_by, name, price, product_status, update_time, quantity,
                     product_type)
values (now(), 'SYSTEM', 'SYSTEM', '한 번에 구매하기', 8600, 'SELLING', now(), null, 'TICKET');
insert into user_ticket(create_time, created_by, modified_by, update_time, user_id, ticket_count)
values (now(), 'SYSTEM', 'SYSTEM', now(), 1, 100);

insert into book(create_time, created_by, modified_by, update_time, title, book_status)
values (now(), 'SYSTEM', 'SYSTEM', now(), 'spring 비법', 'SELLING');

insert into episode(create_time, created_by, modified_by, update_time, book_id, book_status, title, ticket_price,
                    content)
values (now(), 'SYSTEM', 'SYSTEM', now(), 1, 'SELLING', 'ch1.객체지향', 1, '객체지향 프로그래밍이란 무엇인가?');
