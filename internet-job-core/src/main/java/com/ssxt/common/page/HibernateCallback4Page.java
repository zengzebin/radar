//package com.ssxt.common.page;
//
//import java.math.BigInteger;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Set;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.engine.NamedQueryDefinition;
//import org.hibernate.impl.SessionFactoryImpl;
//import org.hibernate.type.Type;
//import org.springframework.orm.hibernate3.HibernateCallback;
//
///**
// * 查询分页
// *
// */
//public class HibernateCallback4Page implements HibernateCallback<Object> {
//	// 分页对象
//	private PageBean pageBean;
//
//
//	@SuppressWarnings("unused")
//	private HibernateCallback4Page() {
//	}
//
//	/**
//	 * 初始化分页对象
//	 * 
//	 * @param pageBean
//	 */
//	public HibernateCallback4Page(PageBean pageBean) {
//		this.pageBean = pageBean;
//	}
//
//	/**
//	 * 重写回调接口方法
//	 */
//	@Override
//	public Object doInHibernate(Session session) throws HibernateException, SQLException {
//		int flag = pageBean.getSqlOrHql();
//		// 执行hql
//		if (flag == Constant.HQL) {
//			return doInHql(session);
//			// 执行sql
//		} else if (flag == Constant.SQL) {
//			return doInSql(session);
//			// 其它情况
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 根据hql查询分页
//	 * 
//	 * @param session
//	 * @return 
//	 * @return list
//	 * @throws HibernateException
//	 * @throws SQLException
//	 */
//	public Object doInHql(Session session) throws HibernateException, SQLException {
//		// hql语句
//		StringBuffer sbf = new StringBuffer("");
//		if (pageBean.getHql() != null && !"".equals(pageBean.getHql())) {
//			sbf.append(pageBean.getHql());
//		} else if (pageBean.getQueryName() != null && !"".equals(pageBean.getQueryName())) {
//			//命名的Hql语句
//			NamedQueryDefinition nqd = ((SessionFactoryImpl) session.getSessionFactory())
//					.getNamedQuery(pageBean.getQueryName());
//			if (nqd != null) {
//				String queryString = nqd.getQueryString();
//				if (null != queryString && !"".equals(queryString)) {
//					pageBean.setHql(queryString);
//					sbf.append(pageBean.getHql());
//				}
//			}
//		}
//		// 根据hql语句返回总记录数
//		String hqlCount = "select count(*) " + sbf.substring(new String(sbf).toUpperCase().indexOf("FROM"));
//        
//        hqlCount = removeOrderStr(hqlCount);
//		
//		// 执行查询语句
//		Query query = session.createQuery(hqlCount);
//		if (pageBean.getHqlParam() != null) {
//			// hql参数列表获取
//			Object[] param = pageBean.getHqlParam();
//			// 为hql设置参数
//			for (int i = 0; i < param.length; i++) {
//				if (param[i] == null) {
//					continue;
//				}
//				query.setParameter(i, param[i]);
//			}
//		} else if (pageBean.getParamMap() != null) {
//			query.setProperties(pageBean.getParamMap());
//		}
//		// 当前总记录数
//		int resultCount = ((Long) query.uniqueResult()).intValue();
//		// 总页数
//		int pageCount = (resultCount / pageBean.getPageSize()) + (resultCount % pageBean.getPageSize() > 0 ? 1 : 0);
//		if (pageBean.getPageNum() <= 0) {
//			pageBean.setPageNum(1);
//		}
//		if (pageBean.getPageNum() > pageCount) {
//			// 当前页
//			pageBean.setPageNum(pageCount);
//		}
//		if (pageCount == 0) {
//			pageBean.setPageNum(1);
//			pageBean.setPageCount(0);
//			return pageBean.getResultList();
//		}
//		query = session.createQuery(pageBean.getHql());
//		if (pageBean.getHqlParam() != null) {
//			// hql参数列表获取
//			Object[] param = pageBean.getHqlParam();
//			// 为hql设置参数
//			for (int i = 0; i < param.length; i++) {
//				if (param[i] == null) {
//					continue;
//				}
//				query.setParameter(i, param[i]);
//			}
//		} else if (pageBean.getParamMap() != null) {
//			query.setProperties(pageBean.getParamMap());
//		}
//		// 设置分页开始记录点
//		query.setFirstResult((pageBean.getPageNum() - 1) * pageBean.getPageSize());
//		// 显示最大记录数
//		query.setMaxResults(pageBean.getPageSize());
//		// 查询结果列表
//		pageBean.setResultList(query.list());
//		// 当前总记录数
//		pageBean.setResultCount(resultCount);
//		// 总页数
//		pageBean.setPageCount(pageCount);
//	
//		return pageBean.getResultList();
//	}
//
//	/**
//	 * 根据sql查询分页
//	 * 
//	 * @param session
//	 * @return list
//	 * @throws HibernateException
//	 * @throws SQLException
//	 */
//	public Object doInSql(Session session) throws HibernateException, SQLException, SecurityException {
//		// sql语句
//		StringBuffer sbf = new StringBuffer("");
//		if (pageBean.getSql() != null && !"".equals(pageBean.getSql())) {
//			sbf.append(pageBean.getSql());
//		} else if (pageBean.getQueryName() != null && !"".equals(pageBean.getQueryName())) {
//			//命名的sql语句
//			NamedQueryDefinition nqd = ((SessionFactoryImpl) session.getSessionFactory())
//					.getNamedQuery(pageBean.getQueryName());
//			if (nqd != null) {
//				String queryString = nqd.getQueryString();
//				if (null != queryString && !"".equals(queryString)) {
//					pageBean.setHql(queryString);
//					sbf.append(pageBean.getHql());
//				}
//			}
//		}
//		// 根据sql查询总记录数
//		String sqlCount = "select count(*) " + sbf.substring(sbf.indexOf("from"));
//		SQLQuery query = session.createSQLQuery(sqlCount);
//		if (pageBean.getSqlParam() != null) {
//			// sql参数列表获取
//			Object[] param = pageBean.getSqlParam();
//			// 为sql设置参数
//			for (int i = 0; i < param.length; i++) {
//				if (param[i] == null) {
//					continue;
//				}
//				// 虽然是SQL查询,但并非JDBC,而是hibernate的SQL查询,所以参数索引依然从0开始 ----by 王晨
//				// query.setParameter(i+1, param[i]);
//				query.setParameter(i, param[i]);
//			}
//		} else if (pageBean.getParamMap() != null) {
//			query.setProperties(pageBean.getParamMap());
//		}
//		// 当前总记录数
//		int resultCount = ((BigInteger) query.uniqueResult()).intValue();
//		// 总页数
//		int pageCount = (resultCount / pageBean.getPageSize()) + (resultCount % pageBean.getPageSize() > 0 ? 1 : 0);
//		if (pageBean.getPageNum() <= 0) {
//			pageBean.setPageNum(1);
//		}
//		if (pageBean.getPageNum() > pageCount) {
//			// 当前页
//			pageBean.setPageNum(pageCount);
//		}
//		if (pageCount == 0) {
//			pageBean.setPageNum(0);
//			pageBean.setPageCount(0);
//			return pageBean.getResultList();
//		}
//		query = session.createSQLQuery(pageBean.getSql()).addEntity(pageBean.getBeanClass());
//		if (pageBean.getSqlParam() != null) {
//			// sql参数列表获取
//			Object[] param = pageBean.getSqlParam();
//			// 为sql设置参数
//			for (int i = 0; i < param.length; i++) {
//				if (param[i] == null) {
//					continue;
//				}
//				query.setParameter(i, param[i]);
//			}
//		}
//		// 指定 执行SQL查询时字段所对应的hibernate类型
//		Map<String, Type> resultMap = pageBean.getResultMap();
//		if (resultMap != null && resultMap.size() > 0) {
//			Set entrySet = resultMap.entrySet();
//			for (Iterator it = entrySet.iterator(); it.hasNext();) {
//				Entry entry = (Entry) it.next();
//				query = query.addScalar((String) entry.getKey(), (Type) entry.getValue());
//			}
//		}
//		// 设置起始记录点
//		query.setFirstResult((pageBean.getPageNum() - 1) * pageBean.getPageSize());
//		// 最大显示记录数
//		query.setMaxResults(pageBean.getPageSize());
//		// 封装新Bean
//		List l = query.list();
//		List newL = new ArrayList();
//		for (int i = 0; i < l.size(); i++) {
//			Object[] obj = (Object[]) l.get(i);
//			// Class clazz = pageBean.getBeanClass();
//			Object o = (Object) obj[0];
//			newL.add(o);
//		}
//		// 查询结果列表
//		pageBean.setResultList(newL);
//		// 当前总记录数
//		pageBean.setResultCount(resultCount);
//		// 总页数
//		pageBean.setPageCount(pageCount);
//
//		return pageBean.getResultList();
//	}
//	
//	
//	/**
//	 * 去掉排序字符串
//	 * @param hql
//	 * @return
//	 */
//	public static String removeOrderStr(final String hql) {
//        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE); 
//        Matcher m = p.matcher(hql); 
//        StringBuffer sb = new StringBuffer(); 
//        while (m.find()) { 
//            m.appendReplacement(sb, ""); 
//        } 
//        m.appendTail(sb); 
//        return sb.toString(); 
//    }
//}