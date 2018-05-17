package com.ssxt.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ssxt.common.persistence.ImgBaseEntity;

/**
 * WeaRainImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wea_rain_img")
public class WeaRainImg extends ImgBaseEntity<WeaRainImg> {
	// todo
	private static final long serialVersionUID = 1L;

	public final static int TYPE_MULTI = 1;
	public final static int TYPE_SINGLE = 0;

	private String showTime;
	private String path;
	private String createTime;
	private String status;
	private Integer predict;

	public Integer getPredict() {
		return predict;
	}

	public void setPredict(Integer predict) {
		this.predict = predict;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

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

}