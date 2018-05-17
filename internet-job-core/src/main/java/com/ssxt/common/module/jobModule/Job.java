package com.ssxt.common.module.jobModule;

import com.ssxt.common.entity.JobEntity;

/** 
* @ClassName: Job 
* @Description: common Job interface
* @author Nick
* @date 2016年4月21日 下午5:03:45 
*  
*/
public interface Job {
	public final static String TYPE_COMMON_JOB = "Common"; //common task
	public final static String TYPE_SPECIAL_JOB = "Special"; //special task
	public final static String RUN_TYPE_AUTO_RUN = "Auto"; //automatic configuration
	public final static String RUN_TYPE_ARTIFICIAL_RUN = "Artificial"; //artificial configuration
	public final static boolean IS_RUNNING_TRUE = true; 	//is running
	public final static boolean IS_RUNNING_FALSE = false; 	//not running
	
	/** 
	* @Description: Get JobEntity bean
	* @return JobEntity JobEntity 
	*/
	public JobEntity getJobEntity();
	
	/** 
	* @Description: set jobEntity bean
	* @param JobEntity jobEntity 
	* @return void 
	*/
	public void setJobEntity(JobEntity jobEntity);
	
	/** 
	* @Description: create JobEntity bean
	* @return JobEntity 
	*/
	public JobEntity createJobEntity();
	
	/** 
	* @Description: do something before execute a job 
	* @return void 
	 * @throws Exception 
	*/
	public void beforeExecute() throws Exception;
	
	/** 
	* @Description: do something after execute a job 
	* @return void 
	 * @throws Exception 
	*/
	public void afterExecute() throws Exception;
	
	/** 
	* @Description: execute job
	* @return String 
	* @throws Exception
	*/
	public void execute() throws Exception;
}
