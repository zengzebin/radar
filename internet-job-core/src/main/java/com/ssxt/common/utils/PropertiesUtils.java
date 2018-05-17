package com.ssxt.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.NumberUtils;

/**
 * @ClassName: PropertiesUtils
 * @Description: util class for getting properties
 * @author nick
 * @date 2016年4月24日 上午7:37:47
 * 
 */
public class PropertiesUtils {
	private final static String CONFIG_FILE_PATH = "config.properties";
	private static PropertiesLoader propLoader = new PropertiesLoader(PropertiesUtils.CONFIG_FILE_PATH);
	private static Map<String, String> map = new HashMap<String, String>();

	private static String getConfig(String key) throws Exception {
		String value = map.get(key);
		if (value == null) {
			value = propLoader.getProperty(key);
			map.put(key, value);
		}
		value = new String(value.getBytes("ISO-8859-1"), "utf-8"); 
		return value;
	}
	//========================= cloud properties ================================
	/**
	 * @Description: interval bucket time to get cloudy picture from url
	 * @param key
	 * @return intervalTime
	 * @throws Exception
	 */
	public static String getIntervalBucket() throws Exception {
		return getConfig("intervalBucket");
	}
	
	/**
	 * @Description: get prefix of url to load cloud picture from Internet
	 * @param key
	 * @return String
	 * @throws Exception
	 */
	public static String getPreNetCloudUrl() throws Exception {
		return getConfig("pre_net_cloudUrl");
	}
	
	/**
	 * @Description: get prefix of url to load cloud picture from server
	 * @return String
	 * @throws Exception
	 */
	public static String getPreSerCloudUrl() throws Exception {
		return getConfig("pre_ser_cloudUrl");
	}
	
	/** 
	* @Description: get picture timeout maximun time
	* @return int
	* @throws Exception 
	*/
	public static int getPicTimeout() throws Exception {
		return NumberTools.StringToInt(getConfig("pic_timeout"));
	}
	
	/** 
	* @Description: get path to save cloud pictures
	* @return String
	* @throws Exception 
	*/
	public static String getCloudSavePath() throws Exception {
		return getConfig("pre_ser_cloudUrl");
	}
	
	/** 
	* @Description: get '.jpg' prefix of img
	* @return String
	* @throws Exception 
	*/
	public static String getImgJpg() throws Exception {
		return getConfig("img_jpg");
	}
	/** 
	 * @Description: get '.png' prefix of img
	 * @return String
	 * @throws Exception 
	 */
	public static String getImgPng() throws Exception {
		return getConfig("img_png");
	}
	
	/** 
	* @Description: get request cloud picture path
	* @return String
	* @throws Exception 
	*/
	public static String getCloudPath() throws Exception {
		return getConfig("cloud_path");
	}
	//======================== radar properties ===========================================
	/** 
	* @Description: get '.gif' prefix of img
	* @return String
	* @throws Exception 
	*/
	public static String getImgGif() throws Exception {
		return getConfig("img_gif");
	}
	
	/** 
	* @Description: get prefix of request url
	* @return String
	* @throws Exception 
	*/
	public static String getPreNetRadarUrl() throws Exception {
		return getConfig("pre_net_radarUrl");
	}
	
	/** 
	* @Description: get center part of request url
	* @return String
	* @throws Exception 
	*/
	public static String getCenterNetRadarUrl() throws Exception {
		return getConfig("center_net_readarUrl");
	}
	
	/** 
	* @Description: get prefix of save path
	* @return String
	* @throws Exception 
	*/
	public static String getPreRadarSerPath() throws Exception {
		return getConfig("pre_ser_radarUrl");
	}
	
	/** 
	* @Description: get centger part of save path
	* @return String
	* @throws Exception 
	*/
	public static String getCenterRadarSerPath() throws Exception {
		return getConfig("center_ser_radarUrl");
	}
	
	/** 
	* @Description: get Request radar path
	* @return String
	* @throws Exception 
	*/
	public static String getRadarPath() throws Exception {
		return getConfig("radar_path");
	}

	/** 
	* @Description: get Request radar path
	* @return String
	* @throws Exception 
	*/
	public static String getWeatherApiURL() throws Exception {
		return getConfig("weather_api_url");
	}

	/** 
	* @Description: get Request radar path
	* @return String
	* @throws Exception 
	*/
	public static String getWeatherApiKey() throws Exception {
		return getConfig("weather_api_key");
	}

	/** 
	* @Description: get Request radar path
	* @return String
	* @throws Exception 
	*/
	public static int getWeatherThreadCount() throws Exception {
		int i=1;
		try{
			i=Integer.parseInt(getConfig("weather_thread_count"));
		}catch(Exception e){
			i=1;
		}
		return i;
	}
	
	public static String getRainPicUrlPrefix() throws Exception {

		return getConfig("rainPicUrlPrefix");
	}
	
	public static String getRainPicUrlSuffix() throws Exception {

		return getConfig("rainPicUrlSuffix");
	}

	public static String getSaveRainPath() throws Exception {
		return getConfig("saveRainPath");
	}

	/** 
	* @Description: get Request radar path
	* @return String
	* @throws Exception 
	*/
	public static int getWeatherThreadSleep() throws Exception {
		int i=0;
		try{
			i=Integer.parseInt(getConfig("weather_thread_sleep"));
		}catch(Exception e){
			i=0;
		}
		return i;
	}
}
