package com.sysu.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

public class SysuVO implements Serializable {
	
	private Integer sysu_id;
	private String sysu_account;
	private String sysu_passwd;
	private Integer sysu_type;
	private String sysu_lastname;
	private String sysu_firstname;
	private String sysu_email;
	private Date sysu_joindate;
	private Integer sysu_status;

	public SysuVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysuVO(Integer sysu_id, String sysu_account, String sysu_passwd, Integer sysu_type, String sysu_lastname,
			String sysu_firstname, String sysu_email, Date sysu_joindate, Integer sysu_status) {
		super();
		this.sysu_id = sysu_id;
		this.sysu_account = sysu_account;
		this.sysu_passwd = sysu_passwd;
		this.sysu_type = sysu_type;
		this.sysu_lastname = sysu_lastname;
		this.sysu_firstname = sysu_firstname;
		this.sysu_email = sysu_email;
		this.sysu_joindate = sysu_joindate;
		this.sysu_status = sysu_status;
	}

	public Integer getSysu_id() {
		return sysu_id;
	}

	public void setSysu_id(Integer sysu_id) {
		this.sysu_id = sysu_id;
	}

	public String getSysu_account() {
		return sysu_account;
	}

	public void setSysu_account(String sysu_account) {
		this.sysu_account = sysu_account;
	}

	public String getSysu_passwd() {
		return sysu_passwd;
	}

	public void setSysu_passwd(String sysu_passwd) {
		this.sysu_passwd = sysu_passwd;
	}

	public Integer getSysu_type() {
		return sysu_type;
	}

	public void setSysu_type(Integer sysu_type) {
		this.sysu_type = sysu_type;
	}

	public String getSysu_lastname() {
		return sysu_lastname;
	}

	public void setSysu_lastname(String sysu_lastname) {
		this.sysu_lastname = sysu_lastname;
	}

	public String getSysu_firstname() {
		return sysu_firstname;
	}

	public void setSysu_firstname(String sysu_firstname) {
		this.sysu_firstname = sysu_firstname;
	}

	public String getSysu_email() {
		return sysu_email;
	}

	public void setSysu_email(String sysu_email) {
		this.sysu_email = sysu_email;
	}

	public Date getSysu_joindate() {
		return sysu_joindate;
	}

	public void setSysu_joindate(Date sysu_joindate) {
		this.sysu_joindate = sysu_joindate;
	}

	public Integer getSysu_status() {
		return sysu_status;
	}

	public void setSysu_status(Integer sysu_status) {
		this.sysu_status = sysu_status;
	}
	
}
