CREATE TABLE weather(
	weather_num number(4) primary key,
	weather_name VARCHAR2 (20 char) not null,
	weather_when Date not null,
	weather_descrption VARCHAR2 (20 char) not null,
	weather_temp number(4,2) not null,
	weather_feels_like number(4,2) not null,
	weather_temp_min number(4,2) not null,
	weather_temp_max number(4,2) not null,
	weather_pressure number(4) not null,
	weather_humidity number(3) not null
);

DROP TABLE weather cascade constraint purge;

INSERT INTO weather VALUES(weather_seq.nextval,'incheon',to_date('2023-11-01','YYYY-MM-DD'),'흐림',24.09,25.10,20,29,1019,70);

TRUNCATE TABLE weather;

CREATE SEQUENCE weather_seq;
DROP SEQUENCE weather_seq;

select * from weather;

select * FROM weather ORDER BY weather_when ASC;