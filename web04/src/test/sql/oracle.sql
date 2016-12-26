CREATE TABLE connecter_info
(
   ano       number(11)  PRIMARY KEY ,
   m_addr    varchar(30),
   m_port    varchar(10),
   m_agent   varchar(300)
);



CREATE TABLE guestbook
(
   idx         number(11) PRIMARY KEY ,
   name        varchar(50),
   email       varchar(50),
   passwd      varchar(50),
   content     varchar2(1000),
   post_date   DATE default SYSDATE
);




--상품 상세

CREATE TABLE product
(
   product_id             number(11)  PRIMARY KEY ,
   product_name           varchar(200),
   price                  number(6),
   description            varchar2(1000),
   picture_url            varchar(500),
   regdate                DATE default SYSDATE,
   original_picture_url   varchar(200)
);


CREATE TABLE tbl_member
(
   userid       varchar(50) PRIMARY key,
   userpw       varchar(50),
   username     varchar(50),
   email        varchar(100),
   regdate      date DEFAULT sysdate,
   updatedate   date DEFAULT sysdate,
   tel          varchar(20),
   address      varchar(200)
);


INSERT INTO tbl_member(userid,
                       userpw,
                       username,
                       email,
                       regdate,
                       updatedate,
                       tel,
                       address)
     VALUES ('qpeet', '111',
             '홍길동',
             'junhocom876@gmail.com',
             null,
             null,
             010-353-2323,
             '경기도 수원시');





INSERT INTO tbl_member(userid,
                       userpw,
                       username,
                       email,
                       regdate,
                       updatedate,
                       tel,
                       address)
     VALUES ('braverokmc',
             '1111',
             '최준호',
             'braverokmc@gmail.com',
             null,
             null,
             '010-222-1111',
             NULL);



INSERT INTO product(product_id,
                    product_name,
                    price,
                    description,
                    picture_url,
                    regdate)
     VALUES ((select nvl(max(product_id)+1, 1) from product),
             '레몬',
             1500,
             '풍부한 레몬 ----',
             '/resources/template/img/page3-img1.jpg',
             null);



INSERT INTO product(product_id,
                    product_name,
                    price,
                    description,
                    picture_url,
                    regdate)
     VALUES ((select nvl(max(product_id)+1, 1) from product),
             '사과',
             2500,
             '풍부한 레몬 ----',
             '/resources/template/img/page3-img2.jpg',
             null);



INSERT INTO guestbook(idx,
                      name,
                      email,
                      passwd,
                      content,
                      post_date)
     VALUES ((select nvl(max(idx)+1, 1) from guestbook),
             'kim',
             'braverokmc@gmail.com',
             '1234',
             '방명록 내용',
             null);

INSERT INTO guestbook(idx,
                      name,
                      email,
                      passwd,
                      content,
                      post_date)
     VALUES ( (select nvl(max(idx)+1, 1) from GUESTBOOK),
             '홍길동',
             'kert@gmail.com',
             '1111',
             '라라라랄라',
             null);


             
insert into tbl_member (userid, userpw, username, email, regdate, updatedate, tel, address) 
 VALUES('admin', '1111', '관리자', 'braverokmc79@gmail.com', null, null, '112', '119');
 
 
COMMIT;




--장바구니 테이블
create table cart(
 
 idx number PRIMARY KEY  ,
 userid VARCHAR(50) not null,
 product_id int not null,
 amount int default 1
 );

insert into cart (idx, USERID, product_id, amount ) values( (select nvl(max(idx)+1, 1) from cart), 'braverokmc', 1, 10 );
commit;
--뷰 테이블 생성
 
create or replace view cart_v as

 select idx, product_name, price, amount, m.userid as userid , p.product_id as product_id, 
	picture_url,
 price*amount as money
 from cart c , tbl_member m, product p
 where c.userid=m.userid
 and c.product_id=p.product_id
 order by idx;
 
commit;
 
-- 쿼리 합계
select sum(money) money,
	case
		when sum(money) >= 50000 then 0
		when sum(money) < 50000 then 2500
	end as fee

from cart_v
where userid='admin';
 

-- 메모 테이블
create table memo (

 idx int  primary key,
 writer varchar(100),
 memo varchar2(1000),
 write_date date  default sysdate

);



--게시판 테이블

create table board(

	idx number not null primary key  ,
	userid VARCHAR(50) not null,
	subject VARCHAR(100) not null,
	content VARCHAR2(1000) not null,
	hit number DEFAULT 0  ,
	post_date date DEFAULT sysdate,
	filename VARCHAR (100),
	filesize LONG ,
	down number default 0 ,
	ref number default 0  ,
	depth number default 0 ,
	reorder number default 0 
);


--글쓰기 (답변이 아닌 경우)

insert INTO board (idx, userid, subject, content,  ref)

 	values ((select nvl(max(idx)+1, 1) from board), 'braverokmc', '제목', '내용', (select nvl(max(idx)+1, 1) from board));


-- 이름이 나오게 하기 위해서 member table과 조인
-- 
select idx, username, subject, post_date, hit, ref, depth,	
	reorder from board b , tbl_member m
	where b.userid =m.userid
	order by b.idx desc; 

	
	
create or replace view board_v as 
select idx, username, subject, post_date, hit, ref, depth, down, filename,	
	reorder from board b , tbl_member m
	where b.userid =m.userid
	order by b.idx desc; 



DECLARE
  i number :=1;
  
  begin 
    while i <= 567 loop
     insert into board (idx, userid, subject, content, ref) values
     ( (select nvl(max(idx)+1, 1) from board) , 'braverokmc', '제목은', '내용' ,
     ( select nvl(max(idx)+1, 1) from board ) );
     i :=i+1 ;
  end loop ;

end; 

commit;


select idx, userid, username, subject, hit, post_date, rn
 
from
 	(
	select A.*, rownum as rn
	 from
	(
		select b.IDX , b.USERID, m.username, b.SUBJECT, b.HIT, b.POST_DATE   from board b, tbl_member m
		
		where  b.userid =m.userid
		order by idx desc
	) A

)
 where  rn BETWEEN 0 and 10;


select * from board_v;


----- 댓글 페이지 나누기  쿼리



select * 
 from ( select A.*, rownum as rn
	from
	 (
	   select
		comment_idx, board_idx, userid, name, content, post_date
	   from board_comment b, member m
	   where b.userid=m.id and board_idx=572
         ) A
)
where rn between 1 and 10;








