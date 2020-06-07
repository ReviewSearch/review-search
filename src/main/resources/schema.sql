create table if not exists REPOSITORY
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists PULL_REQUEST
(
    id bigint not null,
    github_repo bigint not null,
    state varchar(255) not null,
    updated_at datetime,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists COMMENT
(
    id bigint not null,
    number bigint not null,
    pull_request bigint not null,
    login varchar(255) not null,
    content text not null,
    updated_at datetime,
    html_url varchar(255) not null,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;