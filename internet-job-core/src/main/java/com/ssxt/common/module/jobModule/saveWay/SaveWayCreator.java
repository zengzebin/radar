package com.ssxt.common.module.jobModule.saveWay;

/** 
* @ClassName: SaveWayCreator 
* @Description: get real execution object
* @author Nick
* @date 2016年4月24日 上午10:42:29 
*  
*/
public class SaveWayCreator extends SaveCreator {

	@Override
	public SaveWayInterface factoryMethod(String way) throws Exception {
		if (SaveCreator.SAVE_CLOUD_IN_FILE.equals(way)) {
			return new SaveCloudImpl();
		} else if (SaveCreator.SAVE_CLOUD_IN_DB.equals(way)) {
			return new SaveCloudImpl();
		} else if (SaveCreator.SAVE_RADAR_IN_FILE.equals(way)) {
			return new SaveRadarImpl();
		} else if (SaveCreator.SAVE_RADAR_IN_DB.equals(way)) {
			return new SaveRadarImpl();
//		} else if (SaveCreator.SAVE_RAIN_IN_FILE.equals(way)) {
//			return new SaveRainImpl();
//		} else if (SaveCreator.SAVE_RAIN_IN_DB.equals(way)) {
//			return new SaveRainImpl();
		} else {
			throw new Exception("传入参数有无，工厂无法生成该对象");
		}
	}

}
