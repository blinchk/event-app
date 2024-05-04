insert into
    payment_type(id, type, title)
values
    (1, 'CASH', 'Sularaha'),
    (2, 'BANK_TRANSFER', 'Pangaülekanne')
on conflict do nothing;

insert into
    event(uuid, name, time)
values
    ('7d0b6de8-dc8a-436d-bb31-b35108f19cb4', 'Aenean commodo', '2024-05-12 00:00:00.000000'),
    ('234e813a-296c-4283-b67f-3e3952e682f5', 'Fusce ex dui, finibus eu luctus vel', '2024-04-01 00:00:00.000000'),
    ('b4cc49a2-5d7d-4de5-a5f4-88718a537f48', 'Munentis meus eu massa viera ultri', '2024-03-15 00:00:00.000000'),
    ('65c28b81-fb54-45d8-abfc-380e517df09d', 'Integer nec nulla vitae', '2024-06-10 00:00:00.000000')
on conflict do nothing;