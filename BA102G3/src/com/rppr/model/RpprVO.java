package com.rppr.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class RpprVO implements Serializable{
	private Integer rppr_id;
	private Integer prod_id;
	private Integer user_id;
	private Timestamp rppr_date;
	private Integer rppr_status;
	private String rppr_tittle;
	private String rppr_content;
	
	public RpprVO (){
		
	}

	public Integer getRppr_id() {
		return rppr_id;
	}

	public void setRppr_id(Integer rppr_id) {
		this.rppr_id = rppr_id;
	}

	public Integer getProd_id() {
		return prod_id;
	}

	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Timestamp getRppr_date() {
		return rppr_date;
	}

	public void setRppr_date(Timestamp rppr_date) {
		this.rppr_date = rppr_date;
	}

	public Integer getRppr_status() {
		return rppr_status;
	}

	public void setRppr_status(Integer rppr_status) {
		this.rppr_status = rppr_status;
	}

	public String getRppr_tittle() {
		return rppr_tittle;
	}

	public void setRppr_tittle(String rppr_tittle) {
		this.rppr_tittle = rppr_tittle;
	}

	public String getRppr_content() {
		return rppr_content;
	}

	public void setRppr_content(String rppr_content) {
		this.rppr_content = rppr_content;
	}

	
	
}
