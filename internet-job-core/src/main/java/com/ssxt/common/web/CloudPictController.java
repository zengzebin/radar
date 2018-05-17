package com.ssxt.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssxt.common.entity.JobEntity;
import com.ssxt.common.service.JobLCService;

@Controller
@RequestMapping(value = "/jobTest")
public class CloudPictController {
	
	@Autowired
	private JobLCService jobService;
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void jobTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JobEntity jobEntity = jobService.getJobById(1);
		JobEntity jobEntity2 = jobEntity;
		jobEntity2.setId(3);
		jobEntity2.setLastExecutor("updateTest");
		jobService.saveEntity(jobEntity2);
	}
	
	/*// 云图采集
	@RequestMapping(value = "/gather", method = RequestMethod.GET)
	public void openCloudGather(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String dateTime = request.getParameter("dateTime"); // 20160301084500

		PrintWriter out = response.getWriter();
		out.print("云图采集开启请关闭窗口");
		out.flush();
		long period = 30 * 60 * 1000; // 30分钟

		while (true) {

			try {
				String starttime = dateTime;// 指定时间
				String addtime = starttime; // 开始时间
				while (true) {
					addtime = StringTools.addMin(addtime, 30);// 加30分钟
					String urltime = addtime + "000";
					System.out.println("after:" + urltime);
					String url = "http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxcl_asc_e99_achn_lno_py_"
							+ urltime + ".jpg";
					// String url="http://baidu.com";
					Cloud.saveToFile(url, StringTools.addHour(addtime, 8));
					Thread.sleep(period);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}*/

	/*// 查询云图，用于展示
	@RequestMapping(value = "/searchCloudPict", method = RequestMethod.POST)
	@ResponseBody
	public String searchCloudPict(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String beginTimeStr = request.getParameter("beginTime");
		String endTimeStr = request.getParameter("endTime");
		try {
			Date beginDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(beginTimeStr);
			Date endDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(endTimeStr);
			if (DateTools.DateCompare(beginDate, endDate) > 0) {
				return "";
			}
			URL url = null;
			StringBuffer buf = new StringBuffer("");

			String urlTime = beginTimeStr;
			Date urlDate = DateTools.stringDateFormate(beginTimeStr, DateTools.DEFAULT_PATTERN);
			while (StringUtils.isNotBlank(urlTime) && DateTools.DateCompare(urlDate, endDate) <= 0) {
				String httpUrl = "http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxcl_asc_e99_achn_lno_py_"
						+ urlTime + "000.jpg";
				url = new URL(httpUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				// 超时响应时间为5秒
				conn.setConnectTimeout(5 * 1000);
				// 通过输入流获取图片数据
				InputStream inStream = conn.getInputStream();
				// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
				byte[] data = FileTools.readInputStream(inStream);
				if (data.length > 2000) {
					buf.append(urlTime + "000" + "#");
				}
				urlTime = getTime(urlTime);
				urlDate = DateTools.stringDateFormate(beginTimeStr, DateTools.DEFAULT_PATTERN);
			}
			String resultStr = "";
			if (buf != null && buf.length() > 0) {
				resultStr = buf.substring(0, buf.length() - 1);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 打开链接
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String json = "{\"success\" : true, \"msg\" : \"fulure\"}";
		return json;
	}
*/
	@RequestMapping(value = "/getCloudPictList", method = RequestMethod.GET)
	public String getCloudPictList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "CloudPictureView/cloudPictList";

	}

	/*// 雷达区域采集
	@RequestMapping(value = "/openRadarGather", method = RequestMethod.GET)
	public void openRadarGather(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String dateTime = request.getParameter("dateTime");// 20160301090000
		PrintWriter out = response.getWriter();
		out.print("雷达区域采集开启请关闭窗口");
		out.flush();
		long period = 10 * 60 * 1000;// 每十分钟采集一次
		try {
			String starttime = dateTime;// 指定时间
			String addtime = starttime; // 开始时间
			while (true) {
				addtime = StringTools.addMin(addtime, 10);// 加30分钟
				String urltime = addtime + "001";
				System.out.println("after:" + urltime);
				Radar ra = new Radar(urltime, 2);
				ra.start();
				Thread.sleep(period);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/
	/*// 雷达城市采集
	@RequestMapping(value = "/openRadarAreaGather", method = RequestMethod.GET)
	public void openRadarAreaGather(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String dateTime = request.getParameter("dateTime");// 20160301092000
		PrintWriter out = response.getWriter();
		out.print("雷达城市采集开启请关闭窗口");
		out.flush();

		try {
			long period = 5 * 60 * 1000;
			String starttime = dateTime;// 指定时间
			String addtime = starttime; // 开始时间
			while (true) {
				addtime = StringTools.addMin(addtime, 5);// 加30分钟
				String urltime = addtime + "000";
				System.out.println("after:" + urltime);
				Radar ra = new Radar(urltime, 1);
				ra.start();
				Thread.sleep(period);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	/** 
	* @Description: 获取时间
	* @param String dataStr
	* @return	String
	* @throws ParseException 
	*/
/*	private String getTime(String dateStr) throws ParseException {
		Date date = DateTools.stringDateFormate(dateStr, DateTools.DEFAULT_PATTERN);
		String resultTime = DateTools.DateFormate(DateTools.getNext30Mins(date));
		return resultTime;
	}
	*/
	
	
}
