package com.internet.cms.service.job;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.internet.cms.JobParam;
import com.internet.cms.basic.util.DataUtil;
import com.internet.cms.dao.job.IJobDao;
import com.internet.cms.dao.job.IWorkDao;
import com.internet.cms.model.job.Job;
import com.internet.cms.model.job.Work;
import com.internet.cms.page.PageBoundsUtil;
import com.internet.cms.page.Pager;

@Service("jobService")
public class JobService implements IJobService {
	private static final Logger log = LoggerFactory.getLogger(JobService.class);
	private IJobDao jobDao;

	public IJobDao getJobDao() {
		return jobDao;
	}
	@Inject
	public void setJobDao(IJobDao jobDao) {
		this.jobDao = jobDao;
	}
	
	private IWorkDao workDao;

	public IWorkDao getWorkDao() {
		return workDao;
	}
	@Inject
	public void setWorkDao(IWorkDao workDao) {
		this.workDao = workDao;
	}
	
	/**
	 * 根据任务id获取当前任务的信息
	 * @param id 当前任务id
	 * @return 任务的详细信息
	 */
	public Job load(int id) {
		return jobDao.load(id);
	}

	/**
	 * 添加任务
	 * @param job 任务对象
	 * @param pid 父任务
	 */
	public void add(Job job) {
		UUID uuid=UUID.randomUUID();
		String taskCode=uuid.toString();
		job.setTask_code(taskCode);
		job.setCreatetime(DataUtil.date2Str(new Date()));
		job.setLastupdatetime(DataUtil.date2Str(new Date()));
		if(job.getInterval_second()==null)
		job.setInterval_second(0);
		
		//添加任务信息
		jobDao.add(job);
//		job.setTask_code(String.valueOf(job.getId()));
//		jobDao.update(job);
		
		//启用则启动任务
		if(1 == job.getIs_enable()){
			startTask(job,job.getTask_code());
		}
	}
	
	private void startTask(Job task,String taskCode){
		try {
			String cronExpress = getCronExpressionFromDB(taskCode);
			if(cronExpress.indexOf("null")==-1){
				JobDetail jobDetail = new JobDetail();
			 	jobDetail.setName(taskCode);
				jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
				jobDetail.setJobClass(getClassByTask(task.getJob_class()));
				String name="cron_" + taskCode;
				String group=Scheduler.DEFAULT_GROUP;
				String jobName=jobDetail.getName();
				String jobGroup=Scheduler.DEFAULT_GROUP;
				
						
				CronTrigger cronTrigger = new CronTrigger(name,group, jobName,jobGroup);
				cronTrigger.setCronExpression(cronExpress);
				task.setLaststartime(DataUtil.date2Str(new Date()));
				task.setRun_status(JobParam.RUN_STATUS_GOING);
				jobDao.update(task);

//				JobDataMap map=jobDetail.getJobDataMap();
				jobDetail.getJobDataMap().put(JobParam.PARAM_JOB, task);
				String msg=String.format("=========job 启动任务 %s:%s", task.getId(),task.getTask_name());
				log.info(msg);
				scheduler.scheduleJob(jobDetail, cronTrigger); 

				
			}
		}catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e.getMessage());
		}catch (ParseException e) {
			log.error("startTask ParseException", e.getMessage());
		} catch (Exception e) {
			log.error("startTask Exception", e.getMessage());
			e.printStackTrace();
		}
	}
	public Work addWorkByJob(Job job,Integer excuter_id,String cronExpress){
		Work work=new Work();
		work.setTask_code(job.getTask_code());
		work.setStarttime(job.getLaststartime());
		work.setSite_id(job.getSite_id());
		work.setCron_expression(cronExpress);
		work.setJob_class(job.getJob_class());
		work.setExcuter_id(excuter_id);//操作者
		work.setJob_id(job.getId());
		work.setJob_name(job.getTask_name());
		work.setTask_status(JobParam.WORK_STATUS_GOING);
		work.setCost(-1L);
		int workId=workDao.add(work);
		return work;
	}

	public void endWork(Work work){
		Work newWork=workDao.load(work.getId());
//		if(newWork==null){
//			return;
//		}
		newWork.setCost(work.getCost());
		newWork.setRunlog(work.getRunlog());
		newWork.setTask_status(work.getTask_status());
		newWork.setEndtime(DataUtil.date2Str(new Date()));
		newWork.setCost(work.getCost());
		workDao.update(newWork);
	}
	/**
	 * 手工启动任务，或者任务出发
	 * @param task
	 * @param taskCode
	 */
	public void startTaskOnce(Job task,String taskCode,Integer excuter_id){
		try {
			String cronExpress = getCronExpressionFromDB(taskCode);
			if(cronExpress.indexOf("null")==-1){
				JobDetail jobDetail = new JobDetail();
				JobDataMap map=jobDetail.getJobDataMap();
			 	jobDetail.setName(taskCode);
				jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
				jobDetail.setJobClass(getClassByTask(task.getJob_class()));
				String name="cron_" + taskCode;
				String group=Scheduler.DEFAULT_GROUP;
				String jobName=jobDetail.getName();
				String jobGroup=Scheduler.DEFAULT_GROUP;
						
//				CronTrigger cronTrigger = new CronTrigger(name,group, jobName,jobGroup);
//				cronTrigger.setCronExpression(cronExpress);
//				scheduler.scheduleJob(jobDetail, cronTrigger); 
				SimpleTrigger simpleTrigger = new SimpleTrigger(name,group, jobName,jobGroup,
						new Date(new Date().getTime() + 1000L),null,0,0L);
				task.setLaststartime(DataUtil.date2Str(new Date()));
				task.setRun_status(JobParam.RUN_STATUS_GOING);
//				Work work=addWorkByJob(task,  0, cronExpress);		
				map.put(JobParam.PARAM_JOB, task);
//				map.put(JobParam.PARAM_WORK, work);
				jobDao.update(task);
				scheduler.scheduleJob(jobDetail, simpleTrigger); 
			}
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e.getMessage());
		}catch (ParseException e) {
			log.error("startTask ParseException", e.getMessage());
		} catch (Exception e) {
			log.error("startTask Exception", e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 更新任务
	 * @param job 任务对象
	 */
	public void updateAndStart(Job job) {
		//因为结束任务没有那么快，所以才有endTask的操作
		String oldTaskCode = jobDao.load(job.getId()).getTask_code();
		try {
			//结束定时调度
			endTask(oldTaskCode);
			
			job.setLastupdatetime(DataUtil.date2Str(new Date()));
			UUID uuid=UUID.randomUUID();
			String taskCode=uuid.toString();
			job.setTask_code(taskCode);

			if(job.getInterval_second()==null)
			job.setInterval_second(0);
			
			jobDao.update(job);
			
			//启用则启动任务
			if(1 == job.getIs_enable()){
				startTask(job,job.getTask_code());
			}
		} catch (SchedulerException e) {
			log.error("JobService endTask SchedulerException",e);
		}
	}

	public void delete(int id) {
		Job job = jobDao.load(id);
		if(null != job){
			try {
				endTask(job.getTask_code());
				
				jobDao.delete(id);
			} catch (SchedulerException e) {
				log.error("JobService delete SchedulerException",e);
			}
		}
	}
	
	/**
	 * 根据父id获取所有的子任务(根据父亲id加载任务，该方面首先检查SystemContext中是否存在排序如果没有存在把orders加进去)
	 * @param pid 父任务id
	 * @return 子任务列表信息
	 */
	public Pager<Job> listJob() {
		//获取用户总数
		int count = jobDao.listJobCount();
		//封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("createtime.desc");
		//获取用户分页列表集合信息
		List<Job> list = jobDao.listJob(pageBounds);
		//封装用户分页的Pager对象
		Pager<Job> pages = new Pager<Job>(count,list);
		return pages;
	}
	
	/**
	 * 系统初始加载任务
	 */
	public void loadTask()throws Exception{
		List<Job> tasks = (List<Job>)jobDao.listJob();
		if(tasks.size()>0){
			for (int i = 0; i < tasks.size(); i++) {
				Job task=tasks.get(i);
				//任务开启状态 执行任务调度
				if(1 == task.getIs_enable()){
					try {
						//设置任务名称
						if(StringUtils.isNotBlank(task.getTask_code())){
						}else{
							UUID uuid=UUID.randomUUID();
							String uuString=uuid.toString();
							task.setTask_code(uuString);
						}
						jobDao.update(task);
						startTask(task, task.getTask_code());
						
//						JobDetail jobDetail = new JobDetail();
//						jobDetail.setName(task.getTask_code());
//						jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
//						//设置任务执行类
//						jobDetail.setJobClass(getClassByTask(task.getJob_class()));
//						//添加任务参数
//						//jobDetail.setJobDataMap(getJobDataMap(task.getAttr()));
//						CronTrigger cronTrigger = new CronTrigger("cron_" + i,Scheduler.DEFAULT_GROUP, jobDetail.getName(),Scheduler.DEFAULT_GROUP);
//						
//						cronTrigger.setCronExpression(getCronExpressionFromDB(task.getTask_code()));
//						//调度任务
//						scheduler.scheduleJob(jobDetail, cronTrigger); 
//					} catch (SchedulerException e) {
//						log.error("JobService SchedulerException", e.getMessage());
//					} catch (ClassNotFoundException e) {
//						log.error("JobService ClassNotFoundException", e.getMessage());
					} catch(Exception e){
						log.error("JobService Exception", e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public String getCronExpressionFromDB(String taskCode) throws Exception {
		//设置任务规则
		Job task = jobDao.loadByTaskCode(taskCode);
		if(null != task){
			if (JobParam.EXECYCLE_CRON == task.getExecycle()) {
				//周期执行
				return task.getCron_expression();
			} else {
				Integer execycle = task.getTask_interval_unit();
				String excep="" ;
				if (execycle.equals(JobParam.EXECYCLE_MONTH)) {
					excep="0  "+task.getMinute() +" "+task.getHour()+" "+ task.getDay_of_month() +" * ?";
				} else if (execycle.equals(JobParam.EXECYCLE_WEEK)) {
					excep="0  "+task.getMinute() +" "+task.getHour()+" "+" ? " +" * "+task.getDay_of_week();
				} else if (execycle.equals(JobParam.EXECYCLE_DAY)) {
					excep="0  "+task.getMinute() +" "+task.getHour()+" "+" * * ?";
				} else if (execycle.equals(JobParam.EXECYCLE_HOUR)) {
					excep="0 0 */"+task.getInterval_hour()+" * * ?";
				} else if (execycle.equals(JobParam.EXECYCLE_MINUTE)) {
					excep="0  */"+task.getInterval_minute() +" * * * ?";
				}else if (execycle.equals(JobParam.EXECYCLE_SECOND)) {
					excep="*/"+task.getInterval_second() +" * * * * ?";
				}
				return excep;
			}
		}
		return "";
	}
	/**
	 * 
	 * @param taskCode
	 * @throws SchedulerException
	 */
	private void endTask(String taskCode) throws SchedulerException{
//		endWork(work);
		scheduler.deleteJob(taskCode, Scheduler.DEFAULT_GROUP);
	}
	
	/**
	 * 
	 * @param taskClassName 任务执行类名
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private Class getClassByTask(String taskClassName) throws ClassNotFoundException{
		return Class.forName(taskClassName);
	}

	@Autowired
	private Scheduler scheduler;

	@Override
	public void update(Job job) {
		 jobDao.update(job);		
	}

}
