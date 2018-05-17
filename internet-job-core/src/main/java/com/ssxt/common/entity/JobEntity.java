package com.ssxt.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ssxt.common.persistence.DataEntity;

@Entity
@Table(name = "sys_job")
public class JobEntity extends DataEntity<JobEntity> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String uiOrder;
	private String type;
	private String implName;
	private Boolean isRunning;
	private String runType;
	private Integer intervalSeconds;
	private String lastPointFinish;
	private String lastExecutor;
	private String lastStartime;
	private String lastEndtime;
	private String beginTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUiOrder() {
		return uiOrder;
	}

	public void setUiOrder(String uiOrder) {
		this.uiOrder = uiOrder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImplName() {
		return implName;
	}

	public void setImplName(String implName) {
		this.implName = implName;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public Integer getIntervalSeconds() {
		return intervalSeconds;
	}

	public void setIntervalSeconds(Integer intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}

	public String getLastPointFinish() {
		return lastPointFinish;
	}

	public void setLastPointFinish(String lastPointFinish) {
		this.lastPointFinish = lastPointFinish;
	}

	public String getLastExecutor() {
		return lastExecutor;
	}

	public void setLastExecutor(String lastExecutor) {
		this.lastExecutor = lastExecutor;
	}

	public String getLastStartime() {
		return lastStartime;
	}

	public void setLastStartime(String lastStartime) {
		this.lastStartime = lastStartime;
	}

	public String getLastEndtime() {
		return lastEndtime;
	}

	public void setLastEndtime(String lastEndtime) {
		this.lastEndtime = lastEndtime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

}
