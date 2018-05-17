package com.ssxt.common.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtil implements ApplicationContextAware {

	/*
	 * private ApplicationContext applicationContext;
	 * 
	 * public ApplicationContext getApplicationContext() { return
	 * applicationContext; }
	 * 
	 * public void setApplicationContext(ApplicationContext applicationContext)
	 * throws BeansException { this.applicationContext = applicationContext; }
	 */

	/*
	 * class TestClass {
	 * 
	 * static int testInt = 0 ;
	 * 
	 * public static setTestInt ( int a ) { TestClass.testInt = a ; }
	 * 
	 * public void setInt ( int a1 ) { setTestInt ( a1 ); } }
	 */

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringBeanUtil.applicationContext = applicationContext;
	}

	public static <T> T getBean(String id, Class<T> clasz) {

		return applicationContext.getBean(id, clasz);
	}

	public static <T> T getBean(Class<T> clasz) {

		return applicationContext.getBean(clasz);
	}
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyPropertiesIgnoreNull(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

}
