package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherBasic implements Serializable{
	protected String hum;	//相对湿度（%）
	protected String pcpn;	//降水量（mm）
	protected String pres;	//气压
//	protected String tmp;	//温度
	protected Wind wind;	



	/**
	 * @return the hum
	 */
	public String getHum() {
		return hum;
	}

	/**
	 * @param hum the hum to set
	 */
	public void setHum(String hum) {
		this.hum = hum;
	}

	/**
	 * @return the pcpn
	 */
	public String getPcpn() {
		return pcpn;
	}

	/**
	 * @param pcpn the pcpn to set
	 */
	public void setPcpn(String pcpn) {
		this.pcpn = pcpn;
	}

	/**
	 * @return the pres
	 */
	public String getPres() {
		return pres;
	}

	/**
	 * @param pres the pres to set
	 */
	public void setPres(String pres) {
		this.pres = pres;
	}

	/**
	 * @return the wind
	 */
	public Wind getWind() {
		return wind;
	}

	/**
	 * @param wind the wind to set
	 */
	public void setWind(Wind wind) {
		this.wind = wind;
	}

}
