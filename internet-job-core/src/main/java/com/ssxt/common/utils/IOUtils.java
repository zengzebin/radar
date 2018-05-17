package com.ssxt.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName: IOUtils
 * @Description: operation IO stream
 * @author nick
 * @date 2016年4月25日 上午11:22:43
 * 
 */
public class IOUtils {
	/**
	 * @Description: close inputStreams
	 * @param ins
	 * @return void
	 */
	public static void closeInputStream(InputStream... ins) {
		if (ins != null && ins.length > 0) {
			try {
				for (InputStream in : ins) {
					if (in != null) {
						in.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description: close OutputStreams
	 * @param outs
	 * @return void
	 */
	public static void closeOutputStream(OutputStream... outs) {
		if (outs != null && outs.length > 0) {
			try {
				for (OutputStream out : outs) {
					if (out != null) {
						out.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
