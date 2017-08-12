package com.func.model;

import java.io.Serializable;

public class FuncVO implements Serializable {

	private Integer func_id;
	private String func_name;
	private String func_path;
	private Integer func_status;

	public FuncVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FuncVO(Integer func_id, String func_name, String func_path, Integer func_status) {
		super();
		this.func_id = func_id;
		this.func_name = func_name;
		this.func_path = func_path;
		this.func_status = func_status;
	}

	public Integer getFunc_status() {
		return func_status;
	}

	public void setFunc_status(Integer func_status) {
		this.func_status = func_status;
	}

	public Integer getFunc_id() {
		return func_id;
	}

	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getFunc_path() {
		return func_path;
	}

	public void setFunc_path(String func_path) {
		this.func_path = func_path;
	}
	

	
}
