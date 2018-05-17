package com.ssxt.common.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssxt.common.entity.WeatherEntity;

@Repository
public class WeatherDao extends GenericDaoImpl<WeatherEntity,Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void saveOrUpdate(List<WeatherEntity> weatherList) {
		int i=0;
		for (WeatherEntity weatherEntity : weatherList) {
			getHibernateTemplate().saveOrUpdate(weatherEntity);			
			if (++i/50 == 0) {
			    this.getHibernateTemplate().flush();
			    this.getHibernateTemplate().clear();
			}
		}
	    this.getHibernateTemplate().flush();
	    this.getHibernateTemplate().clear();
		
	}
	
	

}
