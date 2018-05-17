package com.internet.cms.dao.job;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.internet.cms.model.job.Work;

public interface IWorkDao {
	/**
	 * 根据任务id获取当前任务的信息
	 * @param id 当前任务id
	 * @return 任务的详细信息
	 */
	public Work load(@Param("id") int id);
	
	/**
	 * 根据父id获取所有的子任务(根据父亲id加载任务，该方面首先检查SystemContext中是否存在排序如果没有存在把orders加进去)
	 * @param pid 父任务id
	 * @return 子任务列表信息
	 */
	public List<Work> listWork(PageBounds pageBounds);
	
	public List<Work> listWork();
	
	/**
	 * 添加任务信息
	 * @param Work 任务对象
	 */
	public int add(Work Work);
	
	/**
	 * 更新任务
	 * @param Work 任务对象
	 */
	public void update(Work Work);
	
	/**
	 * 刪除任务
	 * @param Work 任务对象
	 */
	public void delete(@Param("id") int id);

	public int listWorkCount();

//	public Work loadByWorkCode(@Param("taskcode") String taskcode);

}
