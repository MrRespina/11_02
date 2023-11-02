package com.ji.bigdata005;

import java.util.ArrayList;

public class EarthMain {

	public static void main(String[] args) {

		ArrayList<EarthQuake> eq1 = new ArrayList<EarthQuake>();
		ArrayList<EarthQuake> eq2 = new ArrayList<EarthQuake>();
		
		//eq1 = getEarthInfo();
		//printEarthInfo(eq1);		
		//insertEarth(eq1);
		
		eq2 = selectEarth();
		printEarthInfo(eq2);	
		//createCSV(eq2);
		
	}

	// 1-1 ArrayList 생성하여 DAO로부터 생성된 ArrayList 받고 출력()
	public static ArrayList<EarthQuake> getEarthInfo() {

		ArrayList<EarthQuake> aleq = EarthDAO.getEarthQuakeInfo();

		return aleq;

	}

	// 1-2 ArrayList에 객체 담겼나 확인.
	public static void printEarthInfo(ArrayList<EarthQuake> aleq) {

		int cnt;
		System.out.println("===== 출력 시작 =====");
		
		for (cnt = 0; cnt < aleq.size(); cnt++) {

			System.out.println("PK : " + aleq.get(cnt).getNum());
			System.out.println("시군구명 : " + aleq.get(cnt).getSgg_nm());
			System.out.println("시설명 : " + aleq.get(cnt).getEqup_nm());
			System.out.println("경도 : " + aleq.get(cnt).getXcord());
			System.out.println("위도 : " + aleq.get(cnt).getYcord());
			System.out.println("=================");

		}
		
		System.out.println(cnt +"개 출력 완료됨.");

	}
	
	// 2-1 DB에 받은 ArrayList 삽입
	public static void insertEarth(ArrayList<EarthQuake> aleq) {
		
		String result = EarthDAO.insertEarthQuake(aleq);
		printResult(result);
		
	}
	
	// 2-2 성공or실패or에러 확인용 메소드
	public static void printResult(String result) {
		
		System.out.println("=== 처리 완료 ===");
		System.out.println(result);
		
	}
	
	// 3 DB에 있는 정보들 SELECT 문으로 가져오기, 출력은 1-2에서 만든 printEarthInfo로 실험.
	public static ArrayList<EarthQuake> selectEarth() {
		
		ArrayList<EarthQuake> aleq = EarthDAO.selectEarthQuake();
		return aleq;

	}
	
	// 4 DB에 있는 정보 CSV 파일로 만들기.	
	public static void createCSV(ArrayList<EarthQuake> aler) {
		
		EarthDAO.createEarthCSV(aler);
		
	}
	
}
