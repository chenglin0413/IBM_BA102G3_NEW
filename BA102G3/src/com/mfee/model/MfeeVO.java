package com.mfee.model;

import java.io.Serializable;
import java.sql.Date;

public class MfeeVO implements Serializable{
	private Integer mfee_id;
	private Integer user_id;
	private Date mfee_date;
	private Date pay_date;

	public MfeeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MfeeVO(Integer mfee_id, Integer user_id, Date mfee_date, Date pay_date) {
		super();
		this.mfee_id = mfee_id;
		this.user_id = user_id;
		this.mfee_date = mfee_date;
		this.pay_date = pay_date;
	}

	public Integer getMfee_id() {
		return mfee_id;
	}

	public void setMfee_id(Integer mfee_id) {
		this.mfee_id = mfee_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getMfee_date() {
		return mfee_date;
	}

	public void setMfee_date(Date mfee_date) {
		this.mfee_date = mfee_date;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}


	
	
}
