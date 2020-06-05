create table if not exists REPOSITORY
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    primary key(id)
);

create table if not exists PULL_REQUEST
(
    number bigint not null,
    github_repo bigint not null,
    state varchar(255) not null,
    updated_at datetime,
    primary key(number)
);

create table if not exists COMMENT
(
    id bigint not null,
    pull_request bigint not null,
    login varchar(255) not null,
    content text not null,
    updated_at datetime,
    html_url varchar(255) not null,
    primary key(id)
);