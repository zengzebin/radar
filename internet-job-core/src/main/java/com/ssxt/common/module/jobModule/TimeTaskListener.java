package com.ssxt.common.module.jobModule;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * @ClassName: TimeTaskListener
 * @Description: 启动job
 * @author Nick
 * @date 2016年4月21日 下午4:24:18
 * 
 */
public class TimeTaskListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(TimeTaskListener.class);

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("============= - " + TimeTaskListener.class.getSimpleName() + " - 监听器已经加载结束!");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("============= - " + TimeTaskListener.class.getSimpleName() + " - 监听器开始加载......");
		new TimerManager();
	}

}
