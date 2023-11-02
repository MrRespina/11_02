package com.ji.bigdata004;

import java.math.BigDecimal;
import java.sql.Date;

//	Oracle 자료형 vs JAVA 자료형
//	varchar2		String
//	date			Date
//	number			BigDecimal (빅데이터에서 주력으로 사용)

//	**BigDecimal : 정수, 실수 모드 소화 가능한 자료형이다.
//	BidDecimal bd = new BigDecimal(value);

public class Weather {

	private int weather_num;
	private String weather_name;
	private Date weather_when;
	private String weather_descrption;
	private BigDecimal weather_temp;
	private BigDecimal weather_feels_like;
	private BigDecimal weather_temp_min;
	private BigDecimal weather_temp_max;
	private BigDecimal weather_pressure;
	private BigDecimal weather_humidity;
	
	public Weather(int weather_num, String weather_name, Date weather_when, String weather_descrption,
			BigDecimal weather_temp, BigDecimal weather_feels_like, BigDecimal weather_temp_min,
			BigDecimal weather_temp_max, BigDecimal weather_pressure, BigDecimal weather_humidity) {
		super();
		this.weather_num = weather_num;
		this.weather_name = weather_name;
		this.weather_when = weather_when;
		this.weather_descrption = weather_descrption;
		this.weather_temp = weather_temp;
		this.weather_feels_like = weather_feels_like;
		this.weather_temp_min = weather_temp_min;
		this.weather_temp_max = weather_temp_max;
		this.weather_pressure = weather_pressure;
		this.weather_humidity = weather_humidity;
	}

	public Weather() {
		super();
	}

	public int getWeather_num() {
		return weather_num;
	}

	public void setWeather_num(int weather_num) {
		this.weather_num = weather_num;
	}

	public String getWeather_name() {
		return weather_name;
	}

	public void setWeather_name(String weather_name) {
		this.weather_name = weather_name;
	}

	public Date getWeather_when() {
		return weather_when;
	}

	public void setWeather_when(Date weather_when) {
		this.weather_when = weather_when;
	}

	public String getWeather_descrption() {
		return weather_descrption;
	}

	public void setWeather_descrption(String weather_descrption) {
		this.weather_descrption = weather_descrption;
	}

	public BigDecimal getWeather_temp() {
		return weather_temp;
	}

	public void setWeather_temp(BigDecimal weather_temp) {
		this.weather_temp = weather_temp;
	}

	public BigDecimal getWeather_feels_like() {
		return weather_feels_like;
	}

	public void setWeather_feels_like(BigDecimal weather_feels_like) {
		this.weather_feels_like = weather_feels_like;
	}

	public BigDecimal getWeather_temp_min() {
		return weather_temp_min;
	}

	public void setWeather_temp_min(BigDecimal weather_temp_min) {
		this.weather_temp_min = weather_temp_min;
	}

	public BigDecimal getWeather_temp_max() {
		return weather_temp_max;
	}

	public void setWeather_temp_max(BigDecimal weather_temp_max) {
		this.weather_temp_max = weather_temp_max;
	}

	public BigDecimal getWeather_pressure() {
		return weather_pressure;
	}

	public void setWeather_pressure(BigDecimal weather_pressure) {
		this.weather_pressure = weather_pressure;
	}

	public BigDecimal getWeather_humidity() {
		return weather_humidity;
	}

	public void setWeather_humidity(BigDecimal weather_humidity) {
		this.weather_humidity = weather_humidity;
	}
	
}
