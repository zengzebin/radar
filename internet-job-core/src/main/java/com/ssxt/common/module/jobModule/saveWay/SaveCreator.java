package com.ssxt.common.module.jobModule.saveWay;

import java.io.InputStream;
import java.util.Date;

/** 
* @ClassName: SaveCreator 
* @Description: object client to execute save
* @author Nick
* @date 2016年4月24日 上午10:43:05 
*  
*/
public abstract class SaveCreator {
	public final static String SAVE_CLOUD_IN_FILE = "CloudFile";
	public final static String SAVE_RADAR_IN_FILE = "RadarFile";
	public final static String SAVE_CLOUD_IN_DB = "CloudDB";
	public final static String SAVE_RADAR_IN_DB = "RadarDB";
	public final static String SAVE_RAIN_IN_DB = "RainDB";
	public final static String SAVE_RAIN_IN_FILE = "RainFILE";
	
	public abstract SaveWayInterface factoryMethod(String way) throws Exception;
	
	/** 
	* @Description: choose special way to save picture by parameter 'way'. 
	* @param inputStream
	* @param way
	* @param savePosition
	* @returnS tring
	* @throws Exception 
	*/
	public boolean saveInFile(Date showTime,InputStream inputStream, String way, String path) throws Exception {
		SaveWayInterface saveImpl = factoryMethod(way);
		return saveImpl.saveInFile(showTime,inputStream,  path);
	}
	
	public void saveInDB(Date showTime,String way, boolean status) throws Exception {
		SaveWayInterface saveImpl = factoryMethod(way);
		saveImpl.saveInDB(showTime,status);
	}
}
