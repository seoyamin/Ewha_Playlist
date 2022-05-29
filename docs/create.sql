#CREATE DATABASE DB2022Team06;

use DB2022Team06;

create table db2022_music (
    music_id int,
    singer varchar(45) not null,
    playtime float,
    songwriter varchar(45),
    title varchar(45) not null,
    likes int,
    genre varchar(45),
    age_limit boolean not null,
    release_date Date,
    situation varchar(45) not null,
    season varchar(45) not null,
    music_age int not null,
    primary key(music_id)
);


create table db2022_user (
    user_id int,
    name varchar(45),
    nickname varchar(45),
    age int,
    favorite_genre varchar(45),
    primary key(user_id)
);

create table db2022_playlist_user (
    user_id int,
    music_id int,
    age_limit boolean,
    primary key(user_id, music_id),
    foreign key(user_id) references db2022_user(user_id),
    foreign key(music_id) references db2022_music(music_id)
);

create table db2022_playlist_age (
    music_id int,
    age_limit boolean,
    age_type int,
    primary key(music_id),
    foreign key(music_id) references db2022_music(music_id)
);

create table db2022_playlist_season (
    music_id int,
    age_limit boolean,
    season_type varchar(45),
    primary key(music_id),
    foreign key(music_id) references db2022_music(music_id)
);

create table db2022_playlist_situation (
    music_id int,
    age_limit boolean,
    situation_type varchar(45),
    primary key(music_id),
    foreign key(music_id) references db2022_music(music_id)
);

