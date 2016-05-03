create table users (
	user_num	integer		not null	auto_increment	comment '사용자번호',
    username	varchar(20)	not null	comment '이메일',
    password	varchar(100) not null	comment '비밀번호',
    name		varchar(20)	not null	comment '사용자이름',
    cre_date	datetime	not null	comment '가입일',
    enabled		tinyint(4)	not null	comment 'default',
    constraint	pk_user_num	primary key	(user_num)
)
comment '사용자정보';


create unique index uix_users on users ( username asc );


create table authorities (
	user_role_id	integer	not null	auto_increment	comment '권한번호',
    username		varchar(20)	not null	comment '이메일',
    authority		varchar(20)	not null	comment '권한',
    constraint pk_user_role_id primary key (user_role_id),
    constraint fk_username foreign key (username) references users (username)
)
comment '권한정보';

create table  feeds (
      feed_id         integer	not null        auto_increment  comment '피드번호',
      username        varchar(20)	not null	comment '이메일',
      feedlink        varchar(100)	not null	comment '피드주소',
      feedtitle       varchar(100)	not null    comment '피드명',
      feedauthor      varchar(20)	comment '피드저자',
      copyright       varchar(20)   comment 'copyright',
      imageurl        varchar(100)   comment '이미지주소',
      language        varchar(10)   comment '피드언어',
      pub_date        varchar(45)   comment '등록날짜',
      constraint      pk_feed_id      primary key     (feed_id),
      constraint      fk_username     foreign key     (username)      references users (username)
     )
 comment '피드정보';
 
 create unique index uix_feeds on feeds (feedlink);  
 
 create table article (
      article_id      integer not null        auto_increment  comment '기사번호',
      articlelink     varchar(100)    comment '기사주소',
      feedlink        varchar(100)    not null        comment '피드주소',
      articleauthority        varchar(50)     comment '기사작성자',
      articletitle    varchar(50)     comment '기사제목',
      content         varchar(500)    comment '기사내용',
      constraint      pk_article_id   primary key     (article_id),
      constraint      fk_feedlink     foreign key     (feedlink)      references feeds (feedlink)
     )
comment '기사정보';

create unique index uix_article on article (articlelink);