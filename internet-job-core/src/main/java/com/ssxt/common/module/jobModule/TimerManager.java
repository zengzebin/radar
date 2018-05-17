package com.ssxt.common.module.jobModule;

import java.text.ParseException;
import java.util.Map;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.ssxt.common.entity.JobEntity;
import com.ssxt.common.service.JobLCService;
import com.ssxt.common.utils.DateTools;

public class TimerManager {
	private static Logger logger = Logger.getLogger(TimerManager.class);

	public TimerManager() {
		Map<String, Job> jobsMap = JobLCService.getJobsMap();
		if (jobsMap != null && jobsMap.size() > 0) {
			Object[] keys = sortByLaunchingTime(jobsMap);
			for (Object key : keys) {
				try{
					Job job = jobsMap.get(key);
					JobEntity jobEntity = job.getJobEntity();
					if (jobEntity.getRunType() != null && jobEntity.getRunType().equals(Job.RUN_TYPE_AUTO_RUN)) {
						joinInSchedule(job);
						logger.info(this.getClass().getName() + "...job名为 -" + key + " -已经成功加入schedule");
					} else
						if (jobEntity.getRunType() != null && jobEntity.getRunType().equals(Job.RUN_TYPE_ARTIFICIAL_RUN)) {
						logger.info(this.getClass().getName() + "...job名为 -" + key + " -需要手动设置才能启动");
					}
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
	}

	/**
	 * @Description: job join in schedule
	 * @param job
	 * @return void
	 * @throws ParseException 
	 */
	private void joinInSchedule(Job job) throws ParseException {
		Timer timer = new Timer();
		TimerRunnerTask timerRunnerTask = new TimerRunnerTask(job);
		JobEntity jobEntity = job.getJobEntity();
		String beginTime = jobEntity.getBeginTime();
		int intervalSeconds = jobEntity.getIntervalSeconds();
		if (intervalSeconds == -1) {
			timer.schedule(timerRunnerTask, DateTools.stringDateFormate(beginTime, null));
		} else {
			timer.schedule(timerRunnerTask, DateTools.stringDateFormate(beginTime, "yyyy-MM-dd HH:mm:ss"), intervalSeconds);
		}
	}

	/**
	 * @Description: sort by trigger time
	 * @param  jobsMap
	 * @return String[]
	 */
	private Object[] sortByLaunchingTime(Map<String, Job> jobsMap) {
		// TODO Auto-generated method stub
		return (Object[]) jobsMap.keySet().toArray();
	}

}
