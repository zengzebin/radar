package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherDaily  extends WeatherBasic  implements Serializable{
	private String date;	//时间
	private CondDaily cond;//天气状况
    private String pop;	//降水概率
    private Template tmp;	//温度
    private Astro astro; //天文数值  
    private String vis;
    
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
	 * @return the cond
	 */
	public CondDaily getCond() {
		return cond;
	}
	/**
	 * @param cond the cond to set
	 */
	public void setCond(CondDaily cond) {
		this.cond = cond;
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
	public Template getTmp() {
		return tmp;
	}
	/**
	 * @param tmp the tmp to set
	 */
	public void setTmp(Template tmp) {
		this.tmp = tmp;
	}
	/**
	 * @return the astro
	 */
	public Astro getAstro() {
		return astro;
	}
	/**
	 * @param astro the astro to set
	 */
	public void setAstro(Astro astro) {
		this.astro = astro;
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
class Astro {//天文数值  
    private String sr;	//日出时间
    private String ss;	//日落时间
	/**
	 * @return the sr
	 */
	public String getSr() {
		return sr;
	}
	/**
	 * @param sr the sr to set
	 */
	public void setSr(String sr) {
		this.sr = sr;
	}
	/**
	 * @return the ss
	 */
	public String getSs() {
		return ss;
	}
	/**
	 * @param ss the ss to set
	 */
	public void setSs(String ss) {
		this.ss = ss;
	}

}