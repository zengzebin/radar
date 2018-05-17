package com.ssxt.common.utils;

public class NumberTools {
	
	/** 
	* @Description: convert string to int
	* @param numStr
	* @return int
	*/
	public static int StringToInt(String numStr) {
		return new Double(numStr).intValue();
	}
	
	/** 
	* @Description: convert string to long
	* @param numStr
	* @return long
	*/
	public static long StringToLong(String numStr) {
		return new Double(numStr).longValue();
	}
}
