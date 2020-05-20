drop table if exists STOCK_TRADE_INFO;

create table STOCK_TRADE_INFO
(
    TRANSACTIONID INTEGER(11) NOT NULL auto_increment,
    TRADEID       VARCHAR(20) not null,
    VERSION       VARCHAR(20) not null,
    SECURITYCODE  VARCHAR(20) not null,
    QUANTITY      INTEGER     not null,
    DBOPERATION   INTEGER     not null,
    USEROPERATION INTEGER     not null,
    primary key (TRANSACTIONID)
);

comment on column STOCK_TRADE_INFO.TRADEID is 'trade indentifer';

comment on column STOCK_TRADE_INFO.VERSION is 'starts with 1 for given tradeId';

comment on column STOCK_TRADE_INFO.SECURITYCODE is 'security identifier e.g. INF => infosys';

comment on column STOCK_TRADE_INFO.QUANTITY is 'quantity of security e.g. 10 infosys shares';

comment on column STOCK_TRADE_INFO.DBOPERATION is 'data operation:
1.insert 2.update 3.cancel';

comment on column STOCK_TRADE_INFO.USEROPERATION is 'user operation:
1.buy 2.sell';

