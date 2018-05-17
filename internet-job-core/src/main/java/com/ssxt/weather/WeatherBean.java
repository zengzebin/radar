package com.ssxt.weather;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class WeatherBean  implements Serializable{
	private Aqi aqi;
	private Basic basic;
	private List<WeatherDaily> daily_forecast;
    private List<WeatherHourly> hourly_forecast;
    private WeatherNow  now;
    private String  status;
    private Suggestion suggestion;
	/**
	 * @return the aqi
	 */
	public Aqi getAqi() {
		return aqi;
	}
	/**
	 * @param aqi the aqi to set
	 */
	public void setAqi(Aqi aqi) {
		this.aqi = aqi;
	}
	/**
	 * @return the basic
	 */
	public Basic getBasic() {
		return basic;
	}
	/**
	 * @param basic the basic to set
	 */
	public void setBasic(Basic basic) {
		this.basic = basic;
	}
	/**
	 * @return the daily_forecast
	 */
	public List<WeatherDaily> getDaily_forecast() {
		return daily_forecast;
	}
	/**
	 * @param daily_forecast the daily_forecast to set
	 */
	public void setDaily_forecast(List<WeatherDaily> daily_forecast) {
		this.daily_forecast = daily_forecast;
	}
	/**
	 * @return the hourly_forecast
	 */
	public List<WeatherHourly> getHourly_forecast() {
		return hourly_forecast;
	}
	/**
	 * @param hourly_forecast the hourly_forecast to set
	 */
	public void setHourly_forecast(List<WeatherHourly> hourly_forecast) {
		this.hourly_forecast = hourly_forecast;
	}
	/**
	 * @return the now
	 */
	public WeatherNow getNow() {
		return now;
	}
	/**
	 * @param now the now to set
	 */
	public void setNow(WeatherNow now) {
		this.now = now;
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
	/**
	 * @return the suggestion
	 */
	public Suggestion getSuggestion() {
		return suggestion;
	}
	/**
	 * @param suggestion the suggestion to set
	 */
	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}
}
@SuppressWarnings("serial")
class Basic implements Serializable {
	//基本信息
    private String city;	//城市名称
    private String cnty;	//国家
    private String id;	//www.heweather.com/documents/cn-city-list
    private String lat;	//城市维度
    private String lon;	//城市经度
    private UpdateInfo update;	//更新时间
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the cnty
	 */
	public String getCnty() {
		return cnty;
	}
	/**
	 * @param cnty the cnty to set
	 */
	public void setCnty(String cnty) {
		this.cnty = cnty;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * @return the update
	 */
	public UpdateInfo getUpdate() {
		return update;
	}
	/**
	 * @param update the update to set
	 */
	public void setUpdate(UpdateInfo update) {
		this.update = update;
	}
}
class UpdateInfo{
    private String loc;	//当地时间
    private String utc;	//UTC时间
	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * @return the utc
	 */
	public String getUtc() {
		return utc;
	}
	/**
	 * @param utc the utc to set
	 */
	public void setUtc(String utc) {
		this.utc = utc;
	}
}