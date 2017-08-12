package com.stpm.model;

import java.sql.Date;

public class StpmVO implements java.io.Serializable {

	private Integer stpm_id;
	private Integer store_id;
	private String stpm_name;
	private String stpm_desc;
	private String stpm_content;
	private Date stpm_startdate;
	private Date stpm_enddate;
	private Integer stpm_status;

	public Integer getStpm_id() {
		return stpm_id;
	}

	public void setStpm_id(Integer stpm_id) {
		this.stpm_id = stpm_id;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getStpm_name() {
		return stpm_name;
	}

	public void setStpm_name(String stpm_name) {
		this.stpm_name = stpm_name;
	}

	public String getStpm_desc() {
		return stpm_desc;
	}

	public void setStpm_desc(String stpm_desc) {
		this.stpm_desc = stpm_desc;
	}

	public String getStpm_content() {
		return stpm_content;
	}

	public void setStpm_content(String stpm_content) {
		this.stpm_content = stpm_content;
	}

	public Date getStpm_startdate() {
		return stpm_startdate;
	}

	public void setStpm_startdate(Date stpm_startdate) {
		this.stpm_startdate = stpm_startdate;
	}

	public Date getStpm_enddate() {
		return stpm_enddate;
	}

	public void setStpm_enddate(Date stpm_enddate) {
		this.stpm_enddate = stpm_enddate;
	}

	public Integer getStpm_status() {
		return stpm_status;
	}

	public void setStpm_status(Integer stpm_status) {
		this.stpm_status = stpm_status;
	}

}
