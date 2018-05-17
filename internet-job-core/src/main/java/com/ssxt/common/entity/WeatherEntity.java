package com.ssxt.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ssxt.common.persistence.DataEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "wea_weather")
public class WeatherEntity extends DataEntity<AreaEntity> {    
    
	private String cityId;	//城市
	private String cityName;	//城市
	private String showTime;	//时间
    private String sr;	//日出时间
    private String ss;	//日落时间
	
	private String hum;	//相对湿度（%）
	private String pcpn;	//降水量（mm）
    private String pop;	//降水概率
	private String pres;	//气压

    private String windDeg;	// "10", 
    private String windDir;	// "北风", 
    private String windSc;	// "3级", 
    private String windSpd;	// "15" 
    

    private String dayCode;	//www.heweather.com/documents/condition-code
    private String nightCode;	//夜间天气状况代码
    private String dayTxt;	//白天天气状况描述
    private String nightTxt;	//夜间天气状况描述
    

    private String tmpMax;	//最高温度
    private String tmpMin;	//最低温度
    
    private String status;	//1.正常， 2.不正常
	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
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
	 * @return the windDeg
	 */
	public String getWindDeg() {
		return windDeg;
	}
	/**
	 * @param windDeg the windDeg to set
	 */
	public void setWindDeg(String windDeg) {
		this.windDeg = windDeg;
	}
	/**
	 * @return the windDir
	 */
	public String getWindDir() {
		return windDir;
	}
	/**
	 * @param windDir the windDir to set
	 */
	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}
	/**
	 * @return the windSc
	 */
	public String getWindSc() {
		return windSc;
	}
	/**
	 * @param windSc the windSc to set
	 */
	public void setWindSc(String windSc) {
		this.windSc = windSc;
	}
	/**
	 * @return the windSpd
	 */
	public String getWindSpd() {
		return windSpd;
	}
	/**
	 * @param windSpd the windSpd to set
	 */
	public void setWindSpd(String windSpd) {
		this.windSpd = windSpd;
	}
	/**
	 * @return the dayCode
	 */
	public String getDayCode() {
		return dayCode;
	}
	/**
	 * @param dayCode the dayCode to set
	 */
	public void setDayCode(String dayCode) {
		this.dayCode = dayCode;
	}
	/**
	 * @return the nightCode
	 */
	public String getNightCode() {
		return nightCode;
	}
	/**
	 * @param nightCode the nightCode to set
	 */
	public void setNightCode(String nightCode) {
		this.nightCode = nightCode;
	}
	/**
	 * @return the dayTxt
	 */
	public String getDayTxt() {
		return dayTxt;
	}
	/**
	 * @param dayTxt the dayTxt to set
	 */
	public void setDayTxt(String dayTxt) {
		this.dayTxt = dayTxt;
	}
	/**
	 * @return the nightTxt
	 */
	public String getNightTxt() {
		return nightTxt;
	}
	/**
	 * @param nightTxt the nightTxt to set
	 */
	public void setNightTxt(String nightTxt) {
		this.nightTxt = nightTxt;
	}
	/**
	 * @return the tmpMax
	 */
	public String getTmpMax() {
		return tmpMax;
	}
	/**
	 * @param tmpMax the tmpMax to set
	 */
	public void setTmpMax(String tmpMax) {
		this.tmpMax = tmpMax;
	}
	/**
	 * @return the tmpMin
	 */
	public String getTmpMin() {
		return tmpMin;
	}
	/**
	 * @param tmpMin the tmpMin to set
	 */
	public void setTmpMin(String tmpMin) {
		this.tmpMin = tmpMin;
	}
	/**
	 * @return the showTime
	 */
	public String getShowTime() {
		return showTime;
	}
	/**
	 * @param showTime the showTime to set
	 */
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    
    
	
	
}
