//package com.ssxt.common.module.jobModule.jobImpl;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ssxt.common.entity.JobEntity;
//import com.ssxt.common.module.jobModule.Job;
//import com.ssxt.common.service.JobLCService;
//import com.ssxt.common.utils.DateTools;
//
//public abstract class BaseJob implements Job {
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	public JobEntity jobEntity = new JobEntity();
//	
//	@Autowired
//	public JobLCService jobService;
//
//	public JobEntity getJobEntity() {
//		return jobEntity;
//	}
//
//	public void setJobEntity(JobEntity jobEntity) {
//		this.jobEntity = jobEntity;
//
//	}
//
//	public JobEntity createJobEntity() {
//		return new JobEntity();
//	}
//
//	public void beforeExecute() throws Exception {
//		Job job = JobLCService.getJobByName(this.getClass().getSimpleName());
//		JobEntity jobEntity = job.getJobEntity();
//		jobEntity.setLastExecutor("");
//		jobEntity.setLastStartime(DateTools.DateFormate(new Date()));
//		jobEntity.setIsRunning(Job.IS_RUNNING_TRUE);
//		jobService.updateEntity(jobEntity);
//		logger.info("job名为 -" + this.getClass().getSimpleName() + "- 的字段 isRunning 已经被设置为 '1'");
//	}
//
//	public void afterExecute() throws Exception {
//		Job job = JobLCService.getJobByName(this.getClass().getSimpleName());
//		JobEntity jobEntity = job.getJobEntity();
//		jobEntity.setLastEndtime(DateTools.DateFormate(new Date()));
//		jobEntity.setIsRunning(Job.IS_RUNNING_FALSE);
//		jobService.saveEntity(jobEntity);
//		logger.info("job名为 -" + this.getClass().getSimpleName() + "- 的字段 isRunning 已经被设置为 '0'");
//	}
//
//	public abstract void execute() throws Exception;
//
//}
