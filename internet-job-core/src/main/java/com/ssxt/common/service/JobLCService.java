package com.ssxt.common.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.JobDao;
//import com.ssxt.common.dao.JobDao;
import com.ssxt.common.dao.Parameter;
import com.ssxt.common.entity.JobEntity;
import com.ssxt.common.module.jobModule.Job;
import com.ssxt.common.persistence.BaseEntity;
import com.ssxt.common.utils.SpringContextHelper;

/**
 * @ClassName: JobService
 * @Description: Job Service level
 * @author Nick
 * @date 2016年4月22日 下午11:12:35
 * 
 */
@Service
@Transactional
public class JobLCService {
	private static Logger logger = Logger.getLogger(JobLCService.class);
	public static Map<String, Job> jobsMap = new HashMap<String, Job>();
	@Autowired
	private JobDao jobDao; 

	public JobEntity getJobById(int id) {
		JobEntity jobEntity = jobDao.getById(id);
		return jobEntity;
	}

	public void saveEntity(JobEntity jobEntityf) {
		jobDao.saveOrUpdate(jobEntityf);
	}

	public void updateEntity(JobEntity jobEntity) {
		jobDao.saveOrUpdate(jobEntity);
	}

	public void deleteEntity(JobEntity jobEntity) {
	}

	// ============== test end =============================================
	public static Map<String, Job> getJobsMap() {
		return jobsMap;
	}

	/**
	 * @Description: put a job into memory
	 * @param String
	 *            jobName
	 * @param Job
	 *            job
	 * @return void
	 */
	public static void add2JobsMap(String jobName, Job job) {
		jobsMap.put(jobName, job);
		logger.info("job同步数据库和缓存开始...");
		loadJobFromDB(jobName, job);
		logger.info("job同步数据库和缓存结束...");
	}

	/**
	 * @Description: get Job from BataBase
	 * @param  jobName
	 * @param  job
	 * @return void
	 */
	private static void loadJobFromDB(String jobName, Job job) {
		JobDao jobDao = SpringContextHelper.getBean(JobDao.class);
		JobEntity jobEntity = null;
//				jobDao.getByHql("from JobEntity job where job.name = :p1 and job.delFlag = :p2", new Parameter(jobName, BaseEntity.DELFLAG_DEFAULT));
		if (jobEntity == null) {
			initJobEntity(job);
			jobDao.save(job.getJobEntity());
		}
		if (jobsMap.containsKey(jobName)) {
			jobsMap.remove(jobName);
		}
		job.setJobEntity(jobEntity);
		jobsMap.put(jobName, job);
	}

	/**
	 * @Description: set default values into jobEntity
	 * @param  job
	 * @return void
	 */
	private static void initJobEntity(Job job) {
		JobEntity jobEntity = job.getJobEntity();
		jobEntity.setName(job.getClass().getSimpleName());
		jobEntity.setImplName(job.getClass().getName());
		jobEntity.setType(Job.TYPE_COMMON_JOB);
		jobEntity.setIsRunning(Job.IS_RUNNING_FALSE);
		jobEntity.setRunType(Job.RUN_TYPE_ARTIFICIAL_RUN);
	}

	public static Job getJobByName(String key) throws Exception {
		if (jobsMap.containsKey(key)) {
			return jobsMap.get(key);
		} else {
			throw new Exception("缓存中不存在 -" + key + "- 的Job");
		}
	}
}
