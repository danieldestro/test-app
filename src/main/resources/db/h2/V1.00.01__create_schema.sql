
create sequence SEQ_COUNTRY;
create sequence SEQ_MTH_BKD;
create sequence SEQ_PROPOSAL;
create sequence SEQ_SERVICE;
create sequence seq_price_summary;
create sequence seq_srv_price_bkd;
create sequence seq_srv_price_summary;

create table country (id integer not null, name varchar(255), primary key (id));
create table proposal (id integer not null, name varchar(255), primary key (id));
create table service (id integer not null, name varchar(255), proposal_id integer, months int not null, primary key (id));
create table price_summary (id integer not null, price decimal(19,2), proposal_id integer, primary key (id));
create table srv_price_summary (id integer not null, price_summary_id integer, primary key (id));
create table srv_price_breakdown (id integer not null, country_id int not null, srv_price_summary_id integer, primary key (id));
create table month_breakdown (id integer not null, cost decimal(19,2), month_number integer, srv_price_breakdown_id integer, primary key (id));

alter table month_breakdown add constraint FK_month_breakdown foreign key (srv_price_breakdown_id) references srv_price_breakdown(id);
alter table price_summary add constraint FK_price_summary foreign key (proposal_id) references proposal(id);
alter table service add constraint FK_service foreign key (proposal_id) references proposal(id);
alter table srv_price_breakdown add constraint FK_srv_price_breakdown foreign key (srv_price_summary_id) references srv_price_summary(id);
alter table srv_price_breakdown add constraint FK_srv_price_breakdown_country foreign key (country_id) references country(id);
alter table srv_price_summary add constraint FK_srv_price_summary foreign key (price_summary_id) references price_summary(id);