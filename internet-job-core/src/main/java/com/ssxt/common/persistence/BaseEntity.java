package com.ssxt.common.persistence;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {
	// todo
	private static final long serialVersionUID = 1L;
	
	public static String DELFLAG_DEFAULT = "0";
	public static String DELFLAG_DELETE = "1";
}
