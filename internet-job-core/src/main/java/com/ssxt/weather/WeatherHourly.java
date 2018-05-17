package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherHourly extends WeatherBasic  implements Serializable{
	private String date;	//时间
    private String pop;	//降水概率
    private String tmp;	//温度
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the pop
	 */
	public String getPop() {
		return pop;
	}
	/**
	 * @param pop the pop to set
	 */
	public void setPop(String pop) {
		this.pop = pop;
	}
	/**
	 * @return the tmp
	 */
	public String getTmp() {
		return tmp;
	}
	/**
	 * @param tmp the tmp to set
	 */
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
}
