create table if not exists campaign(
  id bigint not null auto_increment,
  name varchar(45) not null,
  team_id bigint not null,
  start_date datetime not null,
  end_date datetime not null,
  primary key (id)
);

create table if not exists client(
  id bigint not null auto_increment,
  name varchar(45) not null,
  email varchar(45) not null,
  birth_date datetime not null,
  team_id bigint not null,
  primary key (id)
);

create table if not exists client_campaign(
  id bigint not null auto_increment,
  client_id bigint not null,
  campaign_id bigint not null,
  primary key (id),
  foreign key (client_id) references client(id),
  foreign key (campaign_id) references campaign(id)
);