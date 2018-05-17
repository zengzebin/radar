package com.ssxt.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
* @ClassName: SpringContextHelper 
* @Description: get bean from spring application context configuration file
* @author Nick
* @date 2016年4月22日 上午10:12:25 
*  
*/

public class SpringContextHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	/** 
	* @Description: get bean by bean name
	* @param String beanName
	* @return  T 
	*/
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T)applicationContext.getBean(beanName);
	}
	
	/** 
	* @Description: get bean by required type of bean
	* @param requiredType
	* @return T 
	*/
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHelper.applicationContext = applicationContext; 
	}
}