package com.prod.model;

import java.io.Serializable;
import java.sql.Date;

public class ProdVO implements Serializable{
	private Integer prod_id;
	public ProdVO(Integer prod_id, Integer store_id, String prod_name, String prod_descript, Integer prod_price,
			String prod_sort, String prod_format, String prod_brand, Date prod_updatetime, Integer prod_soldcount,
			Integer prod_status, Integer prod_count, Integer prod_score) {
		super();
		this.prod_id = prod_id;
		this.store_id = store_id;
		this.prod_name = prod_name;
		this.prod_descript = prod_descript;
		this.prod_price = prod_price;
		this.prod_sort = prod_sort;
		this.prod_format = prod_format;
		this.prod_brand = prod_brand;
		this.prod_updatetime = prod_updatetime;
		this.prod_soldcount = prod_soldcount;
		this.prod_status = prod_status;
		this.prod_count = prod_count;
		this.prod_score = prod_score;
	}
	private Integer store_id;
	private String prod_name;
	private String prod_descript;
	private Integer prod_price;
	private String  prod_sort;
	private String  prod_format;
	private String  prod_brand;
	private Date  prod_updatetime;
	private Integer prod_soldcount;
	private Integer prod_status;
	private Integer prod_count;
	private Integer prod_score;
	
	
	public ProdVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_descript() {
		return prod_descript;
	}
	public void setProd_descript(String prod_descript) {
		this.prod_descript = prod_descript;
	}
	public Integer getProd_price() {
		return prod_price;
	}
	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}
	public String getProd_sort() {
		return prod_sort;
	}
	public void setProd_sort(String prod_sort) {
		this.prod_sort = prod_sort;
	}
	public String getProd_format() {
		return prod_format;
	}
	public void setProd_format(String prod_format) {
		this.prod_format = prod_format;
	}
	public String getProd_brand() {
		return prod_brand;
	}
	public void setProd_brand(String prod_brand) {
		this.prod_brand = prod_brand;
	}
	public Date getProd_updatetime() {
		return prod_updatetime;
	}
	public void setProd_updatetime(Date Prod_updatetime) {
		this.prod_updatetime = Prod_updatetime;
	}
	public Integer getProd_soldcount() {
		return prod_soldcount;
	}
	public void setProd_soldcount(Integer prod_soldcount) {
		this.prod_soldcount = prod_soldcount;
	}
	public Integer getProd_status() {
		return prod_status;
	}
	public void setProd_status(Integer prod_status) {
		this.prod_status = prod_status;
	}
	public Integer getProd_count() {
		return prod_count;
	}
	public void setProd_count(Integer prod_count) {
		this.prod_count = prod_count;
	}
	public Integer getProd_score() {
		return prod_score;
	}
	public void setProd_score(Integer prod_score) {
		this.prod_score = prod_score;
	}
	
	
	
}
