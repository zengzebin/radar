package com.ssxt.common.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ssxt.common.persistence.DataEntity;

@Entity
@Table(name = "area")
public class AreaEntity extends DataEntity<AreaEntity> {
	// todo
	private static final long serialVersionUID = 1L;
	
	private String areaName;
	private String code;
//	private AreaEntity parentId;
	private Integer parentId;
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="parent_id")
//	@NotFound(action = NotFoundAction.IGNORE)
//	@NotNull
//	public AreaEntity getParentId() {
//		return parentId;
//	}

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public AreaEntity(String areaName, String code, Integer parentId) {
		super();
		this.areaName = areaName;
		this.code = code;
		this.parentId = parentId;
	}
	public AreaEntity() {
		super();
	}
	
	
}
