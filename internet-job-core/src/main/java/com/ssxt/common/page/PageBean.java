package com.ssxt.common.page;

import java.util.List;
import java.util.Map;
import org.hibernate.type.Type;

/**
 * 
 * 分页bean
 * 
 * @version 1.0 2009-4-21 下午03:13:15
 */
public class PageBean{
	// 显示页码
	private int pageNum = 1;
	// 一页显示的记录数
	private int pageSize = Constant.PAGE_SIZE;
	// 总页数
	private int pageCount;
	// 总记录数
	private int resultCount;
	// 集合列表
	private List resultList;
	// 执行的sql语句
	private String sql;
	// 执行的hql语句
	private String hql;
	//命名的HQL语句名
	private String queryName;
	// 执行查询语句的类型
	private int sqlOrHql;
	// 参map
	private Map<String, Object> paramMap;
	// hql参数列表
	private Object[] hqlParam;
	// 页面跳转
	private String redirectUrl;
	// sql参数列表
	private Object[] sqlParam;
	// sql实体类型
	private Class beanClass;
	// 执行SQL查询返回结果时字段对应的hibernate类型MAP
	private Map<String, Type> resultMap;


	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Object[] getSqlParam() {
		return sqlParam;
	}

	public void setSqlParam(Object[] sqlParam) {
		this.sqlParam = sqlParam;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Object[] getHqlParam() {
		return hqlParam;
	}

	public void setHqlParam(Object[] hqlParam) {
		this.hqlParam = hqlParam;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if (null != pageNum && pageNum > 0) {
			this.pageNum = pageNum;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (null != pageSize && pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public int getSqlOrHql() {
		return sqlOrHql;
	}

	public void setSqlOrHql(int sqlOrHql) {
		this.sqlOrHql = sqlOrHql;
	}
	
	public Map<String, Type> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Type> resultMap) {
		this.resultMap = resultMap;
	}

	public String getQueryName() {
		return queryName;
	}
	
	public String setQueryName(String queryName) {
		return this.queryName = queryName;
	}
}