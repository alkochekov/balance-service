create table if not exists BALANCE
(
    ID                      bigserial primary key    not null,
    USER                    varchar(255)             not null,
    BALANCE                 double precision         not null
);

create index if not exists idx_b_user on BALANCE (USER );
create index if not exists idx_b_balance on BALANCE (BALANCE);



