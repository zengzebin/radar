package com.ssxt.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssxt.common.entity.AreaEntity;
import com.ssxt.common.page.PageControl;
import com.ssxt.common.page.SqlCondGroup;
import com.ssxt.common.persistence.BaseEntity;

@Repository
public class AreaDao extends GenericDaoImpl<AreaEntity,Integer> implements Serializable {

	private static  String fieldstr="id,areaName,code,parentId,delFlag";
	// todo
	private static final long serialVersionUID = 1L;

	public List<AreaEntity> getSingleStArea() {
		// TODO Auto-generated method stub
//		List<AreaEntity> areaList = findBySql(
//				"select " + fieldstr + " from area  where area.id in (select area.id from AreaEntity a where a.parentId = :p1 and a.delFlag = :p2) and area.delFlag = :p3",
////				"from AreaEntity area where area.id in (select area.id from AreaEntity a where a.parentId.id = :p1 and a.delFlag = :p2) and area.delFlag = :p3",
//				new Parameter(1, BaseEntity.DELFLAG_DEFAULT, BaseEntity.DELFLAG_DEFAULT));
		List<SqlCondGroup> conList = new ArrayList<SqlCondGroup>();
		PageControl pageControl = PageControl.getQueryOnlyInstance();
		conList.add(new SqlCondGroup("delFlag", "0", "=", "and"));
		conList.add(new SqlCondGroup("parentId", null, "in (select id from AreaEntity a where a.parentId = 1 and a.delFlag = 0) ", "and"));
	
		return findByCondition(pageControl, conList);
	}

	/**
	 * @Description: get mosaicing graph
	 * @return List<AreaEntity>
	 */
	public List<AreaEntity> getMultiStAreas() {
		System.out.println("AreaDao.getMultiStAreas() " + "");
//		List<AreaEntity> areaList = findBySql(
////				"from AreaEntity area where   area.delFlag = :p1",
////				"　select " + fieldstr + " from area  where area.parentId.id = :p1 and area.delFlag = :p2",
//				"　select " + fieldstr + " from area  where area.parentId = :p1 and area.delFlag = :p2",
//				new Parameter(1, BaseEntity.DELFLAG_DEFAULT));
		List<SqlCondGroup> conList = new ArrayList<SqlCondGroup>();
		PageControl pageControl = PageControl.getQueryOnlyInstance();
		conList.add(new SqlCondGroup("delFlag", "0", "=", "and"));
		conList.add(new SqlCondGroup("parentId", 1, "=", "and"));
	
		return findByCondition(pageControl, conList);
	}

	public List<AreaEntity> getAllEnableAeas() {
//		List<Map> mapList=findBySql(
//				"　select " + fieldstr + " from area  where  delFlag = 0 ",null,Map.class);
//		System.out.println("AreaDao.getAllEnableAeas(mapList) " + mapList);

		List<SqlCondGroup> conList = new ArrayList<SqlCondGroup>();
		PageControl pageControl = PageControl.getQueryOnlyInstance();
		conList.add(new SqlCondGroup("delFlag", "0", "=", "and"));
		List<AreaEntity> areaList =findByCondition(pageControl, conList);
		return areaList;
	}
}
