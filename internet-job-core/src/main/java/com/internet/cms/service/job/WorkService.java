package com.internet.cms.service.job;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.internet.cms.basic.util.DataUtil;
import com.internet.cms.dao.job.IWorkDao;
import com.internet.cms.model.job.Job;
import com.internet.cms.model.job.Work;
import com.internet.cms.page.PageBoundsUtil;
import com.internet.cms.page.Pager;

@Service("taskService")
public class WorkService implements IWorkService {
	private static final Logger log = LoggerFactory.getLogger(WorkService.class);
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
	public Work load(int id) {
		return workDao.load(id);
	}

	/**
	 * 添加任务
	 * @param task 任务对象
	 * @param pid 父任务
	 */
	public void add(Work task) {
		UUID uuid=UUID.randomUUID();
		String taskCode=uuid.toString();
		task.setTask_code(taskCode);
		
		//添加任务信息
		workDao.add(task);
//		job.setWork_code(String.valueOf(job.getId()));
//		jobDao.update(job);
		
	}
	
	/**
	 * 更新任务
	 * @param job 任务对象
	 */
	public void update(Work job) {
		try {
			workDao.update(job);
		} catch (Exception e) {
			log.error("WorkService endWork SchedulerException",e);
		}
	}

	
	/**
	 * 根据父id获取所有的子任务(根据父亲id加载任务，该方面首先检查SystemContext中是否存在排序如果没有存在把orders加进去)
	 * @param pid 父任务id
	 * @return 子任务列表信息
	 */
	public Pager<Work> listWork() {
		//获取用户总数
		int count = workDao.listWorkCount();
		//封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		//获取用户分页列表集合信息
		List<Work> list = workDao.listWork(pageBounds);
		//封装用户分页的Pager对象
		Pager<Work> pages = new Pager<Work>(count,list);
		return pages;
	}
	public void delete(int id) {
		Work job = workDao.load(id);
		if(null != job){
			try {
				workDao.delete(id);
			} catch (Exception e) {
				log.error("Work delete Exception",e);
			}
		}
	}
	
	

}
