package com.ssxt.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/** 
* @ClassName: URLConnectionUtils 
* @Description: send https request
* @author Nick
* @date 2016年4月24日 上午10:06:09 
*  
*/
public class URLConnectionUtils {
	private final static String REQUEST_METHOD_GET = "GET";
	private final static String REQUEST_METHOD_POST = "POST";
	private  static Logger logger = Logger.getLogger(URLConnectionUtils.class);
	
	public static InputStream sendGetRequest(String httpUrl) throws Exception {
		HttpURLConnection conn = getHttpsURLConnection(httpUrl);
		conn.setRequestMethod(URLConnectionUtils.REQUEST_METHOD_GET);
		conn.setConnectTimeout( PropertiesUtils.getPicTimeout());
		logger.info("开始发送请求，URL:    " + httpUrl + "  ......");
		InputStream in = conn.getInputStream();
		return in;	
	}
	
	private static HttpURLConnection getHttpsURLConnection(String httpUrl) throws IOException {
		URL url = new URL(httpUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		return conn;
	}
	
	public static InputStream sendPostRequest(String httpUrl, String params) {
		// todo
		return null;
	}
}
