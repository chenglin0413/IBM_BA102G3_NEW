package com.auth.model;

import java.io.Serializable;

public class AuthVO implements Serializable {
	
	private Integer sysu_id;
	private Integer func_id;

	public AuthVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthVO(Integer sysu_id, Integer func_id) {
		super();
		this.sysu_id = sysu_id;
		this.func_id = func_id;
	}

	public Integer getSysu_id() {
		return sysu_id;
	}

	public void setSysu_id(Integer sysu_id) {
		this.sysu_id = sysu_id;
	}

	public Integer getFunc_id() {
		return func_id;
	}

	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}

}
