package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Suggestion implements Serializable{
	
	private SuggestionDetail comf;	//舒适度指数
	private SuggestionDetail cw;	//洗车指数
	private SuggestionDetail drsg;	//穿衣指数
	private SuggestionDetail flu;	//感冒指数
	private SuggestionDetail sport;	//运动指数
	private SuggestionDetail trav;	//旅游指数
	private SuggestionDetail uv;	//紫外线指数
	/**
	 * @return the comf
	 */
	public SuggestionDetail getComf() {
		return comf;
	}
	/**
	 * @param comf the comf to set
	 */
	public void setComf(SuggestionDetail comf) {
		this.comf = comf;
	}
	/**
	 * @return the cw
	 */
	public SuggestionDetail getCw() {
		return cw;
	}
	/**
	 * @param cw the cw to set
	 */
	public void setCw(SuggestionDetail cw) {
		this.cw = cw;
	}
	/**
	 * @return the drsg
	 */
	public SuggestionDetail getDrsg() {
		return drsg;
	}
	/**
	 * @param drsg the drsg to set
	 */
	public void setDrsg(SuggestionDetail drsg) {
		this.drsg = drsg;
	}
	/**
	 * @return the flu
	 */
	public SuggestionDetail getFlu() {
		return flu;
	}
	/**
	 * @param flu the flu to set
	 */
	public void setFlu(SuggestionDetail flu) {
		this.flu = flu;
	}
	/**
	 * @return the sport
	 */
	public SuggestionDetail getSport() {
		return sport;
	}
	/**
	 * @param sport the sport to set
	 */
	public void setSport(SuggestionDetail sport) {
		this.sport = sport;
	}
	/**
	 * @return the trav
	 */
	public SuggestionDetail getTrav() {
		return trav;
	}
	/**
	 * @param trav the trav to set
	 */
	public void setTrav(SuggestionDetail trav) {
		this.trav = trav;
	}
	/**
	 * @return the uv
	 */
	public SuggestionDetail getUv() {
		return uv;
	}
	/**
	 * @param uv the uv to set
	 */
	public void setUv(SuggestionDetail uv) {
		this.uv = uv;
	}
}
class SuggestionDetail implements Serializable{
    private String brf;	//名称
    private String txt;	//说明
	/**
	 * @return the brf
	 */
	public String getBrf() {
		return brf;
	}
	/**
	 * @param brf the brf to set
	 */
	public void setBrf(String brf) {
		this.brf = brf;
	}
	/**
	 * @return the txt
	 */
	public String getTxt() {
		return txt;
	}
	/**
	 * @param txt the txt to set
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
}