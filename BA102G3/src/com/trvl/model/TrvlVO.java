package com.trvl.model;

import java.io.Serializable;
import java.sql.Date;

public class TrvlVO implements Serializable {
	
	private Integer trvl_id;
	private Integer user_id;
	private Date trvl_date;
	private String trvl_tittle;
	private String trvl_loc;
	private String trvl_content;
	private Integer trvl_count;
	private Integer trvl_score;
	
	public TrvlVO () {
		
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

	public Date getTrvl_date() {
		return trvl_date;
	}

	public void setTrvl_date(Date trvl_date) {
		this.trvl_date = trvl_date;
	}

	public String getTrvl_tittle() {
		return trvl_tittle;
	}

	public void setTrvl_tittle(String trvl_tittle) {
		this.trvl_tittle = trvl_tittle;
	}

	public String getTrvl_loc() {
		return trvl_loc;
	}

	public void setTrvl_loc(String trvl_loc) {
		this.trvl_loc = trvl_loc;
	}

	public String getTrvl_content() {
		return trvl_content;
	}

	public void setTrvl_content(String trvl_content) {
		this.trvl_content = trvl_content;
	}

	public Integer getTrvl_count() {
		return trvl_count;
	}

	public void setTrvl_count(Integer trvl_count) {
		this.trvl_count = trvl_count;
	}

	public Integer getTrvl_score() {
		return trvl_score;
	}

	public void setTrvl_score(Integer trvl_score) {
		this.trvl_score = trvl_score;
	}

	
}
