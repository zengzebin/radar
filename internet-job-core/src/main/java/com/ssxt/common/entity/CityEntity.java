package com.ssxt.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ssxt.common.persistence.DataEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "wea_city")
public class CityEntity extends DataEntity<CityEntity> {
	
	private String cityId ;
	private String cityEn ;
	private String cityCn ;
	private String area ;
	private String province ;
	private int level=0;
	private int parentId=0;
	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the cityEn
	 */
	public String getCityEn() {
		return cityEn;
	}
	/**
	 * @param cityEn the cityEn to set
	 */
	public void setCityEn(String cityEn) {
		this.cityEn = cityEn;
	}
	/**
	 * @return the cityCn
	 */
	public String getCityCn() {
		return cityCn;
	}
	/**
	 * @param cityCn the cityCn to set
	 */
	public void setCityCn(String cityCn) {
		this.cityCn = cityCn;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public CityEntity() {
		super();
	}
	public CityEntity(String cityId, String cityEn, String cityCn, String area, String province, int level,
			int parentId) {
		super();
		this.cityId = cityId;
		this.cityEn = cityEn;
		this.cityCn = cityCn;
		this.area = area;
		this.province = province;
		this.level = level;
		this.parentId = parentId;
	}
	
	
	
}
