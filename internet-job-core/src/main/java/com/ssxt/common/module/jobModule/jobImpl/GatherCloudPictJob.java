package com.ssxt.common.module.jobModule.jobImpl;

import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internet.cms.task.BaseJob;
import com.ssxt.common.module.jobModule.saveWay.SaveCreator;
import com.ssxt.common.module.jobModule.saveWay.SaveWayCreator;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.common.utils.URLConnectionUtils;

/**
 * @ClassName: GatherCloudPictJob
 * @Description: gather cloud picture on time
 * @author Nick
 * @date 2016年4月22日 下午11:18:58
 * 
 */
//@Service
//@Transactional
public class GatherCloudPictJob extends BaseJob {
	private static Logger logger = Logger.getLogger(GatherCloudPictJob.class);

	public GatherCloudPictJob() {
		logger.info("初始化........" + this.getClass().getName() + "  ");
	}


	/**
	 * @Description: infer imgShowTime from jobTime, and then can infer urlTime from imgShowTime.
	 *               imgShowTime = jobTime - 45(minutes);
	 *               urlTime = imgShowTime - 8(hours);
	 * @param httpUrlPrefix
	 * @return String
	 * @throws Exception
	 */
//	private String jointRequestUrl(String httpUrlPrefix) throws Exception {
//		Date imgShowTime = DateTools.clearSeconds(DateTools.getNextNMins(new Date(), -45));
//		String urlTime = DateTools.DateFormate(DateTools.getNextNHours(imgShowTime, -8), "yyyyMMddHHmmss");
//		 
//		String httpUrl = httpUrlPrefix + urlTime + "000" + PropertiesUtils.getImgJpg();
//		return httpUrl;
//	}

	/**
	 * ypx
	 * 获取当前最新云图url
	 * @param httpUrlPrefix
	 * @return
	 * @throws Exception
	 */
	public String joinRequestUrl(Date showTime,String httpUrlPrefix) throws Exception {
		
		String urlTime = DateTools.DateFormate(showTime, "yyyyMMddHHmmss");
		 
		String httpUrl = String.format("%s%s000%s", httpUrlPrefix , urlTime , PropertiesUtils.getImgJpg());
		return httpUrl;
	}

	public Date getShowTime() {
		Date imgShowTime = null;
		Date now = new Date();

		int minutes = now.getMinutes() / 15 * 15;
		imgShowTime = new Date(now.getYear(), now.getMonth(), now.getDate(), now.getHours(), minutes);
		// imgShowTime.setMinutes(imgShowTime.getMinutes()-45);
		imgShowTime.setMinutes(imgShowTime.getMinutes() - 75);
		imgShowTime.setHours(imgShowTime.getHours() - 8);
		return imgShowTime;
	}
	@Override
	public void executeRun(JobExecutionContext context) throws Exception {
		Date showTime=getShowTime();
		InputStream intputStream = getImgInputStream(showTime);
		String savePath = PropertiesUtils.getCloudSavePath();
		SaveCreator saveCreator = new SaveWayCreator();
		boolean isSuccessful = saveCreator.saveInFile(showTime,intputStream,  SaveCreator.SAVE_CLOUD_IN_FILE, savePath);
		if(isSuccessful)
		saveCreator.saveInDB(showTime,SaveCreator.SAVE_CLOUD_IN_FILE, isSuccessful);
		
	}

	private InputStream getImgInputStream(Date showTime) throws Exception {
		String httpUrlPrefix = PropertiesUtils.getPreNetCloudUrl();
		String httpUrl = joinRequestUrl(showTime,httpUrlPrefix);
		System.out.println("获取地址 " + httpUrl);
		return URLConnectionUtils.sendGetRequest(httpUrl);
	}

	public static void main(String[] args) throws Exception {
		GatherCloudPictJob job = new GatherCloudPictJob();
		Date showTime=job.getShowTime();
		String httpUrlPrefix = PropertiesUtils.getPreNetCloudUrl();
		String httpUrl =job.joinRequestUrl(showTime,httpUrlPrefix);
		System.out.println("获取地址 " + httpUrl);
		String savePath = PropertiesUtils.getCloudSavePath();
		System.out.println("===获取地址 " + httpUrl);

		System.out.println("===保存地址 " + savePath);
	}
}
