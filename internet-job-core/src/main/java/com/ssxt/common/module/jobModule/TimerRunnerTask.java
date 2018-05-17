package com.ssxt.common.module.jobModule;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.ssxt.common.dao.JobDao;
import com.ssxt.common.dao.Parameter;
import com.ssxt.common.entity.JobEntity;
import com.ssxt.common.persistence.BaseEntity;
import com.ssxt.common.utils.SpringContextHelper;

/**
 * @ClassName: TimerRunnerTask
 * @Description: task
 * @author Nick
 * @date 2016年4月21日 下午5:45:50
 * 
 */
public class TimerRunnerTask extends TimerTask {
	private static Logger logger = Logger.getLogger(TimerRunnerTask.class);
	private JobDao jobDao = SpringContextHelper.getBean(JobDao.class);
	private Job job;

	public TimerRunnerTask(Job job) {
		this.job = job;
	}

	@Override
	public void run() {
		try {
			job.beforeExecute();
			job.execute();
			job.afterExecute();
		} catch (Exception e) {
			if (job != null && job.getJobEntity() != null) {
				String jobName = job.getJobEntity().getName();
				stopJob(jobName);
			}
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}

	private void stopJob(String jobName) {
//		jobDao.update("update JobEntity job set job.isRunning = 0 where job.name = :p1 and job.delFlag = :p2", new Parameter(jobName, BaseEntity.DELFLAG_DEFAULT));
//		jobDao.flush();
	}

}
