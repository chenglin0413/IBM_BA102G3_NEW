package com.store.model;

import java.io.Serializable;

public class StoreVO implements Serializable{
	private Integer store_id;
	private Integer user_id;
	private String store_name;
	private String store_time;
	private String store_phone;
	private String store_describe;
	private Integer store_ter;
	private String store_floor;
	private Double store_lon;
	private Double store_lat;
	private Integer store_inout;
	private Integer store_count;
	private Integer store_score;
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_time() {
		return store_time;
	}
	public void setStore_time(String store_time) {
		this.store_time = store_time;
	}
	public String getStore_phone() {
		return store_phone;
	}
	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}
	public String getStore_describe() {
		return store_describe;
	}
	public void setStore_describe(String store_describe) {
		this.store_describe = store_describe;
	}
	public Integer getStore_ter() {
		return store_ter;
	}
	public void setStore_ter(Integer store_ter) {
		this.store_ter = store_ter;
	}
	public Double getStore_lon() {
		return store_lon;
	}
	public void setStore_lon(Double store_lon) {
		this.store_lon = store_lon;
	}
	public Double getStore_lat() {
		return store_lat;
	}
	public void setStore_lat(Double store_lat) {
		this.store_lat = store_lat;
	}
	public Integer getStore_inout() {
		return store_inout;
	}
	public void setStore_inout(Integer store_inout) {
		this.store_inout = store_inout;
	}
	public Integer getStore_count() {
		return store_count;
	}
	public void setStore_count(Integer store_count) {
		this.store_count = store_count;
	}
	public Integer getStore_score() {
		return store_score;
	}
	public void setStore_score(Integer store_score) {
		this.store_score = store_score;
	}
	public String getStore_floor() {
		return store_floor;
	}
	public void setStore_floor(String store_floor) {
		this.store_floor = store_floor;
	}
	
}
