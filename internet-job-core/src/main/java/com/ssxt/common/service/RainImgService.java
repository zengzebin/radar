package com.ssxt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.RadarImgDao;
import com.ssxt.common.dao.RainImgDao;
import com.ssxt.common.entity.RadarImgEntity;
import com.ssxt.common.entity.WeaRainImg;

@Service
@Transactional
public class RainImgService {
	@Autowired
	public RainImgDao rainImgDao;
	
	public void saveOrUpdate(WeaRainImg  entity) {
		rainImgDao.saveOrUpdate(entity);
	}
}
