package com.reta.model;

import java.io.Serializable;
import java.sql.Date;

public class RetaVO implements Serializable{
	private Integer reta_id;
	private Integer avtb_id;
	private Integer user_id;
	private Integer reta_number;
	private Integer reta_status;
	private Integer reta_grant;
	private Date reta_date;
	private Integer reta_rank_res;
	private String reta_review;
	private Date reta_reviewdate;
	private Date rest_rpdate;
	private String rest_rpcomm;
	private Integer rest_rpstatus;
	
	public RetaVO() {
		super();
	}

	public RetaVO(Integer reta_id, Integer avtb_id, Integer user_id, Integer reta_number, Integer reta_status,
			Integer reta_grant, Date reta_date, Integer reta_rank_res, String reta_review, Date reta_reviewdate,
			Date rest_rpdate, String rest_rpcomm, Integer rest_rpstatus) {
		super();
		this.reta_id = reta_id;
		this.avtb_id = avtb_id;
		this.user_id = user_id;
		this.reta_number = reta_number;
		this.reta_status = reta_status;
		this.reta_grant = reta_grant;
		this.reta_date = reta_date;
		this.reta_rank_res = reta_rank_res;
		this.reta_review = reta_review;
		this.reta_reviewdate = reta_reviewdate;
		this.rest_rpdate = rest_rpdate;
		this.rest_rpcomm = rest_rpcomm;
		this.rest_rpstatus = rest_rpstatus;
	}

	public Integer getReta_id() {
		return reta_id;
	}

	public void setReta_id(Integer reta_id) {
		this.reta_id = reta_id;
	}

	public Integer getAvtb_id() {
		return avtb_id;
	}

	public void setAvtb_id(Integer avtb_id) {
		this.avtb_id = avtb_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getReta_number() {
		return reta_number;
	}

	public void setReta_number(Integer reta_number) {
		this.reta_number = reta_number;
	}

	public Integer getReta_status() {
		return reta_status;
	}

	public void setReta_status(Integer reta_status) {
		this.reta_status = reta_status;
	}

	public Integer getReta_grant() {
		return reta_grant;
	}

	public void setReta_grant(Integer reta_grant) {
		this.reta_grant = reta_grant;
	}

	public Date getReta_date() {
		return reta_date;
	}

	public void setReta_date(Date reta_date) {
		this.reta_date = reta_date;
	}

	public Integer getReta_rank_res() {
		return reta_rank_res;
	}

	public void setReta_rank_res(Integer reta_rank_res) {
		this.reta_rank_res = reta_rank_res;
	}

	public String getReta_review() {
		return reta_review;
	}

	public void setReta_review(String reta_review) {
		this.reta_review = reta_review;
	}

	public Date getReta_reviewdate() {
		return reta_reviewdate;
	}

	public void setReta_reviewdate(Date reta_reviewdate) {
		this.reta_reviewdate = reta_reviewdate;
	}

	public Date getRest_rpdate() {
		return rest_rpdate;
	}

	public void setRest_rpdate(Date rest_rpdate) {
		this.rest_rpdate = rest_rpdate;
	}

	public String getRest_rpcomm() {
		return rest_rpcomm;
	}

	public void setRest_rpcomm(String rest_rpcomm) {
		this.rest_rpcomm = rest_rpcomm;
	}

	public Integer getRest_rpstatus() {
		return rest_rpstatus;
	}

	public void setRest_rpstatus(Integer rest_rpstatus) {
		this.rest_rpstatus = rest_rpstatus;
	}

	
}
