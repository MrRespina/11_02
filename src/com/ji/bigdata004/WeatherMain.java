package com.ji.bigdata004;

import java.util.ArrayList;

public class WeatherMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		//get JSON
		Weather we = getWeatherObject();
		
		//Insert
		insertWeather(we);

		//SELECT DB ORDER BY Date, With Print DB Data
		ArrayList<Weather> al = getDBInfo();
		
		//CREATE CSV File
		WriteToCSV(al);

	}
	
	public static ArrayList<Weather> getDBInfo() {
		
		ArrayList<Weather> al = WeatherDAO.getMyWeather();
		printDB(al);
		
		return al;
		
	}
	
	public static void insertWeather(Weather we) {
		
		WeatherDAO.insertInfo(we);
		
	}
	
	public static Weather getWeatherObject() {
		
		Weather we = WeatherDAO.getWeather();	
		return we;
		
	}

	public static void WriteToCSV(ArrayList<Weather> ar) {

		WeatherDAO.writeToCSV(ar);

	}

	public static void printDB(ArrayList<Weather> al) {

		for (int i = 0; i < al.size(); i++) {

			System.out.println("==========");
			System.out.println("PK : " + al.get(i).getWeather_num());
			System.out.println("입력일자 : " + al.get(i).getWeather_when());
			System.out.println("도시명 : " + al.get(i).getWeather_name());
			System.out.println("기상상태 : " + al.get(i).getWeather_descrption());
			System.out.println("온도 : " + al.get(i).getWeather_temp());
			System.out.println("체감 온도 : " + al.get(i).getWeather_feels_like());
			System.out.println("최저 온도 : " + al.get(i).getWeather_temp_min());
			System.out.println("최고 온도 : " + al.get(i).getWeather_temp_max());
			System.out.println("기압 : " + al.get(i).getWeather_pressure());
			System.out.println("습도 : " + al.get(i).getWeather_humidity());
			System.out.println("==========");
		}

	}

	public static void printInfo(Weather we) {

		System.out.println("==========");
		System.out.println("도시명 : " + we.getWeather_name());
		System.out.println("기상상태 : " + we.getWeather_descrption());
		System.out.println("온도 : " + we.getWeather_temp());
		System.out.println("체감 온도 : " + we.getWeather_feels_like());
		System.out.println("최저 온도 : " + we.getWeather_temp_min());
		System.out.println("최고 온도 : " + we.getWeather_temp_max());
		System.out.println("기압 : " + we.getWeather_pressure());
		System.out.println("습도 : " + we.getWeather_humidity());
		System.out.println("==========");

	}

}
