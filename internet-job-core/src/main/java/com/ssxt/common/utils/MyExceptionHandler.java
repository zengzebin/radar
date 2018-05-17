package com.ssxt.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常统一处理
 * 
 * @author Administrator
 *
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
	private static Logger log = Logger.getLogger(MyExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception error) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", "{\"error\":1}");
		log.error("错误信息", error);

		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("{\"error\":1}");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("error");
	}

}
