package com.ssxt.common.module.jobModule.saveWay;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.util.file.File;

import com.ssxt.common.dao.CloudImgDao;
import com.ssxt.common.dao.RadarImgDao;
import com.ssxt.common.dao.RainImgDao;
import com.ssxt.common.entity.CloudImgEntity;
import com.ssxt.common.entity.RadarImgEntity;
import com.ssxt.common.entity.WeaRainImg;
import com.ssxt.common.persistence.BaseEntity;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.FileTools;
import com.ssxt.common.utils.PropertiesUtils;
import com.ssxt.common.utils.SpringContextHelper;

public class SaveRainImpl  {

	private static Logger logger = Logger.getLogger(SaveRainImpl.class);
	private static String relativePath = "";
	// private Date imgShowTime;

	private RainImgDao rainImgDao = SpringContextHelper.getBean(RainImgDao.class);

	public boolean saveInFile(String picName, InputStream in, String path) throws Exception {
//		String imgFileName = DateTools.DateFormate(showTime, "yyyyMMddHHmmss") + PropertiesUtils.getImgPng();
		String imgFileName = picName;
		relativePath = (path +File.separator+ imgFileName).replaceAll(PropertiesUtils.getPreRadarSerPath(), "");
		logger.info("relativePath0:" + relativePath + "   ..........");
		return FileTools.saveImg(in, path, imgFileName);
	}

	public void saveInDB(Date showTime, boolean status,int predict) throws Exception {

		WeaRainImg entity = new WeaRainImg();
	
		entity.setCreateTime(DateTools.DateFormate(new Date()));
		entity.setDelFlag(BaseEntity.DELFLAG_DEFAULT);
		//entity.setPath(relativePath.replace("\\", "/"));
		String path=relativePath.replace("\\", "/");
		path=path.substring(path.indexOf("Image")-1,path.length());
		entity.setPath(path);
		List list=rainImgDao.find("from WeaRainImg where path='"+path+"'");
		if(list.size()>=1){
			logger.info("这个已经存在:"+showTime);
			return;
		}
		entity.setPredict(predict);
		entity.setShowTime(DateTools.DateFormate(showTime));
		if (status) {
			entity.setStatus(RadarImgEntity.STATUS_NORMAL);
		} else {
			entity.setStatus(RadarImgEntity.STATUS_ABNORMAL);
		}
        rainImgDao.saveOrUpdate(entity);
		rainImgDao.flush();
		logger.info("图片保存的相对路径是:   " + relativePath + "   ..........");
	}
	

}
