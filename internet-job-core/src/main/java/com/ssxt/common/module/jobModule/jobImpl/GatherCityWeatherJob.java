package com.ssxt.common.module.jobModule.jobImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.internet.cms.task.BaseJob;
import com.ssxt.common.entity.CityEntity;
import com.ssxt.common.entity.WeatherEntity;
import com.ssxt.common.page.PageControl;
import com.ssxt.common.page.SqlCondGroup;
import com.ssxt.common.service.CityService;
import com.ssxt.common.service.WeatherService;
//import com.ssxt.common.service.JobLCService;
import com.ssxt.common.util.SpringBeanUtil;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.weather.WeatherBean;
import com.ssxt.weather.WeatherDaily;
import com.ssxt.weather.WeatherHelper;
import com.alibaba.fastjson.JSONObject;  


public class GatherCityWeatherJob extends BaseJob {
	private static Logger logger = Logger.getLogger(GatherCityWeatherJob.class);
	
	@Resource
	private CityService cityService;	
	@Resource
	private WeatherService weatherService;
	
	public GatherCityWeatherJob() {
		logger.info("初始化........" + this.getClass().getName()  );
		cityService=SpringBeanUtil.getBean(CityService.class);
		weatherService=SpringBeanUtil.getBean(WeatherService.class);
	}
	
	@Override
	public void executeRun(JobExecutionContext context) throws Exception {
		List<SqlCondGroup> conList = new ArrayList<SqlCondGroup>();
		PageControl pageControl = com.ssxt.common.page.PageControl.getQueryOnlyInstance();
//		conList.add(new SqlCondGroup("level", 1, "=", "and"));
		List<CityEntity> list=cityService.findByCondition(pageControl, conList);
		if(list!=null){
			int threadCount=PropertiesUtils.getWeatherThreadCount() ;
			if(threadCount==1){
				logger.info(String.format("======使用单线程模式======"));
				List<WeatherEntity> weatherList=new ArrayList<WeatherEntity>();
				CityWeatherSnatcher.accessWeatherEntity(list, weatherList);
				if(weatherList.size()>0){
					weatherService.saveOrUpdate(weatherList);	
				}
			}
			else{
				logger.info(String.format("======使用多线程模式======"));
				
				int perThreadSize=list.size()/threadCount+1;
				if(list.size()%threadCount==0){
					perThreadSize=perThreadSize-1;
				}
				logger.info(String.format("======开始线程:%s,每个线程项目数:%s", threadCount,perThreadSize));
				int start=0;
				for(int i=0;i<threadCount;i++){
					List<CityEntity> list_sub=null;
					if(i==threadCount-1){
						list_sub=list.subList(start,list.size()-1);
					}
					else{
						list_sub=list.subList(start, start+perThreadSize);
					}
					start+=perThreadSize;
					CityWeatherSnatcher snatcher=new CityWeatherSnatcher(list_sub);
					new Thread(snatcher).start();
				}
			}
		}
	}
	

	private  Date getShowTime(){
		Date imgShowTime =null;		
		Date now=new Date();
		
		imgShowTime = new Date(now.getYear(),now.getMonth(),now.getDate(),
				now.getHours(), now.getMinutes());
//		imgShowTime.setMinutes(imgShowTime.getMinutes()-20);
		int minutes=imgShowTime.getMinutes()/10*10;
		imgShowTime.setMinutes(minutes-130);
		return imgShowTime;
	}
	

	public static void main(String[] args) throws Exception {
		GatherCityWeatherJob job = new GatherCityWeatherJob();
		Date showTime=job.getShowTime();
		String cityId="CN101010600";
		String jsonstr=CityWeatherSnatcher.getCityWeatherInfo(cityId);
		System.out.println("jsonstr1() " + jsonstr);
		WeatherBean weatherBean=JSONObject.parseObject(jsonstr, WeatherBean.class);
		String jsonstr2=JSONObject.toJSONString(weatherBean);
		System.out.println("jsonstr2() " + jsonstr2);
	}
	
}
