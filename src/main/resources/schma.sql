create table if not exists REPOSITORY
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    primary key(id)
);

create table if not exists PULL_REQUEST
(
    id bigint not null,
    repository bigint not null,
    name varchar(255) not null,
    updated_at datetime,
    primary key(id)
);

create table if not exists COMMENT
(
    id bigint auto_increment not null,
    pull_request bigint not null,
    diffHunk varchar(255) not null,
    content varchar(255) not null,
    htmlUrl varchar(255) not null,
    updated_at datetime,
    primary key(id)
);