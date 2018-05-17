package com.ssxt.common.module.jobModule.jobImpl;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class RadarBean implements Serializable{
	private  Date showTime;
	private  InputStream inputStream;
	private String way;
	private String path;
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
