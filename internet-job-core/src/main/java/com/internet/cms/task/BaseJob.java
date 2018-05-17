package com.internet.cms.task;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.wicket.util.file.File;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.internet.cms.JobParam;
import com.internet.cms.basic.util.DataUtil;
import com.internet.cms.model.job.Job;
import com.internet.cms.model.job.Work;
import com.internet.cms.service.job.JobService;
import com.internet.cms.service.job.WorkService;
import com.ssxt.common.utils.SpringContextHelper;

public abstract class BaseJob implements org.quartz.Job {
	private static Logger logger = Logger.getLogger(BaseJob.class);
	// private JobService jobService =
	// SpringContextHelper.getBean(JobService.class);

	JobService getJobService() {
		return SpringContextHelper.getBean(JobService.class);
	}

	WorkService getWorkService() {
		return SpringContextHelper.getBean(WorkService.class);
	}

	// private JobService jobService = SpringBeanUtil.getBean("jobService",
	// JobService.class);
	private StringBuffer log = new StringBuffer();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobService jobService = getJobService();
		WorkService workService = getWorkService();
		String className = getClass().getSimpleName();
		boolean succFlag = false;
		String msg = "";
		long t1 = System.nanoTime();
		long t2 = t1;
		msg = String.format("=========work【%s】开始执行", className);
		// JobDataMap map=context.getJobDetail().getJobDataMap();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		Job job = (Job) data.get(JobParam.PARAM_JOB);
		String startTime = DataUtil.date2Str(new Date());
		job.setLaststartime(startTime);
		Integer excuter = 0;
		Work work = jobService.addWorkByJob(job, excuter, "unknown");
		logger.info(msg);
		try {
			executeRun(context);
			succFlag = true;
			t2 = System.nanoTime();
			work.setTask_status(JobParam.WORK_STATUS_OK);
		} catch (Exception e) {
			logger.error("=========work执行出错！", e);
			log.append(File.separator);
			log.append(File.separator);
			log.append("=========work执行出错");
			log.append(File.separator);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log.append(sw.toString());
			work.setTask_status(JobParam.WORK_STATUS_ERROR);
			t2 = System.nanoTime();
		}
		String endTime = DataUtil.date2Str(new Date());
		job.setLastendtime(endTime);
		work.setEndtime(endTime);
		work.setCost((t2 - t1) / 1000);
		msg = String.format("=========work【%s】执行完成, %s", className, succFlag ? "正常结束" : "出现问题");

		work.setRunlog(log.toString());
		jobService.endWork(work);
		jobService.update(job);

		logger.info(msg);
		;
	}

	public abstract void executeRun(JobExecutionContext context) throws Exception;

}
