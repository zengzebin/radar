package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CondDaily implements Serializable{//天气状况

    private String code_d;	//www.heweather.com/documents/condition-code
    private String code_n;	//夜间天气状况代码
    private String txt_d;	//白天天气状况描述
    private String txt_n;	//夜间天气状况描述
	/**
	 * @return the code_d
	 */
	public String getCode_d() {
		return code_d;
	}
	/**
	 * @param code_d the code_d to set
	 */
	public void setCode_d(String code_d) {
		this.code_d = code_d;
	}
	/**
	 * @return the code_n
	 */
	public String getCode_n() {
		return code_n;
	}
	/**
	 * @param code_n the code_n to set
	 */
	public void setCode_n(String code_n) {
		this.code_n = code_n;
	}
	/**
	 * @return the txt_d
	 */
	public String getTxt_d() {
		return txt_d;
	}
	/**
	 * @param txt_d the txt_d to set
	 */
	public void setTxt_d(String txt_d) {
		this.txt_d = txt_d;
	}
	/**
	 * @return the txt_n
	 */
	public String getTxt_n() {
		return txt_n;
	}
	/**
	 * @param txt_n the txt_n to set
	 */
	public void setTxt_n(String txt_n) {
		this.txt_n = txt_n;
	}

}
