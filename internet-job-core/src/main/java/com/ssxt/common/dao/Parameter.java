package com.ssxt.common.dao;

import java.util.HashMap;

/** 
* @ClassName: Parameter 
* @Description: 参数封装
* @author Nick
* @date 2016年4月21日 上午8:42:01 
*  
*/
public class Parameter extends HashMap<String, Object> {
	
	// todo
	private static final long serialVersionUID = 1L;

	/** 
	* @Description: 值集
	* @param param    
	*/
	public Parameter(Object... params) {
		if (params != null && params.length != 0 ) {
			for (int i = 0; i < params.length; i++) {
				this.put("p" + (i + 1), params[i]);
			}
		}
	}
	
	/** 
	* @Description: 键值对集
	* @param params   
	*/
	public Parameter(Object[][]... params) {
		if (params != null && params.length > 0) {
			for (Object[] obs : params) {
				if (obs.length == 2) {
					this.put( (String)obs[0], obs[1]);
				}
			}
		}
	}
}
