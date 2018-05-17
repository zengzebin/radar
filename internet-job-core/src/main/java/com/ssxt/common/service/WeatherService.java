package com.ssxt.common.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.WeatherDao;
import com.ssxt.common.dao.RadarImgDao;
import com.ssxt.common.entity.WeatherEntity;
import com.ssxt.common.entity.RadarImgEntity;
import com.ssxt.common.page.PageControl;
import com.ssxt.common.page.SqlCondGroup;

@Service
@Transactional
public class WeatherService {
	@Autowired
	public WeatherDao weatherDao;

	
	public void saveOrUpdate(WeatherEntity entity) {
		weatherDao.saveOrUpdate(entity);
	}


	public List<WeatherEntity> findByCondition(PageControl pageControl, List<SqlCondGroup> conList) {
		return weatherDao.findByCondition(pageControl, conList);
		
	}


	public void saveOrUpdate(List<WeatherEntity> weatherList) {
		weatherDao.saveOrUpdate(weatherList);
		
	}
	
}
