To get check launch the jar file as:
    java CheckRunner 3-1 2-5 5-1 card-1234
or as get request:
    http://localhost:8080/check?itemId=1&quantity=2&card=1234&itemId=5&quantity=10


SQL commands to create Database:
    CREATE DATABASE "ItemsListShop"
        WITH
        OWNER = root
        ENCODING = 'UTF8'
        LC_COLLATE = 'Russian_Belarus.1251'
        LC_CTYPE = 'Russian_Belarus.1251'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1
        IS_TEMPLATE = False;


SQL commands to create tables(or you can just launch the program and it will create the tables its self):
    CREATE TABLE IF NOT EXISTS public.items
    (
        id bigint NOT NULL,
        action boolean NOT NULL,
        cost real NOT NULL,
        name character varying(255) COLLATE pg_catalog."default",
        CONSTRAINT items_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS public.items
        OWNER to root;


CREATE TABLE IF NOT EXISTS public.card
(
    card_number bigint NOT NULL,
    percents real NOT NULL,
    CONSTRAINT card_pkey PRIMARY KEY (card_number)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.card
    OWNER to root;

SQL commands for filling the tables if data is empty:

    CREATE DATABASE "ItemsListShop"
        WITH
        OWNER = root
        ENCODING = 'UTF8'
        LC_COLLATE = 'Russian_Belarus.1251'
        LC_CTYPE = 'Russian_Belarus.1251'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1
        IS_TEMPLATE = False;

    insert into card(card_number,percents) values(1234,30);
    insert into card(card_number,percents) values(6666,6);
    insert into card(card_number,percents) values(7777,77.7);

    insert into items(id,action,cost,name) values(1,true,1.5,'Beer');
    insert into items(id,action,cost,name) values(2,true,0.95,'Cucumber');
    insert into items(id,action,cost,name) values(3,false,1,'Tomato');
    insert into items(id,action,cost,name) values(4,true,2,'Kola');
    insert into items(id,action,cost,name) values(5,false,0.35,'Potato');
    insert into items(id,action,cost,name) values(6,false,4.8,'Toy');


