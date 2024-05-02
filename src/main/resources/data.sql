insert into
    payment_type(id, type, title)
values
    (1, 'CASH', 'Sularaha'),
    (2, 'BANK_TRANSFER', 'Pangaülekanne')
on conflict do nothing;

insert into
    event(name, time)
values
    ('Aenean commodo', '2024-05-12 00:00:00.000000'),
    ('Fusce ex dui, finibus eu luctus vel', '2024-04-01 00:00:00.000000'),
    ('Munentis meus eu massa viera ultri', '2024-03-15 00:00:00.000000'),
    ('Integer nec nulla vitae', '2024-06-10 00:00:00.000000');