
package test;
 
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import org.junit.Test;

import com.ssxt.common.gather.Cloud;
import com.ssxt.common.utils.DateTools;
import com.ssxt.common.utils.StringTools;
public class TestLog {
    public static void main(String[] args) throws Exception {
    	String readPath = "E:" + File.separator + "licheng.png";
    	String savePath = "E:" + File.separator+ "d" +File.separator+"dd";
    	File saveFile = new File(savePath);
    	File readFile = new File(readPath);
    	if (!saveFile.exists()) {
    		saveFile.mkdirs();
    	}
    	File imgFile = new File(savePath + File.separator + "lichengssss.png");
		FileOutputStream out = null;
    	FileInputStream in = null;
    	try{
    		out = new FileOutputStream(imgFile);
    		in = new FileInputStream(readFile);
    		int len = 0;
        	byte[] buf = new byte[1024];
        	while((len = in.read(buf)) != -1) {
        		out.write(buf, 0, len - 1);
        	}
        	out.flush();
    	} finally {
    		if (out != null) {
    			out.close();
    		}
    		if (in != null) {
    			in.close();
    		}
    	}
    }
    
    @Test
    public void test1() throws IOException {
    	String readPath = "E:" + File.separator + "licheng.jpg";
    	String savePath = "E:" + File.separator+ "d" +File.separator+"dd";
    	File saveFile = new File(savePath);
    	File readFile = new File(readPath);
    	if (!saveFile.exists()) {
    		saveFile.mkdirs();
    	}
    	FileOutputStream out = null;
    	FileImageInputStream in = null;
    	ByteArrayOutputStream outStream = null;
    	try{
    		in = new FileImageInputStream(readFile);
    		outStream = new ByteArrayOutputStream();
    		int len = 0;
        	byte[] buf = new byte[1024];
        	while((len = in.read(buf)) != -1) {
        		outStream.write(buf, 0, len - 1);
        	}
        	byte[] data = outStream.toByteArray();
        	out = new FileOutputStream(saveFile + File.separator + "lichengdssss.jpg");
        	out.write(data);
    		/*int len = 0;
        	byte[] buf = new byte[1024];
        	while((len = in.read(buf)) != -1) {
        		out.write(buf, 0, len - 1);
        	}*/
        	out.flush();
    	} finally {
    		if (out != null) {
    			out.close();
    		}
    		if (in != null) {
    			in.close();
    		}
    		if (outStream != null) {
    			outStream.close();
    		}
    	}
    }
    
    @Test
    public void test2() {
    	/*Date date = DateTools.clearSeconds(DateTools.getNextNMins(new Date(), -20));
    	String savePath = (File.separator + DateTools.DateFormate(date, "yyyyMMdd"));
    	System.out.println(savePath);*/
    	StringBuffer buf= new StringBuffer("http://www.baidu.com:8080/licheng/radar/ared/ddir/201623185000.gif");
    	String relativePath = buf.toString().replaceAll("http://www.baidu.com:8080", "");
    	System.out.println(relativePath);
    	/*String goal = buf.substring(0, buf.length() - 1);
    	String[] goals = goal.split("/");
    	System.out.println(buf.substring(0, buf.length() - 1));
    	System.out.println(goals[goals.length - 1]);*/
    }
    
    public static void gatherYuntu() {
    		String dateTime = "20160417084500";
			   while (true){
				 
				   try {
					   String starttime = dateTime;//指定时间
		 		       String addtime=starttime; //开始时间
		 		     while(true){
		 		         addtime= StringTools.addMin(addtime,30);//加30分钟
		 		         String urltime=addtime+"000";
		  		         System.out.println("after:"+urltime); 
		  		         String url="http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxcl_asc_e99_achn_lno_py_"+urltime+".jpg";
		  		        // String url="http://baidu.com";
//		  		         Cloud.saveToFile(url,StringTools.addHour(addtime,8));
		 	          }
					
				 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
    }
}