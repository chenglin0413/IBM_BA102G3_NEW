package com.prpi.model;

public class PrpiVO implements java.io.Serializable{
	
	private Integer prpi_id;
	private Integer prod_id;
	private String prpi_name;
	private byte[] prpi_img;
	
	
	public Integer getPrpi_id() {
		return prpi_id;
	}
	public void setPrpi_id(Integer prpi_id) {
		this.prpi_id = prpi_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public String getPrpi_name() {
		return prpi_name;
	}
	public void setPrpi_name(String prpi_name) {
		this.prpi_name = prpi_name;
	}
	public byte[] getPrpi_img() {
		return prpi_img;
	}
	public void setPrpi_img(byte[] prpi_img) {
		this.prpi_img = prpi_img;
	}


}
