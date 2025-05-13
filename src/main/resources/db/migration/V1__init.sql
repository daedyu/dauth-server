create table applications (
    id bigint primary key not null auto_increment unique,
    name varchar(255) unique,
    owner_id varchar(255) not null,
    client_id varchar(255) unique,
    client_secret varchar(255),
    url text,
    redirect_url text,
    is_public bit
);

create table users (
    id bigint primary key not null auto_increment unique,
    dodam_id varchar(255) not null,
    fk_client_id varchar(255) not null,
    scopes text not null,
    role varchar(20),
    foreign key (fk_client_id) references applications (client_id)
);

create table frameworks (
    id bigint primary key not null auto_increment,
    name varchar(125) not null,
    type varchar(255) not null,
    color text
);

create table application_frameworks (
    id bigint primary key not null auto_increment,
    fk_application_id bigint,
    fk_framework_id bigint,
    foreign key (fk_application_id) references applications (id),
    foreign key (fk_framework_id) references frameworks (id)
);