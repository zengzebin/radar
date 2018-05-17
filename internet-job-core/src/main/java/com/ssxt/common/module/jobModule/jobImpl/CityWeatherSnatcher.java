package com.ssxt.common.module.jobModule.jobImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;
import com.ssxt.common.entity.CityEntity;
import com.ssxt.common.entity.WeatherEntity;
import com.ssxt.common.service.WeatherService;
import com.ssxt.common.util.SpringBeanUtil;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.weather.WeatherBean;
import com.ssxt.weather.WeatherDaily;
import com.ssxt.weather.WeatherHelper;

public class CityWeatherSnatcher  implements Runnable{

	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CityWeatherSnatcher.class);
	private List<CityEntity> cityList;
	
	
	@Override
	public void run() {
		System.out.println("====天气获取线程开始");
		List<WeatherEntity> weatherList=new ArrayList<WeatherEntity>();
		accessWeatherEntity(cityList, weatherList);
		if(weatherList.size()>0){
			try {
				saveToDB(weatherList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("====天气获取线程结束");
	}

	public static void accessWeatherEntity(List<CityEntity> cityList, List<WeatherEntity> weatherList) {
		for (CityEntity cityEntity : cityList) {			
			String jsonstr=null;
			try{
				jsonstr=getCityWeatherInfo(cityEntity.getCityId());
				WeatherBean weatherBean=JSONObject.parseObject(jsonstr, WeatherBean.class);
				for (WeatherDaily from : weatherBean.getDaily_forecast()) {
					WeatherEntity entity = new WeatherEntity();
					WeatherHelper.dailyToEntity(from, entity);
					entity.setCityId(cityEntity.getCityId());
					entity.setCityName(cityEntity.getCityCn());
					entity.setShowTime(from.getDate());
					entity.setDelFlag("0");
					weatherList.add(entity);
					Thread.sleep(PropertiesUtils.getWeatherThreadSleep()*1000);
				}

			}catch (Exception e) {

				logger.error("json:{}",jsonstr);
				logger.error("操作城市天气信息失败：",e);
			}
		}
	}
	/**
	 * 获取城市天气
	 * @param cityId
	 * @return
	 * @throws Exception 
	 */
	public static String getCityWeatherInfo(String cityId) throws Exception{
		String json="";
		try {
			Document doc = Jsoup.connect(PropertiesUtils.getWeatherApiURL())
//					  .data("city", "汕头")
					  .data("cityid", cityId)
					  .userAgent("Mozilla")
					  .header("apikey", PropertiesUtils.getWeatherApiKey())
					  .ignoreContentType(true)
//					  .cookie("auth", "token")
					  .timeout(3000)
					  .get();
			json=doc.body().text();
			json=json.substring(json.indexOf("[")+1);
//			json=json.substring(json.indexOf("}")+1);
			json=json.substring(0,json.lastIndexOf("]"));
//			json=json.substring(json.indexOf("}")+1);
		} catch (Exception e) {
			logger.error("json:"+json);
			logger.error("获取城市天气信息失败：",e);
		}
		return json;
	}
	public void saveToDB (List<WeatherEntity> weatherList) throws Exception{
		try {
			weatherService.saveOrUpdate(weatherList);			
		} catch (Exception e) {
			logger.error("保存城市天气信息失败："+e.getMessage());
			throw e;
		}
	}
	public CityWeatherSnatcher(List<CityEntity> cityList) {
		super();
		this.cityList = cityList;
		this.weatherService=SpringBeanUtil.getBean(WeatherService.class);
	}

	@Resource
	private WeatherService weatherService;

}
