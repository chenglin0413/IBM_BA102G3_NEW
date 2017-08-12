package com.prpm.model;

public class PrpmVO implements java.io.Serializable {

	private Integer stpm_id;
	private Integer prod_id;
	private Integer prpm_price;
	private Integer prpm_status;

	public Integer getStpm_id() {
		return stpm_id;
	}

	public void setStpm_id(Integer stpm_id) {
		this.stpm_id = stpm_id;
	}

	public Integer getProd_id() {
		return prod_id;
	}

	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}

	public Integer getPrpm_price() {
		return prpm_price;
	}

	public void setPrpm_price(Integer prpm_price) {
		this.prpm_price = prpm_price;
	}

	public Integer getPrpm_status() {
		return prpm_status;
	}

	public void setPrpm_status(Integer prpm_status) {
		this.prpm_status = prpm_status;
	}

}
