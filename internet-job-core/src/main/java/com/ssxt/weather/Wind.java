package com.ssxt.weather;

import java.io.Serializable;

//风力风向
@SuppressWarnings("serial")
public class Wind  implements Serializable{

    private String deg;	// "10", 
    private String dir;	// "北风", 
    private String sc;	// "3级", 
    private String spd;	// "15" 
	/**
	 * @return the deg
	 */
	public String getDeg() {
		return deg;
	}
	/**
	 * @param deg the deg to set
	 */
	public void setDeg(String deg) {
		this.deg = deg;
	}
	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	/**
	 * @return the sc
	 */
	public String getSc() {
		return sc;
	}
	/**
	 * @param sc the sc to set
	 */
	public void setSc(String sc) {
		this.sc = sc;
	}
	/**
	 * @return the spd
	 */
	public String getSpd() {
		return spd;
	}
	/**
	 * @param spd the spd to set
	 */
	public void setSpd(String spd) {
		this.spd = spd;
	}
}
