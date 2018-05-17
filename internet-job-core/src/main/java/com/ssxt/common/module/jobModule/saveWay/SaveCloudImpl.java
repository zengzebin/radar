package com.ssxt.common.module.jobModule.saveWay;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ssxt.common.dao.CloudImgDao;
import com.ssxt.common.entity.CloudImgEntity;
import com.ssxt.common.persistence.BaseEntity;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.FileTools;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.common.utils.SpringContextHelper;

public class SaveCloudImpl implements SaveWayInterface {

	private CloudImgDao cloudImgDao = SpringContextHelper.getBean(CloudImgDao.class);
	private static String relativePath = "";
	// private static Date imgShowTime;
	private static Logger logger = Logger.getLogger(SaveCloudImpl.class);

	public boolean saveInFile(Date showTime, InputStream in, String path) throws Exception {
		// prepared 'imgShowTime' to use in method 'saveInDB'
		// imgShowTime = DateTools.clearSeconds(DateTools.getNextNMins(new
		// Date(), -45));
		String fileName = DateTools.DateFormate(showTime, "yyyyMMddHHmmss").substring(0, 8);
		path = path + File.separator + fileName;
		String imgFileName = DateTools.DateFormate(showTime, "yyyyMMddHHmmss") + PropertiesUtils.getImgJpg();
		// prepared 'relativePath' to use in method 'saveInDB'
		relativePath = fileName + File.separator + imgFileName;
		// relativePath = (path +
		// imgFileName).replaceAll(PropertiesUtils.getPreRadarSerPath(), "");
		return FileTools.saveImg(in, path, imgFileName);
	}

	public void saveInDB(Date showTime, boolean status) throws Exception {
		CloudImgEntity entity = new CloudImgEntity();
		entity.setCreateTime(DateTools.DateFormate(new Date()));
		entity.setDelFlag(BaseEntity.DELFLAG_DEFAULT);
		entity.setPath(relativePath.replace("\\", "/"));
		if (status) {
			entity.setStatus(CloudImgEntity.STATUS_NORMAL);
		} else {
			entity.setStatus(CloudImgEntity.STATUS_ABNORMAL);
		}
		entity.setShowTime(DateTools.DateFormate(showTime));
		cloudImgDao.saveOrUpdate(entity);
		cloudImgDao.flush();
		logger.info("图片保存的相对路径是:   " + relativePath + "   ..........");
	}

}
