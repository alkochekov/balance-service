create table if not exists HISTORY
(
    ID                      bigserial primary key    not null,
    BALANCE_ID              bigint                   not null,
    USER                    varchar(255)             not null,
    OPERATION               varchar(255)             not null,
    DATE_TIME               timestamp                not null,
    FOREIGN KEY (BALANCE_ID, USER) REFERENCES BALANCE (ID, USER)
);
create index if not exists idx_o_operation on HISTORY (OPERATION);
