package com.ji.bigdata005;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chung.db.managaer.JiDBManager;
import com.ji.http001.JiHttpClient;

public class EarthDAO {

	// JSON Parsing with Return
	public static ArrayList<EarthQuake> getEarthQuakeInfo() {

		String addr = "http://openapi.seoul.go.kr:8088/70646b63596a69683437716f794e49/json/TlEtqkP/1/50/";
		InputStream is = null;
		EarthQuake eq = null;
		ArrayList<EarthQuake> aleq = new ArrayList<EarthQuake>();

		try {
			
			is = JiHttpClient.download(addr); // addr로 연결
			String str = JiHttpClient.convert(is, "UTF-8");
			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(str);

			JSONObject jo2 = (JSONObject) jo.get("TlEtqkP");
			JSONArray ja = (JSONArray) jo2.get("row");

			for (int i = 0; i < ja.size(); i++) {

				jo2 = (JSONObject) ja.get(i);
				eq = new EarthQuake(i + 1, jo2.get("SGG_NM").toString(), jo2.get("EQUP_NM").toString(),
						new BigDecimal(jo2.get("XCORD").toString()), new BigDecimal(jo2.get("YCORD").toString()));
				aleq.add(eq);

			}

			is.close();
			return aleq;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}

	}

	// Insert Into DB Table
	public static String insertEarthQuake(ArrayList<EarthQuake> aleq) {

		String q = "";
		PreparedStatement pstmt = null;
		Connection conn = null;

		try {

			conn = JiDBManager.connect();

			for (int i = 0; i < aleq.size(); i++) {

				q = "INSERT INTO earth_quake VALUES(?,?,?,?,?)";
				pstmt = conn.prepareStatement(q);
				pstmt.setInt(1, aleq.get(i).getNum());
				pstmt.setString(2, aleq.get(i).getSgg_nm());
				pstmt.setString(3, aleq.get(i).getEqup_nm());
				pstmt.setBigDecimal(4, aleq.get(i).getXcord());
				pstmt.setBigDecimal(5, aleq.get(i).getYcord());
				System.out.println((i + 1) + "번째 데이터 입력됨.");
				pstmt.executeUpdate();

			}

			return "DB 입력 성공";

		} catch (Exception e) {
			e.printStackTrace();
			return "DB 입력 오류";
		} finally {
			JiDBManager.close(conn, pstmt, null);
		}

	}

	// SELECT DB
	public static ArrayList<EarthQuake> selectEarthQuake() {

		ArrayList<EarthQuake> aleq = new ArrayList<EarthQuake>();
		String q = "SELECT * FROM earth_quake ORDER BY sgg_nm ASC";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		EarthQuake eq = null;

		try {

			conn = JiDBManager.connect();
			pstmt = conn.prepareStatement(q);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				eq = new EarthQuake(rs.getInt("num"), rs.getString("sgg_nm"), rs.getString("equp_nm"),
						rs.getBigDecimal("xcord"), rs.getBigDecimal("ycord"));

				aleq.add(eq);

			}

			return aleq;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		} finally {
			JiDBManager.close(conn, pstmt, rs);
		}

	}

	// CREATE CSV
	public static String createEarthCSV(ArrayList<EarthQuake> aleq) {

		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream("C:/Users/sdedu/Desktop/Dev/Example/EarthQuake.CSV", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);

			for (int i = 0; i < aleq.size(); i++) {

				bw.write(aleq.get(i).getNum() + ",");
				bw.write(aleq.get(i).getSgg_nm() + ",");
				bw.write(aleq.get(i).getEqup_nm() + ",");
				bw.write(aleq.get(i).getXcord() + ",");
				bw.write(aleq.get(i).getYcord() + "\n");	
				bw.flush();

			}

			bw.close();
			osw.close();
			fos.close();
			
			return "출력 성공";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "출력 실패";
			
		}

	}

}
