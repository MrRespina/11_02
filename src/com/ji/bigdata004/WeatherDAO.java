package com.ji.bigdata004;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chung.db.managaer.JiDBManager;
import com.ji.http001.JiHttpClient;

//https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=54898a19d62f84551762623123fa4564&lang=kr&units=metric

public class WeatherDAO {

	public static Weather getWeather() {

		String addr = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=54898a19d62f84551762623123fa4564&lang=kr&units=metric";
		InputStream is;
		Weather we = new Weather();

		try {

			is = JiHttpClient.download(addr);
			String str = JiHttpClient.convert(is, "UTF-8");

			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(str);

			JSONArray ja = (JSONArray) jo.get("weather");
			// description은 Array이기때문에 jo(coord(jp로 받은 JSON의 이름),최상위) > ja(weather,coord 내의
			// 배열) > jo2(description,weather 배열 내의 객체)로 가져옴.
			JSONObject jo2 = (JSONObject) ja.get(0);

			// name만 유일하게 최상위객체에 있으므로 바로 가져옴.
			we.setWeather_name(jo.get("name").toString());

			// description을 먼저 받는 이유는 main에서 받을 자료가 많아서 먼저 받음.
			we.setWeather_descrption(jo2.get("description").toString());

			jo2 = (JSONObject) jo.get("main");

			we.setWeather_temp(new BigDecimal(jo2.get("temp").toString()));

			we.setWeather_feels_like(new BigDecimal(jo2.get("feels_like").toString()));

			we.setWeather_temp_min(new BigDecimal(jo2.get("temp_min").toString()));

			we.setWeather_temp_max(new BigDecimal(jo2.get("temp_max").toString()));

			we.setWeather_pressure(new BigDecimal(jo2.get("pressure").toString()));

			we.setWeather_humidity(new BigDecimal(jo2.get("humidity").toString()));

			return we;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;

	}

	public static void insertInfo(Weather we) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {

			con = JiDBManager.connect();

			sql = "INSERT INTO weather VALUES(weather_seq.nextval,?,sysdate,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, we.getWeather_name());
			pstmt.setString(2, we.getWeather_descrption());
			pstmt.setBigDecimal(3, we.getWeather_temp());
			pstmt.setBigDecimal(4, we.getWeather_feels_like());
			pstmt.setBigDecimal(5, we.getWeather_temp_min());
			pstmt.setBigDecimal(6, we.getWeather_temp_max());
			pstmt.setBigDecimal(7, we.getWeather_pressure());
			pstmt.setBigDecimal(8, we.getWeather_humidity());

			if (pstmt.executeUpdate() == 1) {
				System.out.println("입력 성공!");
			} else {
				System.out.println("입력 실패 ㅠㅠ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JiDBManager.close(con, pstmt, null);
		}

	}

	public static ArrayList<Weather> getMyWeather() {

		ArrayList<Weather> weathers = new ArrayList<Weather>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Weather w = null;

		try {

			con = JiDBManager.connect();
			sql = "select * FROM weather ORDER BY weather_when ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				w = new Weather();

				w.setWeather_num(rs.getInt("weather_num"));
				w.setWeather_name(rs.getString("weather_name"));
				w.setWeather_when(rs.getDate("weather_when"));
				w.setWeather_descrption(rs.getString("weather_descrption"));
				w.setWeather_temp(rs.getBigDecimal("weather_temp"));
				w.setWeather_feels_like(rs.getBigDecimal("weather_feels_like"));
				w.setWeather_temp_min(rs.getBigDecimal("weather_temp_min"));
				w.setWeather_temp_max(rs.getBigDecimal("weather_temp_max"));
				w.setWeather_pressure(rs.getBigDecimal("weather_pressure"));
				w.setWeather_humidity(rs.getBigDecimal("weather_humidity"));

				weathers.add(w);

			}

			return weathers;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;

		} finally {
			JiDBManager.close(con, pstmt, rs);
		}

	}

	public static void writeToCSV(ArrayList<Weather> wers) {

		FileOutputStream fos = null;
		try {

			fos = new FileOutputStream("C:/Users/sdedu/Desktop/Dev/Example/Weather.csv", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			
			BufferedWriter bw = new BufferedWriter(osw);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,E,kk,mm");

			for (Weather we : wers) {

				bw.write(we.getWeather_num() + ",");
				bw.write(sdf.format(we.getWeather_when()) + ",");
				bw.write(we.getWeather_descrption() + ",");
				bw.write(we.getWeather_temp() + ",");
				bw.write(we.getWeather_feels_like() + ",");
				bw.write(we.getWeather_temp_min() + ",");
				bw.write(we.getWeather_temp_max() + ",");
				bw.write(we.getWeather_pressure() + ",");
				bw.write(we.getWeather_humidity() + "\n");
				bw.flush();

			}
			
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("CSV 파일 생성 완료");
		
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
