package com.dish.model;

import java.io.Serializable;

/**
 * @author Yun
 *
 */
public class DishVO implements Serializable{
	private Integer dish_id;
	private Integer rest_id;
	private String dish_name;
	private Integer dish_price;
	private Integer dish_status;
	private String dish_detail;
	private String dish_note;
	
	
	
	public DishVO() {
		super();
	}



	public DishVO(Integer dish_id, Integer rest_id, String dish_name, Integer dish_price, Integer dish_status,
			String dish_detail, String dish_note) {
		super();
		this.dish_id = dish_id;
		this.rest_id = rest_id;
		this.dish_name = dish_name;
		this.dish_price = dish_price;
		this.dish_status = dish_status;
		this.dish_detail = dish_detail;
		this.dish_note = dish_note;
	}



	public Integer getDish_id() {
		return dish_id;
	}



	public void setDish_id(Integer dish_id) {
		this.dish_id = dish_id;
	}



	public Integer getRest_id() {
		return rest_id;
	}



	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}



	public String getDish_name() {
		return dish_name;
	}



	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}



	public Integer getDish_price() {
		return dish_price;
	}



	public void setDish_price(Integer dish_price) {
		this.dish_price = dish_price;
	}



	public Integer getDish_status() {
		return dish_status;
	}



	public void setDish_status(Integer dish_status) {
		this.dish_status = dish_status;
	}



	public String getDish_detail() {
		return dish_detail;
	}



	public void setDish_detail(String dish_detail) {
		this.dish_detail = dish_detail;
	}



	public String getDish_note() {
		return dish_note;
	}



	public void setDish_note(String dish_note) {
		this.dish_note = dish_note;
	}


}
		