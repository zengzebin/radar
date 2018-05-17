package com.ssxt.common.module.jobModule.jobImpl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import com.internet.cms.task.BaseJob;
import com.ssxt.common.entity.AreaEntity;
import com.ssxt.common.module.jobModule.saveWay.SaveCreator;
import com.ssxt.common.module.jobModule.saveWay.SaveRainImpl;
import com.ssxt.common.module.jobModule.saveWay.SaveWayCreator;
import com.ssxt.common.service.AreaService;
import com.ssxt.common.util.SpringBeanUtil;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.common.utils.URLConnectionUtils;

public class GatherRainPicJob extends BaseJob {
	private static Logger logger = Logger.getLogger(GatherRainPicJob.class);

	public GatherRainPicJob() {
		logger.info("初始化........" + this.getClass().getName());
		logger.info("采集降雨图");
	}

	@Override
	public void executeRun(JobExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		int hour[] = { 24,48, 72, 96, 120, 144, 168 };
//		int hour[] = { 24};
	   Calendar calendar=Calendar.getInstance();
	   Date nowTime=calendar.getTime();
	   SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String picUrl = PropertiesUtils.getRainPicUrlPrefix()+df.format(nowTime)+PropertiesUtils.getRainPicUrlSuffix();
		for (int i = 0; i < hour.length; i++) {
			for (int j = 0; j <= 23; j++) {
				int predict = hour[i];
				InputStream inputStream = null;
				String httpUrl = "";
				String picName = "";
				try {
					df = new SimpleDateFormat("yyyyMMddHH");// 设置日期格式
					Date showTime = getShowTime(j,calendar);
					if (predict > 100) {
						picName = df.format(showTime) + "00" + predict + "00.JPG";
					} else {
						picName = df.format(showTime) + "000" + predict + "00.JPG";
					}

					httpUrl = picUrl + picName;
					logger.info(httpUrl);
//					inputStream = requestImg("http://image.nmc.cn/product/2017/06/29/STFC/medium/SEVP_NMC_STFC_SFER_ER24_ACHN_L88_P9_20170629070002400.JPG");
				 	inputStream = requestImg(httpUrl);
                    logger.info("成功请求URL：......" + httpUrl + "......图片");
					boolean isSuccessful=saveImg(picName, SavePath(), inputStream, showTime, predict);
					logger.info("图片保存成功---- ");
//					if(isSuccessful)
//					break;
				 } catch (Exception e) {
					logger.info("失败请求URL：......" + httpUrl + "......图片");
					logger.info(e.getMessage());
				}
			}
		}
	}

	private boolean saveImg(String picName, String savePath, InputStream inputStream, Date showTime, int predict)
			throws Exception {
		SaveRainImpl service = new SaveRainImpl();
		boolean isSuccessful = service.saveInFile(picName, inputStream, savePath);
		if (isSuccessful)
			service.saveInDB(showTime, isSuccessful, predict);
		return isSuccessful;
	}

	private String SavePath() throws Exception {
		StringBuffer savePathBuf = new StringBuffer(
				PropertiesUtils.getPreRadarSerPath() + PropertiesUtils.getSaveRainPath());
		savePathBuf.append(File.separator + DateTools.DateFormate(new Date(), "yyyyMMdd"));
		return savePathBuf.toString();
	}

	private InputStream requestImg(String httpUrl) throws Exception {
		InputStream intputStream = URLConnectionUtils.sendGetRequest(httpUrl);
		return intputStream;
	}

	/**
	 * @Description: 获取时间
	 * @return Date
	 */
	private Date getShowTime(int hour, Calendar calendar) {
//		Date imgShowTime = new Date();
	  
	  
	    calendar.set(Calendar.HOUR_OF_DAY,hour);
	    calendar.set(Calendar.MINUTE,0);
	    calendar.set(Calendar.SECOND,0);
        Date imgShowTime =calendar.getTime();
//		imgShowTime = DateTools.setSeconds(imgShowTime, 0);
//		int minutes = DateTools.getMinutes(imgShowTime);
//		// minutes = minutes - (minutes % 6);
//		minutes = minutes - (minutes % 12);
//		imgShowTime = DateTools.setMinutes(imgShowTime, minutes);
//		imgShowTime = DateTools.getNextNMins(imgShowTime, -240);
        return imgShowTime;
	}

	public static void main(String[] args) {
		GatherRainPicJob r = new GatherRainPicJob();
		// System.out.println(r.SavePath().toString());
	}
}
