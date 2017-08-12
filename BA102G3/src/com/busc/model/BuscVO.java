package com.busc.model;

public class BuscVO implements java.io.Serializable {

	private Integer busc_id;
	private String busc_end;
	private String busc_line;
	private String busc_time;
	private String busc_route;

	public Integer getBusc_id() {
		return busc_id;
	}

	public void setBusc_id(Integer busc_id) {
		this.busc_id = busc_id;
	}

	public String getBusc_end() {
		return busc_end;
	}

	public void setBusc_end(String busc_end) {
		this.busc_end = busc_end;
	}

	public String getBusc_line() {
		return busc_line;
	}

	public void setBusc_line(String busc_line) {
		this.busc_line = busc_line;
	}

	public String getBusc_time() {
		return busc_time;
	}

	public void setBusc_time(String busc_time) {
		this.busc_time = busc_time;
	}

	public String getBusc_route() {
		return busc_route;
	}

	public void setBusc_route(String busc_route) {
		this.busc_route = busc_route;
	}

}
