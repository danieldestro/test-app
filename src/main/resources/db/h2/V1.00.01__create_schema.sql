
create table month_breakdown (id integer generated by default as identity, cost decimal(19,2), month_number integer, srv_price_breakdown_id integer, primary key (id));
create table price_summary (id integer generated by default as identity, price decimal(19,2), proposal_id integer, primary key (id));
create table proposal (id integer generated by default as identity, name varchar(255), primary key (id));
create table service (id integer generated by default as identity, name varchar(255), proposal_id integer, primary key (id));
create table srv_price_breakdown (id integer generated by default as identity, country_code varchar(255), srv_price_summary_id integer, primary key (id));
create table srv_price_summary (id integer generated by default as identity, price_summary_id integer, primary key (id));

alter table month_breakdown add constraint FK_month_breakdown foreign key (srv_price_breakdown_id) references srv_price_breakdown;
alter table price_summary add constraint FK_price_summary foreign key (proposal_id) references proposal;
alter table service add constraint FK_service foreign key (proposal_id) references proposal;
alter table srv_price_breakdown add constraint FK_srv_price_breakdown foreign key (srv_price_summary_id) references srv_price_summary;
alter table srv_price_summary add constraint FK_srv_price_summary foreign key (price_summary_id) references price_summary;