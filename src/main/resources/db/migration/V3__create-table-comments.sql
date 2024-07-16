create table comments(
    id bigint not null auto_increment,
    content varchar(300) not null,
    timestamp datetime not null,
    is_resolution boolean not null,
    discussion_id bigint not null,
    creator_id bigint not null,
    primary key (id)
);