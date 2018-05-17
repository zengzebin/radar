package com.ssxt.common.module.jobModule.jobImpl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.internet.cms.task.BaseJob;
import com.ssxt.common.entity.AreaEntity;
import com.ssxt.common.entity.RadarImgEntity;
import com.ssxt.common.module.jobModule.saveWay.SaveCreator;
import com.ssxt.common.module.jobModule.saveWay.SaveWayCreator;
import com.ssxt.common.service.AreaService;
//import com.ssxt.common.service.JobLCService;
import com.ssxt.common.util.SpringBeanUtil;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.common.utils.URLConnectionUtils;

public class GatherRadarPictJob extends BaseJob {
	private static Logger logger = Logger.getLogger(GatherRadarPictJob.class);

	@Autowired
	public AreaService areaService;

	public GatherRadarPictJob() {
		logger.info("初始化........" + this.getClass().getName());
		areaService = SpringBeanUtil.getBean("areaService", AreaService.class);
	}
	
	@Override
	public void executeRun(JobExecutionContext context) throws Exception {
		String httpUrlPrefix = PropertiesUtils.getPreNetRadarUrl();
		String httpUrlCenter = PropertiesUtils.getCenterNetRadarUrl();
		Date showTime = getShowTime();
		// execute getting and saving mosaicing graphs
		saveMultiGraphs(showTime, httpUrlPrefix, httpUrlCenter);
		// execute getting and saving single station graphs
		saveSingleGraphs(showTime, httpUrlPrefix, httpUrlCenter);
	}

	/**
	 * @Description: save single station graphs
	 * @param httpUrlPrefix
	 * @param httpUrlCenter
	 * @return void
	 * @throws Exception
	 */
	private void saveSingleGraphs(Date showTime, String httpUrlPrefix, String httpUrlCenter) throws Exception {
		List<AreaEntity> singleAreaList = areaService.getSingleStAreas();
		if (singleAreaList == null || singleAreaList.size() <= 0) {
			throw new Exception("获取单站点区域失败");
		}
		List<AreaEntity> totalList = areaService.getAllEnableAeas();
		logger.info("totalList size=" + totalList.size());
		for (AreaEntity area : singleAreaList) {
			String savePath = jointSavePath(showTime, totalList, area, RadarImgEntity.TYPE_SINGLE);
			String httpUrl = jointHttpUrl(httpUrlPrefix, httpUrlCenter, area, RadarImgEntity.TYPE_SINGLE, showTime);
			InputStream inputStream=null;
			try{
				inputStream = requestImg(httpUrl);
				logger.info("成功请求URL：......" + httpUrl + "......图片");
				saveImg(showTime, savePath, inputStream);
				logger.info("图片保存成功----   " + area.getAreaName() + " saveto... " + savePath);
			}catch(Exception e){
				logger.info("失败请求URL：......" + httpUrl + "......图片");
				logger.info(e.getMessage());
			}
			Thread.sleep(2000);
		}
	}

	/**
	 * @Description: mosaicing graphs
	 * @param httpUrlPrefix
	 * @param httpUrlCenter
	 * @throws Exception
	 */
	private void saveMultiGraphs(Date showTime, String httpUrlPrefix, String httpUrlCenter) throws Exception {
		List<AreaEntity> multiAreaList = null;
		List<AreaEntity> totalList = areaService.getAllEnableAeas();
		multiAreaList = areaService.getSunList(totalList, 1);
		if (multiAreaList == null || multiAreaList.size() <= 0) {
			throw new Exception("获取拼接图区域失败");
		}
		for (AreaEntity area : multiAreaList) {
			String savePath = jointSavePath(showTime, totalList, area, RadarImgEntity.TYPE_MULTI);
			String httpUrl = jointHttpUrl(httpUrlPrefix, httpUrlCenter, area, RadarImgEntity.TYPE_MULTI, showTime);
			InputStream inputStream=null;
			try{
				inputStream = requestImg(httpUrl);
				logger.info("成功请求URL：......" + httpUrl + "......图片");
				saveImg(showTime, savePath, inputStream);
				logger.info("图片保存成功----   " + area.getAreaName() + " saveto... " + savePath);				
			}catch(Exception e){
				logger.info("失败请求URL：......" + httpUrl + "......图片");
				logger.info(e.getMessage());
			}
			Thread.sleep(1000);
		}
	}

	/** 
	* @Description: 拼接http url
	* @param httpUrlPrefix
	* @param httpUrlCenter
	* @param area
	* @param savePath
	* @return String 
	* @throws Exception 
	*/
	private String jointHttpUrl(String httpUrlPrefix, String httpUrlCenter, AreaEntity area, int type, Date showTime) throws Exception {
		String code = area.getCode().toLowerCase();
		String preRequestUrl = httpUrlPrefix + code + httpUrlCenter;
		String httpUrl = joinRequestUrl(showTime, preRequestUrl, type);
		return httpUrl;
	}

	/** 
	* @Description: 拼接保存路径
	* @param showTime
	* @param totalList
	* @param area
	* @return String
	* @throws Exception 
	*/
	private String jointSavePath(Date showTime, List<AreaEntity> totalList, AreaEntity area, int type) throws Exception {
		StringBuffer savePathBuf = new StringBuffer(PropertiesUtils.getPreRadarSerPath() + PropertiesUtils.getCenterRadarSerPath());
		savePathBuf.append(File.separator + type);
		savePathBuf.append(File.separator + DateTools.DateFormate(showTime, "yyyyMMdd"));
		String areaLevelPath = getAreaLevelPath(totalList, area);
		savePathBuf.append(File.separator + areaLevelPath);
		return savePathBuf.toString();
	}

	/**
	 * @Description: 遍历生成area文件目录
	 * @param area
	 * @return String
	 */
	private String getAreaLevelPath(List<AreaEntity> totalList, AreaEntity area) {
		String buf = new String();
		AreaEntity a = area;
		while (a.getParentId() != 0) {
			buf = (a.getCode() + File.separator + buf);
			a = areaService.getAreaEntityInList(totalList, a.getParentId());
		}
		return buf;
	}

	private InputStream requestImg(String httpUrl) throws Exception {
		InputStream intputStream = URLConnectionUtils.sendGetRequest(httpUrl);
		return intputStream;
	}
	
	/**
	 * @Description: save image in file and DB
	 * @param httpUrlPrefix
	 * @param httpUrlCenter
	 * @param code
	 * @param typeSingle
	 * @throws Exception
	 */
	private void saveImg(Date showTime, String savePath, InputStream inputStream) throws Exception {
		 SaveCreator saveCreator = new SaveWayCreator();
    	boolean isSuccessful = saveCreator.saveInFile(showTime, inputStream, SaveCreator.SAVE_RADAR_IN_FILE, savePath);
//		boolean isSuccessful = true;

	 	if(isSuccessful)
		saveCreator.saveInDB(showTime, SaveCreator.SAVE_RADAR_IN_DB, isSuccessful);
	 
    }

	private String joinRequestUrl(Date showTime, String preRequestUrl, int type) throws Exception {
		// Date showTime = getShowTime();
		String urlTime = DateTools.DateFormate(DateTools.getNextNHours(showTime, -8), "yyyyMMddHHmmss");
		StringBuffer urlBuf = new StringBuffer(preRequestUrl);
		// urlBuf.append(File.separator + urlTime);
		urlBuf.append(urlTime);
		urlBuf.append("00" + type);
		urlBuf.append(PropertiesUtils.getImgPng());
		return urlBuf.toString();
	}

	/** 
	* @Description: 获取雷达图拍摄时间
	* @return Date
	*/
	private Date getShowTime() {
		Date imgShowTime = new Date();
		imgShowTime = DateTools.setSeconds(imgShowTime, 0);
		int minutes = DateTools.getMinutes(imgShowTime);
//		minutes = minutes - (minutes % 6);
		minutes = minutes - (minutes % 12);
		imgShowTime = DateTools.setMinutes(imgShowTime, minutes);
		imgShowTime = DateTools.getNextNMins(imgShowTime, -120);
		return imgShowTime;
	}

}
