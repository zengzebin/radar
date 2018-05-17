package com.ssxt.common.persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DataEntity<T> extends BaseEntity<T> {
	// todo
	private static final long serialVersionUID = 1L;
	protected String delFlag;
	protected Integer id;
	public DataEntity() {
		super();
		this.delFlag = BaseEntity.DELFLAG_DEFAULT;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
