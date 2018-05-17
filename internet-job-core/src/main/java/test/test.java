package test;

import java.util.Calendar;
import java.util.Date;

import com.ssxt.common.utils.DateTools;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    Calendar calendar=Calendar.getInstance();
     	    calendar.set(Calendar.HOUR_OF_DAY, 23);
		    Date imgShowTime =calendar.getTime();
			imgShowTime = DateTools.setSeconds(imgShowTime, 0);
//			int minutes = DateTools.getMinutes(imgShowTime);
//			// minutes = minutes - (minutes % 6);
//			minutes = minutes - (minutes % 12);
//			imgShowTime = DateTools.setMinutes(imgShowTime, minutes);
//			imgShowTime = DateTools.getNextNMins(imgShowTime, -240);
			
			System.out.println(imgShowTime);
	}

}
