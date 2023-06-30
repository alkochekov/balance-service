create table if not exists USER
(
    ID                      bigserial primary key    not null,
    USERNAME                varchar(255)                  not null,
    PASSWORD                varchar(255)             not null,
    ROLE                    varchar(255)             not null

);