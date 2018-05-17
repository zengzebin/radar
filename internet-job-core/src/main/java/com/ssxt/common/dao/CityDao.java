package com.ssxt.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssxt.common.entity.CityEntity;
import com.ssxt.common.page.PageControl;
import com.ssxt.common.page.SqlCondGroup;
import com.ssxt.common.persistence.BaseEntity;

@Repository
public class CityDao extends GenericDaoImpl<CityEntity,Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CityEntity loadByCityId(String cityId){
		CityEntity city=null;
		List<SqlCondGroup> conList = new ArrayList<SqlCondGroup>();
		PageControl pageControl = PageControl.getQueryOnlyInstance();
		conList.add(new SqlCondGroup("cityId", cityId, "=", "and"));
		List<CityEntity> list=findByCondition(pageControl, conList);
		if(list!=null&&list.size()==1){
			city=list.get(0);
		}
		else{
			
		}
		return city;
		
	}

}
