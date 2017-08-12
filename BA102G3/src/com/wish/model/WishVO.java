package com.wish.model;

import java.io.Serializable;
import java.sql.Date;

public class WishVO implements Serializable{
	private Integer user_id;
	private Integer prod_id;
	private Date wish_date;
	public WishVO() {
		super();
	}
	
	
	public WishVO(Integer user_id, Integer prod_id, Date wish_date) {
		super();
		this.user_id = user_id;
		this.prod_id = prod_id;
		this.wish_date = wish_date;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public Date getWish_date() {
		return wish_date;
	}
	public void setWish_date(Date wish_date) {
		this.wish_date = wish_date;
	}
	
	
	
	
	
}
