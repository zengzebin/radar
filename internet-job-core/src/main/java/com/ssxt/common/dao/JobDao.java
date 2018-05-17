package com.ssxt.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ssxt.common.entity.JobEntity;

@Repository
public class JobDao extends GenericDaoImpl<JobEntity,Integer> implements Serializable {
	
	// todo
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public JobEntity getById(int id) {
		Session session = super.getSession();
		List<JobEntity> list = (List<JobEntity>)session.createCriteria(JobEntity.class).list();
		/*Query query = session.createQuery("from JobEntity job where job.id = :p");
		JobEntity jobEntity = (JobEntity)query.setParameter("p", 1).uniqueResult();*/
		return list.get(0);
	}
}
