package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cond implements Serializable{//天气状况
	private String code;	//天气状况代码
    private String txt;	//天气状况描述
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
