package com.ssxt.common.module.jobModule.saveWay;

import java.io.InputStream;
import java.util.Date;

public interface SaveWayInterface {
	boolean saveInFile(Date showTime,InputStream in, String path) throws Exception;
	
	void saveInDB(Date showTime,boolean status) throws Exception;
}
