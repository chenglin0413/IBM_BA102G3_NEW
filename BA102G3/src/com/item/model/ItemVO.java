package com.item.model;

import java.io.Serializable;
import java.sql.Date;

public class ItemVO implements Serializable{
	
	
	private Integer ord_id;
	public ItemVO(Integer ord_id, Integer prod_id, Integer item_qty, Integer item_score, String item_review,
			Date item_reviewdate) {
		super();
		this.ord_id = ord_id;
		this.prod_id = prod_id;
		this.item_qty = item_qty;
		this.item_score = item_score;
		this.item_review = item_review;
		this.item_reviewdate = item_reviewdate;
	}
	public ItemVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer prod_id;
	private Integer item_qty;
	private Integer item_score;
	private String  item_review;
	private Date    item_reviewdate;
	public Integer getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(Integer ord_id) {
		this.ord_id = ord_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public Integer getItem_qty() {
		return item_qty;
	}
	public void setItem_qty(Integer item_qty) {
		this.item_qty = item_qty;
	}
	public Integer getItem_score() {
		return item_score;
	}
	public void setItem_score(Integer item_score) {
		this.item_score = item_score;
	}
	public String getItem_review() {
		return item_review;
	}
	public void setItem_review(String item_review) {
		this.item_review = item_review;
	}
	public Date getItem_reviewdate() {
		return item_reviewdate;
	}
	public void setItem_reviewdate(Date item_reviewdate) {
		this.item_reviewdate = item_reviewdate;
	}
	
	
	
	
	
}
