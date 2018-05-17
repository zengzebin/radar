package com.ssxt.common.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.CityDao;
import com.ssxt.common.dao.RadarImgDao;
import com.ssxt.common.entity.CityEntity;
import com.ssxt.common.entity.RadarImgEntity;
import com.ssxt.common.page.PageControl;
import com.ssxt.common.page.SqlCondGroup;

@Service
@Transactional
public class CityService {
	@Resource
	public CityDao cityDao;

	
	public void saveOrUpdate(CityEntity entity) {
		cityDao.saveOrUpdate(entity);
	}


	public List<CityEntity> findByCondition(PageControl pageControl, List<SqlCondGroup> conList) {
		return cityDao.findByCondition(pageControl, conList);
		
	}
	
}
