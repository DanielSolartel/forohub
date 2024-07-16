create table discussions(
    id bigint not null auto_increment,
    subject varchar(100) not null unique,
    content varchar(300) not null unique,
    creation_time datetime not null,
    is_active boolean not null,
    creator_id bigint not null,
    category varchar(100) not null,
    primary key (id)
);