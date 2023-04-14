insert into users (create_time, created_by, deleted, email, modified_by, name, password, role, update_time, user_type)
values (now(), null, false, 'test1@test.com', null, 'test1',
        '$2a$10$ohFBZh88iFBlChRUg9UYRedSDjWtLBuOtelwdNNqSYbaHDmejzQv6', 'USER', now(), 'GENERAL');
