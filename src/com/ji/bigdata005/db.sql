CREATE TABLE earth_quake(
	num number(4) primary key,
	SGG_NM varchar2(10 char) not null,
	EQUP_NM varchar2(35 char) not null,
	XCORD number(9,6) not null,
	YCORD number(8,6) not null
);

CREATE SEQUENCE earth_quake_seq;
DROP SEQUENCE earth_quake_seq;

DROP TABLE earth_quake cascade constraint purge;
insert into earth_quake VALUES(earth_quake_seq.nextval,'A','B','서울시행복동',123.06,63.183);

SELECT * FROM earth_quake;

TRUNCATE TABLE earth_quake;

SELECT * FROM earth_quake ORDER BY sgg_nm ASC;