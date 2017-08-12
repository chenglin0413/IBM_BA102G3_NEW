package com.trvlrank.model;

import java.io.Serializable;

public class TrvlRankVO implements Serializable {
	
	private Integer trvl_id;
	private Integer user_id;
	private Integer trvlrank_score;
	
	public TrvlRankVO(){
		
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

	public Integer getTrvlrank_score() {
		return trvlrank_score;
	}

	public void setTrvlrank_score(Integer trvlrank_score) {
		this.trvlrank_score = trvlrank_score;
	}
	
	
	
	
}
