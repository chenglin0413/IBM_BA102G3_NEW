package com.trpi.model;

import java.io.Serializable;
import java.sql.Date;

public class TrpiVO implements Serializable {
	private Integer trpi_id;
	private Integer trvl_id;
	private byte[] trpi_img;
	private String trpi_imgfmt;
	
	public TrpiVO(){
		
	}

	public Integer getTrpi_id() {
		return trpi_id;
	}

	public void setTrpi_id(Integer trpi_id) {
		this.trpi_id = trpi_id;
	}

	public Integer getTrvl_id() {
		return trvl_id;
	}

	public void setTrvl_id(Integer trvl_id) {
		this.trvl_id = trvl_id;
	}

	public byte[] getTrpi_img() {
		return trpi_img;
	}

	public void setTrpi_img(byte[] trpi_img) {
		this.trpi_img = trpi_img;
	}

	public String getTrpi_imgfmt() {
		return trpi_imgfmt;
	}

	public void setTrpi_imgfmt(String trpi_imgfmt) {
		this.trpi_imgfmt = trpi_imgfmt;
	}
	
}
