
CREATE SEQUENCE SEQ_COUNTRY AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE SEQ_MTH_BKD AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE seq_price_summary AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE SEQ_PROPOSAL AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE SEQ_SERVICE AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE seq_srv_price_bkd AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE seq_srv_price_summary AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;

create table country (id int not null, name varchar(255), primary key (id));
create table month_breakdown (id int not null, cost numeric(19,2), month_number int, srv_price_breakdown_id int, primary key (id));
create table price_summary (id int not null, price numeric(19,2), proposal_id int, primary key (id));
create table proposal (id int not null, name varchar(255), primary key (id));
create table service (id int not null, name varchar(255), proposal_id int, primary key (id));
create table srv_price_breakdown (id int not null, country_id int not null, srv_price_summary_id int, primary key (id));
create table srv_price_summary (id int not null, price_summary_id int, primary key (id));

alter table month_breakdown add constraint FK_month_breakdown foreign key (srv_price_breakdown_id) references srv_price_breakdown(id);
alter table price_summary add constraint FK_price_summary foreign key (proposal_id) references proposal(id);
alter table service add constraint FK_service foreign key (proposal_id) references proposal(id);
alter table srv_price_breakdown add constraint FK_srv_price_breakdown foreign key (srv_price_summary_id) references srv_price_summary(id);
alter table srv_price_breakdown add constraint FK_srv_price_breakdown_country foreign key (country_id) references country(id);
alter table srv_price_summary add constraint FK_srv_price_summary foreign key (price_summary_id) references price_summary(id);