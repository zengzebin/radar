package com.ssxt.common.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssxt.common.dao.AreaDao;
import com.ssxt.common.entity.AreaEntity;

@Service
@Transactional
public class AreaService {
	@Autowired
	public AreaDao areaDao;
	
	public List<AreaEntity> getSingleStAreas() {
		return areaDao.getSingleStArea();
	}
	
	public List<AreaEntity> getMultiStAreas() {
		return areaDao.getMultiStAreas();
	}
	

	public List<AreaEntity> getAllEnableAeas() {
		return areaDao.getAllEnableAeas();
	}


	public List<AreaEntity> getAllSunList(List<AreaEntity> totalList, AreaEntity parent) {
		return getAllSunList(totalList, new ArrayList<AreaEntity>(), parent);
	}
	public List<AreaEntity> getAllSunList(List<AreaEntity> totalList,
			List<Integer> parentCodeList) {
		List<AreaEntity> sunList=new ArrayList<AreaEntity>();
		for (Iterator<Integer> iterator = parentCodeList.iterator(); iterator.hasNext();) {
			Integer parentCode = (Integer) iterator.next();
			List<AreaEntity> tmpList=getAllSunList(totalList, parentCode);
			if(tmpList!=null)
			for (Iterator<AreaEntity> iterator2 = tmpList.iterator(); iterator2.hasNext();) {
				AreaEntity AreaEntity = (AreaEntity) iterator2.next();
				if(!sunList.contains(AreaEntity))
					sunList.add(AreaEntity);
			}
		}
		
		return sunList;
	}
	/**
	 * 获取所有子部门
	 * @param totalList
	 * @param parentCode
	 * @return
	 */
	public List<AreaEntity> getAllSunList(List<AreaEntity> totalList, Integer parentCode) {
		return getAllSunList(totalList, new ArrayList<AreaEntity>(),parentCode);
	}


	public List<AreaEntity> getAllSunList(List<AreaEntity>  totalList,
			List<AreaEntity> sunList, AreaEntity parent) {
		if(!sunList.contains(parent))sunList.add(parent);
		Iterator<AreaEntity>  it = totalList.iterator();
		while (it.hasNext()) {
			AreaEntity bean =  it.next();
			if (parent.getId().equals(bean.getParentId())) {
				getAllSunList(totalList, sunList, bean);
			}
		}
		return sunList;
	}
	public List<AreaEntity> getAllSunList(List<AreaEntity>  totalList,
			List<AreaEntity> sunList, Integer parentCode) {
//		if(parentCode==null||-1==parentCode)
//			parentCode=0;
		AreaEntity parent=getAreaEntityInList(totalList, parentCode);
		if(parent==null)
			return null;
		sunList.add(parent);
		Iterator<AreaEntity>  it = totalList.iterator();
		while (it.hasNext()) {
			AreaEntity bean =  it.next();
			if (parent.getId().equals(bean.getParentId())) {
				getAllSunList(totalList, sunList, bean);
			}
		}
		return sunList;
	}
	/**
	 * 
	 * @param totalList
	 * @param parentCode
	 * @return
	 */
	public List<AreaEntity> getSunList(List<AreaEntity>  totalList, Integer parentCode) {
		List<AreaEntity> sunList = new ArrayList<AreaEntity>();
		Iterator<AreaEntity>  it = totalList.iterator();
		while (it.hasNext()) {
			AreaEntity bean = it.next();
			if (parentCode.equals(bean.getParentId())) {
				sunList.add(bean);
			}
		}
		return sunList;
	}
	/**
	 * 在所有数据中根据id找出数据
	 * @param totalList
	 * @param dptId
	 * @return
	 */
	public AreaEntity getAreaEntityInList(List<AreaEntity>  totalList, Integer dptId) {
		if(dptId==null)
			return null;
		Iterator<AreaEntity>  it = totalList.iterator();
		while (it.hasNext()) {
			AreaEntity bean = it.next();
			if (dptId.equals(bean.getId())) {
				return bean;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param totalList
	 * @param parentId
	 * @return
	 */
	public List<AreaEntity> getTreeSortList(List<AreaEntity>  totalList, Integer parentId){
		List<AreaEntity> list=new ArrayList<AreaEntity>();
		List<AreaEntity> tmpList=getSunList(totalList, parentId);
		if(tmpList!=null)
		for (AreaEntity AreaEntity : tmpList) {
			list.add(AreaEntity);
			list.addAll(getTreeSortList(totalList, AreaEntity.getId()));
		}
		return list;
		
	}
	
}
