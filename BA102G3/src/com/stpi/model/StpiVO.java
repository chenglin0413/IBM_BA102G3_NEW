package com.stpi.model;

import java.io.Serializable;
import java.sql.Date;

public class StpiVO implements Serializable{
	private Integer stpi_id;
	public StpiVO(Integer stpi_id, Integer store_id, Integer stpi_name, byte[] stpi_img, String stpi_imgfmt) {
		super();
		this.stpi_id = stpi_id;
		this.store_id = store_id;
		this.stpi_name = stpi_name;
		this.stpi_img = stpi_img;
		this.stpi_imgfmt = stpi_imgfmt;
	}
	public StpiVO(){
		super();
	}
	private Integer store_id;
	private Integer stpi_name;
	private byte[] stpi_img;
	private String stpi_imgfmt;
	public Integer getStpi_id() {
		return stpi_id;
	}
	public void setStpi_id(Integer stpi_id) {
		this.stpi_id = stpi_id;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getStpi_name() {
		return stpi_name;
	}
	public void setStpi_name(Integer stpi_name) {
		this.stpi_name = stpi_name;
	}
	public byte[] getStpi_img() {
		return stpi_img;
	}
	public void setStpi_img(byte[] stpi_img) {
		this.stpi_img = stpi_img;
	}
	public String getStpi_imgfmt() {
		return stpi_imgfmt;
	}
	public void setStpi_imgfmt(String stpi_imgfmt) {
		this.stpi_imgfmt = stpi_imgfmt;
	}
	
	
	
}
