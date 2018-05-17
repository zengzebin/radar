package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Aqi implements Serializable{

	private CityAqi city ;

	/**
	 * @return the city
	 */
	public CityAqi getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(CityAqi city) {
		this.city = city;
	}
}

@SuppressWarnings("serial")
class CityAqi implements Serializable{
	private String aqi; // 空气质量指数
	private String co; // 一氧化碳1小时平均值(ug/m³)
	private String no2; // 二氧化氮1小时平均值(ug/m³)
	private String o3; // 臭氧1小时平均值(ug/m³)
	private String pm10; // PM10 1小时平均值(ug/m³)
	private String pm25; // PM2.5 1小时平均值(ug/m³)
	private String qlty; // 空气质量类别
	private String so2; // 二氧化硫1小时平均值(ug/m³)

	/**
	 * @return the aqi
	 */
	public String getAqi() {
		return aqi;
	}

	/**
	 * @param aqi
	 *            the aqi to set
	 */
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	/**
	 * @return the co
	 */
	public String getCo() {
		return co;
	}

	/**
	 * @param co
	 *            the co to set
	 */
	public void setCo(String co) {
		this.co = co;
	}

	/**
	 * @return the no2
	 */
	public String getNo2() {
		return no2;
	}

	/**
	 * @param no2
	 *            the no2 to set
	 */
	public void setNo2(String no2) {
		this.no2 = no2;
	}

	/**
	 * @return the o3
	 */
	public String getO3() {
		return o3;
	}

	/**
	 * @param o3
	 *            the o3 to set
	 */
	public void setO3(String o3) {
		this.o3 = o3;
	}

	/**
	 * @return the pm10
	 */
	public String getPm10() {
		return pm10;
	}

	/**
	 * @param pm10
	 *            the pm10 to set
	 */
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	/**
	 * @return the pm25
	 */
	public String getPm25() {
		return pm25;
	}

	/**
	 * @param pm25
	 *            the pm25 to set
	 */
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	/**
	 * @return the qlty
	 */
	public String getQlty() {
		return qlty;
	}

	/**
	 * @param qlty
	 *            the qlty to set
	 */
	public void setQlty(String qlty) {
		this.qlty = qlty;
	}

	/**
	 * @return the so2
	 */
	public String getSo2() {
		return so2;
	}

	/**
	 * @param so2
	 *            the so2 to set
	 */
	public void setSo2(String so2) {
		this.so2 = so2;
	}
}