package com.repm.model;

import java.sql.Date;

public class RepmVO implements java.io.Serializable {

	private Integer repm_id;
	private Integer rest_id;
	private String repm_name;
	private String repm_desc;
	private String repm_content;
	private Date repm_startdate;
	private Date repm_enddate;
	private Integer repm_status;

	public Integer getRepm_id() {
		return repm_id;
	}

	public void setRepm_id(Integer repm_id) {
		this.repm_id = repm_id;
	}

	public Integer getRest_id() {
		return rest_id;
	}

	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}

	public String getRepm_name() {
		return repm_name;
	}

	public void setRepm_name(String repm_name) {
		this.repm_name = repm_name;
	}

	public String getRepm_desc() {
		return repm_desc;
	}

	public void setRepm_desc(String repm_desc) {
		this.repm_desc = repm_desc;
	}

	public String getRepm_content() {
		return repm_content;
	}

	public void setRepm_content(String repm_content) {
		this.repm_content = repm_content;
	}

	public Date getRepm_startdate() {
		return repm_startdate;
	}

	public void setRepm_startdate(Date repm_startdate) {
		this.repm_startdate = repm_startdate;
	}

	public Date getRepm_enddate() {
		return repm_enddate;
	}

	public void setRepm_enddate(Date repm_enddate) {
		this.repm_enddate = repm_enddate;
	}

	public Integer getRepm_status() {
		return repm_status;
	}

	public void setRepm_status(Integer repm_status) {
		this.repm_status = repm_status;
	}

}
