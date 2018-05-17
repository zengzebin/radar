package com.ssxt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.CloudImgDao;
import com.ssxt.common.entity.CloudImgEntity;

@Service
@Transactional
public class CloudImgService {
	@Autowired
	public CloudImgDao cloudImgDao;
	
	public void saveOrUpdate(CloudImgEntity entity) {
		cloudImgDao.saveOrUpdate(entity);
	}
}
