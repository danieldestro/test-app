
create table month_breakdown (id int auto_increment not null, cost numeric(19,2), month_number int, srv_price_breakdown_id int, primary key (id));
create table price_summary (id int auto_increment not null, price numeric(19,2), proposal_id int, primary key (id));
create table proposal (id int auto_increment not null, name varchar(255), primary key (id));
create table service (id int auto_increment not null, name varchar(255), proposal_id int, primary key (id));
create table srv_price_breakdown (id int auto_increment not null, country_code varchar(255), srv_price_summary_id int, primary key (id));
create table srv_price_summary (id int auto_increment not null, price_summary_id int, primary key (id));

alter table month_breakdown add constraint FK_month_breakdown foreign key (srv_price_breakdown_id) references srv_price_breakdown(id);
alter table price_summary add constraint FK_price_summary foreign key (proposal_id) references proposal(id);
alter table service add constraint FK_service foreign key (proposal_id) references proposal(id);
alter table srv_price_breakdown add constraint FK_srv_price_breakdown foreign key (srv_price_summary_id) references srv_price_summary(id);
alter table srv_price_summary add constraint FK_srv_price_summary foreign key (price_summary_id) references price_summary(id);