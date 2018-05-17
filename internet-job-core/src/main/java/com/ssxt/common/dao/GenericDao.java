package com.ssxt.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.springframework.dao.DataAccessException;

import com.ssxt.common.page.PageControl;
import com.ssxt.common.page.SqlCondGroup;

/**
 *
 */
public interface GenericDao<T extends Serializable, PK extends Serializable> {
	// -------------------- 基本检索、增加、修改、删除操作 --------------------
	// 根据主键获取实体。如果没有相应的实体，返回 null。
    public T get(PK id) throws DataAccessException;
    
    // 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
    public T getWithLock(PK id, LockMode lock);
    
    // 根据主键获取实体。如果没有相应的实体，抛出异常。
    public T load(PK id);
    
    // 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
    public T loadWithLock(PK id, LockMode lock);
    
    // 获取全部实体。
    public List<T> loadAll();
 
    // 根据实例查询
    public List<T> queryByExample(T exampleInstance) throws DataAccessException;
    
	// 根据实例查询第一个实体
	public T getFirstByExample(T entity);
	
    // 存储实体到数据库
    public PK save(T entity);
    
    // 增加或更新实体
    public void saveOrUpdate(T entity);

    // 增加或更新集合中的全部实体
    public void saveOrUpdateAll(Collection<T> entities);
    
    // 更新实体
    public void update(T entity);
    
    // 更新实体并加锁
    public void updateWithLock(T entity, LockMode lock);

    // 删除指定的实体
    public void delete(T entity);

    // 加锁并删除指定的实体
    public void deleteWithLock(T entity, LockMode lock);

    // 根据主键删除指定实体
    public void deleteByKey(PK id);

    // 根据主键加锁并删除指定的实体
    public void deleteByKeyWithLock(PK id, LockMode lock);

    // 删除集合中的全部实体
    public void deleteAll(Collection<T> entities);
    
  // -------------------- HSQL ----------------------------------------------
    // 使用HSQL语句检索数据
    public List find(String queryString);
    
    // 使用带参数的HSQL语句检索数据
    public List findByParam(String queryString, Object value);
    
    // 使用带参数的HSQL语句检索数据
    public List findByParam(String queryString, Object[] values);
    
    // 使用带命名的参数的HSQL语句检索数据
    public List findByNamedParam(String queryString, String[] paramNames,
    		Object[] values);
    
    // 使用带命名的参数的HSQL语句检索数据
    public List findByNamedParam(String queryString, Map<String, Object> paramMap);
    
    // 使用命名的HSQL语句检索数据
    public List findByNamedQuery(String queryName);

    // 使用带参数的命名HSQL语句检索数据
    public List findByNamedQuery(String queryName, Object[] values);
    
    // 使用带命名参数的命名HSQL语句检索数据
    public List findByNamedQueryAndNamedParam(String queryName,
            String[] paramNames, Object[] values);
    
    // 使用带命名参数的命名HSQL语句检索数据
    public List findByNamedQueryAndNamedParam(String queryName, Map<String, Object> paramMap);

    // 使用带命名参数的命名HSQL语句检索数据
    public List findByValueBean(String queryString, Object valueBean);
    	
    // 使用HSQL语句检索数据，返回 Iterator，使用缓存，推荐只读对象使用
    public Iterator iterate(String queryString);

    // 使用带参数HSQL语句检索数据，返回 Iterator，使用缓存，推荐只读对象使用
    public Iterator iterate(String queryString, Object[] values);

    // 关闭检索返回的 Iterator，使用缓存，推荐只读对象使用
    public void closeIterator(Iterator it);
    
    
    // 使用HSQL语句直接增加、更新、删除实体
    public int bulkUpdate(String queryString);
    
    // 使用带参数的HSQL语句增加、更新、删除实体
    public int bulkUpdate(String queryString, Object[] values);
    
    // 使用带参数的HSQL语句增加、更新、删除实体
    public int executeUpdate(String queryString, Object[] values);
    
//    // -------------------------------- 分页查询 --------------------------------
//	// 使用带参数的HSQL语句检索数据
//	public PageBean findByParam4Page(String queryString, Object value, Integer pageNum, Integer pageSize);
//
//	// 使用带参数的HSQL语句检索数据
//	public PageBean findByParam4Page(String queryString, Object[] values, Integer pageNum, Integer pageSize);
//
//	// 使用带命名的参数的HSQL语句检索数据
//	public PageBean findByNamedParam4Page(String queryString, String[] paramNames, Object[] values, Integer pageNum, Integer pageSize);

//	// 使用带命名参数的命名HSQL语句检索数据
//	public PageBean findByNamedParam4Page(String queryString, Map<String, Object> paramMap, Integer pageNum, Integer pageSize);
//
//	// 使用命名的HSQL语句检索数据
//	public PageBean findByNamedQuery4Page(String queryName, Integer pageNum, Integer pageSize);
//
//	// 使用带参数的命名HSQL语句检索数据
//	public PageBean findByNamedQuery4Page(String queryName, Object[] values, Integer pageNum, Integer pageSize);
//
//	// 使用带命名参数的命名HSQL语句检索数据
//	public PageBean findByNamedQueryAndNamedParam4Page(String queryName, String[] paramNames, Object[] values, Integer pageNum,
//			Integer pageSize);
//
//	// 使用带命名参数的命名HSQL语句检索数据
//	public PageBean findByNamedQueryAndNamedParam4Page(String queryName, Map<String, Object> paramMap, Integer pageNum,
//			Integer pageSize);
    
    // -------------------------------- Others --------------------------------

    // 加锁指定的实体
    public void lock(T entity, LockMode lockMode);

    // 强制初始化指定的实体
    public void initialize(Object proxy);

    // 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
    public void flush();
    
    // 查询实例的数量
    public long getCount();
    
    //清除旧对象的session
    public void clearSession();
    
	//接管实例（未实现）
    public void reattach(T persistentObject);

    //脱管实例（未实现）
    public void detach(T persistentObject);

    
    
    //ypx
    

	/**
	 * 返回按属性条件查询的结果列表
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param propertyName   属性名
	 * @param value          属性值
	 * @param sign           符号
	 * @param type           条件连接类型(and还是or)
	 * @return               查询结果(List列表)
	 */	
	public List<T> findByProperties(PageControl pageControl,
			String[] propertyName, Object[] value, String[] sign, String[] type);
	
	/**
	 * 返回按属性条件查询的结果列表,采用in方式,即propertyName中第一个propertyName对应valueList中的第一个List，以此类推
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param propertyName   属性名
	 * @param valueList          属性值列表
	 * @param sign           符号
	 * @param type           条件连接类型(and还是or)
	 * @return               查询结果(List列表)
	 */	
	@SuppressWarnings("unchecked")
	public List<T> findByInProperties(PageControl pageControl,
			String[] propertyName, List[] valueList, String[] sign, String[] type);

	/**
	 * 使用SQL语句查询数据
	 * @param sql   查询语句
	 * @return      查询结果(List列表)
	 */
	@SuppressWarnings("unchecked")
	public List findBySQL(String sql);

	/**
	 * 使用SQL语句查询数据，附加查询参数
	 * @param sql   查询语句
	 * @return      查询结果(List列表)
	 */
	@SuppressWarnings("unchecked")
	public List findBySQL(String sql,Object[] object);
	/**
	 * 使用SQL语句查询数据
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param sql   查询语句
	 * @return      查询结果(List列表)
	 */
	@SuppressWarnings("unchecked")
	public List findBySQL(PageControl pageControl,String sql);

	/**
	 * 使用SQL语句查询数据，附加查询参数
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param sql   查询语句
	 * @param value 属性值
	 * @param returnType 返回的类型，为Map,List或者Bean类型
	 * @return      查询结果(List列表)
	 */
	@SuppressWarnings("unchecked")
	public List findBySQL(PageControl pageControl,String hql,Object[] object);
	

	/**
	 * 使用SQL语句查询数据，附加查询参数
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param sql   查询语句
	 * @param returnType 返回的类型，为Map,List或者Bean类型(null默认)
	 * @return      查询结果(List列表)
	 */
	@SuppressWarnings("unchecked")
	public List findByNativeSQL(PageControl pageControl,String sql,Class returnType);

	/**
	 * 使用SQL语句查询数据，附加查询参数
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param sql   查询语句
	 * @param value 属性值
	 * @param returnType 返回的类型，为Map,List或者Bean类型(null默认)
	 * @return      查询结果(List列表)
	 */
	@SuppressWarnings("unchecked")
	public List findByNativeSQL(PageControl pageControl,String sql,Object[] object,Class returnType);
	

	/**
	 * 
	 * hql返回按SqlCondGroup条件查询的结果列表<br/>
	 * (这里描述这个方法适用条件 – 可选)<br/>
	 * (这里描述这个方法的执行流程 – 可选)<br/>
	 * (这里描述这个方法的使用方法 – 可选)<br/>
	 * (这里描述这个方法的注意事项 – 可选)<br/>
	 * @param pageControl
	 * @param conList
	 * @return 
	 *List
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List findByCondition(PageControl pageControl,List<SqlCondGroup> conList);
	/**
	 * 
	 * sql返回按SqlCondGroup条件查询的结果列表<br/>
	 * (这里描述这个方法适用条件 – 可选)<br/>
	 * (这里描述这个方法的执行流程 – 可选)<br/>
	 * (这里描述这个方法的使用方法 – 可选)<br/>
	 * (这里描述这个方法的注意事项 – 可选)<br/>
	 * @param pageControl
	 * @param conList
	 * @param preWhereSql where之前的语句,如"select * from abc"
	 * @return 
	 *List
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List findByNativeCondition(PageControl pageControl,
			List<SqlCondGroup> conList,String preWhereSql,Class returnType);
	/**
	 * 
	 * sql返回按propertyName,valueList,sign,type条件查询的结果列表<br/>
	 * @param pageControl
	 * @param preWhereSql where之前的语句,如"select * from abc"
	 * @param propertyName
	 * @param valueList
	 * @param sign
	 * @param type
	 * @return 
	 *List
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List findByNativeInProperties(PageControl pageControl,String preWhereSql,
			String[] propertyName, List[] valueList, String[] sign, String[] type,Class returnType);
	
	
	/**
	 * 返回带统计函数、按属性条件查询的结果列表（List<Map>）,采用in方式,即propertyName中第一个propertyName对应valueList中的第一个List，以此类推
	 * 必须在列前带表的别名如a.colmn1,c.colmn2
	 * @param pageControl    用来控制排序和分页的参数类
	 * @param fromTable    	 from表名，多表如A m left outer join B n
	 * @param useProperty    需要获得的参数名
	 * @param alias			 别名，用map.get(别名)获取参数值:
	 * @param propertyName   属性名
	 * @param valueList      属性值列表
	 * @param sign           符号
	 * @param type           条件连接类型(and还是or)
	 * @return               查询结果(List列表)
	 */	
	@SuppressWarnings("unchecked")
	public List findByStatistics(PageControl pageControl,String fromTable,String[] useProperty,String[] alias, String[] propertyName, List[] valueList, String[] sign, String[] type);

	/**
	 * 
	 * 创建数据<br/>
	 * @param schoolId
	 * @param userId
	 * @param userName
	 * @param reason
	 * @param domain
	 * @return
	 */
	public Serializable saveDomain(String schoolId, Integer userId, String userName, String reason, T domain);
	/**
	 * 修改数据
	 * @param schoolId
	 * @param userId
	 * @param userName
	 * @param reason
	 * @param oldBean
	 * @param newBean
	 */
	public void updateDomain(String schoolId, Integer userId, String userName, String reason, T oldBean, T newBean);
	public boolean existDomain(T domain);
	public void commonConList(List<SqlCondGroup> conList,T bean);
	
	  /**
	  * 
	  * @param sql 语句
	  * @param parameter 返回的字段对应vo
	  * @param c  vo
	  * @return
	  */
    public List<?>  findListSQL(final String sql,final String []parameter, Class<?> c);

}