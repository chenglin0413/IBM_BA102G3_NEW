package com.user.model;

import java.io.Serializable;
import java.sql.Date;

public class UserVO implements Serializable {
	
	private Integer user_id;
	private String user_account;
	private String user_passwd;
	private Integer user_type;
	private String user_lastname;
	private String user_firstname;
	private String user_phone;
	private String user_mobile;
	private String user_address;
	private String user_email;
	private Date user_joindate;
	private Integer user_status;
	private byte[] user_img;
	private String user_imgfmt;

	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserVO(Integer user_id, String user_account, String user_passwd, Integer user_type, String user_lastname,
			String user_firstname, String user_phone, String user_mobile, String user_address, String user_email,
			Date user_joindate, Integer user_status, byte[] user_img, String user_imgfmt) {
		super();
		this.user_id = user_id;
		this.user_account = user_account;
		this.user_passwd = user_passwd;
		this.user_type = user_type;
		this.user_lastname = user_lastname;
		this.user_firstname = user_firstname;
		this.user_phone = user_phone;
		this.user_mobile = user_mobile;
		this.user_address = user_address;
		this.user_email = user_email;
		this.user_joindate = user_joindate;
		this.user_status = user_status;
		this.user_img = user_img;
		this.user_imgfmt = user_imgfmt;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_passwd() {
		return user_passwd;
	}

	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public String getUser_lastname() {
		return user_lastname;
	}

	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}

	public String getUser_firstname() {
		return user_firstname;
	}

	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Date getUser_joindate() {
		return user_joindate;
	}

	public void setUser_joindate(Date user_joindate) {
		this.user_joindate = user_joindate;
	}

	public Integer getUser_status() {
		return user_status;
	}

	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}

	public byte[] getUser_img() {
		return user_img;
	}

	public void setUser_img(byte[] user_img) {
		this.user_img = user_img;
	}

	public String getUser_imgfmt() {
		return user_imgfmt;
	}

	public void setUser_imgfmt(String user_imgfmt) {
		this.user_imgfmt = user_imgfmt;
	}
	
}
