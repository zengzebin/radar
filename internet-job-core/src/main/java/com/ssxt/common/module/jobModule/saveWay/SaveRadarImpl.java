package com.ssxt.common.module.jobModule.saveWay;

import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.wicket.util.file.File;

import com.ssxt.common.dao.CloudImgDao;
import com.ssxt.common.dao.RadarImgDao;
import com.ssxt.common.entity.CloudImgEntity;
import com.ssxt.common.entity.RadarImgEntity;
import com.ssxt.common.persistence.BaseEntity;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.FileTools;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.common.utils.SpringContextHelper;

public class SaveRadarImpl implements SaveWayInterface {

	private static Logger logger = Logger.getLogger(SaveRadarImpl.class);
	private static String relativePath = "";
	// private Date imgShowTime;

	private RadarImgDao radarImgDao = SpringContextHelper.getBean(RadarImgDao.class);

	public boolean saveInFile(Date showTime, InputStream in, String path) throws Exception {
		String imgFileName = DateTools.DateFormate(showTime, "yyyyMMddHHmmss") + PropertiesUtils.getImgPng();
		relativePath = (path + imgFileName).replaceAll(PropertiesUtils.getPreRadarSerPath(), "");
		logger.info("relativePath0:" + relativePath + "   ..........");
		return FileTools.saveImg(in, path, imgFileName);
	}

	public void saveInDB(Date showTime, boolean status) throws Exception {

		RadarImgEntity entity = new RadarImgEntity();
		entity.setCreateTime(DateTools.DateFormate(new Date()));
		entity.setDelFlag(BaseEntity.DELFLAG_DEFAULT);
		//entity.setPath(relativePath.replace("\\", "/"));
		String path=relativePath.replace("\\", "/");
		path=path.substring(path.indexOf("Image")-1,path.length());
		entity.setPath(path);
		entity.setShowTime(DateTools.DateFormate(showTime));
		if (status) {
			entity.setStatus(RadarImgEntity.STATUS_NORMAL);
		} else {
			entity.setStatus(RadarImgEntity.STATUS_ABNORMAL);
		}
		radarImgDao.saveOrUpdate(entity);
		radarImgDao.flush();
		logger.info("图片保存的相对路径是:   " + relativePath + "   ..........");
	}

}
