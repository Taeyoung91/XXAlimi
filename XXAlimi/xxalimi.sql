create table users (
	user_num	integer		not null	auto_increment	comment '사용자번호',
    username	varchar(50)	not null	comment '이메일',
    password	varchar(100) not null	comment '비밀번호',
    name		varchar(20)	not null	comment '사용자이름',
    role		varchar(20)	not null	comment '권한',
    cre_date	datetime	not null	comment '가입일',
    enabled		tinyint(4)	not null	comment 'default',
    constraint	pk_user_num	primary key	(user_num)
)
comment '사용자정보';


create unique index uix_users on users ( username asc );


CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);

create table  feeds (
      feed_id         integer		not null    auto_increment  comment '피드번호',
      username        varchar(50)		not null	comment '이메일',
      feedlink        varchar(200)		not null	comment '피드주소',
      usersfeedtitle		  varchar(250)			not null	comment '사용자+피드주소',
      feedtitle       varchar(100)			not null    comment '피드명',
      feedauthor      varchar(50)			default null	comment '피드저자',
      copyright       varchar(50)  			default null 	comment 'copyright',
      imageurl        varchar(100) 			default null  comment '이미지주소',
      language        varchar(10) 			default null  comment '피드언어',
      pub_date        varchar(45) 			default null  comment '등록날짜',
      cre_date		  datetime			not null		comment '가입일',
      constraint      pk_feed_id      	primary key     (feed_id),
      constraint      fk_username2     foreign key     (username)      references users (username)
      on delete cascade
      on update cascade
     )
 comment '피드정보';
 
 create unique index uix_feeds on feeds ( usersfeedtitle asc );
 
 create table articles (
      article_id		integer 		not null	auto_increment	comment '기사번호',
      usersfeedtitle		  varchar(250)			not null	comment '사용자+피드주소',
      username			varchar(50)		not null					comment '이메일',
      articlelink       varchar(500)    default null 				comment '기사주소',
      feedtitle       varchar(100)			not null    comment '피드명',
      feedlink        varchar(200)	not null	comment '피드주소',
      articleauthority  varchar(100)     							comment '기사작성자',
      articletitle    	varchar(100)  	default null   				comment '기사제목',
      content         	text  			default null  				comment '기사내용',
      pub_date        varchar(45) 			default null  comment '등록날짜',
      cre_date			datetime		not null					comment '가입일',
      constraint      	pk_article_id   primary key     (article_id),
      constraint      fk_usersfeedtitle     foreign key     (usersfeedtitle)      references feeds (usersfeedtitle)
      on delete cascade
      on update cascade
     )
comment '기사정보';

 create table Keywords (
      keyword_id		integer 		not null	auto_increment	comment '기사번호',
      username			varchar(50)		not null					comment '이메일',
      feedtitle       varchar(100)			not null    comment '피드명',
      keyword			varchar(30)		not null		comment '키워드',
      cre_date			datetime		not null					comment '키워드등록일',
      constraint      	pk_Keyword_id   primary key     (Keyword_id),
      constraint      fk_userskeyword     foreign key     (username)      references users (username)
      on delete cascade
      on update cascade
     )
comment '키워드정보';

alter table keywords add feedlink varchar(200) not null;