use securenotes;

create table users (
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
);
# drop table users;

create table authorities (
    authority varchar(50),
    username varchar(50),
    constraint fk_authority_user foreign key (username) references users(username)
);
# drop table authorities;

create unique index ix_auth_user on authorities(username,authority);
