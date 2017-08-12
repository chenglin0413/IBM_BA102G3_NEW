package com.tlcm.model;

import java.io.Serializable;
import java.sql.Date;

public class TlcmVO implements Serializable {
	private Integer tlcm_id;
	private Integer trvl_id;
	private Integer user_id;
	private Date tlcm_date;
	private String tlcm_content;
	
	public TlcmVO () {
		
	}

	public Integer getTlcm_id() {
		return tlcm_id;
	}

	public void setTlcm_id(Integer tlcm_id) {
		this.tlcm_id = tlcm_id;
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

	public Date getTlcm_date() {
		return tlcm_date;
	}

	public void setTlcm_date(Date tlcm_date) {
		this.tlcm_date = tlcm_date;
	}

	public String getTlcm_content() {
		return tlcm_content;
	}

	public void setTlcm_content(String tlcm_content) {
		this.tlcm_content = tlcm_content;
	}
	 	 
	 
}
