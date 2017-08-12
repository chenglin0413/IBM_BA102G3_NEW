package com.rptl.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class RptlVO implements Serializable {
	private Integer rptl_id;
	private Integer trvl_id;
	private Integer user_id;
	private Timestamp rptl_date;
	private Integer rptl_status;
	private String rptl_tittle;
	private String rptl_content;
	
	public RptlVO (){
		
	}

	public Integer getRptl_id() {
		return rptl_id;
	}

	public void setRptl_id(Integer rptl_id) {
		this.rptl_id = rptl_id;
	}

	public Integer getTrvl_id() {
		return trvl_id;
	}

	public void setTrvl_id(Integer trvl_id) {
		this.trvl_id = trvl_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Timestamp getRptl_date() {
		return rptl_date;
	}

	public void setRptl_date(Timestamp rptl_date) {
		this.rptl_date = rptl_date;
	}

	public Integer getRptl_status() {
		return rptl_status;
	}

	public void setRptl_status(Integer rptl_status) {
		this.rptl_status = rptl_status;
	}

	public String getRptl_tittle() {
		return rptl_tittle;
	}

	public void setRptl_tittle(String rptl_tittle) {
		this.rptl_tittle = rptl_tittle;
	}

	public String getRptl_content() {
		return rptl_content;
	}

	public void setRptl_content(String rptl_content) {
		this.rptl_content = rptl_content;
	}
	
	
	
	
}
