package com.rest.model;

import java.io.Serializable;

public class RestVO implements Serializable{
	private Integer rest_id;
	private Integer user_id;
	private String rest_name;
	private String rest_address;
	private String rest_phone;
	private String rest_trans;
	private String rest_detail;
	private String rest_hours;
	private Integer rest_ter;
	private Integer rest_floor;
	private Double rest_lon;
	private Double rest_lat;
	private Integer rest_inout;
	private Integer rest_type;
	private Integer rest_count;
	private Integer rest_score;

	public RestVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestVO(Integer rest_id, Integer user_id, String rest_name, String rest_address, String rest_phone,
			String rest_trans, String rest_detail, String rest_hours, Integer rest_ter, Integer rest_floor,
			Double rest_lon, Double rest_lat, Integer rest_inout, Integer rest_type, Integer rest_count,
			Integer rest_score) {
		super();
		this.rest_id = rest_id;
		this.user_id = user_id;
		this.rest_name = rest_name;
		this.rest_address = rest_address;
		this.rest_phone = rest_phone;
		this.rest_trans = rest_trans;
		this.rest_detail = rest_detail;
		this.rest_hours = rest_hours;
		this.rest_ter = rest_ter;
		this.rest_floor = rest_floor;
		this.rest_lon = rest_lon;
		this.rest_lat = rest_lat;
		this.rest_inout = rest_inout;
		this.rest_type = rest_type;
		this.rest_count = rest_count;
		this.rest_score = rest_score;
	}

	public Integer getRest_id() {
		return rest_id;
	}

	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getRest_address() {
		return rest_address;
	}

	public void setRest_address(String rest_address) {
		this.rest_address = rest_address;
	}

	public String getRest_phone() {
		return rest_phone;
	}

	public void setRest_phone(String rest_phone) {
		this.rest_phone = rest_phone;
	}

	public String getRest_trans() {
		return rest_trans;
	}

	public void setRest_trans(String rest_trans) {
		this.rest_trans = rest_trans;
	}

	public String getRest_detail() {
		return rest_detail;
	}

	public void setRest_detail(String rest_detail) {
		this.rest_detail = rest_detail;
	}

	public String getRest_hours() {
		return rest_hours;
	}

	public void setRest_hours(String rest_hours) {
		this.rest_hours = rest_hours;
	}

	public Integer getRest_ter() {
		return rest_ter;
	}

	public void setRest_ter(Integer rest_ter) {
		this.rest_ter = rest_ter;
	}

	public Integer getRest_floor() {
		return rest_floor;
	}

	public void setRest_floor(Integer rest_floor) {
		this.rest_floor = rest_floor;
	}

	public Double getRest_lon() {
		return rest_lon;
	}

	public void setRest_lon(Double rest_lon) {
		this.rest_lon = rest_lon;
	}

	public Double getRest_lat() {
		return rest_lat;
	}

	public void setRest_lat(Double rest_lat) {
		this.rest_lat = rest_lat;
	}

	public Integer getRest_inout() {
		return rest_inout;
	}

	public void setRest_inout(Integer rest_inout) {
		this.rest_inout = rest_inout;
	}

	public Integer getRest_type() {
		return rest_type;
	}

	public void setRest_type(Integer rest_type) {
		this.rest_type = rest_type;
	}

	public Integer getRest_count() {
		return rest_count;
	}

	public void setRest_count(Integer rest_count) {
		this.rest_count = rest_count;
	}

	public Integer getRest_score() {
		return rest_score;
	}

	public void setRest_score(Integer rest_score) {
		this.rest_score = rest_score;
	}
		
}