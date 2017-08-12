package com.ord.model;

import java.io.Serializable;
import java.sql.Date;

public class OrdVO implements Serializable{
	
	
	private Integer ord_id;
	public OrdVO(Integer ord_id, Integer user_id, Integer store_id, Date ord_date, Integer ord_total, Integer ord_bill,
			Integer ord_grant, Integer ord_status, Integer ord_sscore, Date ord_rpdate, String ord_rpcomm,
			Integer ord_rpstatus) {
		super();
		this.ord_id = ord_id;
		this.user_id = user_id;
		this.Store_id = store_id;
		this.ord_date = ord_date;
		this.ord_total = ord_total;
		this.ord_bill = ord_bill;
		this.ord_grant = ord_grant;
		this.ord_status = ord_status;
		this.ord_sscore = ord_sscore;
		this.ord_rpdate = ord_rpdate;
		this.ord_rpcomm = ord_rpcomm;
		this.ord_rpstatus = ord_rpstatus;
	}
	private Integer user_id;
	public OrdVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer Store_id;
	private Date    ord_date;
	private Integer ord_total;
	private Integer ord_bill;
	private Integer ord_grant;
	private Integer ord_status;
	private Integer ord_sscore;
	private Date    ord_rpdate;
	private String  ord_rpcomm;
	private Integer ord_rpstatus;
	public Integer getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(Integer ord_id) {
		this.ord_id = ord_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getStore_id() {
		return Store_id;
	}
	public void setStore_id(Integer store_id) {
		Store_id = store_id;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	public Integer getOrd_total() {
		return ord_total;
	}
	public void setOrd_total(Integer ord_total) {
		this.ord_total = ord_total;
	}
	public Integer getOrd_sscore() {
		return ord_sscore;
	}
	public void setOrd_sscore(Integer ord_sscore) {
		this.ord_sscore = ord_sscore;
	}
	public Integer getOrd_bill() {
		return ord_bill;
	}
	public void setOrd_bill(Integer ord_bill) {
		this.ord_bill = ord_bill;
	}
	public Integer getOrd_grant() {
		return ord_grant;
	}
	public void setOrd_grant(Integer ord_grant) {
		this.ord_grant = ord_grant;
	}
	public Integer getOrd_status() {
		return ord_status;
	}
	public void setOrd_status(Integer ord_status) {
		this.ord_status = ord_status;
	}
	public Integer getOrd_rpstatus() {
		return ord_rpstatus;
	}
	public void setOrd_rpstatus(Integer ord_rpstatus) {
		this.ord_rpstatus = ord_rpstatus;
	}
	public Date getOrd_rpdate() {
		return ord_rpdate;
	}
	public void setOrd_rpdate(Date ord_rpdate) {
		this.ord_rpdate = ord_rpdate;
	}
	public String getOrd_rpcomm() {
		return ord_rpcomm;
	}
	public void setOrd_rpcomm(String ord_rpcomm) {
		this.ord_rpcomm = ord_rpcomm;
	}
	
	
	
	
	
	
}
