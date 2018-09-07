
CREATE TABLE ex (
	exid NUMBER(4) primary key,
	title VARCHAR(50) unique,
	startdate VARCHAR(10),
	enddate VARCHAR(10),
	placeid NUMBER(3),
	price NUMBER(5) CHECK (price > 0)
);

CREATE TABLE places (
	placeid NUMBER(3) primary key,
	placename VARCHAR(40) unique,
	closedday VARCHAR(10),
	locid NUMBER(3)
);

CREATE TABLE loc (
	locid NUMBER(3) primary key,
	locname VARCHAR(40) unique
);

CREATE TABLE users (
	userid VARCHAR(10) primary key,
	pw VARCHAR(10),
	resexid NUMBER(4)
);

CREATE TABLE reviews (
	reviewid NUMBER(4),
	exid NUMBER(4),
	userid VARCHAR(10),
	grade NUMBER(3),
	review VARCHAR(100)
);


INSERT INTO ex
VALUES (0001, '모니카와 떠나는 세계명화 여행전', '2018.07.23', '2018.09.26', 001, 13000);
  
INSERT INTO places
VALUES(001, '홍익대학교 대학로 아트센터 갤러리', '없음', 01);

INSERT INTO loc
VALUES (001, '서울');
  
INSERT INTO users
VALUES ('ex', 'ex', 0001);

INSERT INTO reviews
VALUES (0001, 0001, 'ex', 5, 'hi');
  