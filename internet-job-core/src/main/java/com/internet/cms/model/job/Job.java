package com.internet.cms.model.job;

public class Job {

	private int id;
	private String site_id;
	private String task_code;
	private String task_type;
	private String task_name;
	private String job_class;
	private Integer execycle;
	private Integer day_of_month;
	private Integer day_of_week;
	private Integer hour;
	private Integer minute;
	private Integer interval_hour;
	private Integer interval_minute;
	private Integer task_interval_unit;
	private String cron_expression;
	

	private Integer is_enable;
	private String task_remark;
	private String createtime;
	private String lastupdatetime;
	
	public Integer interval_second ;
	public int cmd_code ;
	public String cmd_text ;
	public int run_status ;

	public int user_id ;


	public String laststartime ;
	public String lastendtime ;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getJob_class() {
		return job_class;
	}

	public void setJob_class(String job_class) {
		this.job_class = job_class;
	}

	public Integer getExecycle() {
		return execycle;
	}

	public void setExecycle(Integer execycle) {
		this.execycle = execycle;
	}

	public Integer getDay_of_month() {
		return day_of_month;
	}

	public void setDay_of_month(Integer day_of_month) {
		this.day_of_month = day_of_month;
	}

	public Integer getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(Integer day_of_week) {
		this.day_of_week = day_of_week;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getInterval_hour() {
		return interval_hour;
	}

	public void setInterval_hour(Integer interval_hour) {
		this.interval_hour = interval_hour;
	}

	public Integer getInterval_minute() {
		return interval_minute;
	}

	public void setInterval_minute(Integer interval_minute) {
		this.interval_minute = interval_minute;
	}

	public Integer getTask_interval_unit() {
		return task_interval_unit;
	}

	public void setTask_interval_unit(Integer task_interval_unit) {
		this.task_interval_unit = task_interval_unit;
	}

	public String getCron_expression() {
		return cron_expression;
	}

	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}

	public Integer getIs_enable() {
		return is_enable;
	}

	public void setIs_enable(Integer is_enable) {
		this.is_enable = is_enable;
	}

	public String getTask_remark() {
		return task_remark;
	}

	public void setTask_remark(String task_remark) {
		this.task_remark = task_remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getTask_code() {
		return task_code;
	}

	public void setTask_code(String task_code) {
		this.task_code = task_code;
	}

	/**
	 * @return the interval_second
	 */
	public Integer getInterval_second() {
		return interval_second;
	}

	/**
	 * @param interval_second the interval_second to set
	 */
	public void setInterval_second(Integer interval_second) {
		this.interval_second = interval_second;
	}

	/**
	 * @return the cmd_code
	 */
	public int getCmd_code() {
		return cmd_code;
	}

	/**
	 * @param cmd_code the cmd_code to set
	 */
	public void setCmd_code(int cmd_code) {
		this.cmd_code = cmd_code;
	}

	/**
	 * @return the cmd_text
	 */
	public String getCmd_text() {
		return cmd_text;
	}

	/**
	 * @param cmd_text the cmd_text to set
	 */
	public void setCmd_text(String cmd_text) {
		this.cmd_text = cmd_text;
	}

	/**
	 * @return the run_status
	 */
	public int getRun_status() {
		return run_status;
	}

	/**
	 * @param run_status the run_status to set
	 */
	public void setRun_status(int run_status) {
		this.run_status = run_status;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the laststartime
	 */
	public String getLaststartime() {
		return laststartime;
	}

	/**
	 * @param laststartime the laststartime to set
	 */
	public void setLaststartime(String laststartime) {
		this.laststartime = laststartime;
	}

	/**
	 * @return the lastendtime
	 */
	public String getLastendtime() {
		return lastendtime;
	}

	/**
	 * @param lastendtime the lastendtime to set
	 */
	public void setLastendtime(String lastendtime) {
		this.lastendtime = lastendtime;
	}

	/**
	 * @return the site_id
	 */
	public String getSite_id() {
		return site_id;
	}

	/**
	 * @param site_id the site_id to set
	 */
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}


}
