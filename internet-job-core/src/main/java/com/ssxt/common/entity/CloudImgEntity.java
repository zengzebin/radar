package com.ssxt.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ssxt.common.persistence.ImgBaseEntity;

@Entity
@Table(name = "wea_cloud_img")
public class CloudImgEntity extends ImgBaseEntity<CloudImgEntity> {

	private static final long serialVersionUID = 1L;
	
	

	private String showTime;
	private String path;
	private String createTime;
	private String status;


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

}
