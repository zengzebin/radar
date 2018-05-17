package com.ssxt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.RadarImgDao;
import com.ssxt.common.entity.RadarImgEntity;

@Service
@Transactional
public class RadarImgService {
	@Autowired
	public RadarImgDao radarImgDao;
	
	public void saveOrUpdate(RadarImgEntity entity) {
		radarImgDao.saveOrUpdate(entity);
	}
}
