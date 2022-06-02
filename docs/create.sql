# CREATE DATABASE DB2022Team06 default character set utf8 collate utf8_unicode_ci;

use DB2022Team06;

# 각 곡의 작사작곡가 정보를 담은 테이블 생성 - music id, 작사작곡가명
create table db2022_songwriter (
    music_id int,
    songwriter varchar(45),
    primary key(music_id, songwriter)
) default character set utf8 collate utf8_unicode_ci;

create index index_songwriter on db2022_songwriter(music_id, songwriter);

INSERT INTO db2022_songwriter VALUES
(1,"제시"),
(1,"싸이"),
(1,"bobblehead"),
(1,"염따"),
(1,"유건형"),
(1,"bayb"),
(2,"소연"),
(2,"네이슨"),
(3,"Belcalis Almanzar"),
(3,"Megan Pete"),
(3,"Austin Owens,"),
(3,"James Foye III"),
(3,"Frank Rodriguez"),
(3,"Jorden Thorpe"),
(4,"Lourdiz"),
(4,"B Ham"),
(4,"Ryan OG"),
(4,"Mike Crook"),
(4,"Doja Cat & Tyga"),
(5,"Montero Hill"),
(5,"Denzel Baptiste"),
(5,"David Biral"),
(5,"Omer Fedi"),
(5,"Rosario Lenzo"),
(6,"GAYLE"),
(6,"Sara Davis"),
(6,"David Pittenger"),
(7,"Alec Benjamin"),
(7,"Nolan Lambroza"),
(7,"Michael Pollack"),
(8,"Ariana Grande"),
(8,"Savan Kotecha"),
(8,"Ilya"),
(9,"Justin Bieber"),
(9,"Miles Ale"),
(9,"Louis Bell"),
(9,"Felisha King)"),
(9,"Ashton Simmonds"),
(9,"Giveon Evans"),
(10,"Toni Watson"),
(11,"이건우"),
(11,"신철"),
(11,"윤일상"),
(12,"김이나"),
(12,"조영수"),
(13,"강은경"),
(13,"조영수"),
(14,"박건호"),
(14,"조용필"),
(15,"최비룡"),
(15,"최고야"),
(16,"KENZIE"),
(16,"Moonshine"),
(16,"Adrian Mckinnon"),
(17,"종현"),
(17,"LDN Noise"),
(17,"Ryan S. Jhun"),
(17,"Adrian Mckinnon"),
(18,"서지음"),
(18,"Sophia Brennan"),
(18,"Elle Campbell"),
(18,"Nick Hahn"),
(19,"J.Y.Park"),
(19,"Ollipop"),
(19,"Hayley Aitken"),
(20,"KENZIE"),
(20,"Thomas Troelsen"),
(20,"Mikkel Remee Sigvardt"),
(21,"김이나"),
(21,"박근철"),
(22,"이상은"),
(23,"방시혁"),
(24,"이소라"),
(24,"이승환"),
(25,"이찬혁"),
(26,"휘성"),
(26,"황찬희"),
(27,"황소윤"),
(28,"Billie Joe Armstrong"),
(28,"Mike Dirnt"),
(28,"Tré Cool"),
(29,"Brian Harold May"),
(30,"김윤아");

# 각 곡의 가수 정보를 담은 테이블 생성 - music id, 가수명
create table db2022_singer (
    music_id int,
    singer varchar(45),
    primary key(music_id, singer)
) default character set utf8 collate utf8_unicode_ci;

create index index_singer on db2022_singer(music_id, singer);

INSERT INTO db2022_singer VALUES
(1,"제시"),
(2,"(여자)아이들"),
(3,"Cardi B (feat. Megan Thee Stallion)"),
(4,"Tyga"),
(4,"Doja Cat"),
(5,"Lil Nas X"),
(6,"GAYLE"),
(7,"Alec Benjamin"),
(8,"Ariana Grande"),
(9,"Justin Bieber (Feat. Daniel Caesar & Giveon)"),
(10,"Tones And I"),
(11,"김연자"),
(12,"임영웅"),
(13,"홍진영"),
(14,"조용필"),
(15,"송가인"),
(16,"NCT DREAM"),
(17,"SHINee"),
(18,"IVE"),
(19,"TWICE"),
(20,"f(x)"),
(21,"하울"),
(21,"제이"),
(22,"이상은"),
(23,"백지영"),
(24,"이소라"),
(25,"AKMU (악뮤)"),
(26,"윤하"),
(27,"새소년"),
(28,"Green Day"),
(29,"Queen"),
(30,"자우림");

# 각 곡의 정보를 담은 테이블 생성 - 재생시간, 제목, 좋아요 수, 장르, 나이제한 여부, 발매일, 상황 추천, 계절 추천, 나이대 추천
create table db2022_music (
    music_id int AUTO_INCREMENT,
    playtime Time, 
    title varchar(45) not null,
    likes int,
    genre varchar(45),
    age_limit boolean not null,
    release_date Date,
    situation varchar(45) not null,
    season varchar(45) not null,
    music_age int not null,
    primary key(music_id),
    check (genre in ('랩/힙합','POP','KPOP','트로트','발라드','록/메탈','없음'))
) default character set utf8 collate utf8_unicode_ci;

create index index_music on db2022_music(music_id);

INSERT INTO db2022_music(playtime,title,likes,genre,age_limit,release_date,situation,season,music_age) VALUES
("00:02:54","ZOOM",34498,"랩/힙합",false,"2022-04-13","운동","여름",10 ),
("00:02:40","MY BAG",51204,"랩/힙합",false,"2022-03-14","출퇴근","여름",10 ),
("00:03:07","WAP",28958,"랩/힙합",true,"2020-08-07","운동","겨울",30 ),
("00:03:35","Freaky Deaky",3695,"랩/힙합",true,"2022-02-25","공부","가을",20 ),
("00:02:17","MONTERO(Call Me By Your Name)",23000,"랩/힙합",true,"2021-04-23","여행","봄",20 ),
("00:02:48","abcdefu",92667,"POP",true,"2021-08-13","공부","가을",20 ),
("00:02:49","Let Me Down Slowly",38428,"POP",false,"2018-11-16","공부","봄",10 ),
("00:03:24","Santa Tell Me",215428,"POP",false,"2014-11-24","여행","겨울",30 ),
("00:03:18","Peaches",213596,"POP",true,"2021-10-08","여행","봄",20 ),
("00:03:29","Dance Monkey",169935,"POP",false,"2019-05-10","출퇴근","가을",30 ),
("00:03:38","아모르 파티",72806,"트로트",false,"2013-07-23","운동","여름",50 ),
("00:04:02","이젠 나만 믿어요",154066,"트로트",false,"2020-04-03","여행","겨울",50 ),
("00:03:26","사랑의 배터리",29114,"트로트",false,"2009-06-19","운동","여름",40 ),
("00:03:48","단발머리",7292,"트로트",false,"1980-03-20","여행","겨울",60 ),
("00:03:57","트로트가 나는 좋아요",8436,"트로트",false,"2020-12-26","운동","여름",60 ),
("00:03:40","Hello Future",99591,"KPOP",false,"2021-06-28","여행","여름",10 ),
("00:03:12","View",136749,"KPOP",false,"2015-05-18","운동","여름",20 ),
("00:02:57","LOVE DIVE",118304,"KPOP",false,"2022-04-05","공부","봄",10 ),
("00:03:26","Feel Special",106520,"KPOP",false,"2019-09-23","공부","가을",10 ),
("00:03:45","Hot Summer",37872,"KPOP",false,"2011-06-14","운동","여름",20 ),
("00:04:35","Perhaps Love (사랑인가요)",73707,"발라드",false,"2006-01-26","출퇴근","봄",30 ),
("00:04:11","비밀의 화원",26219,"발라드",false,"2003-03-10","출퇴근","봄",30 ),
("00:03:59","총맞은것처럼",40509,"발라드",false,"2008-11-13","출퇴근","봄",30 ),
("00:03:56","바람이 분다",109102,"발라드",false,"2004-12-10","공부","겨울",30 ),
("00:04:50","어떻게 이별까지 사랑하겠어, 널 사랑하는 거지",381043,"발라드",false,"2019-09-25","여행","겨울",30 ),
("00:03:48","비밀번호 486",57584,"록/메탈",false,"2007-03-15","여행","겨울",30 ),
("00:03:48","집에",10579,"록/메탈",false,"2019-10-04","출퇴근","가을",20 ),
("00:02:54","American Idiot",6010,"록/메탈",true,"2004-09-21","운동","봄",30 ),
("00:02:01","We Will Rock You",31157,"록/메탈",false,"1977-10-07","운동","가을",20 ),
("00:04:45","스물다섯, 스물하나",185117,"록/메탈",false,"2022-04-13","여행","가을",40 );

# 유저 정보 테이블 생성 - user id, 이름, 닉네임, 나이, 선호 장르
create table db2022_user (
    user_id int AUTO_INCREMENT,
    name varchar(45),
    nickname varchar(45) unique,
    age int,
    favorite_genre varchar(45),
    primary key(user_id)
) default character set utf8 collate utf8_unicode_ci;

create index index_music on db2022_user(user_id);

# 마이 플레이리스트 테이블 생성 - user id, music id, 곡의 나이 제한 여부, 곡의 장르
create table db2022_playlist_user (
    user_id int ,
    music_id int,
    genre varchar(45),
    age_limit boolean,
    primary key(user_id, music_id),
    foreign key(user_id) references db2022_user(user_id),
    foreign key(music_id) references db2022_music(music_id)
) default character set utf8 collate utf8_unicode_ci;

create view db2022_all_song_adult as
    select title, singer
    from db2022_music natural join db2022_singer
    where db2022_music.music_id = db2022_singer.music_id;
    
create view db2022_all_song_minor as
    select title, singer
    from db2022_music natural join db2022_singer
    where db2022_music.music_id = db2022_singer.music_id and db2022_music.age_limit=false;