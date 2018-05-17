package com.ssxt.weather;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Template implements Serializable{ //温度
    private String max;	//最高温度
    private String min;	//最低温度
	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(String max) {
		this.max = max;
	}
	/**
	 * @return the min
	 */
	public String getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(String min) {
		this.min = min;
	}

}
