package com.ssxt.common.module.jobModule.jobImpl;

import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ssxt.common.module.jobModule.saveWay.SaveCreator;
import com.ssxt.common.module.jobModule.saveWay.SaveWayCreator;

public class RadarThread extends Thread {
	private static Logger logger = Logger.getLogger(RadarThread.class);

	private RadarBean bean;

	public RadarThread(RadarBean bean) {
		this.bean = bean;
	}

	public void run() {
		int count=1;
		SaveCreator saveCreator = new SaveWayCreator();
		logger.info("图片获取不到开启线程再次读取当前线程id=" + Thread.currentThread().getId());
		for (int i = 0; i < 2; i++) {
			count++;
			try {
				Thread.sleep(60000);
				logger.info("线程id=" + Thread.currentThread().getId() + "第" + count + "次读取图片");
				boolean isSuccessful = saveCreator.saveInFile(bean.getShowTime(), bean.getInputStream(),
						SaveCreator.SAVE_RADAR_IN_FILE, bean.getPath());
				if (isSuccessful) {
					saveCreator.saveInDB(bean.getShowTime(), SaveCreator.SAVE_RADAR_IN_DB, isSuccessful);
					logger.info("线程id=" + Thread.currentThread().getId() + "第" + count+ "次读取图片成功");
					break;
				} else {
					logger.info("线程id=" + Thread.currentThread().getId() + "第" + count + "次读取图片失败");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		String url = "D:/9090apache-tomcat-7.0.63/webapps/Image/radar/1/20170213/ancn/20170213091200.png";
		url = url.substring(url.indexOf("Image"), url.length());
		logger.info(url);

	}

}
