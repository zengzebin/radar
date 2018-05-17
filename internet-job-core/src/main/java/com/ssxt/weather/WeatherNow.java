package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherNow  extends WeatherBasic implements Serializable{

	private Cond cond;//天气状况
	private String fl;	//体感温度
	private String tmp;	//温度
	protected String vis;	//能见度（km）


	/**
	 * @return the fl
	 */
	public String getFl() {
		return fl;
	}
	/**
	 * @param fl the fl to set
	 */
	public void setFl(String fl) {
		this.fl = fl;
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
	/**
	 * @return the cond
	 */
	public Cond getCond() {
		return cond;
	}
	/**
	 * @param cond the cond to set
	 */
	public void setCond(Cond cond) {
		this.cond = cond;
	}
	/**
	 * @return the vis
	 */
	public String getVis() {
		return vis;
	}
	/**
	 * @param vis the vis to set
	 */
	public void setVis(String vis) {
		this.vis = vis;
	}
}
