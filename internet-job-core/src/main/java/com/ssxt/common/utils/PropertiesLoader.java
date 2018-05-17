package com.ssxt.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @ClassName: PropertiesLoader
 * @Description: 加载properties文件
 * @author nick
 * @date 2016年4月24日 上午7:25:56
 * 
 */
public class PropertiesLoader {

	private Logger logger = Logger.getLogger(this.getClass());

	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	private Properties properties;

	public PropertiesLoader(String... filePath) {
		logger.info("- " + this.getClass() + " -开始加载配置文件路径...");
		properties = loadPath(filePath);
		logger.info("- " + this.getClass() + " -完成配置文件加载...");
	}

	public String getProperty(String key) throws Exception {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new Exception(" -" + this.getClass() + "- 获取不到key为 -" + key + "- 的值");
		}
		return value;
	}

	private Properties loadPath(String[] filePath) {
		Properties properties = new Properties();
		for (String path : filePath) {
			Resource resource = resourceLoader.getResource(path);
			InputStream in = null;
			try {
				in = resource.getInputStream();
				properties.load(in);
			} catch (IOException e) {
				logger.info(e.getMessage());
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
		return properties;
	}
}
