package com.internet.cms.model.job;

public class Work {

	private int id;
	private int job_id ;
	private String task_code ;
	private String job_name ;
	private String job_class ;
	private String cron_expression ;
	private int task_status ;
	private String site_id ;
	private int excuter_id ;
	private String starttime ;
	private String endtime ;
	private Long cost ;
	private String runlog ;
	
	
	/**
	 * @return the job_id
	 */
	public int getJob_id() {
		return job_id;
	}

	/**
	 * @param job_id the job_id to set
	 */
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	/**
	 * @return the task_code
	 */
	public String getTask_code() {
		return task_code;
	}

	/**
	 * @param task_code the task_code to set
	 */
	public void setTask_code(String task_code) {
		this.task_code = task_code;
	}

	/**
	 * @return the job_name
	 */
	public String getJob_name() {
		return job_name;
	}

	/**
	 * @param job_name the job_name to set
	 */
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	/**
	 * @return the job_class
	 */
	public String getJob_class() {
		return job_class;
	}

	/**
	 * @param job_class the job_class to set
	 */
	public void setJob_class(String job_class) {
		this.job_class = job_class;
	}

	/**
	 * @return the cron_expression
	 */
	public String getCron_expression() {
		return cron_expression;
	}

	/**
	 * @param cron_expression the cron_expression to set
	 */
	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}

	/**
	 * @return the task_status
	 */
	public int getTask_status() {
		return task_status;
	}

	/**
	 * @param task_status the task_status to set
	 */
	public void setTask_status(int task_status) {
		this.task_status = task_status;
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

	/**
	 * @return the excuter_id
	 */
	public int getExcuter_id() {
		return excuter_id;
	}

	/**
	 * @param excuter_id the excuter_id to set
	 */
	public void setExcuter_id(int excuter_id) {
		this.excuter_id = excuter_id;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the cost
	 */
	public Long getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Long cost) {
		this.cost = cost;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the runlog
	 */
	public String getRunlog() {
		return runlog;
	}

	/**
	 * @param runlog the runlog to set
	 */
	public void setRunlog(String runlog) {
		this.runlog = runlog;
	}

	public Work(int job_id, String task_code, String job_name, String job_class, String cron_expression,
			int task_status, String site_id, int excuter_id, String starttime, String endtime, Long cost, String runlog) {
		super();
		this.job_id = job_id;
		this.task_code = task_code;
		this.job_name = job_name;
		this.job_class = job_class;
		this.cron_expression = cron_expression;
		this.task_status = task_status;
		this.site_id = site_id;
		this.excuter_id = excuter_id;
		this.starttime = starttime;
		this.endtime = endtime;
		this.cost = cost;
		this.runlog = runlog;
	}

	public Work() {
		super();
	}



}
