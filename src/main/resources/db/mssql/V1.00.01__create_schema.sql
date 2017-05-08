
CREATE SEQUENCE SEQ_MTH_BKD AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE seq_price_summary AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE SEQ_PROPOSAL AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE SEQ_SERVICE AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE seq_srv_price_bkd AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;
CREATE SEQUENCE seq_srv_price_summary AS int START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE NO CYCLE CACHE 10;

create table month_breakdown (id int identity not null, cost numeric(19,2), month_number int, srv_price_breakdown_id int, primary key (id));
create table price_summary (id int identity not null, price numeric(19,2), proposal_id int, primary key (id));
create table proposal (id int identity not null, name varchar(255), primary key (id));
create table service (id int identity not null, name varchar(255), proposal_id int, primary key (id));
create table srv_price_breakdown (id int identity not null, country_code varchar(255), srv_price_summary_id int, primary key (id));
create table srv_price_summary (id int identity not null, price_summary_id int, primary key (id));

alter table month_breakdown add constraint FK_nn3llvcm8lq475yovpg9fgmhd foreign key (srv_price_breakdown_id) references srv_price_breakdown;
alter table price_summary add constraint FK_4wbyrvbdqwoavd90ypbmlpjj9 foreign key (proposal_id) references proposal;
alter table service add constraint FK_b0o7ui2729tols69ona4ffgre foreign key (proposal_id) references proposal;
alter table srv_price_breakdown add constraint FK_d8269i0di55rvfoyvfj5q4d42 foreign key (srv_price_summary_id) references srv_price_summary;
alter table srv_price_summary add constraint FK_vq1wguwumcmsqhnxydxa7to3 foreign key (price_summary_id) references price_summary;