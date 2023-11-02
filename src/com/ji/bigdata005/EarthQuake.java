package com.ji.bigdata005;

import java.math.BigDecimal;

public class EarthQuake {
	
	private int num;
	private String sgg_nm;
	private String equp_nm;
	private BigDecimal xcord;
	private BigDecimal ycord;
	
	public EarthQuake(int num, String sgg_nm, String equp_nm, BigDecimal xcord, BigDecimal ycord) {
		super();
		this.num = num;
		this.sgg_nm = sgg_nm;
		this.equp_nm = equp_nm;
		this.xcord = xcord;
		this.ycord = ycord;
	}
	
	public EarthQuake() {
		super();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSgg_nm() {
		return sgg_nm;
	}

	public void setSgg_nm(String sgg_nm) {
		this.sgg_nm = sgg_nm;
	}

	public String getEqup_nm() {
		return equp_nm;
	}

	public void setEqup_nm(String equp_nm) {
		this.equp_nm = equp_nm;
	}

	public BigDecimal getXcord() {
		return xcord;
	}

	public void setXcord(BigDecimal xcord) {
		this.xcord = xcord;
	}

	public BigDecimal getYcord() {
		return ycord;
	}

	public void setYcord(BigDecimal ycord) {
		this.ycord = ycord;
	}

	

}
