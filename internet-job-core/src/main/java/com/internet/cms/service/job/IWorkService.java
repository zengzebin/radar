package com.internet.cms.service.job;

import com.internet.cms.model.job.Job;
import com.internet.cms.model.job.Work;
import com.internet.cms.page.Pager;

public interface IWorkService {
	/**
	 * 根据任务id获取当前任务的信息
	 * @param id 当前任务id
	 * @return 任务的详细信息
	 */
	public Work load(int id);
	
	/**
	 * 添加任务
	 * @param job 任务对象
	 * @param pid 父任务
	 */
	public void add(Work job);
	/**
	 * 更新任务
	 * @param job
	 */
	public void update(Work job);

	/**
	 * 删除任务
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 获取任务列表
	 * @return
	 */
	public Pager<Work> listWork();
}
